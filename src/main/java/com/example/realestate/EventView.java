package com.example.realestate;


import com.example.observer.Observer;

public class EventView implements Observer {
    @Override
    public void update(Object args) {
        String[] eventData = ((String)args).split("::");
        if(eventData == null) { return;}
        String eventType = eventData[0];
        String eventInfo = eventData[1];
        System.out.println("\n" + eventType + ":\n" + eventInfo + "\n");
    }
}
