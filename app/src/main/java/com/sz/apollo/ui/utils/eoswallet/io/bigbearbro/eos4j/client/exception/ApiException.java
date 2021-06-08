package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.api.result.error.ApiError;


/**
 *
 * @author wangyan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiException extends RuntimeException {
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private static final long serialVersionUID = 1L;

	private ApiError error;

	public ApiError getError() {
		return error;
	}

	public void setError(ApiError error) {
		this.error = error;
	}

	public ApiException(ApiError apiError) {
		this.error = apiError;
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		if (error != null) {
			try {
				return new ObjectMapper().writeValueAsString(error);
			} catch (JsonProcessingException e) {
				return error.getMessage();
			}
		}
		return super.getMessage();
	}
}
