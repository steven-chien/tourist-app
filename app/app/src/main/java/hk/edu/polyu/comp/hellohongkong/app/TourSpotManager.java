package hk.edu.polyu.comp.hellohongkong.app;

import java.util.ArrayList;

/**
 * Created by ChiKuen on 2016/4/12.
 */
public class TourSpotManager {
    private static TourSpotManager svTourSpotManager;
    private ArrayList<TourSpot> mvTourSpotList = new ArrayList<>();

    private TourSpotManager() {

    }

    public static TourSpotManager getInstance() {
        if (svTourSpotManager == null) {
            svTourSpotManager = new TourSpotManager();
        }
        return svTourSpotManager;
    }

    public void addTourSpot(TourSpot pTourSpot) {
        mvTourSpotList.add(pTourSpot);
    }

    public ArrayList<TourSpot> getTourSpotList() {
        return mvTourSpotList;
    }

    public TourSpot getTourSpot(String pID) {
        for (TourSpot lvTourSpot : mvTourSpotList) {
            if (pID.equals(lvTourSpot.getID())) {
                return lvTourSpot;
            }
        }
        return null;
    }

    public TourSpot getTourSpot(double pLatitude, double pLongitude) {
        for (TourSpot lvTourSpot : mvTourSpotList) {
            if (lvTourSpot.getLatitude() == pLatitude && lvTourSpot.getLongitude() == pLongitude) {
                return lvTourSpot;
            }
        }
        return null;
    }
}
