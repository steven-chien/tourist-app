package hk.edu.polyu.comp.hellohongkong.app;

/**
 * Created by zhoumoyuan on 4/13/16.
 */
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


class ImageUtil extends AsyncTask<String, String, Bitmap> {

    int response = -1;

    protected Bitmap doInBackground(String... address) {
        try {
            //通过代码 模拟器浏览器访问图片的流程
            URL url = new URL(address[0]);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setAllowUserInteraction(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.connect();
            response = conn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                //获取服务器返回回来的流
                InputStream is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                is.close();
                return bitmap;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}