package com.smt.bj.house;

import com.smt.bj.result.Result;

public class HouseRule {
    
    private final PunterHand punterHand;
    private final DealerHand dealerHand;
    
    public HouseRule(DealerHand dealerHand, PunterHand punterHand){
        this.punterHand = punterHand;
        this.dealerHand = dealerHand;
        this.dealerHand.setHouseRule(this);
        this.punterHand.setHouseRule(this);
        
    }
    
    public boolean allowPunterHit(){
        if (dealerHand.isBlackJack()){
            return false;
        }
        if (punterHand.isBusted() || punterHand.isStood() || punterHand.isSplit()
                || punterHand.isSurrendered() || punterHand.isDoubled()){
            return false;
        }
        if (punterHand.result().value() == 21){
            return false;
        }
        //Cannot hit on split Aces
        if (punterHand.getSplitLevel() > 0 && punterHand.getHand().leftCard().isAce()){
            return false;
        }
        return true;
    }
    
    //Dealer stand on all (both hard and soft) 17
    public boolean allowDealerHit(){
        Result result = dealerHand.getHand().result();
        return 0 <= result.value() && result.value() < 17;
    }
    
    public boolean allowSplit(){
        if (dealerHand.isBlackJack()){
            return false;
        }
        if (!punterHand.getHand().isPair()){
            return false;
        }
        //Cannot split more than 3 times (i.e. can have a maximum of 4 hands)
        if (punterHand.getSplitLevel() >= 2){
            return false;
        }
        return true;
    }
    
    public boolean allowSurrender(){
        if (dealerHand.isBlackJack()){
            return false;
        }
        if (punterHand.getSplitLevel() > 0 || punterHand.getHand().size() != 2){
            return false;
        }
        return true;
    }
    
    public boolean allowDouble(){
        return allowPunterHit() && punterHand.getHand().size() == 2;
    }

    public DealerHand getDealerHand() {
        return dealerHand;
    }
}
