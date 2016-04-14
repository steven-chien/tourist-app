package hk.edu.polyu.comp.hellohongkong.app;

import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

import hk.edu.polyu.comp.hellohongkong.app.R;

public class TourHotSpotMapActivity extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener, OnMapReadyCallback {
    private static TourSpotManager svTourSpotManager;
    private Resources mvResources;

    private GoogleApiClient mvGoogleApiClient;
    private Location mvLastLocation;
    private MapFragment mvMapFragment;
    private GoogleMap mvMap;

    private Toolbar mvToolbar;

    private double[] mvHKLocation = new double[]{22.28552, 114.15769};
    private double mvCurrentLatitude = 0;
    private double mvCurrentLongitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_hot_spot_map);

        svTourSpotManager = TourSpotManager.getInstance();
        mvResources = getResources();

        mvToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(mvToolbar);
        ActionBar lvActionBar = getSupportActionBar();
        lvActionBar.setDisplayHomeAsUpEnabled(true);
        if (mvToolbar != null) {
            mvToolbar.setTitle(mvResources.getString(R.string.app_name));
        }

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
        fragmentTransaction.add(R.id.mapContainer, mvMapFragment);
        fragmentTransaction.commit();

        mvMapFragment.getMapAsync(this);
    }

    public void displayTourSpotMarker(String pDisplayType) {
        ArrayList<TourSpot> lvTourSpotList = svTourSpotManager.getTourSpotList();

        if (pDisplayType.equals("All")) {
            for (TourSpot lvTourSpot : lvTourSpotList) {
                addMarkerOnMap(lvTourSpot.getName(), lvTourSpot.getLatitude(), lvTourSpot.getLongitude(), lvTourSpot.getColor());
            }
        }
        else {
            for (TourSpot lvTourSpot : lvTourSpotList) {
                if (pDisplayType.equals(lvTourSpot.getHKRegion())) {
                    addMarkerOnMap(lvTourSpot.getName(), lvTourSpot.getLatitude(), lvTourSpot.getLongitude(), lvTourSpot.getColor());
                }
            }
        }

        addMarkerOnMap("Current Location", mvCurrentLatitude, mvCurrentLongitude, 0f);
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
        mvMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mvHKLocation[0], mvHKLocation[1]), 9.6f));
        displayTourSpotMarker("All");

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

            System.out.println("Latitude: " + mvCurrentLatitude);
            System.out.println("Longitude: " + mvCurrentLongitude);
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
    public boolean onCreateOptionsMenu(Menu pMenu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tour_hot_spot_map, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        mvMap.clear();
        switch (pItem.getItemId()) {
            case R.id.action_bar_hongkong:
                System.out.println("Hong Kong");
                mvToolbar.setTitle(mvResources.getString(R.string.action_bar_hongkong));
                mvToolbar.setTitleTextColor(Color.CYAN);
                displayTourSpotMarker("Hong Kong");
                return true;
            case R.id.action_bar_kowloon:
                System.out.println("Kowloon");
                mvToolbar.setTitle(mvResources.getString(R.string.action_bar_kowloon));
                mvToolbar.setTitleTextColor(Color.YELLOW);
                displayTourSpotMarker("Kowloon");
                return true;
            case R.id.action_bar_newterritories:
                System.out.println("New Territories");
                mvToolbar.setTitle(mvResources.getString(R.string.action_bar_newterritories));
                mvToolbar.setTitleTextColor(Color.GREEN);
                displayTourSpotMarker("New Territories");
                return true;
            case R.id.action_bar_all:
                mvToolbar.setTitle(mvResources.getString(R.string.app_name));
                mvToolbar.setTitleTextColor(Color.WHITE);
                displayTourSpotMarker("All");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(pItem);

        }
    }

    @Override
    protected void onResume() {
        addMarkerOnMap("Current Location", mvCurrentLatitude, mvCurrentLongitude, 0f);
        super.onResume();
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

//    public double round(double pValue, int pPlaces) {
//        if (pPlaces < 0) {
//            return pValue;
//        }
//
//        long lvFactor = (long) Math.pow(10, pPlaces);
//        pValue = pValue * lvFactor;
//        long lvTemp = Math.round(pValue);
//        return (double) lvTemp / lvFactor;
//    }
}
