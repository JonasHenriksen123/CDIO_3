package com.company;

public class Felter {
    
private String navn;
private Felter nextFelt;
private int index;

public Felter(String navn, int index){
    this.navn = navn;
    this.index = index;

}
    public void setNextFelt( Felter s )
    {
        nextFelt = s;
    }

    public Felter getNextFelt(  )
    {
        return nextFelt;
    }

    public String getName(  )
    {
        return navn;
    }

    public int getIndex()
    {
        return index;
    }

    }

