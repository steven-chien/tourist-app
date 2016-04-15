package hk.edu.polyu.comp.hellohongkong.app;

import android.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.*;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface OnImageDownloadCompleteEventListener {
    public void OnImageDownloadComplete(Bitmap bitmap);
}

public class TourSpotDetailActivity extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener, OnMapReadyCallback {
    private Resources mvResources;
    private ServerRequestManager svServerRequestManager;
    private TourSpotManager svTourSpotManager;
    private EventManager svEventManager;
    private TourSpot mvTourSpot;

    private int mvScreenWidth = 0;
    private int mvScreenHeight = 0;

    private GoogleApiClient mvGoogleApiClient;
    private Location mvLastLocation;
    private MapFragment mvMapFragment;
    private GoogleMap mvMap;
    private double mvCurrentLatitude = 0;
    private double mvCurrentLongitude = 0;

    private Toolbar mvToolbar;
    private ScrollView mvTourSpotDetailScrollView;
    private ImageView mvTourSpotDetailImageView;
    private TextView mvTourSpotDetailNameTextView;
    private RelativeLayout mvTourSpotDetailLocationButton;
    private RelativeLayout mvTourSpotDetailEventButton;
    private TextView mvTourSpotDetailDescriptionTextView;
    private LinearLayout mvLocationHeader;
    private RelativeLayout mvMapContainer;
    private LinearLayout mvEventHeader;
    private ListView mvEventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_spot_detail);

        mvResources = getResources();
        svServerRequestManager = ServerRequestManager.getInstance();
        svTourSpotManager = TourSpotManager.getInstance();
        svEventManager = EventManager.getInstance();

        Bundle lvExtra = getIntent().getExtras();
        if (lvExtra != null) {
            String lvTourSpotID = lvExtra.getString("id");
            mvTourSpot = svTourSpotManager.getTourSpot(lvTourSpotID);
        }
        else {
//            mvTourSpot = svTourSpotManager.getTourSpotList().get(0);
        }

        // Get screen width and height
        DisplayMetrics lvDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(lvDisplayMetrics);
        mvScreenWidth = lvDisplayMetrics.widthPixels;
        mvScreenHeight = lvDisplayMetrics.heightPixels;

        mvToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(mvToolbar);
        ActionBar lvActionBar = getSupportActionBar();
        lvActionBar.setDisplayHomeAsUpEnabled(true);
        if (mvToolbar != null) {
            mvToolbar.setTitle(mvResources.getString(R.string.app_name));
        }

        mvTourSpotDetailScrollView = (ScrollView) findViewById(R.id.tourSpotDetailScrollView);

        // Image
        mvTourSpotDetailImageView = (ImageView) findViewById(R.id.tourSpotDetailImageView);
        Bitmap lvTourSpotDetailImage = null;

        svServerRequestManager.requestImage(mvTourSpot.getImageURL(), new OnImageDownloadCompleteEventListener() {
            @Override
            public void OnImageDownloadComplete(Bitmap lvTourSpotDetailImage) {
                int lvImageWidth = lvTourSpotDetailImage.getWidth();
                int lvImageHeight = lvTourSpotDetailImage.getHeight();
                ViewGroup.LayoutParams lvImageViewLayoutParams = mvTourSpotDetailImageView.getLayoutParams();
                lvImageViewLayoutParams.height = mvScreenWidth * lvImageHeight / lvImageWidth;
                mvTourSpotDetailImageView.setImageBitmap(lvTourSpotDetailImage);
            }
        });


        // Tour Spot Name
        mvTourSpotDetailNameTextView = (TextView) findViewById(R.id.tourSpotNameTextView);
        mvTourSpotDetailNameTextView.setText(mvTourSpot.getName());

        // Floating Action Button
        mvTourSpotDetailLocationButton = (RelativeLayout) findViewById(R.id.tourSpotDetailLocationButton);
        mvTourSpotDetailLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvTourSpotDetailScrollView.scrollTo(0, (int) mvLocationHeader.getY());
            }
        });
        mvTourSpotDetailEventButton = (RelativeLayout) findViewById(R.id.tourSpotDetailEventButton);
        mvTourSpotDetailEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvTourSpotDetailScrollView.scrollTo(0, (int) mvEventHeader.getY());
            }
        });


        // Tour Spot Description
        mvTourSpotDetailDescriptionTextView = (TextView) findViewById(R.id.tourSpotDetailDescriptionTextView);
        mvTourSpotDetailDescriptionTextView.setText(mvTourSpot.getDescription());

        // Location
        mvLocationHeader = (LinearLayout) findViewById(R.id.locationHeader);
        if (mvGoogleApiClient == null) {
            mvGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            System.out.println("******************************************************** Google Client Created");
        }
        mvMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.tourSpotDetailMapContainer, mvMapFragment);
        fragmentTransaction.commit();
        mvMapFragment.getMapAsync(this);

        // Event
        mvEventHeader = (LinearLayout) findViewById(R.id.eventHeader);
        mvEventListView = (ListView) findViewById(R.id.tourSpotDetailListView);
        setupEventList();
    }

    public void setupEventList() {
        ArrayList<Event> lvEventList = svEventManager.getEventList();
        ArrayList<HashMap<String, String>> lvArrayList = new ArrayList<>();
        for (Event lvEvent : lvEventList) {
            HashMap<String, String> lvItem = new HashMap<>();
            lvItem.put("name", lvEvent.getName());
            lvItem.put("start", Integer.toString(lvEvent.getStartTime()));
            lvItem.put("end", Integer.toString(lvEvent.getEndTime()));
            lvItem.put("location", lvEvent.getLocation());
            lvItem.put("description", lvEvent.getDescription());
            lvArrayList.add(lvItem);
        }
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), lvArrayList, R.layout.simplerow, new String[]{"name", "location", "start", "end", "description"}, new int[]{R.id.rowTextView1, R.id.rowTextView2, R.id.start, R.id.end, R.id.description});
        if (mvEventListView != null) {
            mvEventListView.setAdapter(adapter);
            mvEventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    public void addMarkerOnMap(String pTitle, double pLatitude, double pLongitude, float pColor) {
        if (mvMap != null) {
            if (pTitle.equals("Current Location")) {
                mvMap.addMarker(new MarkerOptions()
                        .position(new LatLng(pLatitude, pLongitude))
                        .title(pTitle));
            }
            else {
                mvMap.addMarker(new MarkerOptions()
                        .position(new LatLng(pLatitude, pLongitude))
                        .title(pTitle)
                        .icon(BitmapDescriptorFactory.defaultMarker(pColor)));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mvMap = map;
        mvMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mvTourSpot.getLatitude(), mvTourSpot.getLongitude()) , 12f));
        addMarkerOnMap(mvTourSpot.getName(), mvTourSpot.getLatitude(), mvTourSpot.getLongitude(), mvTourSpot.getColor());
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        try {
            mvLastLocation = LocationServices.FusedLocationApi.getLastLocation(mvGoogleApiClient);
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        if (mvLastLocation != null) {
            mvCurrentLatitude = mvLastLocation.getLatitude();
            mvCurrentLongitude = mvLastLocation.getLongitude();
            addMarkerOnMap("Current Location", mvCurrentLatitude, mvCurrentLongitude, 0f);
        }
    }

    @Override
    public void onConnectionSuspended(int p) {
        System.out.println("Connection Suspended: " + p);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // An unresolvable error has occurred and a connection to Google APIs
        // could not be established. Display an error message, or handle
        // the failure silently

        System.out.println("Connection Fail");
    }

    @Override
    protected void onStart() {
        mvGoogleApiClient.connect();
        System.out.println("onStart connect");
        super.onStart();
    }

    @Override
    protected void onStop() {
        mvGoogleApiClient.disconnect();
        System.out.println("onStop disconnect");
        super.onStop();
    }
}
