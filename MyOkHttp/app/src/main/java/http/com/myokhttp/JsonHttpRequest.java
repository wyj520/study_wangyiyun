package http.com.myokhttp;

import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */
public class JsonHttpRequest implements IHttpRequest {
    private String url;
    private byte[] data;
    private CallBackListener listener;
    private HttpURLConnection connection;

    @Override
    public void setUrl(String url) {
      this.url=url;
    }

    @Override
    public void setData(byte[] data) {
  this.data=data;
    }

    @Override
    public void setListener(CallBackListener listener) {
   this.listener=listener;
    }

    @Override
    public void excute() {
         //访问网络的具体操作
        URL url=null;
        try{
            url=new URL(this.url);
            connection= (HttpURLConnection) url.openConnection();//打开http连接
            connection.setConnectTimeout(6000);//设置超时时间
            connection.setUseCaches(false);//不使用缓存
            connection.setInstanceFollowRedirects(true);//是成员函数，仅用于当前函数，设置这个连接是否可以被重定向
            connection.setReadTimeout(3000);//响应的超时时间
            connection.setDoInput(true);//设置这个连接是否可写入数据
            connection.setDoOutput(true);//设置这个时间是否可以输出数据
            connection.setRequestMethod("get");//设置请求方式
            connection.setRequestProperty("Contetn-Type","application/json;charset=utf-8");//设置消息类型
            connection.connect();//连接，从上述至此的配置必须要在connect之前完成，实际上他只是建立了一个与服务器的tcp连接；

            //使用字节流发送数据
            OutputStream out=connection.getOutputStream();
            BufferedOutputStream bos=new BufferedOutputStream(out);
            bos.write(data);
            bos.flush();
            out.close();
            bos.close();

            //字符流写入数据
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){//得到服务器返回码是否连接成功
                InputStream in=connection.getInputStream();
                listener.onSuccess(in);//就把我们的数据返回到框架里了

            }else {
                throw new RuntimeException("请求失败");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
