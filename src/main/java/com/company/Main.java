package com.company;

import java.awt.*;
import java.io.*;

public class Main {
    private static GuiController guiController = new GuiController();
    private static int numberPlayers;
    private static MonopolySpil monopolySpil = new MonopolySpil();
    private static String[] text;

    public static void main(String[] args){
        getGameText();
        setUpGame();
        playGame();
    }

    private static void playGame(){
        for (int playerTurn = 1; playerTurn < 5; playerTurn++){
            monopolySpil.rollDice();
            monopolySpil.setPlayerBrik(playerTurn, monopolySpil.getDiceValue());
            fieldAction(playerTurn);
        }
    }

    private static void fieldAction(int playerturn){
        switch (monopolySpil.getPlayerBrik(playerturn)){
            //Handling for felter der er veje.
            case 2: case 3: case 5: case 6: case 8: case 9: case 11: case 12: case 14: case 15: case 17: case 18: case 20: case 21: case 23: case 24:
                if (monopolySpil.getOwned(monopolySpil.getPlayerBrik(playerturn))){
                    if (monopolySpil.getOwner(monopolySpil.getPlayerBrik(playerturn)) == playerturn){
                        break;
                    } else {
                     if (monopolySpil.getOwner(monopolySpil.getPlayerBrik(playerturn))
                             == monopolySpil.getOwner(monopolySpil.getSoesterFelt(monopolySpil.getPlayerBrik(playerturn)))){
                         monopolySpil.addToPlayerBalance(monopolySpil.getOwner(monopolySpil.getPlayerBrik(playerturn)),
                                 2 * (monopolySpil.getPris(monopolySpil.getPlayerBrik(playerturn))));
                         monopolySpil.addToPlayerBalance(playerturn, -2 * (monopolySpil.getPris(monopolySpil.getPlayerBrik(playerturn))));
                     } else {
                         monopolySpil.addToPlayerBalance(monopolySpil.getOwner(monopolySpil.getPlayerBrik(playerturn)),
                                 monopolySpil.getPris(monopolySpil.getPlayerBrik(playerturn)));
                         monopolySpil.addToPlayerBalance(playerturn, - (monopolySpil.getPris(monopolySpil.getPlayerBrik(playerturn))));
                     }
                    }
                } else {
                    monopolySpil.addToPlayerBalance(playerturn, - (monopolySpil.getPris(monopolySpil.getPlayerBrik(playerturn))));
                    monopolySpil.setOwned(monopolySpil.getPlayerBrik(playerturn));
                    monopolySpil.setOwner(monopolySpil.getPlayerBrik(playerturn), playerturn);
                    guiController.setOwner(monopolySpil.getPlayerName(playerturn), monopolySpil.getPlayerBrik(playerturn));
                    guiController.setBalance(playerturn, monopolySpil.getPlayerBalance(playerturn));
                }
                break;
            default:
                break;
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
        text = new String[4];
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
}
