package com.company;

import org.junit.Assert;

import java.awt.*;

class GuiTest {

    @org.junit.jupiter.api.Test
    void createSpillerName() {
        Gui gui = new Gui();
        gui.setPlayers(1);
        gui.createSpiller(1, "Bjarne", 20 , Color.RED);
        Assert.assertEquals(gui.getName(1), "Bjarne");
    }

    @org.junit.jupiter.api.Test
   void createSpillerBalance() {
        Gui gui = new Gui();
        gui.setPlayers(1);
        gui.createSpiller(1, "Bjarne", 20 , Color.RED);
        Assert.assertEquals(gui.getBalance(1), 20);
   }

    @org.junit.jupiter.api.Test
    void createSpillerColor(){
        Gui gui = new Gui();
        gui.setPlayers(1);
        gui.createSpiller(1, "Bjarne", 20, Color.RED);
        Assert.assertEquals(gui.getCarColor(1), Color.RED);
    }

    @org.junit.jupiter.api.Test
    void setBalance(){
        Gui gui = new Gui();
        gui.setPlayers(1);
        gui.createSpiller(1, "Bjarne", 20, Color.RED);
        gui.setBalance(1, 25);
        Assert.assertEquals(gui.getBalance(1), 25);
    }


}