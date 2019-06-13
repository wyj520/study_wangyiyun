package http.com.myokhttp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */

public class ThreadPoolManager {

    private static ThreadPoolManager threadPoolManager=new ThreadPoolManager();


    public static ThreadPoolManager getInastance(){
        return threadPoolManager;
    }


    //创建队列，将网络请求添加到队列中
    private LinkedBlockingDeque<Runnable> mQueue=new LinkedBlockingDeque<>();

    public void addTask(Runnable runnable){
        if (runnable !=null){
            try{
                mQueue.put(runnable);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //创建线程池
    public ThreadPoolExecutor threadPoolExecutor;
    private ThreadPoolManager(){
        threadPoolExecutor=new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
                        // runnable: 拒接的线程,将拒绝的线程重新放回队列
                        addTask(runnable);
                    }
                });
    }

    //创建“叫号员线程”，不停地获取
    public Runnable coreThread=new Runnable() {
        Runnable runnable=null;

        @Override
        public void run() {
            while (true){
                try {
                    runnable=mQueue.take();
                    threadPoolExecutor.execute(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
