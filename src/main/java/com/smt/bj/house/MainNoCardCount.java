package com.smt.bj.house;

public class MainNoCardCount {

    private final static int NUM_SHOE = 2000000;
     
    public static void main(String[] args) {
        for (int j=0; j<10; j++){
            int counter = 0;
            int winning = 0;
            int i = 0;
            while (i++ < NUM_SHOE){
                House house = new House();
                while (house.getShoe().penetration() > 20){
                    winning += house.duel();
                    counter++;
                }
            }
            System.out.println("Winning = "+winning);
            System.out.println("Counter = "+counter);
            System.out.println("Winning per hand = "+ (double) winning/counter);    
        }
    }
}
