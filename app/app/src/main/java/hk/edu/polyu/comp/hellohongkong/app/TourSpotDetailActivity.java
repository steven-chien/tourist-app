package hk.edu.polyu.comp.hellohongkong.app;

import android.app.FragmentTransaction;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

interface OnImageDownloadCompleteEventListener {
    public void OnImageDownloadComplete(Bitmap bitmap);
}

public class TourSpotDetailActivity extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener, OnMapReadyCallback {
    private Resources mvResources;
    private ServerRequestManager svServerRequestManager;
    private TourSpotManager svTourSpotManager;
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
    private FloatingActionButton mvLocationFAB;
    private FloatingActionButton mvEventFAB;
    private TextView mvTourSpotDetailDescriptionTextView;
    private LinearLayout mvLocationHeader;
    private RelativeLayout mvMapContainer;
    private LinearLayout mvEventHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_spot_detail);

        mvResources = getResources();
        svServerRequestManager = ServerRequestManager.getInstance();
        svTourSpotManager = TourSpotManager.getInstance();

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
//        mvTourSpotDetailNameTextView.setText();

        // Floating Action Button
        mvLocationFAB = (FloatingActionButton) findViewById(R.id.mapFloatingActionButton);
        mvLocationFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvTourSpotDetailScrollView.scrollTo(0, (int) mvLocationHeader.getY());
            }
        });
        mvEventFAB = (FloatingActionButton) findViewById(R.id.eventFloatingActionButton);
        mvEventFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvTourSpotDetailScrollView.scrollTo(0, (int) mvEventHeader.getY());
            }
        });


        // Tour Spot Description
        mvTourSpotDetailDescriptionTextView = (TextView) findViewById(R.id.tourSpotDetailDescriptionTextView);
//        mvTourSpotDetailDescriptionTextView.setText();

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
