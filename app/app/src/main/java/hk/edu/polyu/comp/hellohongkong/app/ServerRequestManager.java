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
    private static ServerRequestManager svServerRequestManager;
    private TourSpotManager svTourSpotManager;
    private JSONParser mvJSONParser;
    private ImageUtil mvImageUtil;

    private JSONObject mvTourSpotJSONObject;

    private ServerRequestManager() {
        svTourSpotManager = TourSpotManager.getInstance();
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

//            ArrayList<TourSpot> lvTourSpotList = svTourSpotManager.getTourSpotList();
//            for (TourSpot lvTourSpot : lvTourSpotList) {
//                System.out.println("ID: " + lvTourSpot.getID());
//                System.out.println("Name: " + lvTourSpot.getName());
//                System.out.println("Latitude: " + lvTourSpot.getLatitude());
//                System.out.println("Longitude: " + lvTourSpot.getLongitude());
//                System.out.println("HK Region: " + lvTourSpot.getHKRegion());
//                System.out.println("Description: " + lvTourSpot.getDescription());
//                System.out.println("Image URL: " + lvTourSpot.getImageURL());
//                System.out.println();
//            }
        }
    }

    public void requestImage(String pURL, OnImageDownloadCompleteEventListener callback) {
        mvImageUtil = new ImageUtil();
        Bitmap lvOutputBitmap = null;
        try {
            lvOutputBitmap = mvImageUtil.execute(pURL).get();
            callback.OnImageDownloadComplete(lvOutputBitmap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        DownloadImage lvDownloadImage = new DownloadImage(pImageView);
//        lvDownloadImage.execute(pURL);
//        return lvDownloadImage.getBitmap();
    }

//    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
//        ImageView mvImageView;
//        Bitmap mvOutputBitmap;
//
//        public DownloadImage(ImageView pImageView) {
//            mvImageView = pImageView;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... pURLs) {
//            String lvURLDisplay = pURLs[0];
//            Bitmap lvOutputBitmap = null;
//            try {
////                URL lvURL = new URL(lvURLDisplay);
////                HttpURLConnection lvHttpURLConnection = (HttpURLConnection) lvURL.openConnection();
////                InputStream lvInputStream = new BufferedInputStream(lvHttpURLConnection.getInputStream());
//
//                InputStream lvInputStream = new java.net.URL(lvURLDisplay).openStream();
//                lvOutputBitmap = BitmapFactory.decodeStream(lvInputStream);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            mvOutputBitmap = lvOutputBitmap;
//            return lvOutputBitmap;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap pResult) {
//            mvImageView.setImageBitmap(pResult);
//        }
//
//        public Bitmap getBitmap() {
//            return mvOutputBitmap;
//        }
//    }
}
