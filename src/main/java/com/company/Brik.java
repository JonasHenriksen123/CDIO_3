package com.company;

public class Brik {

    private int location;

    public Brik() {
        location = 1;
    }

    public void setBrikLocation(int modifier){
        if ((location += modifier) > 24){
            location += modifier - 24;
        } else {
          location += modifier;
        }
    }

    public int getBrikLocation(){
        return location;
    }
}





