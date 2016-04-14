package hk.edu.polyu.comp.hellohongkong.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.sql.Date;


/**
 * Created by zhoumoyuan on 4/14/16.
 */
public class EventsDetail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        TextView nameTextView = (TextView) findViewById(R.id.eventNameTextView);
        TextView locationTextView = (TextView) findViewById(R.id.locationTextView);
        TextView startTextView = (TextView) findViewById(R.id.startTextView);
        TextView endTextView = (TextView) findViewById(R.id.endTextView);
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);

        if (nameTextView != null) {
            nameTextView.setText(getIntent().getStringExtra("name"));
        }
        if (locationTextView != null) {
            locationTextView.setText(getIntent().getStringExtra("location"));
        }
        if (startTextView != null) {
            startTextView.setText(new Date(Long.parseLong(getIntent().getStringExtra("start"))).toString());
        }
        if (endTextView != null) {
            endTextView.setText(new Date(Long.parseLong(getIntent().getStringExtra("end"))).toString());
        }
        if (descriptionTextView != null) {
            descriptionTextView.setText(getIntent().getStringExtra("description"));
        }
    }

}