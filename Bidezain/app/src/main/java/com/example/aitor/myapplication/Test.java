package com.example.aitor.myapplication;

import java.util.Observable;

public class Test extends Observable {
    String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
        this.setChanged();
        this.notifyObservers();
    }
}
