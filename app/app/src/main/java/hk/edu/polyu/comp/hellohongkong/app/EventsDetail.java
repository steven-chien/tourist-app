package hk.edu.polyu.comp.hellohongkong.app;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;


/**
 * Created by zhoumoyuan on 4/14/16.
 */
public class EventsDetail extends AppCompatActivity {
    private Resources mvResources;
    private Toolbar mvToolbar;

    String name, location, description;
    Date startTime, endTime;
    Button mAddCalendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        mvResources = getResources();

        mvToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(mvToolbar);
        ActionBar lvActionBar = getSupportActionBar();
        lvActionBar.setDisplayHomeAsUpEnabled(true);
        if (mvToolbar != null) {
            mvToolbar.setTitle(mvResources.getString(R.string.app_name));
        }

        TextView nameTextView = (TextView) findViewById(R.id.eventDetailEventNameTextView);
        TextView locationTextView = (TextView) findViewById(R.id.eventDetailsLocationTextView);
        TextView startTextView = (TextView) findViewById(R.id.eventDetailsStartTimeTextView);
        TextView endTextView = (TextView) findViewById(R.id.eventDetailsEndTimeTextView);
        TextView descriptionTextView = (TextView) findViewById(R.id.eventDetailsDescriptionTextView);
        mAddCalendarButton = (Button) findViewById(R.id.addToCalendar);

        if(mAddCalendarButton != null) {
            mAddCalendarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonOpenCalendarClick(v);
                }
            });
        }

        if (nameTextView != null) {
            name = getIntent().getStringExtra("name");
            nameTextView.setText(name);
        }
        if (locationTextView != null) {
            location = getIntent().getStringExtra("location");
            locationTextView.setText(location);
        }
        if (startTextView != null) {
            startTime = new Date(Long.parseLong(getIntent().getStringExtra("start")));
            startTextView.setText(startTime.toString());
        }
        if (endTextView != null) {
            endTime =new Date(Long.parseLong(getIntent().getStringExtra("end")));
            endTextView.setText(endTime.toString());
        }
        if (descriptionTextView != null) {
            description = getIntent().getStringExtra("description");
            descriptionTextView.setText(description);
        }
    }

    public void buttonOpenCalendarClick(View view) {
        if(name != null && startTime != null && endTime != null) {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTime());
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTime());
            intent.putExtra(CalendarContract.Events.TITLE, name);
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
            intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
            startActivity(intent);
        }
    }

}