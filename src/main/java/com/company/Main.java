package com.company;

import java.awt.*;
import java.io.*;

public class Main {
    private static GuiController guiController = new GuiController();
    private static int numberPlayers;
    private static MonopolySpil monopolySpil = new MonopolySpil();
    private static String[] text;
    private static int banktrupcyCount = 0;

    public static void main(String[] args){
        getGameText();
        setUpGame();
        playGame();
        endGame();
    }

    private static void endGame() {
        int highestValue = Math.max(monopolySpil.getPlayerBalance(1), monopolySpil.getPlayerBalance(2));
        for (int i = 3; i <= numberPlayers; i++){
            highestValue = Math.max(monopolySpil.getPlayerBalance(i), highestValue);
        }

        int winners = 0;
        for (int i = 1; i <= numberPlayers; i++){
            if (monopolySpil.getPlayerBalance(i) == highestValue){
                winners++;
            }
        }

        String[] winnerNames = new String[winners];
        int i = winners;
        switch (winners){
            case 3:
                if (monopolySpil.getPlayerBalance(4) == highestValue){
                    winnerNames[i - 1] = monopolySpil.getPlayerName(4);
                    i--;
                }
            case 2:
                if (monopolySpil.getPlayerBalance(3) == highestValue) {
                    winnerNames[i - 1] = monopolySpil.getPlayerName(3);
                    i--;
                }
            default:
                if (monopolySpil.getPlayerBalance(2) == highestValue) {
                    winnerNames[i - 1] = monopolySpil.getPlayerName(2);
                    i--;
                }
                if (monopolySpil.getPlayerBalance(1) == highestValue) {
                    winnerNames[i - 1] = monopolySpil.getPlayerName(1);
                }
        }

        switch (winners) {
            case 3:
                guiController.showWinner(winnerNames[0] + ", " + winnerNames[1] + " " + text[20] + " " + winnerNames[0] + " " + text[19] + " " + highestValue);
            case 2:
                guiController.showWinner(winnerNames[0] + " " + text[20] + " " + winnerNames[1] + " " + text[19] + " " + highestValue);
            default:
                guiController.showWinner(winnerNames[0] + " " + text[19] + " " + highestValue);
        }
    }

    private static void playGame(){
        for (int playerTurn = 1; playerTurn < 5; playerTurn++){
            guiController.getInput(text[4] + " " + monopolySpil.getPlayerName(playerTurn) + text[5]);
            inJail(playerTurn);
            if (banktrupcyCount != 0) { break; }

            monopolySpil.rollDice();
            guiController.setDie(monopolySpil.getDiceValue());
            guiController.getInput(text[11] + " " + monopolySpil.getDiceValue());
            moveCar(playerTurn);
            passStart(playerTurn);

            fieldAction(playerTurn);
            if (banktrupcyCount != 0) { break; }
            passStart(playerTurn);

            playerTurn = turnReset(playerTurn);
        }
    }

    private  static void passStart(int playerTurn){
        if (monopolySpil.getPassedStart(playerTurn)){
            monopolySpil.addToPlayerBalance(playerTurn, 2);
            updateBalances();
        }
    }

    private static void inJail(int playerTurn){
       if (monopolySpil.getInJail(playerTurn)){
           if (monopolySpil.getFreeOutOfJail(playerTurn)){
               guiController.getInput(text[13] + text[14]);
               monopolySpil.setFreeOutJail(playerTurn, false);
           }
           else {
               if (checkBanktrupcy(playerTurn, 1)) {
                   guiController.getInput(text[13] + text[15]);
                   monopolySpil.addToPlayerBalance(playerTurn, -1);
                   updateBalances();
               } else {
                   monopolySpil.setBalance(playerTurn, 0);
                   banktrupcyCount = 1;
                   monopolySpil.setBalance(playerTurn, 0);
                   updateBalances();
                   guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[16]);
               }
           }
           monopolySpil.setInJail(playerTurn, false);
       }
    }

    private static void moveCar(int playerTurn){
        int moveFrom = monopolySpil.getPlayerBrik(playerTurn);
        monopolySpil.setPlayerBrik(playerTurn, monopolySpil.getDiceValue());
        int moveTo = monopolySpil.getPlayerBrik(playerTurn);
        guiController.moveCar(playerTurn, moveFrom, moveTo);
    }

    private static void moveCar(int playerTurn, int moveto, int moveFrom){
        guiController.moveCar(playerTurn, moveFrom, moveto);
    }

    private static int turnReset(int playerTurn){
        if (playerTurn == numberPlayers){
            return 0;
        } else {
            return playerTurn;
        }
    }

    private static void fieldAction(int playerTurn){
        switch (monopolySpil.getPlayerBrik(playerTurn)){
            //Handling for felter der er veje.
            case 2: case 3: case 5: case 6: case 8: case 9: case 11: case 12: case 14: case 15: case 17: case 18: case 20: case 21: case 23: case 24:
                vejFelter(playerTurn);
                break;
            case 4: case 10: case 16: case 22:
                chancekort(playerTurn);
                break;
            case 19:
                faengsel(playerTurn);
            default:
                break;
        }
    }

    private static  void faengsel(int playerTurn){
        guiController.getInput(text[7]);
        monopolySpil.setLocation(playerTurn, 7);
        guiController.moveCar(playerTurn, 19, 7);
        monopolySpil.setInJail(playerTurn, true);
    }

    private static void chancekort(int playerTurn){
        int chancekort = monopolySpil.traekChancekort();
                guiController.displayChangecard(monopolySpil.getChancekortTekst(chancekort));
        switch (chancekort){
            case 1:
                int from = monopolySpil.getPlayerBrik(playerTurn);
                monopolySpil.setPlayerBrik(playerTurn, 25 - from);
                guiController.getInput(text[6]);
                moveCar(playerTurn, 1, from);
                break;
            case 2:
                if (checkBanktrupcy(playerTurn, 2)) {
                    monopolySpil.addToPlayerBalance(playerTurn, -2);
                    guiController.getInput(text[6]);
                    updateBalances();
                } else {
                    monopolySpil.setBalance(playerTurn, 0);
                    banktrupcyCount = 1;
                    monopolySpil.setBalance(playerTurn, 0);
                    updateBalances();
                    guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[21]);
                }
                break;
            case 3:
                monopolySpil.setFreeOutJail(playerTurn, true);
                guiController.getInput(text[6]);
                break;
            case 4:
                int from1 = monopolySpil.getPlayerBrik(playerTurn);
                monopolySpil.setPlayerBrik(playerTurn, 24 - from1);
                guiController.getInput(text[6]);
                moveCar(playerTurn, 24, from1);
                vejFelter(playerTurn);
                break;
            case 5:
                guiController.getInput(text[6]);
                for (int i = 1; i <= numberPlayers; i++){
                    if (i == playerTurn){
                        monopolySpil.addToPlayerBalance(playerTurn, numberPlayers - 1);
                    }
                    if (i != playerTurn){
                        if (checkBanktrupcy(i, 1)) {
                            monopolySpil.addToPlayerBalance(i, -1);
                        }
                        else {
                            monopolySpil.setBalance(playerTurn, 0);
                            banktrupcyCount = 1;
                            monopolySpil.setBalance(i, 0);
                            updateBalances();
                            guiController.getInput(monopolySpil.getPlayerName(i) + " " + text[21]);
                        }
                    }
                }
                updateBalances();
                break;
            case 6:
                guiController.getInput(text[6]);
                monopolySpil.addToPlayerBalance(playerTurn, 2);
                updateBalances();
                break;
        }
    }

    private  static void vejFelter(int playerTurn){
        int field = monopolySpil.getPlayerBrik(playerTurn);
        if (monopolySpil.getOwned(field)){
            if (monopolySpil.getOwner(field) == playerTurn){
                guiController.getInput(text[12]);
            }
            if (monopolySpil.getOwner(field) != playerTurn) {
                int owner = monopolySpil.getOwner(field);

                if (owner == monopolySpil.getOwner(monopolySpil.getSoesterFelt(field))){
                    guiController.getInput(monopolySpil.getPlayerName(owner) + " " + text[10] + 2* monopolySpil.getPris(field));
                    if (checkBanktrupcy(playerTurn,2 * monopolySpil.getPris(field))) {
                        monopolySpil.addToPlayerBalance(owner, 2 * (monopolySpil.getPris(field)));
                        monopolySpil.addToPlayerBalance(playerTurn, -2 * (monopolySpil.getPris(field)));
                        updateBalances();                    }
                    else {
                        monopolySpil.setBalance(playerTurn, 0);
                        banktrupcyCount = 1;
                        monopolySpil.setBalance(playerTurn, 0);
                        updateBalances();
                        guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[17]);
                    }
                }
                if (owner != monopolySpil.getOwner(monopolySpil.getSoesterFelt(field))) {
                    guiController.getInput(monopolySpil.getPlayerName(owner) + " " + text[9] + monopolySpil.getPris(field));
                    if (checkBanktrupcy(playerTurn,monopolySpil.getPris(field))) {
                        monopolySpil.addToPlayerBalance(owner, monopolySpil.getPris(field));
                        monopolySpil.addToPlayerBalance(playerTurn, -(monopolySpil.getPris(field)));
                        updateBalances();
                    }
                    else {
                        monopolySpil.setBalance(playerTurn, 0);
                        banktrupcyCount = 1;
                        monopolySpil.setBalance(playerTurn, 0);
                        updateBalances();
                        guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[17]);
                    }
                }
            }
        }
        if (!monopolySpil.getOwned(field)) {
            guiController.getInput(monopolySpil.getFeltNavn(field) + " " + text[8] + monopolySpil.getPris(field) );
            if (checkBanktrupcy(playerTurn,monopolySpil.getPris(field))) {
                monopolySpil.addToPlayerBalance(playerTurn, -(monopolySpil.getPris(field)));
                monopolySpil.setOwner(field, playerTurn);
                guiController.setOwner(monopolySpil.getPlayerName(playerTurn), field);
                updateBalances();
            }
            else {
                monopolySpil.setBalance(playerTurn, 0);
                banktrupcyCount = 1;
                monopolySpil.setBalance(playerTurn, 0);
                updateBalances();
                guiController.getInput(monopolySpil.getPlayerName(playerTurn) + " " + text[18]);
            }
        }
    }

    private static void setUpGame(){
        numberPlayers = guiController.getTotalPlayers(text[0]);
        monopolySpil.saetSpillere(numberPlayers);
        guiController.setPlayers(numberPlayers);
        guiController.getInput(text[1]);

        for (int i = 1; i <= numberPlayers; i++){
            monopolySpil.saetSpillernavn(guiController.getUserName(text[2] + " " + i), i);
            Color color = guiController.getPlayerColor(text[3] + " " + i, numberPlayers);
            guiController.createSpiller(i, monopolySpil.getPlayerName(i), 0, color);
        }
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

    private static boolean checkBanktrupcy(int playerTurn, int ammountDue) {
        return ammountDue < monopolySpil.getPlayerBalance(playerTurn);
    }

    private static void updateBalances(){
        for (int i = 1; i <= numberPlayers; i++){
            guiController.setBalance(i, monopolySpil.getPlayerBalance(i));
        }
    }
}