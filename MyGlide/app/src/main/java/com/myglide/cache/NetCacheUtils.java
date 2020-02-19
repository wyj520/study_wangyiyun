package com.myglide.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author: wyj
 * Date:2019/6/19
 * Description
 *  网络缓存
 */
public class NetCacheUtils {
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils localCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        mLocalCacheUtils = localCacheUtils;
        mMemoryCacheUtils = memoryCacheUtils;
    }

    /**
     * 从网络下载图片
     *
     * @param url
     */
    public Bitmap getBitmapFromNet(String url) {
            Bitmap bitmap=downloadBitmap(url);
            mMemoryCacheUtils.setBitmapToMemory(url, bitmap);// 将图片保存在内存
            mLocalCacheUtils.setBitmapToLocal(url, bitmap);// 将图片保存在本地
          return bitmap;
    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     */
    private Bitmap downloadBitmap(String url) {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();

                //比较暴力的图片压缩处理优化,一些大图片没必要加载原始尺寸
                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inSampleSize = 2;//宽高都压缩为原来的二分之一, 此参数需要根据图片要展示的大小来确定
                option.inPreferredConfig = Bitmap.Config.ARGB_4444;//设置图片格式

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, option);
                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }

}
