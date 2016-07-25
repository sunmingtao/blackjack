package com.smt.bj.house;

public class DealerHand extends PlayerHand{
    
    public DealerHand(Shoe shoe){
        super(shoe);
    }
    
    public void hitOrStand() {
        while(houseRule.allowDealerHit()){
            hit();
        }
    }
    
    public boolean isBlackJack(){
        return hand.isBlackJack();
    }

    @Override
    public String toString() {
        return hand.toString();
    }
    
}
