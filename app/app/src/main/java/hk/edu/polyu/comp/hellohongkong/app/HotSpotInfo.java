package hk.edu.polyu.comp.hellohongkong.app;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by zhoumoyuan on 4/12/16.
 */
public class HotSpotInfo extends AppCompatActivity {
    JSONObject json;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    private static final String url_get = "https://46.101.253.211/api/hotspot";
    private static final String TAG_STATUS = "status";
    private static final String TAG_SUCCESS = "success";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_spot);

        new LoadGetTask().execute();
    }

    /**
     * Background Async Task to get hot spots information by making HTTP Request
     * */
    class LoadGetTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        protected String doInBackground(String... args) {
            // getting JSON string from URL
            try {
                json = jParser.makeHttpRequest(url_get, "GET");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        protected void onPostExecute(String file_url) {
            TextView t = (TextView) findViewById(R.id.response);
            t.setText(json.toString());

            //Check for result
            try {
                if (Objects.equals(json.getString(TAG_STATUS), TAG_SUCCESS)){
                    System.out.println("json status check successful");

                    //Do things here...

                }
            } catch (JSONException e) {
                System.out.println("json status check not successful");
                e.printStackTrace();
            }



        }

    }
}
