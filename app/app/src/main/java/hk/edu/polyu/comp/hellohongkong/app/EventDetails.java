package hk.edu.polyu.comp.hellohongkong.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class EventDetails extends AppCompatActivity {

    private TextView nameTextView;
    private TextView locationTextView;
    private TextView dateTextView;
    private TextView descriptionTextView;

    JSONParser jParser;
    JSONObject json;

    private static String url_get = "https://46.101.253.211/api/events/";
    private static final String TAG_STATUS = "status";
    private static final String TAG_SUCCESS = "success";

    private void displayDetails()
    {
        try
        {
            nameTextView.setText(json.getJSONObject("data").getString("name"));
            locationTextView.setText(json.getJSONObject("data").getString("spot"));
            dateTextView.setText(json.getJSONObject("data").getString("date")+" "+json.getJSONObject("data").getString("time"));
            descriptionTextView.setText(json.toString());
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        nameTextView = (TextView)findViewById(R.id.eventNameTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);
        dateTextView = (TextView)findViewById(R.id.timeTextView);
        descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        String obj_id = (String)intent.getStringExtra("obj_id");
        jParser = new JSONParser();
        url_get = url_get + obj_id;
        new LoadGetTask().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

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

            //Check for result
            try {
                if (Objects.equals(json.getString(TAG_STATUS), TAG_SUCCESS)){
                    System.out.println("json status check successful");
                    //Do things here...
                    displayDetails();
                }
            } catch (JSONException e) {
                System.out.println("json status check not successful");
                e.printStackTrace();
            }
        }

    }
}
