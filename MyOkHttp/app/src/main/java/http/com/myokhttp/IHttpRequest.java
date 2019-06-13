package http.com.myokhttp;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */
public interface IHttpRequest {
    void setUrl(String url);
    void setData(byte[] data);
    void setListener(CallBackListener listener);
    void excute();
}
