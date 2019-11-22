package com.company;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class Gui {

    public Gui(){
        GUI_Field[] fields = new GUI_Field[24];

        GUI_Start start = new GUI_Start();
        start.setTitle("Start");
        start.setSubText("Modtag 2$");
        start.setDescription("Modtag 2$ hver gang du passerer eller lander på start");
        fields[0] = start;

        GUI_Street burger = new GUI_Street();
        burger.setTitle("Burgerbaren");
        burger.setSubText("1$");
        burger.setDescription("Burgerbaren");
        burger.setBackGroundColor(Color.blue);
        fields[1] = burger;

        GUI_Street pizzeriaet = new GUI_Street();
        pizzeriaet.setTitle("Pizzeriaet");
        pizzeriaet.setSubText("1$");
        pizzeriaet.setDescription("Pizzeriaet");
        pizzeriaet.setBackGroundColor(Color.blue);
        fields[2] = pizzeriaet;

        GUI_Chance chance1 = new GUI_Chance();
        chance1.setTitle("Chance");
        chance1.setSubText("");
        chance1.setDescription("Træk et chancekort");
        fields[3] = chance1;

        GUI_Street slikbutikken = new GUI_Street();
        slikbutikken.setTitle("Slikbutikken");
        slikbutikken.setSubText("1$");
        slikbutikken.setDescription("Slikbutikken");
        slikbutikken.setBackGroundColor(Color.orange);
        fields[4] = slikbutikken;

        GUI_Street iskiosken = new GUI_Street();
        iskiosken.setTitle("Iskiosken");
        iskiosken.setSubText("1$");
        iskiosken.setDescription("Iskiosken");
        iskiosken.setBackGroundColor(Color.orange);
        fields[5] = iskiosken;

        GUI_Jail faengsel = new GUI_Jail();
        faengsel.setTitle("Fængsel");
        faengsel.setSubText("Fængsel");
        faengsel.setDescription("Hvis du lander her er du bare på besøg");
        fields[6] = faengsel;

        GUI_Street museet = new GUI_Street();
        museet.setTitle("Museet");
        museet.setSubText("2$");
        museet.setDescription("Museet");
        museet.setBackGroundColor(Color.magenta);
        fields[7] = museet;

        GUI_Street biblio = new GUI_Street();
        biblio.setTitle("Biblioteket");
        biblio.setSubText("2$");
        biblio.setDescription("Biblioteket");
        biblio.setBackGroundColor(Color.magenta);
        fields[8] = biblio;

        GUI_Chance chance2 = new GUI_Chance();
        chance2.setTitle("Chance");
        chance2.setSubText("");
        chance2.setDescription("Træk et chancekort");
        fields[9] = chance2;

        GUI_Street skate = new GUI_Street();
        skate.setTitle("Skaterparken");
        skate.setSubText("2$");
        skate.setDescription("Skaterparken");
        skate.setBackGroundColor(Color.red);
        fields[10] = skate;

        GUI_Street swim = new GUI_Street();
        swim.setTitle("Swimmingpoolen");
        swim.setSubText("2$");
        swim.setDescription("Swimmingpoolen");
        swim.setBackGroundColor(Color.red);
        fields[11] = swim;

        GUI_Refuge parkering = new GUI_Refuge();
        parkering.setTitle("Gratis parkering");
        parkering.setSubText("Gratis parkering");
        parkering.setDescription("Du behøver ikke gøre noget, snup dig en pause");
        fields[12] = parkering;

        GUI_Street spil = new GUI_Street();
        spil.setTitle("Spillehallen");
        spil.setSubText("3$");
        spil.setDescription("Spillehallen");
        spil.setBackGroundColor(Color.YELLOW);
        fields[13] = spil;

        GUI_Street bio = new GUI_Street();
        bio.setTitle("Biografen");
        bio.setSubText("3$");
        bio.setDescription("Biografen");
        bio.setBackGroundColor(Color.YELLOW);
        fields[14] = bio;

        GUI_Chance chance3 = new GUI_Chance();
        chance3.setTitle("Chance");
        chance3.setSubText("");
        chance3.setDescription("Træk et chancekort");
        fields[15] = chance3;

        GUI_Street lege = new GUI_Street();
        lege.setTitle("Legetøjsbutikken");
        lege.setSubText("3$");
        lege.setDescription("Legetøjsbutikken");
        lege.setBackGroundColor(Color.GREEN);
        fields[16] = lege;

        GUI_Street dyr = new GUI_Street();
        dyr.setTitle("Dyrehandlen");
        dyr.setSubText("3$");
        dyr.setDescription("Dyrehandlen");
        dyr.setBackGroundColor(Color.GREEN);
        fields[17] = dyr;

        GUI_Tax move = new GUI_Tax();
        move.setTitle("Gå i fængsel");
        move.setSubText("");
        move.setDescription("Gå lige i fængsel! Du passerer ikke START. Du modtager ikke 2$. I starten af din næste tur skal du betale " +
                "1$ eller bruge 'Du løslades uden omkostninger'-kortet, hvis du har det. Kast derefter terningen, og ryk som normalt. " +
                "Du kan godt modtage husleje, mens du er i fængsel");
        fields[18] = move;

        GUI_Street bowling = new GUI_Street();
        bowling.setTitle("Bowlinghallen");
        bowling.setSubText("4$");
        bowling.setDescription("Bowlinghallen");
        bowling.setBackGroundColor(Color.PINK);
        fields[19] = bowling;

        GUI_Street zoo = new GUI_Street();
        zoo.setTitle("Zoo");
        zoo.setSubText("4$");
        zoo.setDescription("Zoo");
        zoo.setBackGroundColor(Color.PINK);
        fields[20] = zoo;

        GUI_Chance chance4 = new GUI_Chance();
        chance4.setTitle("Chance");
        chance4.setSubText("");
        chance4.setDescription("Træk et chancekort");
        fields[21] = chance4;

        GUI_Street vand = new GUI_Street();
        vand.setTitle("Vandlandet");
        vand.setSubText("4$");
        vand.setDescription("Vandlandet");
        vand.setBackGroundColor(Color.cyan);
        fields[22] = vand;

        GUI_Street strand = new GUI_Street();
        strand.setTitle("Strandpromenaden");
        strand.setSubText("4$");
        strand.setDescription("Strandpromenaden");
        strand.setBackGroundColor(Color.cyan);
        fields[23] = strand;

        GUI gui = new GUI(fields, Color.darkGray);
    }
}
