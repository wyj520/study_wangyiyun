package com.myglide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.myglide.cache.MyBitmapUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * author: wyj
 * Date:2019/6/18
 * Description:消费窗口
 */
public class BitmapDispatch extends Thread {

    //用handler处理线程间切换
    private Handler handler=new Handler(Looper.getMainLooper());

    //创建一个队列
    private LinkedBlockingQueue<BitmapRequest> requestsQueue;

    public BitmapDispatch (LinkedBlockingQueue requestsQueue){
        this.requestsQueue=requestsQueue;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()){ //isInterrupted该线程是否中断
            try {
                //从队列中获取图片请求
                BitmapRequest br=requestsQueue.take();
                //设置占位图
                showLoadingImage(br);
                //加载图片
                Bitmap bitmap=showImageView(br);
                showImageView(br,bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private void showImageView(final BitmapRequest br, final Bitmap bitmap) {
        if (bitmap!=null && br.getImageView()!=null && br.getUrlMd5().equals(br.getImageView().getTag())){
            final ImageView imageView=br.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    if (br.getReqeestListener()!=null){
                        RequestListener listener=br.getReqeestListener();
                        listener.onSuccess(bitmap);
                    }
                }
            });
        }
    }

    private Bitmap showImageView(BitmapRequest br) {
      //  Bitmap bitmap=downloadBitmap(br.getUrl());
        Bitmap bitmap=  MyBitmapUtils.getInstance().loadImage(br.getUrl());
        return bitmap;
    }

    private Bitmap downloadBitmap(String uri) {
        FileOutputStream fos=null;
        InputStream is=null;
        Bitmap bitmap=null;
        try {
            URL url=new URL(uri);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            is=conn.getInputStream();

             bitmap= BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                 try {
                     if (is!=null) {
                         is.close();
                     }
                     if (fos!=null){
                         fos.close();
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
        }
        return bitmap;
    }

    private void showLoadingImage(BitmapRequest br) {
        if (br.getResID()>0 && br.getImageView()!=null){
              final int resId=br.getResID();
            final ImageView imageView=br.getImageView();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(resId);
                }
            });


        }
    }
}
