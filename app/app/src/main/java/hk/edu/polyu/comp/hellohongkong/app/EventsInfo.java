package hk.edu.polyu.comp.hellohongkong.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by zhoumoyuan on 4/14/16.
 */
public class EventsInfo extends AppCompatActivity {
    JSONObject json;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    private static final String url_get = "https://46.101.253.211/api/events";
    private static final String TAG_STATUS = "status";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_DATA = "data";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        ProgressBar pb = (ProgressBar) findViewById(R.id.loadingPanel);
        if (pb != null) {
            pb.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        }
        new LoadGetTask().execute();
    }

    /**
     * Background Async Task to get hot spots information by making HTTP Request
     */
    class LoadGetTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         */
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
            //TextView t = (TextView) findViewById(R.id.response);
            //t.setText(json.toString());

            //Check for result
            try {
                if (Objects.equals(json.getString(TAG_STATUS), TAG_SUCCESS)) {
                    ProgressBar pb = (ProgressBar) findViewById(R.id.loadingPanel);
                    if (pb != null) {
                        pb.setVisibility(View.GONE);
                    }
                    System.out.println("json status is successful");
                    JSONArray jsonArray = json.getJSONArray(TAG_DATA);
                    JSONObject eventsInfo;
                    ListView lv = (ListView) findViewById(R.id.listView);
                    ArrayList<HashMap<String, String>> arraylist = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        eventsInfo = jsonArray.getJSONObject(i);
                        HashMap<String, String> item = new HashMap<>();
                        item.put("name", eventsInfo.getString("name"));
                        item.put("spot", eventsInfo.getString("spot"));
                        item.put("id", eventsInfo.getString("_id"));
                        arraylist.add(item);
                    }
                    SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), arraylist, R.layout.simplerow, new String[]{"name", "spot", "id"}, new int[]{R.id.rowTextView1, R.id.rowTextView2, R.id.tag});
                    if (lv != null) {
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                TextView t1 = (TextView) view.findViewById(R.id.tag);
                                TextView t2 = (TextView) view.findViewById(R.id.rowTextView1);
                                Intent i = new Intent(getApplicationContext(), EventsDetail.class);
                                i.putExtra("id", t1.getText().toString());
                                i.putExtra("name", t2.getText().toString());
                                startActivity(i);
                            }
                        });
                    }

                } else {
                    System.out.println("json status is not successful");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}