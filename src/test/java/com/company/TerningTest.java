package com.company;

public class TerningTest {
    Terning TerningTest = new Terning();

    //Ruller terningen 1000 gangen, og tjekker at alle slagene ligger inde for 1-6

    @org.junit.Test
    public void getFaceValue() {
        for(int i = 0; i < 1000; i++){
        TerningTest.roll();
        int a = TerningTest.getFaceValue();
        junit.framework.Assert.assertTrue(a==1 || a==2 || a==3 || a==4 || a==5 || a==6);
        }
    }



}
