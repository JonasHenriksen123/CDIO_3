package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spiller {

    private String name;
    private Pung pung;
    private Brik brik = new Brik();
    private boolean freeOutJail = false;

    public Spiller(String name){
        pung = new Pung();
        this.name = name;
    }

    public void setBalance(int modifier){
        pung.setBalance(modifier);
    }

    public void addToBalance(int modifier){
        pung.addToBalance(modifier);
    }

    public int getBalance(){
        return pung.getBalance();
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name + pung;
    }

    public void setBrikLocation(int modifier) {
        brik.setBrikLocation(modifier);
    }

    public int getBrikLocation(){
        return brik.getBrikLocation();
    }

    public boolean getFreeOutJail(){
        return freeOutJail;
    }

    public  void setFreeOutJail(Boolean modify){
        freeOutJail = modify;
    }
}



