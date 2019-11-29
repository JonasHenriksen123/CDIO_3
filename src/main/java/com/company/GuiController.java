package com.company;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.io.*;

public class GuiController {
    private GUI_Player[] player;
    private GUI gui;
    private GUI_Field[] fields;
    private boolean[] colors;
    private String[] text;

    //constructor til klassen
    public GuiController(){
        String fileName = "src/main/ressources/guiTekst.txt";
        File file = new File(fileName);
        String line;
        text = new String[32];
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int i = 0; (line = bufferedReader.readLine()) != null; i++){
                text[i] = line;
            }

            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex){
            System.out.println("error reading file '" + fileName + "'");
        }

        fields = new GUI_Field[24];

        GUI_Start start = new GUI_Start();
        start.setTitle(text[0]);
        start.setSubText(text[1]);
        start.setDescription(text[2]);
        fields[0] = start;

        GUI_Street burger = new GUI_Street();
        burger.setTitle(text[3]);
        burger.setSubText("1$");
        burger.setDescription(text[3]);
        burger.setBackGroundColor(Color.blue);
        fields[1] = burger;

        GUI_Street pizzeriaet = new GUI_Street();
        pizzeriaet.setTitle(text[4]);
        pizzeriaet.setSubText("1$");
        pizzeriaet.setDescription(text[4]);
        pizzeriaet.setBackGroundColor(Color.blue);
        fields[2] = pizzeriaet;

        GUI_Chance chance1 = new GUI_Chance();
        chance1.setTitle(text[5]);
        chance1.setSubText("");
        chance1.setDescription(text[6]);
        fields[3] = chance1;

        GUI_Street slikbutikken = new GUI_Street();
        slikbutikken.setTitle(text[7]);
        slikbutikken.setSubText("1$");
        slikbutikken.setDescription(text[7]);
        slikbutikken.setBackGroundColor(Color.orange);
        fields[4] = slikbutikken;

        GUI_Street iskiosken = new GUI_Street();
        iskiosken.setTitle(text[8]);
        iskiosken.setSubText("1$");
        iskiosken.setDescription(text[8]);
        iskiosken.setBackGroundColor(Color.orange);
        fields[5] = iskiosken;

        GUI_Jail faengsel = new GUI_Jail();
        faengsel.setTitle(text[9]);
        faengsel.setSubText(text[9]);
        faengsel.setDescription(text[10]);
        fields[6] = faengsel;

        GUI_Street museet = new GUI_Street();
        museet.setTitle(text[11]);
        museet.setSubText("2$");
        museet.setDescription(text[11]);
        museet.setBackGroundColor(Color.magenta);
        fields[7] = museet;

        GUI_Street biblio = new GUI_Street();
        biblio.setTitle(text[12]);
        biblio.setSubText("2$");
        biblio.setDescription(text[12]);
        biblio.setBackGroundColor(Color.magenta);
        fields[8] = biblio;

        GUI_Chance chance2 = new GUI_Chance();
        chance2.setTitle(text[5]);
        chance2.setSubText("");
        chance2.setDescription(text[6]);
        fields[9] = chance2;

        GUI_Street skate = new GUI_Street();
        skate.setTitle(text[13]);
        skate.setSubText("2$");
        skate.setDescription(text[13]);
        skate.setBackGroundColor(Color.red);
        fields[10] = skate;

        GUI_Street swim = new GUI_Street();
        swim.setTitle(text[14]);
        swim.setSubText("2$");
        swim.setDescription(text[14]);
        swim.setBackGroundColor(Color.red);
        fields[11] = swim;

        GUI_Refuge parkering = new GUI_Refuge();
        parkering.setTitle(text[15]);
        parkering.setSubText(text[15]);
        parkering.setDescription(text[16]);
        fields[12] = parkering;

        GUI_Street spil = new GUI_Street();
        spil.setTitle(text[17]);
        spil.setSubText("3$");
        spil.setDescription(text[17]);
        spil.setBackGroundColor(Color.YELLOW);
        fields[13] = spil;

        GUI_Street bio = new GUI_Street();
        bio.setTitle(text[18]);
        bio.setSubText("3$");
        bio.setDescription(text[18]);
        bio.setBackGroundColor(Color.YELLOW);
        fields[14] = bio;

        GUI_Chance chance3 = new GUI_Chance();
        chance3.setTitle(text[5]);
        chance3.setSubText("");
        chance3.setDescription(text[6]);
        fields[15] = chance3;

        GUI_Street lege = new GUI_Street();
        lege.setTitle(text[19]);
        lege.setSubText("3$");
        lege.setDescription(text[19]);
        lege.setBackGroundColor(Color.GREEN);
        fields[16] = lege;

        GUI_Street dyr = new GUI_Street();
        dyr.setTitle(text[20]);
        dyr.setSubText("3$");
        dyr.setDescription(text[20]);
        dyr.setBackGroundColor(Color.GREEN);
        fields[17] = dyr;

        GUI_Tax move = new GUI_Tax();
        move.setTitle(text[21]);
        move.setSubText("");
        move.setDescription(text[22]);
        fields[18] = move;

        GUI_Street bowling = new GUI_Street();
        bowling.setTitle(text[23]);
        bowling.setSubText("4$");
        bowling.setDescription(text[23]);
        bowling.setBackGroundColor(Color.PINK);
        fields[19] = bowling;

        GUI_Street zoo = new GUI_Street();
        zoo.setTitle(text[24]);
        zoo.setSubText("4$");
        zoo.setDescription(text[24]);
        zoo.setBackGroundColor(Color.PINK);
        fields[20] = zoo;

        GUI_Chance chance4 = new GUI_Chance();
        chance4.setTitle(text[5]);
        chance4.setSubText("");
        chance4.setDescription(text[6]);
        fields[21] = chance4;

        GUI_Street vand = new GUI_Street();
        vand.setTitle(text[25]);
        vand.setSubText("4$");
        vand.setDescription(text[25]);
        vand.setBackGroundColor(Color.cyan);
        fields[22] = vand;

        GUI_Street strand = new GUI_Street();
        strand.setTitle(text[26]);
        strand.setSubText("4$");
        strand.setDescription(text[26]);
        strand.setBackGroundColor(Color.cyan);
        fields[23] = strand;

        gui = new GUI(fields, Color.white);

        makeColors();
    }

    //skriver på et felt hvem ejeren er
    public void setOwner(String name, int field){
        fields[field - 1].setSubText(name);
    }

    //opretter et GUI_Player array efter hvor mange spillere der valgt
    public void setPlayers(int players){
        player = new GUI_Player[players];
    }

    //opretter en spiller med et givent navn, nummer og farve
    public void createSpiller(int number, String name, int balance, Color color){
        //opretter først en bil med valgt farve
        GUI_Car car = new GUI_Car(color, color, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);

        //laver spilleren
        player[number - 1] = new GUI_Player(name, balance, car);

        //tilføjer spilleren til GUIen og sætter bilens placering til felt 1
        gui.addPlayer(player[number -1]);
        fields[0].setCar(player[number - 1], true);
    }

    //sætter en given spillers balance til et givent beløb
    public void setBalance(int number, int balance){
        player[number -1].setBalance(balance);
    }

    //flytter en spillers bil til et nyt felt og fjerner den fra det forgående felt
    public void moveCar(int number, int removeFrom, int moveTo){
        fields[removeFrom - 1].setCar(player[number -1], false);
        fields[moveTo -1 ].setCar(player[number -1], true);
    }

    //viser værdien af terningskastet på guien
    public void setDie(int value){
        gui.setDie(value);
    }

    //viser et chancekorts tekst
    public void displayChangecard(String txt){
        gui.displayChanceCard(txt);
    }

    //beder et givent spillernummer om deres navn og venter på svar
    public String getUserName(String txt){
        return gui.getUserString(txt);
    }

    //viser spilleren en tekst og venter på de trykker på en knap
    public void getInput(String txt){
        gui.getUserButtonPressed(txt, text[31]);
    }

    //spørger spillerne hvor mange spillere de er og venter på svar
    public int getTotalPlayers(String txt){
        return Integer.parseInt(gui.getUserSelection(txt, "2", "3", "4"));
    }

    //beder et givent spillernumme om at valge en farve og venter på svar
    public Color getPlayerColor(String txt, int numberPlayers){
        int i = 0;

        //laver et String array og tilføjer mulige farver til det
        String[] color= new String[4];
        if (colors[3]){
            i++;
            color[3] = text[27];
        }
        if (colors[2]){
            i++;
            color[2] = text[28];
        }
        if (colors[1]){
            i++;
            color[1] = text[29];
        }
        if (colors[0]){
            i++;
            color[0] = text[30];
        }

        //tilføjer de ledige farver til et nyt string array med størrelsen antal ledige farver
        String[] options = new String[i];
        int choices = i;
        for (int counter = 0; counter < 4; counter++){
            if (colors[counter]){
                options[i - 1] = color[counter];
                i--;
            }
        }

        //beder spilleren om at vælge en farve, mulige farver kommer af hvor mange ledige farver der er og kun ledige farver bliver vist
        //hvis der kun er en farve bliver denne aitomatisk valgt for spilleren
        int choice = 0;
        String colorchoice;
        switch (choices){
            case 4:
                colorchoice = gui.getUserSelection(txt, String.valueOf(options[3]), String.valueOf(options[2]), String.valueOf(options[1]), String.valueOf(options[0]));
                break;
            case 3:
                colorchoice = gui.getUserSelection(txt, String.valueOf(options[2]), String.valueOf(options[1]), String.valueOf(options[0]));
                break;
            case 2:
                colorchoice = gui.getUserSelection(txt, String.valueOf(options[1]), String.valueOf(options[0]));
                break;
            default:
                colorchoice = options[0];
                break;
        }

        //ser hvilken farve spilleren har valgt
        if (colorchoice.equals(text[27])){
            choice = 4;
        } if (colorchoice.equals(text[28])){
            choice = 3;
        } if (colorchoice.equals(text[29])){
            choice = 2;
        } if (colorchoice.equals(text[30])){
            choice =1;
        }

        //returner hvilken farve der er valgt
        switch (choice){
            case 4:
                colors[3] = false;
                return Color.red;
            case 3:
                colors[2] = false;
                return Color.blue;
            case 2:
                colors[1] = false;
                return Color.pink;
            default:
                colors[0] = false;
                return Color.green;
        }
    }

    //opretter et boolean array, som bruges til at se om farver allerede er valgt
    private void makeColors(){
        colors = new boolean[4];
        colors[3] = true;
        colors[2] = true;
        colors[1] = true;
        colors[0] = true;
    }

    //viser en tekst til spilleren hvor der står hvem der har vundet spillet
    public void showWinner(String txt){
        gui.displayChanceCard(txt);
    }

    //Bruges til junit test
    public String getName(int number){
        return player[number - 1].getName();
    }

    //Bruges til junit test
    public int getBalance(int number){
        return player[number -1].getBalance();
    }

    //Bruges til junit test
    public Color getCarColor(int number){
        return (player[number -1].getCar()).getPrimaryColor();
    }

}