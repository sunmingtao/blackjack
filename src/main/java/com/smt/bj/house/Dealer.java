package com.smt.bj.house;

public class Dealer extends Player{
    
    private final DealerHand dealerHand;
    
    public Dealer(Shoe shoe){
        super(shoe);
        dealerHand = new DealerHand(shoe);
        dealerHand.hit();
        dealerHand.hit();
    }
    
    public int faceUpCard(){
        return dealerHand.getHand().leftCard().rankValue();
    }
    
    public void hitOrStand(){
        dealerHand.hitOrStand();
    }

    public DealerHand getDealerHand() {
        return dealerHand;
    }
    
}
