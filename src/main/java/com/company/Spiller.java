package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spiller {

    private int playerId;
    private String name;
    private Pung pung;

    public void createPlayer(int playerId, String name){
        pung = new Pung();
        this.playerId = playerId;
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

    public int getPlayerId(){
        return playerId;
    }

    public String toString(){
        return playerId + name + pung;
    }
}



