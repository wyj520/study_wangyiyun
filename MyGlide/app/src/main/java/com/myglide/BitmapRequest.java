package com.myglide;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * author: wyj
 * Date:2019/6/18
 * Description：请求事件
 */
public class BitmapRequest {
    //图片地址
    private String url;

    //图片加载控件：图片占用的内存特别大，需要使用软引用
    private SoftReference<ImageView> imageView;

    //占位图片，加载失败的图片
    private int resID;

    //回放对象
    private RequestListener reqeestListener;

    //图片标识：很重要，避免复用机制造成的图片错位
    private String urlMd5;


    private Context context;

    public BitmapRequest(Context context){
        this.context=context;
    }

    public BitmapRequest load(String url){
        this.url=url;
        this.urlMd5=MD5Utils.toMD5(url);
        return this;
    }

    public BitmapRequest loading(int resId){
        this.resID=resId;
        return this;
    }

    public BitmapRequest listener(RequestListener listener){
        this.reqeestListener=listener;
        return this;
    }

    public void into (ImageView imageView){
         imageView.setTag(this.urlMd5);
         this.imageView=new SoftReference<>(imageView);
         //将图片添加到请求队列中
         RequestManager.getInstance().addBitmapRequest(this);
    }

    //----------get方法----------------------------------------------

    public String getUrl() {
        return url;
    }

    public   ImageView getImageView() {
        return imageView.get();//从软应用中的到ImageView；
    }

    public int getResID() {
        return resID;
    }

    public RequestListener getReqeestListener() {
        return reqeestListener;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public Context getContext() {
        return context;
    }
}
