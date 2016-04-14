package hk.edu.polyu.comp.hellohongkong.app;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by ChiKuen on 2016/4/12.
 */
public class TourSpot {
    private String mvID;
    private String mvName;
    private double mvLatitude;
    private double mvLongitude;
    private String mvHKRegion;
    private String mvDescription;
    private String mvImageURL;
    private float mvColor = BitmapDescriptorFactory.HUE_AZURE;

    public TourSpot(String pID, String pName, double pLatitude, double pLongitude, String pHKRegion, String pDescription, String pImageURL) {
        mvID = pID;
        mvName = pName;
        mvLatitude = pLatitude;
        mvLongitude = pLongitude;
        mvHKRegion = pHKRegion;
        mvDescription = pDescription;
        mvImageURL = pImageURL;

        if (mvHKRegion.equalsIgnoreCase("Hong Kong")) {
            mvColor =BitmapDescriptorFactory.HUE_CYAN;
        }
        else if (mvHKRegion.equalsIgnoreCase("Kowloon")) {
            mvColor =BitmapDescriptorFactory.HUE_YELLOW;
        }
        else if (mvHKRegion.equalsIgnoreCase("New Territories")) {
            mvColor =BitmapDescriptorFactory.HUE_GREEN;
        }
    }

    public String getID() {
        return mvID;
    }
    public String getName() {
        return mvName;
    }
    public double getLatitude() {
        return mvLatitude;
    }
    public double getLongitude() {
        return mvLongitude;
    }
    public String getHKRegion() {
        return mvHKRegion;
    }
    public String getDescription() {
        return mvDescription;
    }
    public String getImageURL() {
        return mvImageURL;
    }
    public float getColor() {
        return mvColor;
    }
}
