package com.common.project.utils.tool;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

/**
 * 获取照片工具
 * @author  chenjunshan on 17-6-8.
 */

public class PhotoUtils {
    public static final int CHOOSE_PHOTO_REQUEST = 111; //相册
    public static final int TAKE_PHOTO_REQUEST = 211;  //拍照
    public static final int CROP_PHOTO_REQUEST = 311; //裁剪
    public static Uri imageUri;


    /**
     * 跳转到相机
     */
    public static void toCamera(Activity activity) {
        imageUri = createImageUri(activity);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, TAKE_PHOTO_REQUEST);
    }

    /**
     * 跳转到相机
     */
    public static void toCamera(Fragment fragment) {
        imageUri = createImageUri(fragment.getActivity());
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        fragment.startActivityForResult(intent, TAKE_PHOTO_REQUEST);
    }

    /**
     * 跳转到相机 ---针对Fragment
     * @param fragment
     * @param uri  自定义图片Uri
     */
    public static void toCamera(Fragment fragment,Uri uri) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        fragment.startActivityForResult(intent, TAKE_PHOTO_REQUEST);
    }


    /**
     * 跳转到相机---针对Activity
     * @param activity
     * @param uri  自定义图片Uri
     */
    public static void toCamera(Activity activity,Uri uri) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, TAKE_PHOTO_REQUEST);
    }

    /**
     * 跳转到相册
     */
    public static void toHhotoAlbum(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, CHOOSE_PHOTO_REQUEST);
    }



    /**
     * 跳转到相册
     */
    public static void toHhotoAlbum(Fragment fragment) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        fragment.startActivityForResult(intent, CHOOSE_PHOTO_REQUEST);
    }


    /**
     * 创建图片
     *
     * @param context
     * @return
     */
    private static Uri createImageUri(Context context) {
        String name = "takePhoto" + System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, name);
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        return uri;
    }

    /**
     * 删除图片
     *
     * @param context
     * @param uri
     */
    public static void delteImageUri(Context context, Uri uri) {
        if (uri == null)
        {   return;}
        context.getContentResolver().delete(uri, null, null);
    }

    public static String getImagePath(Context context, Uri uri) {
        try {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                cursor.close();
                return path;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 裁剪图片
     */
    public static Intent cropPhoto(Uri uri) {
        // 创建File对象，用于存储裁剪后的图片，避免更改原图
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "crop_image.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri outputUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //可裁剪
        intent.putExtra("crop", "true");
        // 裁剪后输出图片的尺寸大小
        //intent.putExtra("outputX", 400);
        //intent.putExtra("outputY", 200);
        //支持缩放
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        //输出图片格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //取消人脸识别
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", false);
        return intent;
    }

}
