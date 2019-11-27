package com.company;

public class Brik {
    private int location;
    private boolean passedStart;

    public Brik() {
        location = 1;
    }

    public void setBrikLocation(int modifier){
        if ((location += modifier) > 24){
            location += modifier - 24;
            passedStart = true;
        } else {
          location += modifier;
        }
    }

    public boolean getPassedStart(){
        if (passedStart) {
        passedStart = false;
        return true;
        } else {
            return false;
        }
    }

    public int getBrikLocation(){
        return location;
    }
}





