package com.myglide.cache;

import android.graphics.Bitmap;
import android.print.PrinterId;
import android.util.Log;
import android.widget.ImageView;

import com.myglide.R;

/**
 * author: wyj
 * Date:2019/6/19
 * Description
 * 自定义图片加载工具
 */
public class MyBitmapUtils {
    private static final String TAG = "****bitmapCache";
    NetCacheUtils mNetCacheUtils;
    LocalCacheUtils mLocalCacheUtils;
    MemoryCacheUtils mMemoryCacheUtils;
    private  static MyBitmapUtils instance=new MyBitmapUtils();



    private MyBitmapUtils() {
        mMemoryCacheUtils = new MemoryCacheUtils();
        mLocalCacheUtils = new LocalCacheUtils();
        mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils, mMemoryCacheUtils);
    }

    public static MyBitmapUtils getInstance() {
        return instance;
    }

    /**
     * 加载图片,优先顺序:内存缓存>本地缓存>网络获取
     * @param url   下载该图片的url
     */
    public Bitmap loadImage( String url) {

        Bitmap bitmap = null;
        // 从内存读
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap != null) {
            Log.e(TAG, "从内存读取图片啦...");
            return bitmap;
        }

        // 从本地读
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap != null) {
            Log.e(TAG, "从本地读取图片啦...");
            mMemoryCacheUtils.setBitmapToMemory(url, bitmap);// 将图片保存在内存
            return bitmap;
        }

        // 从网络读
       bitmap= mNetCacheUtils.getBitmapFromNet( url);
        Log.e(TAG, "从网络读取图片啦...");
        return bitmap;
    }
}
