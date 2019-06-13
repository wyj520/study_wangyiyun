package http.com.myokhttp;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */
public class NetUtils {
    public static<T,M> void sendJsonRequest(String url,T requestData,Class<M> response,JsonDataListener jsonDataListener){
           IHttpRequest httpRequest=new JsonHttpRequest();
           CallBackListener callBackListener=new JsonCallBackListener<>(response,jsonDataListener);
            HttpTask httpTask=new HttpTask(url,requestData,httpRequest,callBackListener);
            ThreadPoolManager.getInastance().addTask(httpTask);

    }
}
