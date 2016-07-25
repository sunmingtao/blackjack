package com.smt.bj.house;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.smt.bj.card.Card;
import com.smt.bj.card.Deck;

public class Shoe {
    
    private final List<Card> cards = new ArrayList<>();
    protected static final int NUM_DESKS = 6;
    
    public Shoe(){
        for (int i = 0; i < NUM_DESKS; i++){
            Deck deck = new Deck();
            cards.addAll(deck.getCards());
        }
        shuffle();
    }
    
    public int penetration(){
        return size() * 100 / (NUM_DESKS * 52);
    }
    
    public boolean hasMoreCards(){
        return size() > 0;
    }
    
    private void shuffle(){
        Collections.shuffle(cards);
    }
    
    public Card deal(){
        return cards.remove(0);
    }
    
    public int size(){
        return cards.size();
    }
    
}
