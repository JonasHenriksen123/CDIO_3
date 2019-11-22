package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spillere {

    public class Player
    {
        private String name;
        private Brik  brik;
        private Spilplade  spilplade;
        private Terning[]  terning;

        public Player(String name, Die[] dice, Board board)
        {
            this.name = name;
            this.terning = dice;
            this.spilplade = board;
            brik = new Brik(board.getStartSquare());
        }

        public void takeTurn()
        {
            // roll dice
            int rollTotal = 0;
            for (int i = 0; i < dice.length; i++)
            {
                dice[i].roll();
                rollTotal += dice[i].getFaceValue();
            }

            Felter newLoc = board.getSquare(piece.getLocation(), rollTotal);
            piece.setLocation(newLoc);

        }

        public Felter getLocation()
        {
            return brik.getLocation();
        }

        public String getName()
        {
            return name;
        }

    }
}



