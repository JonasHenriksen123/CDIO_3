package com.company;

import org.junit.Assert;

import java.awt.*;

class GuiControllerTest {

    @org.junit.jupiter.api.Test
    void createSpillerName() {
        GuiController guiController = new GuiController();
        guiController.setPlayers(1);
        guiController.createSpiller(1, "Bjarne", 20 , Color.RED);
        Assert.assertEquals(guiController.getName(1), "Bjarne");
    }

    @org.junit.jupiter.api.Test
   void createSpillerBalance() {
        GuiController guiController = new GuiController();
        guiController.setPlayers(1);
        guiController.createSpiller(1, "Bjarne", 20 , Color.RED);
        Assert.assertEquals(guiController.getBalance(1), 20);
   }

    @org.junit.jupiter.api.Test
    void createSpillerColor(){
        GuiController guiController = new GuiController();
        guiController.setPlayers(1);
        guiController.createSpiller(1, "Bjarne", 20, Color.RED);
        Assert.assertEquals(guiController.getCarColor(1), Color.RED);
    }

    @org.junit.jupiter.api.Test
    void setBalance(){
        GuiController guiController = new GuiController();
        guiController.setPlayers(1);
        guiController.createSpiller(1, "Bjarne", 20, Color.RED);
        guiController.setBalance(1, 25);
        Assert.assertEquals(guiController.getBalance(1), 25);
    }

}