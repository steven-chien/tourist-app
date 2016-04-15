package hk.edu.polyu.comp.hellohongkong.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by ChiKuen on 2016/4/13.
 */
public class ServerRequestManager {
    private final String mvHotSpotInfoURL = "https://46.101.253.211/api/hotspot";
    private final String mvEventInfoURL = "https://46.101.253.211/api/events";
    private static ServerRequestManager svServerRequestManager;
    private TourSpotManager svTourSpotManager;
    private EventManager svEventManager;
    private JSONParser mvJSONParser;
    private ImageUtil mvImageUtil;

    private JSONObject mvTourSpotJSONObject;
    private JSONObject mvEventJSONObject;

    private ServerRequestManager() {
        svTourSpotManager = TourSpotManager.getInstance();
        svEventManager = EventManager.getInstance();
        mvJSONParser = new JSONParser();
    }

    public static ServerRequestManager getInstance() {
        if (svServerRequestManager == null) {
            svServerRequestManager = new ServerRequestManager();
        }
        return svServerRequestManager;
    }

    public void requestHotSpotInfo() {
        new DownloadHotSpot().execute();
    }

    private class DownloadHotSpot extends AsyncTask<String, Void, String> {

        public DownloadHotSpot() {

        }

        @Override
        protected String doInBackground(String... pURLs) {
            try {
                mvTourSpotJSONObject = mvJSONParser.makeHttpRequest(mvHotSpotInfoURL, "GET");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String pResult) {
            try {
                JSONArray lvJSONArray = mvTourSpotJSONObject.getJSONArray("data");
                for (int i=0; i < lvJSONArray.length(); i++)
                {
                    JSONObject lvTourSpotJSONObject = lvJSONArray.getJSONObject(i);
                    String lvName = lvTourSpotJSONObject.getString("name");
                    String lvHKRegion = lvTourSpotJSONObject.getString("region");
                    JSONObject lvCoordinate = lvTourSpotJSONObject.getJSONObject("coordinate");
                    double lvLatitude = lvCoordinate.getDouble("lat");
                    double lvLongitude = lvCoordinate.getDouble("lon");
                    String lvDescription = lvTourSpotJSONObject.getString("description");
                    String lvImageURL = lvTourSpotJSONObject.getString("preview");
                    String lvID = lvTourSpotJSONObject.getString("_id");

                    TourSpot lvTourSpot = new TourSpot(lvID, lvName, lvLatitude, lvLongitude, lvHKRegion, lvDescription, lvImageURL);
                    svTourSpotManager.addTourSpot(lvTourSpot);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void requestEventInfo() {
        new DownloadEvent().execute();
    }

    private class DownloadEvent extends AsyncTask<String, Void, String> {

        public DownloadEvent() {

        }

        @Override
        protected String doInBackground(String... pURLs) {
            try {
                mvEventJSONObject = mvJSONParser.makeHttpRequest(mvEventInfoURL, "GET");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String pResult) {
            try {
                JSONArray lvJSONArray = mvEventJSONObject.getJSONArray("data");
                for (int i=0; i < lvJSONArray.length(); i++)
                {
                    JSONObject lvEventJSONObject = lvJSONArray.getJSONObject(i);
                    String lvName = lvEventJSONObject.getString("name");
                    int lvStartTime = lvEventJSONObject.getInt("start");
                    int lvEndTime = lvEventJSONObject.getInt("end");
                    String lvLocation = lvEventJSONObject.getString("location");
                    String lvDescription = lvEventJSONObject.getString("description");
                    String lvID = lvEventJSONObject.getString("_id");

                    Event lvEvent = new Event(lvID, lvName, lvStartTime, lvEndTime, lvLocation, lvDescription);
                    svEventManager.addEvent(lvEvent);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void requestImage(String pURL, OnImageDownloadCompleteEventListener callback) {
        mvImageUtil = new ImageUtil();
        Bitmap lvOutputBitmap;
        try {
            lvOutputBitmap = mvImageUtil.execute(pURL).get();
            callback.OnImageDownloadComplete(lvOutputBitmap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
