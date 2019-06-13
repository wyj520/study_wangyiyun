package http.com.myokhttp;

import java.io.InputStream;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */
public interface CallBackListener {
    void onSuccess(InputStream in);

    void onFailure();
}
