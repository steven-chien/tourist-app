package hk.edu.polyu.comp.hellohongkong.app;

import java.util.ArrayList;

/**
 * Created by ChiKuen on 2016/4/15.
 */
public class EventManager {
    private static EventManager svEventManager;
    private ArrayList<Event> mvEventList = new ArrayList<>();

    private EventManager() {

    }

    public static EventManager getInstance() {
        if (svEventManager == null) {
            svEventManager = new EventManager();
        }
        return svEventManager;
    }

    public void addEvent(Event pEvent) {
        mvEventList.add(pEvent);
    }

    public ArrayList<Event> getEventList() {
        return mvEventList;
    }

    public Event getEvent(String pID) {
        for (Event lvEvent : mvEventList) {
            if (pID.equals(lvEvent.getID())) {
                return lvEvent;
            }
        }
        return null;
    }

    public ArrayList<Event> getEvents(String pLocation) {
        ArrayList<Event> lvOutputList = new ArrayList<>();
        for (Event lvEvent : mvEventList) {
            if (pLocation.equals(lvEvent.getLocation())) {
                lvOutputList.add(lvEvent);
            }
        }
        return lvOutputList;
    }
}
