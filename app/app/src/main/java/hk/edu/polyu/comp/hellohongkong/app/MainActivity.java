package hk.edu.polyu.comp.hellohongkong.app;

import android.content.Intent;
import android.media.Image;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static boolean svIsHotSpotInfoGot = false;
    private static boolean svIsEventInfoGot = false;

    private ServerRequestManager svServerRequestManager;

    private FloatingActionButton mvMainMapButton;
    private FloatingActionButton mvMainHotSpotButton;
    private FloatingActionButton mvMainEventButton;
    private FloatingActionButton mvMainNFCButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        svServerRequestManager = ServerRequestManager.getInstance();

        // Request Hot Spot Info
        if (!svIsHotSpotInfoGot) {
            svServerRequestManager.requestHotSpotInfo();
            svIsHotSpotInfoGot = true;
        }

        // Request Event Info
        if (!svIsEventInfoGot) {
            svServerRequestManager.requestEventInfo();
            svIsEventInfoGot = true;
        }

        mvMainMapButton = (FloatingActionButton) findViewById(R.id.mainMapButton);
        mvMainMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TourHotSpotMapActivity.class);
                startActivity(intent);
            }
        });

        mvMainHotSpotButton = (FloatingActionButton) findViewById(R.id.mainHotSpotButton);
        mvMainHotSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HotSpotInfo.class);
                startActivity(intent);
            }
        });

        mvMainEventButton = (FloatingActionButton) findViewById(R.id.mainEventButton);
        mvMainEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventsInfo.class);
                startActivity(intent);
            }
        });

        mvMainNFCButton = (FloatingActionButton) findViewById(R.id.mainNFCButton);
        mvMainNFCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TourSpotDetailActivity.class);
//                startActivity(intent);
            }
        });
    }
}
