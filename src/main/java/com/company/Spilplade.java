package com.company;

public class Spilplade {
    private static final int SIZE = 24;
    private Felt[] Felter = new Felt[SIZE];

    public Spilplade() {
        bygFelter();
    }

    private void bygFelter() {
        for (int i = 1; i <= SIZE; i++) {
            bygFelt(i);
        }
    }

    private void bygFelt(int i) {
        Felter[i - 1] = new Felt("Felt ");
    }

    private String getFeltName(int index){
        return Felter[index - 1].getName();
    }
}