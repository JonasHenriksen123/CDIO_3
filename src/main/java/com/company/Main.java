package com.company;

import java.awt.*;
import java.io.*;

public class Main {
    private static GuiController guiController = new GuiController();
    private static int numberPlayers;
    private static MonopolySpil monopolySpil = new MonopolySpil();
    private static String[] text;
    private static int bankruptcyCount = 0;

    //kalder spillets metoder
    public static void main(String[] args){
        getGameText();
        setUpGame();
        playGame();
        endGame();
    }

    //finder den spiller der har højst score og erklærer den for vinder.
    private static void endGame() {

        //finder den score af spillerne der er højst
        int highestValue = Math.max(monopolySpil.getPlayerBalance(1), monopolySpil.getPlayerBalance(2));
        for (int i = 3; i <= numberPlayers; i++){
            highestValue = Math.max(monopolySpil.getPlayerBalance(i), highestValue);
        }

        //finder ud af om der er flere spiller med den højeste score
        int winners = 0;
        for (int i = 1; i <= numberPlayers; i++){
            if (monopolySpil.getPlayerBalance(i) == highestValue){
                winners++;
            }
        }

        //sætter navnene på vinder/vindere ind i et array
        String[] winnerNames = new String[winners];
        int i = winners;
        for (int c = 1; c <= numberPlayers; c++){
            if (monopolySpil.getPlayerBalance(c) == highestValue) {
                winnerNames[i - 1] = monopolySpil.getPlayerName(c);
                i--;
            }
        }

        //
        switch (winners) {
            case 3:
                guiController.showWinner(winnerNames[0] + ", " + winnerNames[1] + " " + text[20] + " " + winnerNames[0] + " " + text[19] + " " + highestValue);
            case 2:
                guiController.showWinner(winnerNames[0] + " " + text[20] + " " + winnerNames[1] + " " + text[19] + " " + highestValue);
            default:
                guiController.showWinner(winnerNames[0] + " " + text[19] + " " + highestValue);
        }
    }

    //styrer spilernes ture
    private static void playGame(){

        //loop der kører hver spillers tur efter valgte antal spillere
        for (int playerTurn = 1; playerTurn <= numberPlayers; playerTurn++){
            //fortæller hvilken spillers tur det er, og finder ud af om spilleren er blevet sendt i sidste tur
            guiController.getInput(text[4] + " " + monopolySpil.getPlayerName(playerTurn) + text[5]);
            inJail(playerTurn);
            if (bankruptcyCount != 0) { break; }

            //ruller terningen bevæger spilleren og betaler for at passere start, hvis det er tilfældet
            monopolySpil.rollDice();
            guiController.setDie(monopolySpil.getDiceValue());
            guiController.getInput(text[11] + " " + monopolySpil.getDiceValue());
            moveCar(playerTurn);
            passStart(playerTurn);

            //udfører en handling efter hvilket felt spilleren er landet på
            fieldAction(playerTurn);
            if (bankruptcyCount != 0) { break; }
            passStart(playerTurn);

            //Får turen til at gå fra sidste spiller til første spiller
            playerTurn = turnReset(playerTurn);
        }
    }

    //giver spiller penge for at passere start
    private  static void passStart(int playerTurn){
        if (monopolySpil.getPassedStart(playerTurn)){
            monopolySpil.addToPlayerBalance(playerTurn, 2);
            updateBalances();
        }
    }

    //ser om spiller har været sendt i fængsel og om spilleren skal betale sig fri
    private static void inJail(int playerTurn){
       if (monopolySpil.getInJail(playerTurn)){
           //hvis spilleren har et gratis fri ud af fængsel kort
           if (monopolySpil.getFreeOutOfJail(playerTurn)){
               guiController.getInput(text[13] + text[14]);
               monopolySpil.setFreeOutJail(playerTurn, false);
           }
           //hvis spilleren skal betale sig fri
           else {
               if (checkBankruptcy(playerTurn, 1)) {
                   guiController.getInput(text[13] + text[15]);
                   monopolySpil.addToPlayerBalance(playerTurn, -1);
                   updateBalances();
               } else {
                   monopolySpil.setBalance(playerTurn, 0);
                   bankruptcyCount = 1;
                   monopolySpil.setBalance(playerTurn, 0);
                   updateBalances();
                   guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[16]);
               }
           }
           monopolySpil.setInJail(playerTurn, false);
       }
    }

    //flytter spillerens brik i monopolyspil og i gui
    private static void moveCar(int playerTurn){
        int moveFrom = monopolySpil.getPlayerBrik(playerTurn);
        monopolySpil.setPlayerBrik(playerTurn, monopolySpil.getDiceValue());
        int moveTo = monopolySpil.getPlayerBrik(playerTurn);
        guiController.moveCar(playerTurn, moveFrom, moveTo);
    }

    //flytter spillerens brik i monopolyspil og i gui
    private static void moveCar(int playerTurn, int moveto, int moveFrom){
        guiController.moveCar(playerTurn, moveFrom, moveto);
    }

    //sætter turen til første spiller når sidste spillers tur er slut
    private static int turnReset(int playerTurn){
        if (playerTurn == numberPlayers){
            return 0;
        } else {
            return playerTurn;
        }
    }

    //bestem handling efter hvilken type felt der er landet på
    private static void fieldAction(int playerTurn){
        switch (monopolySpil.getPlayerBrik(playerTurn)){
            case 2: case 3: case 5: case 6: case 8: case 9: case 11: case 12: case 14: case 15: case 17: case 18: case 20: case 21: case 23: case 24:
                //Handling for felter der er veje.
                vejFelter(playerTurn);
                break;
            case 4: case 10: case 16: case 22:
                //handling for chancefelter
                chancekort(playerTurn);
                break;
            case 19:
                //handling for gå i fængsel
                faengsel(playerTurn);
            default:
                //felter der ikke påkræver en handling
                break;
        }
    }

    //sætter spilleren i fængsel
    private static  void faengsel(int playerTurn){
        guiController.getInput(text[7]);
        monopolySpil.setLocation(playerTurn, 7);
        guiController.moveCar(playerTurn, 19, 7);
        monopolySpil.setInJail(playerTurn, true);
    }

    //handlinger for chancekort
    private static void chancekort(int playerTurn){
        int chancekort = monopolySpil.traekChancekort();
                guiController.displayChangecard(monopolySpil.getChancekortTekst(chancekort));
        switch (chancekort){
            case 1:
                //ryk til start
                int from = monopolySpil.getPlayerBrik(playerTurn);
                monopolySpil.setPlayerBrik(playerTurn, 25 - from);
                guiController.getInput(text[6]);
                moveCar(playerTurn, 1, from);
                break;
            case 2:
                //betal to til banken
                if (checkBankruptcy(playerTurn, 2)) {
                    monopolySpil.addToPlayerBalance(playerTurn, -2);
                    guiController.getInput(text[6]);
                    updateBalances();
                } else {
                    monopolySpil.setBalance(playerTurn, 0);
                    bankruptcyCount = 1;
                    monopolySpil.setBalance(playerTurn, 0);
                    updateBalances();
                    guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[21]);
                }
                break;
            case 3:
                //få gratis ud af fængsel kort
                monopolySpil.setFreeOutJail(playerTurn, true);
                guiController.getInput(text[6]);
                break;
            case 4:
                //flyt til strandpromenaden
                int from1 = monopolySpil.getPlayerBrik(playerTurn);
                monopolySpil.setPlayerBrik(playerTurn, 24 - from1);
                guiController.getInput(text[6]);
                moveCar(playerTurn, 24, from1);
                vejFelter(playerTurn);
                break;
            case 5:
                //alle andre spillere giver 1 penge til den spiller hvis tur det er
                guiController.getInput(text[6]);
                for (int i = 1; i <= numberPlayers; i++){
                    if (i == playerTurn){
                        monopolySpil.addToPlayerBalance(playerTurn, numberPlayers - 1);
                    }
                    if (i != playerTurn){
                        if (checkBankruptcy(i, 1)) {
                            monopolySpil.addToPlayerBalance(i, -1);
                        }
                        else {
                            monopolySpil.setBalance(playerTurn, 0);
                            bankruptcyCount = 1;
                            monopolySpil.setBalance(i, 0);
                            updateBalances();
                            guiController.getInput(monopolySpil.getPlayerName(i) + " " + text[21]);
                        }
                    }
                }
                updateBalances();
                break;
            case 6:
                //modtag 2 penge fra banken
                guiController.getInput(text[6]);
                monopolySpil.addToPlayerBalance(playerTurn, 2);
                updateBalances();
                break;
            case 7:
                //ryk til skaterparken og få den gratis hvis det ikke har en ejer, ellers betal husleje
                int from2 = monopolySpil.getPlayerBrik(playerTurn);
                monopolySpil.setPlayerBrik(playerTurn, 11 - from2);
                guiController.getInput(text[6]);
                moveCar(playerTurn, 11, from2);
                if (!monopolySpil.getOwned(monopolySpil.getPlayerBrik(playerTurn))) {
                    monopolySpil.setOwner(monopolySpil.getPlayerBrik(playerTurn), playerTurn);
                } else {
                    vejFelter(playerTurn);
                }
                break;
        }
    }

    //handling for felter der kan ejes
    private  static void vejFelter(int playerTurn){
        int field = monopolySpil.getPlayerBrik(playerTurn);

        //handlingr hvis felter ejees af en spiller
        if (monopolySpil.getOwned(field)){

            //hvis spilleren selv ejer feltet
            if (monopolySpil.getOwner(field) == playerTurn){
                guiController.getInput(text[12]);
            }

            //hvis en anden spiller ejer feltet
            if (monopolySpil.getOwner(field) != playerTurn) {
                int owner = monopolySpil.getOwner(field);

                //hvis ejeren ejer begge felter af samme farve
                if (owner == monopolySpil.getOwner(monopolySpil.getSoesterFelt(field))){
                    guiController.getInput(monopolySpil.getPlayerName(owner) + " " + text[10] + 2* monopolySpil.getPris(field));

                    //hvis spilleren kan betale huslejen
                    if (checkBankruptcy(playerTurn,2 * monopolySpil.getPris(field))) {
                        monopolySpil.addToPlayerBalance(owner, 2 * (monopolySpil.getPris(field)));
                        monopolySpil.addToPlayerBalance(playerTurn, -2 * (monopolySpil.getPris(field)));
                        updateBalances();
                    }

                    //hvis spilleren ikke kan betale husleje
                    else {
                        monopolySpil.setBalance(playerTurn, 0);
                        bankruptcyCount = 1;
                        monopolySpil.setBalance(playerTurn, 0);
                        updateBalances();
                        guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[17]);
                    }
                }

                //hvis ejeren ikke ejer begge felter af samme farve
                if (owner != monopolySpil.getOwner(monopolySpil.getSoesterFelt(field))) {
                    guiController.getInput(monopolySpil.getPlayerName(owner) + " " + text[9] + monopolySpil.getPris(field));

                    //hvis spilleren kan betale husleje
                    if (checkBankruptcy(playerTurn,monopolySpil.getPris(field))) {
                        monopolySpil.addToPlayerBalance(owner, monopolySpil.getPris(field));
                        monopolySpil.addToPlayerBalance(playerTurn, -(monopolySpil.getPris(field)));
                        updateBalances();
                    }

                    //hvis spilleren ikke kan betale husleje
                    else {
                        monopolySpil.setBalance(playerTurn, 0);
                        bankruptcyCount = 1;
                        monopolySpil.setBalance(playerTurn, 0);
                        updateBalances();
                        guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[17]);
                    }
                }
            }
        }

        //hvis feltet ikke er ejet
        if (!monopolySpil.getOwned(field)) {
            guiController.getInput(monopolySpil.getFeltNavn(field) + " " + text[8] + monopolySpil.getPris(field) );

            //hvis spilleren kan betale for feltet
            if (checkBankruptcy(playerTurn,monopolySpil.getPris(field))) {
                monopolySpil.addToPlayerBalance(playerTurn, -(monopolySpil.getPris(field)));
                monopolySpil.setOwner(field, playerTurn);
                guiController.setOwner(monopolySpil.getPlayerName(playerTurn), field);
                updateBalances();
            }

            //hvis spilleren ikke kan betale for feltet
            else {
                monopolySpil.setBalance(playerTurn, 0);
                bankruptcyCount = 1;
                monopolySpil.setBalance(playerTurn, 0);
                updateBalances();
                guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[18]);
            }
        }
    }

    //ansvarlig for at starte spillet
    private static void setUpGame(){

        //spørger hvor mange spillere der er og sætter det i spillet
        numberPlayers = guiController.getTotalPlayers(text[0]);
        monopolySpil.saetSpillere(numberPlayers);
        guiController.setPlayers(numberPlayers);
        guiController.getInput(text[1]);

        //spørger om spillernavne og farver til spillere
        for (int i = 1; i <= numberPlayers; i++){
            monopolySpil.saetSpillernavn(guiController.getUserName(text[2] + " " + i), i);
            Color color = guiController.getPlayerColor(text[3] + " " + i, numberPlayers);
            guiController.createSpiller(i, monopolySpil.getPlayerName(i), 0, color);
        }

        //sætter spillernes balance efter hvor mange spillere der er
        for (int i = 1; i <= numberPlayers; i++){
            switch (numberPlayers){
                case 2:
                    monopolySpil.addToPlayerBalance(i, 2);
                case 3:
                    monopolySpil.addToPlayerBalance(i, 2);
                case 4:
                    monopolySpil.addToPlayerBalance(i, 16);
            }
            guiController.setBalance(i, monopolySpil.getPlayerBalance(i));
        }

    }

    //henter tekst der bruges i main
    private static void getGameText(){
        String fileName = "src/main/ressources/gameTekst.txt";
        File file = new File(fileName);
        String line;
        text = new String[22];
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
    }

    //ansvarlig for at se om en spiller kan betale et beløb
    private static boolean checkBankruptcy(int playerTurn, int ammountDue) {
        return ammountDue < monopolySpil.getPlayerBalance(playerTurn);
    }

    //opdaterer alle spilleres balance i guien
    private static void updateBalances(){
        for (int i = 1; i <= numberPlayers; i++){
            guiController.setBalance(i, monopolySpil.getPlayerBalance(i));
        }
    }
}