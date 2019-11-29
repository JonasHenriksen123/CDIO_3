package com.company;


import java.io.*;

public class MonopolySpil {

    private Spiller[] players;
    private Spilplade spilplade;
    private Terning	terning;
    private Chancekort[] chancekort = new Chancekort[6];

    //constructor
    public MonopolySpil() {
        spilplade = new Spilplade();
        terning = new Terning();
        lavChancekort();
    }

    //sætter ens spillers balance til et givet beløb
    public void setBalance(int player, int modifier){
        players[player -1].setBalance(modifier);
    }

    //laver et spiller array efter hvor mange spillere der er valgt
    public void saetSpillere(int spillere){
        players = new Spiller[spillere];
    }

    //ruller terningen
    public void rollDice(){
        terning.roll();
    }

    //henter et givet felts navn
    public String getFeltNavn(int index){
        return spilplade.getFeltName(index);
    }

    //henter den nuværende ansigtsværdi af terningen
    public int getDiceValue(){
        return terning.getFaceValue();
    }

    //opretter et givent spillernummer og giver dem et givent navn
    public void saetSpillernavn(String navn, int spiller){
        players[spiller -1] = new Spiller(navn);
    }

    //henter information om at spilleren har passeret start
    public boolean getPassedStart(int player){
        return players[player - 1].getPassedStart();
    }

    //vælger en tilfældig værdi mellem 1 og 7, begge ender inkluderet
    public int traekChancekort(){
        return (int) ((Math.random()*7)+1);
    }

    //henter et givent chancekorts tekst
    public String getChancekortTekst(int kort){
        return chancekort[kort - 1].getName();
    }

    //henter en given spillers balance
    public int getPlayerBalance(int player){
        return players[player - 1].getBalance();
    }

    //tilføjer et givent beløb til spillerens balance
    public void addToPlayerBalance(int player, int modifier){
        players[player - 1].addToBalance(modifier);
    }

    //henter en given spillers navn
    public String getPlayerName(int player){
        return players[player -1].getName();
    }

    //ændrer en spillers briks position til et givent felt
    public void setPlayerBrik(int player, int newField){
        players[player - 1].setBrikLocation(newField);
    }

    //henter en given spillers briks position
    public int getPlayerBrik(int player){
        return players[player - 1].getBrikLocation();
    }

    //sætter information om du har et get out of jail kort
    public void setFreeOutJail(int player , boolean modifier){
        players[player - 1].setFreeOutJail(modifier);
    }

    //henter information fra setFreeOutJail og løslader spilleren hvis sand
    public boolean getFreeOutOfJail(int player){
        return players[player - 1].getFreeOutJail();
    }

    //henter feltværdien for det andet felt som har samme farve
    public int getSoesterFelt(int index){
        return spilplade.getSoesterfelt(index);
    }

    //henter et felts pris
    public int getPris(int index){
        return spilplade.getPris(index);
    }

    //returnerer om et felt er ejet af en spiller
    public boolean getOwned(int index){
        return spilplade.getOwned(index);
    }

    //Sætter et felt til at være ejet af en given spiller
    public void setOwner(int index, int player){
        spilplade.setOwner(index, player);
    }

    //henter ejeren af et givent felt
    public int getOwner(int index){
        return spilplade.getOwner(index);
    }

    //sætter en given spillers brik til et givent felt, uden at tage højde for om de passerer start
    public void setLocation(int player, int location){
        players[player -1].setLocation(location);
    }

    //sætter metoden sand eller falsk om spilleren har landet på gå i fængsel
    public void setInJail(int player, boolean modifier){
        players[player -1].setInJail(modifier);
    }

    //henter information fra setInJail og sætter spilleren i fængsel
    public boolean getInJail(int player){
        return players[player -1].getInJail();
    }

    //henter teksten til chancekortene
    private void lavChancekort(){
        String fileName = "src/main/ressources/chancekortTekst.txt";
        File file = new File(fileName);
        String line;
        String[] text = new String[7];
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
            chancekort[i] = new Chancekort(text[i]);
        }
    }
}
