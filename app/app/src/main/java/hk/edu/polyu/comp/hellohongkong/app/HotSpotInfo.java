package hk.edu.polyu.comp.hellohongkong.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static final String TAG_DATA = "data";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_spot);

        ProgressBar pb = (ProgressBar) findViewById(R.id.loadingPanel);
        if (pb != null) {
            pb.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        }
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
            //TextView t = (TextView) findViewById(R.id.response);
            //t.setText(json.toString());
            GridView gridview = (GridView) findViewById(R.id.gridView);

            //Check for result
            try {
                if (Objects.equals(json.getString(TAG_STATUS), TAG_SUCCESS)){
                    ProgressBar pb = (ProgressBar) findViewById(R.id.loadingPanel);
                    if (pb != null) {
                        pb.setVisibility(View.GONE);
                    }
                    System.out.println("json status is successful");
                    JSONArray jsonArray = json.getJSONArray(TAG_DATA);
                    JSONObject spotInfo;
                    ArrayList<HashMap<String, Object>> imagelist = new ArrayList<HashMap<String, Object>>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        spotInfo = jsonArray.getJSONObject(i);
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        Bitmap bm = new ImageUtil().execute(spotInfo.getString("preview")).get();
                        map.put("image", scaleCenterCrop(bm, 400, 500));
                        map.put("text", spotInfo.getString("name"));
                        map.put("id", spotInfo.getString("_id"));
                        imagelist.add(map);
                    }
                    ExtendedSimpleAdapter simpleAdapter = new ExtendedSimpleAdapter(getApplicationContext(), imagelist, R.layout.items, new String[] { "image", "text", "id" }, new int[] {R.id.image, R.id.title });
                    if (gridview != null) {
                        gridview.setAdapter(simpleAdapter);
                        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                TextView t = (TextView)view.findViewById(R.id.title);
                                Intent i = new Intent(getApplicationContext(), HotSpotDetail.class);
                                i.putExtra("id", view.getTag().toString());
                                i.putExtra("name", t.getText().toString());
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
    public Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        // Compute the scaling factors to fit the new height and width, respectively.
        // To cover the final image, the final scaling will be the bigger
        // of these two.
        float xScale = (float) newWidth / sourceWidth;
        float yScale = (float) newHeight / sourceHeight;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        // Let's find out the upper left coordinates if the scaled bitmap
        // should be centered in the new size give by the parameters
        float left = (newWidth - scaledWidth) / 2;
        float top = (newHeight - scaledHeight) / 2;

        // The target rectangle for the new, scaled version of the source bitmap will now
        // be
        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

        // Finally, we create a new bitmap of the specified size and draw our new,
        // scaled bitmap onto it.
        Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(source, null, targetRect, null);

        return dest;
    }
}
