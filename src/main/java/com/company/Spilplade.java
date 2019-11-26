package com.company;

import java.io.*;

public class Spilplade {
    private static final int SIZE = 24;
    private Felt[] Felter = new Felt[SIZE];

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

        for (int i = 1; i <= SIZE; i++) {
            bygFelt(text[i -1], i);
        }
    }

    private void bygFelt(String name, int i) {
        Felter[i - 1] = new Felt(name);
    }

    private String getFeltName(int index){
        return Felter[index - 1].getName();
    }
}