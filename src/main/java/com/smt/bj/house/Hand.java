package com.smt.bj.house;

import java.util.ArrayList;
import java.util.List;

import com.smt.bj.card.Card;
import com.smt.bj.card.Rank;
import com.smt.bj.result.BlackJack;
import com.smt.bj.result.Busted;
import com.smt.bj.result.Numeral;
import com.smt.bj.result.Result;

public class Hand {
    public static final Hand BLACK_JACK = new Hand(new Card(Rank.ACE), new Card(Rank.TEN));
    public static final Hand PAIR_A = new Hand(new Card(Rank.ACE), new Card(Rank.ACE));
    public static final Hand PAIR_2 = new Hand(new Card(Rank.TWO), new Card(Rank.TWO));
    public static final Hand PAIR_3 = new Hand(new Card(Rank.THREE), new Card(Rank.THREE));
    public static final Hand PAIR_4 = new Hand(new Card(Rank.FOUR), new Card(Rank.FOUR));
    public static final Hand PAIR_5 = new Hand(new Card(Rank.FIVE), new Card(Rank.FIVE));
    public static final Hand PAIR_6 = new Hand(new Card(Rank.SIX), new Card(Rank.SIX));
    public static final Hand PAIR_7 = new Hand(new Card(Rank.SEVEN), new Card(Rank.SEVEN));
    public static final Hand PAIR_8 = new Hand(new Card(Rank.EIGHT), new Card(Rank.EIGHT));
    public static final Hand PAIR_9 = new Hand(new Card(Rank.NINE), new Card(Rank.NINE));
    public static final Hand PAIR_10 = new Hand(new Card(Rank.TEN), new Card(Rank.TEN));
    
    public static final Hand HARD_5 = new Hand(new Card(Rank.TWO), new Card(Rank.THREE));
    public static final Hand HARD_6 = new Hand(new Card(Rank.TWO), new Card(Rank.FOUR));
    public static final Hand HARD_7 = new Hand(new Card(Rank.TWO), new Card(Rank.FIVE));
    public static final Hand HARD_8 = new Hand(new Card(Rank.TWO), new Card(Rank.SIX));
    public static final Hand HARD_9 = new Hand(new Card(Rank.TWO), new Card(Rank.SEVEN));
    public static final Hand HARD_10 = new Hand(new Card(Rank.TWO), new Card(Rank.EIGHT));
    public static final Hand HARD_11 = new Hand(new Card(Rank.TWO), new Card(Rank.NINE));
    public static final Hand HARD_12 = new Hand(new Card(Rank.TWO), new Card(Rank.TEN));
    public static final Hand HARD_13 = new Hand(new Card(Rank.THREE), new Card(Rank.TEN));
    public static final Hand HARD_14 = new Hand(new Card(Rank.FOUR), new Card(Rank.TEN));
    public static final Hand HARD_15 = new Hand(new Card(Rank.FIVE), new Card(Rank.TEN));
    public static final Hand HARD_16 = new Hand(new Card(Rank.SIX), new Card(Rank.TEN));
    public static final Hand HARD_17 = new Hand(new Card(Rank.SEVEN), new Card(Rank.TEN));
    public static final Hand HARD_18 = new Hand(new Card(Rank.EIGHT), new Card(Rank.TEN));
    public static final Hand HARD_19 = new Hand(new Card(Rank.NINE), new Card(Rank.TEN));
    public static final Hand HARD_20 = new Hand(new Card(Rank.FIVE), new Card(Rank.FIVE), new Card(Rank.TEN));
    
    public static final Hand A2 = new Hand(new Card(Rank.ACE), new Card(Rank.TWO));
    public static final Hand A3 = new Hand(new Card(Rank.ACE), new Card(Rank.THREE));
    public static final Hand A4 = new Hand(new Card(Rank.ACE), new Card(Rank.FOUR));
    public static final Hand A5 = new Hand(new Card(Rank.ACE), new Card(Rank.FIVE));
    public static final Hand A6 = new Hand(new Card(Rank.ACE), new Card(Rank.SIX));
    public static final Hand A7 = new Hand(new Card(Rank.ACE), new Card(Rank.SEVEN));
    public static final Hand A8 = new Hand(new Card(Rank.ACE), new Card(Rank.EIGHT));
    public static final Hand A9 = new Hand(new Card(Rank.ACE), new Card(Rank.NINE));
    
    private final List<Card> cards = new ArrayList<>(2);
    
    public Hand(){
        
    }
    
    public Hand(Card... cards){
        for (Card card : cards){
            this.cards.add(card);
        }
    }
    
    public void add(Card card){
        cards.add(card);
    }
    
    private boolean hasAce(){
        for (Card card: cards){
            if (card.isAce()){
                return true;
            }
        }
        return false;
    }
    
    private boolean hasTen(){
        for (Card card: cards){
            if (card.isTen()){
                return true;
            }
        }
        return false;
    }
    
    public boolean isBlackJack(){
        return cards.size() == 2 && hasAce() && hasTen();
    }
    
    public boolean isPair(){
        return cards.size() == 2 && leftCard().rankValue() == rightCard().rankValue();
    }
    
    public Card leftCard() {
        return cards.get(0);
    }
    
    public Card rightCard() {
        return cards.get(1);
    }
    
    public int size(){
        return cards.size();
    }
    
    private int sum(){
        int value = 0;
        for (Card card: cards){
            value += card.rankValue();
        }
        return value;
    }
    
    public Result result(){
        if (isBlackJack()){
            return new BlackJack();
        }
        int value = sum();
        if (value > 21){
            return new Busted();
        }
        if (hasAce()){
            if (value + 10 <= 21){
                return new Numeral(value + 10, true);
            }
            return new Numeral(value);
        }
        return new Numeral(value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((result() == null) ? 0 : result().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hand other = (Hand) obj;
        if (isPair() != other.isPair()){
            return false;
        }else{
            Result result = result();
            Result otherResult = other.result();
            if (result.getClass() != otherResult.getClass()){
                return false;
            }else if (result instanceof Numeral){
                Numeral numeralResult = (Numeral) result;
                Numeral otherNumeralResult = (Numeral) otherResult;
                return numeralResult.equals(otherNumeralResult);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards){
            sb.append(card.getRank()+" ");
        }
        sb.append(result());
        return sb.toString();
    }
    
}
