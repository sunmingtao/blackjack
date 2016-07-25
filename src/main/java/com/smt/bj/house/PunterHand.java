package com.smt.bj.house;

import com.smt.bj.card.Card;
import com.smt.bj.strategy.Action;
import com.smt.bj.strategy.BasicStrategyLookUpTable;

public class PunterHand extends PlayerHand{
    
    private PunterHand leftChildPunterHand;
    private PunterHand rightChildPunterHand;
    
    private boolean stood = false;
    private boolean surrendered = false;
    private boolean doubled = false;
    private boolean split = false;
    private int splitLevel;
    
    public PunterHand(Shoe shoe, int splitLevel){
        super(shoe);
        this.splitLevel = splitLevel;
    }
    
    public PunterHand(Shoe shoe){
        this(shoe, 0);
    }
    
    public boolean isBlackJack(){
        return hand.isBlackJack() && getSplitLevel() == 0;
    }
    
    public void hitOrStand(int faceUpCard) {
        while (houseRule.allowPunterHit()){
            Action action = BasicStrategyLookUpTable.findAction(hand, faceUpCard, houseRule);
            switch(action){
                case STAND: 
                    stood = true;
                    break;
                case HIT: 
                    hit(); 
                    break;
                case DOUBLE:
                    hit();
                    doubled = true;
                    break;
                case SPLIT:
                    split(faceUpCard);
                    break;
                case SURRENDER:
                    surrendered = true;
                    break;
            }
        }
    }
    
    private void split(int faceUpCard) {
        if (hand.size() != 2){
            throw new IllegalStateException("Hand size is not 2. Split not allowed. Current size is "+hand.size());
        }
        split = true;
        leftChildPunterHand = new PunterHand(shoe, splitLevel+1);
        childPunterHand(faceUpCard, leftChildPunterHand, hand.leftCard());
        rightChildPunterHand = new PunterHand(shoe, splitLevel+1);
        childPunterHand(faceUpCard, rightChildPunterHand, hand.rightCard());
    }

    private void childPunterHand(int faceUpCard, PunterHand childPunterHand, Card splitCard) {
        new HouseRule(houseRule.getDealerHand(), childPunterHand);
        childPunterHand.addCard(splitCard);
        childPunterHand.hit();
        childPunterHand.hitOrStand(faceUpCard);
    }

    public void addCard(Card card){
        hand.add(card);
    }
    
    public void hit(int numCards){
        for (int i=0; i<numCards; i++){
            hit();
        }
    }
    
    public boolean isBusted(){
        return result().value() < 0;
    }

    public boolean isSurrendered() {
        return surrendered;
    }

    public boolean isDoubled() {
        return doubled;
    }

    public boolean isSplit() {
        return split;
    }

    public boolean isStood() {
        return stood;
    }

    public int getSplitLevel() {
        return splitLevel;
    }

    public PunterHand getLeftChildPunterHand() {
        return leftChildPunterHand;
    }

    public PunterHand getRightChildPunterHand() {
        return rightChildPunterHand;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (split){
            sb.append("Split left [").append(leftChildPunterHand.toString()).append("] ");
            sb.append("Split right [").append(rightChildPunterHand.toString()).append("]");
        }else{
            sb.append(hand.toString()).append(" ");
            if (doubled){
                sb.append("Double ");
            }
            if (surrendered){
                sb.append("Surrender ");
            }
        }
        return sb.toString();
    }
    
    

}
