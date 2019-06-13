package http.com.myokhttp;

/**
 * author: wyj
 * Date:2019/5/30
 * Description:
 */
public interface JsonDataListener<T> {
    void onSuccess(T t);
    void onFailure();
}
