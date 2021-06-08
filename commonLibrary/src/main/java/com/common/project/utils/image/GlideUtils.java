package com.common.project.utils.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.common.project.R;

import java.security.MessageDigest;

/**
 * 图片三级缓存工具类(Glide)
 *
 * @author benny on 18-05-04.
 */

public class GlideUtils {
    private static String http = "http:";
    private static String https = "https:";
    private static RequestOptions option;

    /**
     * 效验配置
     *
     * @param imageUrl  地址
     * @param imageView 控件
     * @return boolean
     */
    private static boolean checkConfig(String imageUrl, ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl) || imageView == null) {
            return false;
        }
        return true;
    }

    private static void initRequestOptions() {
        if (option == null) {
            option = new RequestOptions();
        }
        option = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.default_image_circle)
                .error(R.drawable.default_image_circle)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
    }

    /**
     * 获取图片路径，判断是否包含http头
     */
    public static String getImageUrl(String imageUrl) {
        if (!imageUrl.contains(http) && !imageUrl.contains(https)) {
            imageUrl = http + imageUrl;
        }
        return imageUrl;
    }

    /**
     * 加载本地图片
     */
    public static void loadLocal(Context context, String imageUrl, final ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl) || imageView == null) {
            return;
        }
        Glide.with(context).load(imageUrl).into(imageView);
    }

    /**
     * 1.加载网络图片到ImageView中
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl).apply(option).into(imageView);
    }

    /**
     * 1.加载网络图片到ImageView中 是否加入到内存缓存
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView,boolean isMemoryCache) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        if (option == null) {
            option = new RequestOptions();
        }
        option = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.default_image_circle)
                .error(R.drawable.default_image_circle)
                .priority(Priority.HIGH)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .dontAnimate();
        Glide.with(context).load(imageUrl).apply(option).into(imageView);
    }

    /**
     * 1.加载网络图片到ImageView中
     * @param resoureId 默认图片资源ID
     */
    public static void loadImageByDefaultImg(Context context, String imageUrl, ImageView imageView,int resoureId) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        option = new RequestOptions()
                .centerCrop()
                .placeholder(resoureId)
                .error(resoureId)
                .priority(Priority.HIGH)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .dontAnimate();
        Glide.with(context).load(imageUrl).apply(option).into(imageView);
    }

    /**
     * 加载网络压缩图
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadThumbnail(Context context, String imageUrl, ImageView imageView) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        } initRequestOptions();
        Glide.with(context).load(imageUrl).thumbnail(0.75f).apply(option).into(imageView);
    }

    /**
     * 1.加载网络图片 设置宽高
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView, int width, int height) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        option.override(width, height);
        Glide.with(context)
                .load(imageUrl)
                .apply(option)
                .into(imageView);
    }

    /**
     * 加载网络 长方形图片
     */
    public static void rectangleImage(Context context, String imageUrl, ImageView imageView) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option)
                .into(imageView);
    }

    /**
     * 加载网络 长方形图片 设置宽高
     */
    public static void rectangleImage(Context context, String imageUrl, ImageView imageView, int width, int height) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        option.override(width, height);
        Glide.with(context).load(imageUrl)
                .apply(option)
                .into(imageView);
    }


    /**
     * 加载网络 长方形圆角图片
     */
    public static void rectangleRoundImage(Context context, String imageUrl, ImageView imageView) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option)
                .into(imageView);

    }

    /**
     * 加载网络 长方形圆角图片 设置宽高
     */
    public static void rectangleRoundImage(Context context, String imageUrl, ImageView imageView, int width, int height) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option)
                .into(imageView);

    }

    /**
     * 加载长方形圆角图片
     */
    public static void rectangleRoundImage(Context context, final String imageUrl, final ImageView imageView, int mRadius) {
        RequestOptions options = new RequestOptions()
                .transforms(new GlideRoundTransform(context, mRadius))
                .dontAnimate();
        Glide.with(context).load(imageUrl)
                .apply(options).into(imageView);
    }

    /**
     * 加载长方形圆角图片
     */
    public static void rectangleIMRoundImage(final Context context, final String imageUrl, final ImageView imageView, int mRadius) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }

        RequestOptions options = new RequestOptions()
                .transforms(new GlideRoundTransform(context, mRadius))
                .dontAnimate();
        Glide.with(context).load(imageUrl)
                .apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        int width = resource.getIntrinsicWidth();
                        int height = resource.getIntrinsicHeight();
                        //限定的宽高
                        float maxWidth = context.getResources().getDimension(R.dimen.d150);
                        //如果宽大于高
                        if (width > height) {
                            //如果图片实际宽度大于限定宽度，则宽度设定为限定宽度。高度等比例缩放
                            if (width > maxWidth) {
                                float scale = maxWidth / width;
                                width = (int) maxWidth;
                                height = (int) (height * scale);
                            }
                        } else {//如果高大于宽
                            //如果图片实际高度大于限定高度，则图片高度设定为限定高度。宽度等比例缩放
                            if (height > maxWidth) {
                                float scale = maxWidth / height;
                                height = (int) maxWidth;
                                width = (int) (width * scale);
                            }
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        params.width = width;
                        params.height = height;
                        imageView.setLayoutParams(params);
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    /**
     * 加载网络 长方形圆角图片 设置圆角度数、宽高
     */
    public static void rectangleRoundImage(Context context, String imageUrl, ImageView imageView, int mRadius, int width, int height) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option)
                .into(imageView);

    }


    /**
     * 加载网络 正方形图片
     */
    public static void squareImage(Context context, String imageUrl, ImageView imageView) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option)
                .into(imageView);
    }


    /**
     * 加载网络 正方形图片 设置宽高
     */
    public static void squareRoundImage(Context context, String imageUrl, ImageView imageView, int width, int height) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option)
                .into(imageView);

    }

    /**
     * 加载网络 正方形图片 设置圆角
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void squareRoundImage(Context context, String imageUrl, ImageView imageView) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option).into(imageView);

    }

    /**
     * 加载正方形圆角图片
     */
    public static void squareRoundImage(Context context, String imageUrl, ImageView imageView, int mRadius) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option)
                .into(imageView);

    }

    /**
     * 11 加载圆形图片
     */
    public static void loadCircleImg(Context context, String imageUrl, ImageView imageView) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        Glide.with(context).load(imageUrl)
                .apply(option).into(imageView);

    }

    /**
     * 11 加载圆形图片
     */
    public static void loadCircleImg(Context context, String imageUrl, ImageView imageView, int width, int height) {
        if (!checkConfig(imageUrl, imageView)) {
            return;
        }
        initRequestOptions();
        option.transform(new GlideCircleTransform(context));
        Glide.with(context).load(imageUrl)
                .apply(option).into(imageView);

    }




    /**
     * 圆形图片
     */
    public static class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            }

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }


    /**
     * 先在顶部裁剪出一个正方型的图片 在转换成圆形
     */
    public static class GlideCircleTransformTop extends BitmapTransformation {
        public GlideCircleTransformTop(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            }

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            // 裁切后所取的正方形区域边长
            int cropWidth = x >= y ? y : x;
            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, cropWidth, cropWidth, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }


        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }


    /**
     * 正方形圆角图片
     */
    public static class GlideRoundTransform extends BitmapTransformation {

        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            }

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }

    /**
     * 裁剪顶部正方形圆角图片
     */
    public static class GlideSquareRoundTransform extends BitmapTransformation {
        private float radius = 0f;

        public GlideSquareRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideSquareRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap bitmap, int outWidth, int outHeight) {
            int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
            int x = (bitmap.getWidth() - size) / 2;
            int y = (bitmap.getHeight() - size) / 2;
            // 裁切后所取的正方形区域边长
            int cropWidth = x >= y ? y : x;
            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(bitmap, cropWidth, cropWidth, size, size);
            return roundCrop(pool, squared);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            }
            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }


}
