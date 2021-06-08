package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.error.ApiError;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.exception.ApiException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author wangyan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Generator {

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder();

    private static Retrofit retrofit;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static <S> S createService(Class<S> serviceClass, String baseUrl, String Host) {
        if (Host != null) {
            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Host", Host)
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }
            });
        }

        retrofit = builder.baseUrl(baseUrl).client(httpClientBuilder.build())
                .addConverterFactory(JacksonConverterFactory.create()).build();
        return retrofit.create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) throws ApiException, IOException {
        Response<T> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            ApiError apiError = getApiError(response);
            throw new ApiException(apiError);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static ApiError getApiError(Response<?> response) throws IOException, ApiException {
        return (ApiError) retrofit.responseBodyConverter(ApiError.class, new Annotation[0]).convert(response.errorBody());
    }

    public static <T> T parse(String json, Class<T> clazz, boolean failOnUnknownProperties) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
        T result = null;
        try {
            result = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
