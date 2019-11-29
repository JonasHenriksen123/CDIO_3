package com.company;

public class FordelingAfSlagTest {
    static Terning Terningtest = new Terning();
    static int a1; static int a2; static int a3; static int a4; static int a5; static int a6;

    //Vi tester fordelingen af terningeslagene, som gerne skulle ligge på 1/6 hver

    public static void main(String[]args){
        for(int i = 0; i < 10000; i++){
        Terningtest.roll();
        switch (Terningtest.getFaceValue()){
            case 1:
                a1++;
                break;
            case 2:
                a2++;
                break;
            case 3:
                a3++;
                break;
            case 4:
                a4++;
                break;
            case 5:
                a5++;
                break;
            case 6:
                a6++;
                break;
        }
        }
        System.out.println("1: "+ a1 + "   2: "+ a2 + "   3: "+ a3 + "   4: "+ a4 + "   5: "+ a5 + "   6: "+ a6);
    }
}
