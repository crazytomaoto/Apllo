package com.common.project.utils.files;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;

import java.io.File;

/**
 * @author wencheng
 * @create 2019/5/15
 * @Describe 根据URI获取文件真实路径（兼容多张机型）
 */
public class MyFileUtil {

    /***常用的文件路径地址

     Environment.getDataDirectory().getPath()           : /data
     Environment.getDownloadCacheDirectory().getPath()  : /cache
     Environment.getExternalStorageDirectory().getPath(): /mnt/sdcard
     Environment.getRootDirectory().getPath()           : /system
     Context.getCacheDir().getPath()                    : /data/data/com.zhd/cache
     Context.getExternalCacheDir().getPath()            : /mnt/sdcard/Android/data/com.zhd/cache
     Context.getFilesDir().getPath()                    : /data/data/com.zhd/files
     Context.getObbDir().getPath()                      : /mnt/sdcard/Android/obb/com.zhd
     Context.getPackageName()                           : com.zhd
     Context.getPackageCodePath()                       : /data/app/com.zhd-1.apk
     Context.getPackageResourcePath()                   : /data/app/com.zhd-1.apk


     */

    /**
     * 获取SD卡根目录
     *
     * @return
     */
    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getPath();
    }


    /**
     * 设置鲁班压缩目录
     *
     * @return
     */
    public static String getLuBanDir() {
        String dir = getExternalStorageDirectory() + "/bitbt/";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }


    /**
     * 获取拍照后的图片路径
     * @param time 拍照时间戳
     * @return
     */
    public static String getTakePhotoPath(long time) {
        String pal ="";
        String path = getLuBanDir() + "images/";
        File file = new File(path + "test_" +time+ ".jpg");
        if (file != null && file.exists()) {
            pal = file.getAbsolutePath();
        }
        return pal;
    }
    /**
     * 创建拍照的图片路径（Uri）
     * @param time 拍照时间戳
     * return mUri
     */
    public static Uri createTakePhotoPath(Context context,long time) {
        String path = getLuBanDir() + "images/";
        Uri mUri = null;
        File file = new File(path + "test_"+time + ".jpg");
        if (file.exists()) {
            file.delete();
        }

        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (context != null) {
                mUri = FileProvider.getUriForFile(context, "com.ruiec.nextet.provider", file);
            }
        } else {
            //步骤三：获取文件Uri
            mUri = Uri.fromFile(file);
        }
        return mUri;
    }


    /**********************************  根据URI获取文件真实路径（兼容多张机型）  start ********************************/

    /**
     * 根据Uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    public static String getFilePathByUri(Context context, Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) { // api >= 19
            return getRealPathFromUriAboveApi19(context, uri);
        } else { // api < 19
            return getRealPathFromUriBelowAPI19(context, uri);
        }
    }

    /**
     * 适配api19以下(不包括api19),根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    /**
     * 适配api19及以上,根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     *
     * @return
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    /**********************************  根据URI获取文件真实路径（兼容多张机型）  end ********************************/
}
