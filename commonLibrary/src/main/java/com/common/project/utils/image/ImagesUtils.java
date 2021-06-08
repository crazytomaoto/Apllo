package com.common.project.utils.image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import com.common.project.R;
import com.common.project.config.CommonApp;
import com.common.project.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author by benny
 * @date on 2018/11/20.
 * @function 图片处理工具
 */

public class ImagesUtils {

    /**
     * 文件夹目录
     */
    private static String fileFolder = "imageUtils";
    private static String path = "";

    public static String getPath(Context context, String bitName) {
        String path = getApkPicRootDir(context) + "/" + bitName + ".jpeg";
        if (TextUtils.isEmpty(path)) {
            path = "";
        }
        return path;
    }

    /**
     * 某些机型直接获取会为null,在这里处理一下防止国内某些机型返回null
     */
    public static Bitmap getViewBitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }


    /**
     * 保存图片 在相册中显示 测试Ok符合需求
     */

    public static boolean saveBitmap(Bitmap bmp, String bitName, Context context) {
        if (bmp==null){
            ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_bcsb));
            return false;
        }
        String path = "";
        if (bitName.contains(".")) {
            path = getApkPicRootDir(context) + "/" + bitName;
        } else {
            path = getApkPicRootDir(context) + "/" + bitName + ".jpeg";
        }
        File f = new File(path);
        boolean flag = false;
        try {
            f.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            if (bmp != null) {
                bmp.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            }
            flag = true;
            /* // 其次把文件插入到系统图库
            try {
		        MediaStore.Images.Media.insertImage(context.getContentResolver(),
						f.getAbsolutePath(), bitName, null);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }*/
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
            ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_bccg));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_bcsb));
        }
        try {
            fOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 保存图片 在相册中显示 测试Ok符合需求
     */

    public static boolean saveBitmapWithToast(Bitmap bmp, String bitName, Context context,boolean isToast) {
        if (bmp==null){
            if(isToast) {
                ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_bcsb));
            }
            return false;
        }
        String path = "";
        if (bitName.contains(".")) {
            path = getApkPicRootDir(context) + "/" + bitName;
        } else {
            path = getApkPicRootDir(context) + "/" + bitName + ".jpeg";
        }
        File f = new File(path);
        boolean flag = false;
        try {
            f.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            if (bmp != null) {
                bmp.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            }
            flag = true;
            /* // 其次把文件插入到系统图库
            try {
		        MediaStore.Images.Media.insertImage(context.getContentResolver(),
						f.getAbsolutePath(), bitName, null);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }*/
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
            if(isToast) {
                ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_bccg));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if(isToast) {
                ToastUtils.showMidToast(CommonApp.getInstance().getString(R.string.str_bcsb));
            }
        }
        try {
            fOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取程序运行期间文件保存的根目录
     */
    public static String getApkPicRootDir(Context context) {
        String dirPath = null;

        // 判断SDCard是否存在并且是可用的
        if (isSDCardExistAndCanWrite()) {
            dirPath = Environment.getExternalStorageDirectory().getPath()
                    + "/" + fileFolder;
        } else {
            dirPath = context.getFilesDir()
                    .getAbsolutePath()
                    + "/" + fileFolder;
        }
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 判断SDCard是否存在并且是可写的
     */
    public static boolean isSDCardExistAndCanWrite() {
        boolean result = false;
        try {
            result = Environment.MEDIA_MOUNTED.equals(Environment
                    .getExternalStorageState());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    /**
     * 获取本地视频缩略图
     * */
    public static Bitmap getVideoThumbnail(String videoPath) {
        MediaMetadataRetriever media =new MediaMetadataRetriever();
        media.setDataSource(videoPath);
        Bitmap bitmap = media.getFrameAtTime();
        return bitmap;
    }
}
