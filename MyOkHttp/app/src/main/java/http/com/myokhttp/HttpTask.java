package http.com.myokhttp;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * author: wyj
 * Date:2019/5/30
 * Description:
 * T: 有可能是json，object，string
 */
public class HttpTask<T> implements Runnable {

    private IHttpRequest iHttpRequest;
    public HttpTask(String url,T requestData,IHttpRequest httpRequest,CallBackListener listener){
        try {
            this.iHttpRequest=httpRequest;
            iHttpRequest.setUrl(url);
            iHttpRequest.setListener(listener);
            String content= JSON.toJSONString(requestData);
            iHttpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        iHttpRequest.excute();
    }
}
