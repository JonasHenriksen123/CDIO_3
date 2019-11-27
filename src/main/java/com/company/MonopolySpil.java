package com.company;


import java.io.*;

public class MonopolySpil {

    private Spiller[] players;
    private Spilplade spilplade;
    private Terning	terning;
    private Chancekort[] chancekort = new Chancekort[6];

    public MonopolySpil() {
        spilplade = new Spilplade();
        terning = new Terning();
        lavChancekort();
    }

    public void saetSpillere(int spillere){
        players = new Spiller[spillere];
    }

    public void rollDice(){
        terning.roll();
    }

    public String getFeltNavn(int index){
        return spilplade.getFeltName(index);
    }

    public int getDiceValue(){
        return terning.getFaceValue();
    }

    public void saetSpillernavn(String navn, int spiller){
        players[spiller -1] = new Spiller(navn);
    }

    public boolean getPassedStart(int player){
        return players[player - 1].getPassedStart();
    }

    public int traekChancekort(int player){
        int i = (int) ((Math.random()*6)+1);
        if (i == 3){
            this.players[player - 1].setFreeOutJail(true);
        } else {

        }
        return i;
    }

    public String getChancekortTekst(int kort){
        return chancekort[kort - 1].getName();
    }

    public int getPlayerBalance(int player){
        return players[player - 1].getBalance();
    }

    public void setPlayersBalance(int player, int modifier){
        players[player -1].setBalance(modifier);
    }

    public void addToPlayerBalance(int player, int modifier){
        players[player - 1].addToBalance(modifier);
    }

    public String getPlayerName(int player){
        return players[player -1].getName();
    }

    public void setPlayerBrik(int player, int newField){
        players[player - 1].setBrikLocation(newField);
    }

    public int getPlayerBrik(int player){
        return players[player - 1].getBrikLocation();
    }

    public boolean getFreeOutOfJail(int player){
        return players[player - 1].getFreeOutJail();
    }

    public boolean getKeepEffect(int card){
        return chancekort[ card - 1].getKeepEffect();
    }

    public int getSoesterFelt(int index){
        return spilplade.getSoesterfelt(index);
    }

    public int getPris(int index){
        return spilplade.getPris(index);
    }

    public void setOwned(int index){
        spilplade.setOwned(index);
    }

    public boolean getOwned(int index){
        return spilplade.getOwned(index);
    }

    public void setOwner(int index, int player){
        spilplade.setOwner(index, player);
    }

    public int getOwner(int index){
        return spilplade.getOwner(index);
    }



    private void lavChancekort(){
        String fileName = "src/main/ressources/chancekortTekst.txt";
        File file = new File(fileName);
        String line;
        String[] text = new String[6];
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

        for (int i = 0; i <= 5; i++){
            boolean a;
            a = i == 2;
            chancekort[i] = new Chancekort(text[i], a);
        }
    }
}
