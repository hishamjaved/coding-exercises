package com.design.patterns;

import java.util.Observable;
import java.util.Observer;

/**
 * The Observer is known as a behavioural pattern, as it's used to form relationships between objects at runtime
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Screen screen = new Screen();
        DataStore dataStore = new DataStore();
        //register observer
        dataStore.addObserver(screen);
        //send a notification
        dataStore.notifyObservers();
        dataStore.setData("Hello");
        dataStore.notifyObservers();
    }
}

class DataStore extends Observable {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        //mark the observable as changed
        setChanged();
    }
}

class Screen implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        //act on the update
        System.out.println("Data is changed: "+ ((DataStore)o).getData());
    }
}



