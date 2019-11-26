package com.company;

public class Chancekort {
    private String name;
    private boolean keepEffect;

    public Chancekort(String name, boolean keepEffect){
        this.name = name;
        this.keepEffect = keepEffect;
    }

    public String getName() {
        return name;
    }

    public boolean getKeepEffect(){
        return  keepEffect;
    }
}
