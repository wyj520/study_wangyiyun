package com.myglide.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.myglide.MD5Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * author: wyj
 * Date:2019/6/19
 * Description
 */
public class LocalCacheUtils {

    public static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/BitmapThreeCache";

    /**
     * 从本地sdcard读图片
     */
    public Bitmap getBitmapFromLocal(String url) {
        try {
            //用图片下载网址进过处理为合法的文件名后作为保存的文件名.
            String fileName = MD5Utils.toMD5(url);
            File file = new File(CACHE_PATH, fileName);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
                        file));
                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 向sdcard写图片
     *
     * @param url
     * @param bitmap
     */
    public void setBitmapToLocal(String url, Bitmap bitmap) {
        try {
            String fileName = MD5Utils.toMD5(url);

            File file = new File(CACHE_PATH, fileName);

            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {// 如果文件夹不存在, 创建文件夹
                parentFile.mkdirs();
            }

            // 将图片保存在本地
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
