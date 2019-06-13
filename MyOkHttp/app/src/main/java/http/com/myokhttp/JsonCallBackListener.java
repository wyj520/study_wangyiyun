package http.com.myokhttp;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */
public class JsonCallBackListener<T> implements CallBackListener{
    private Class<T> requestClass;
    private Handler handler=new Handler(Looper.getMainLooper());//用于线程切换

    private JsonDataListener jsonDataListener;

    public JsonCallBackListener(Class<T> responseClass,JsonDataListener jsonDataListener){
        this.requestClass=responseClass;
        this.jsonDataListener=jsonDataListener;
    }

    @Override
    public void onSuccess(InputStream in) {
      //将流转化为T类型
        String response=getContent(in);
         final T clazz=   JSON.parseObject(response,requestClass);
         handler.post(new Runnable() {
             @Override
             public void run() {
                 jsonDataListener.onSuccess(clazz);
             }
         });
    }



    @Override
    public void onFailure() {

    }

    private String getContent(InputStream in) {
        String content=null;

        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            StringBuffer sb=new StringBuffer();
            String line=null;

            while ((line=reader.readLine())!=null){
                   sb.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
