package com.smt.bj.house;

public class MainCardCount {

    private final static int NUM_SHOE = 2000000;
    private final static int PENETRATION_LIMIT = 20;
    
    public static void main(String[] args) {
        for (int j=0; j<10; j++){
            int counter = 0;
            int winning = 0;
            int i = 0;
            while (i++ < NUM_SHOE){
                CounterShoe counterShoe = new CounterShoe();
                House house = new House(counterShoe);
                while (house.getShoe().penetration() > PENETRATION_LIMIT){
                    if (counterShoe.timeToBetHard()){
                        winning += house.duel();
                        counter++;    
                    }else{
                        house.duel();
                    }
                }
            }
            System.out.println("Winning = "+winning);
            System.out.println("Counter = "+counter);
            System.out.println("Winning per hand = "+ (double) winning/counter);    
        }
    }

}
