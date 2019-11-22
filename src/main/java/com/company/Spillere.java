package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spillere {

    private int playerId;
    private String name;
    private static int createdAmount = 1;


    public static void createPlayer(){

        Scanner input = new Scanner(System.in);
        int playerAmount = input.nextInt();
        List<List> listofPlayers = new ArrayList<List>();

        ArrayList<String> playerId = new ArrayList<String>();
        ArrayList<String> playerName = new ArrayList<String>();

        for (createdAmount = 1; createdAmount == playerAmount; createdAmount++ ){

            listofPlayers.add(playerId);
            listofPlayers.add(playerName);


        }
    }
        }

    }
}



