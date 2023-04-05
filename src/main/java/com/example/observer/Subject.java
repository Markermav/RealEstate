package com.example.observer;

import java.util.ArrayList;
/**
 * 
 * @author pablo, kalinga
 *
 */
public class Subject {
	ArrayList<Observer> observers;
	public Subject() {
		observers = new ArrayList<Observer>();
	}
	public void addObserver(Observer o) {
		observers.add(o);
	}
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	public void broadcastEvent(Object arg) {
		for(Observer o: observers) {
			o.update(arg);
		}
	}
};



