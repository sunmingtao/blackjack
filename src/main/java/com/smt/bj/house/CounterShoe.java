package com.smt.bj.house;

import com.smt.bj.card.Card;

/**
 * Shoe with a counting ability
 */
public class CounterShoe extends Shoe{
    private int runningCount;
    
    public double trueCount(){
        double remainingDeck = size() / 52;
        return runningCount / remainingDeck;
    }
    
    public boolean timeToBetHard(){
        return runningCount >= 1;
    }
    
    public Card deal(){
        Card card = super.deal();
        updateCount(card);
        return card;
    }

    private void updateCount(Card card) {
        int rankValue = card.rankValue();
        if (2 <= rankValue && rankValue <= 6){
            runningCount++;
        }else if (rankValue == 1 || rankValue == 10){
            runningCount--;
        }
    }
    
    
}
