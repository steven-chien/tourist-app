package hk.edu.polyu.comp.hellohongkong.app;

/**
 * Created by ChiKuen on 2016/4/15.
 */
public class Event {
    private String mvID;
    private String mvName;
    private int mvStartTime;
    private int mvEndTime;
    private String mvLocation;
    private String mvDescription;

    public Event(String pID, String pName, int pStartTime, int pEndTime, String pLocation, String pDescription) {
        mvID = pID;
        mvName = pName;
        mvStartTime = pStartTime;
        mvEndTime = pEndTime;
        mvLocation = pLocation;
        mvDescription = pDescription;
    }

    public String getID() {
        return mvID;
    }
    public String getName() {
        return mvName;
    }
    public int getStartTime() {
        return mvStartTime;
    }
    public int getEndTime() {
        return mvEndTime;
    }
    public String getLocation() {
        return mvLocation;
    }
    public String getDescription() {
        return mvDescription;
    }
}
