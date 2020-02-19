package com.myglide;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * author: wyj
 * Date:2019/6/18
 * Description
 */
public class RequestManager {
    private RequestManager(){
        start();
    }

    private static RequestManager requestManager=new RequestManager();

    public static RequestManager getInstance(){
        return requestManager;
    }

    //线程数量
    private BitmapDispatch[] bitmapDispatches;

    //创建队列
    private LinkedBlockingQueue<BitmapRequest> requestsQueue=new LinkedBlockingQueue<>();

    //将图片请求添加到队列
    public void addBitmapRequest(BitmapRequest bitmapRequest){
        if (bitmapRequest!=null){
            if (!requestsQueue.contains(bitmapRequest)){
                 requestsQueue.add(bitmapRequest);
            }
        }
    }

    private void start() {
       stop();
       startAllDispather();
    }


    //提醒所有的窗口开始服务
    private void startAllDispather() {
         //获取手机支持的最大线程数
        int threadCount=Runtime.getRuntime().availableProcessors();
        bitmapDispatches=new BitmapDispatch[threadCount];
        for (int i = 0; i <threadCount; i++) {
            BitmapDispatch bitmapDispatch=new BitmapDispatch(requestsQueue);
            bitmapDispatch.start();

            //将每个dispatch放入数组中，方便统一管理
            bitmapDispatches[i]=bitmapDispatch;
        }

    }

    //停止所有线程
    private void stop() {
        if (bitmapDispatches!=null && bitmapDispatches.length>0){
            for (BitmapDispatch b : bitmapDispatches) {
                if (!b.isInterrupted()){ //如果线程还在运行
                    b.interrupt(); //结束线程
                }
            }
        }
    }

}
