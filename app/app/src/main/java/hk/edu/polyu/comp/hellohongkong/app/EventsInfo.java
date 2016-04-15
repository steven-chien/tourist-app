package hk.edu.polyu.comp.hellohongkong.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.List;
import java.util.Objects;

/**
 * Created by zhoumoyuan on 4/14/16.
 */
public class EventsInfo extends AppCompatActivity {
    private EventManager svEventManager;
    private Resources mvResources;
    private Toolbar mvToolbar;

    private ListView mvListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        mvResources = getResources();
        svEventManager = EventManager.getInstance();

        mvToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(mvToolbar);
        ActionBar lvActionBar = getSupportActionBar();
        lvActionBar.setDisplayHomeAsUpEnabled(true);
        if (mvToolbar != null) {
            mvToolbar.setTitle(mvResources.getString(R.string.app_name));
        }

        mvListView = (ListView) findViewById(R.id.listView);
        setupUI();
    }

    public void setupUI() {
        ArrayList<Event> lvEventList = svEventManager.getEventList();
        ArrayList<HashMap<String, String>> lvArrayList = new ArrayList<>();
        for (Event lvEvent : lvEventList) {
            HashMap<String, String> lvItem = new HashMap<>();
            lvItem.put("name", lvEvent.getName());
            lvItem.put("start", Long.toString(lvEvent.getStartTime()));
            lvItem.put("end", Long.toString(lvEvent.getEndTime()));
            lvItem.put("location", lvEvent.getLocation());
            lvItem.put("description", lvEvent.getDescription());
            lvArrayList.add(lvItem);
        }
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), lvArrayList, R.layout.simplerow, new String[]{"name", "location", "start", "end", "description"}, new int[]{R.id.rowTextView1, R.id.rowTextView2, R.id.start, R.id.end, R.id.description});
        if (mvListView != null) {
            mvListView.setAdapter(adapter);
            mvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    TextView t1 = (TextView) view.findViewById(R.id.rowTextView1);
                    TextView t2 = (TextView) view.findViewById(R.id.rowTextView2);
                    TextView t3 = (TextView) view.findViewById(R.id.start);
                    TextView t4 = (TextView) view.findViewById(R.id.end);
                    TextView t5 = (TextView) view.findViewById(R.id.description);
                    Intent i = new Intent(getApplicationContext(), EventsDetail.class);
                    i.putExtra("name", t1.getText().toString());
                    i.putExtra("location", t2.getText().toString());
                    i.putExtra("start", t3.getText().toString());
                    i.putExtra("end", t4.getText().toString());
                    i.putExtra("description", t5.getText().toString());
                    startActivity(i);
                }
            });
        }
    }
}