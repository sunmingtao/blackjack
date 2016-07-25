package com.smt.bj.house;

import com.smt.bj.card.Card;
import com.smt.bj.result.Result;

public abstract class PlayerHand {
    protected final Hand hand = new Hand();
    protected final Shoe shoe;
    protected HouseRule houseRule;
    
    public PlayerHand(Shoe shoe) {
        super();
        this.shoe = shoe;
    }
    
    public void setHouseRule(HouseRule houseRule) {
        this.houseRule = houseRule;
    }
    
    public Card hit(){
        Card card = shoe.deal(); 
        hand.add(card);
        return card;
    }
    
    public Result result(){
        return hand.result();
    }
    
    public Hand getHand() {
        return hand;
    }
    
}
