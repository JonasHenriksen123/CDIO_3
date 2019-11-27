package com.company;

import java.io.*;

public class Spilplade {
    private static final int SIZE = 24;
    private Felt[] felter = new Felt[SIZE];

    public Spilplade() {
        bygFelter();
    }

    private void bygFelter() {
        String fileName = "src/main/ressources/feltTekst.txt";
        File file = new File(fileName);
        String line;
        String[] text = new String[24];
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

        int soesterfelt = 0;
        int pris = 0;
        for (int i = 1; i <= SIZE; i++) {
            if (i % 3 == 0) {
                soesterfelt = i - 1;
            } if ((i + 1) % 3 == 0) {
                soesterfelt = i + 1;
            }

            if (soesterfelt + i == 5 || soesterfelt + i == 11){
                pris = 1;
            } if (soesterfelt + i == 17 || soesterfelt + i == 23){
                pris = 2;
            } if (soesterfelt + i == 29 || soesterfelt + i == 35){
                pris = 3;
            } if (soesterfelt + i == 41 || soesterfelt + i == 47){
                pris = 4;
            }

            felter[i - 1] = new Felt(text[i - 1], soesterfelt, pris);
        }
    }

    public String getFeltName(int index){
        return felter[index - 1].getName();
    }

    public int getSoesterfelt(int index){
        return felter[index - 1].getSoesterFelt();
    }

    public int getPris(int index){
        return felter[index - 1].getPris();
    }

    public boolean getOwned(int index){
        return felter[index - 1].getOwned();
    }

    public void setOwner(int index, int player){
        felter[index - 1].setOwner(player);
    }

    public int getOwner(int index){
        return felter[index - 1].getOwner();
    }
}