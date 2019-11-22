package com.company;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        Gui gui = new Gui();

        gui.setPlayers(1);
        gui.createSpiller(1,"Jonas", 20, Color.orange);
    }
}
