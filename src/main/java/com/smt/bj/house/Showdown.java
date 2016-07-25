package com.smt.bj.house;

import com.smt.bj.result.Result;

public class Showdown {

    private final DealerHand dealerHand;
    private final PunterHand punterHand;
    private int result;
    public final static int ONE_UNIT = 100;
    
    public Showdown(DealerHand dealerHand, PunterHand punterHand) {
        this.dealerHand = dealerHand;
        this.punterHand = punterHand;
        showdown();
    }
    
    private void showdown(){
        this.result = calculate();
    }
    
    public int result(){
        return this.result;
    }
    
    public void print(){
        System.out.println("Dealer Hand: "+dealerHand.getHand());
        System.out.println("Punter Hand: "+punterHand);
        System.out.println("Punter wins: "+this.result);
        System.out.println();
    }
    
    private int calculate(){
        if (punterHand.isSplit()){
            Showdown leftShowdown = new Showdown(dealerHand, punterHand.getLeftChildPunterHand());
            Showdown rightShowdown = new Showdown(dealerHand, punterHand.getRightChildPunterHand());
            return leftShowdown.result() + rightShowdown.result();
        }
        if (punterHand.isSurrendered()){
            return (int) (-ONE_UNIT * 0.5) ;
        }
        Result punterResult = punterHand.result();
        Result dealerResult = dealerHand.result();
        if (dealerHand.isBlackJack() && !punterHand.isBlackJack()){
            return -ONE_UNIT;
        }
        if (punterHand.isBlackJack() && !dealerHand.isBlackJack()){
            return (int) (ONE_UNIT * 1.5) ;
        }
        if (punterHand.isBlackJack() && dealerHand.isBlackJack()){
            return 0;
        }
        if (punterHand.isBusted()){
            return -ONE_UNIT;
        }
        int winLose = whoWin(punterResult, dealerResult) * ONE_UNIT;
        if (punterHand.isDoubled()){
            winLose *= 2;
        }
        return winLose;
    }

    private int whoWin(Result punterResult, Result dealerResult) {
        if (punterResult.value() > dealerResult.value()){
            return 1;
        }
        if (punterResult.value() < dealerResult.value()){
            return -1;
        }
        return 0;
    }

}
