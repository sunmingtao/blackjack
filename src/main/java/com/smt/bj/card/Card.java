package com.smt.bj.card;

public class Card implements Comparable<Card>{
    private final Suit suit;
    private final Rank rank;
    private final int value;
    
    public Card (Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
        this.value = suit.getValue() * 13 + rank.faceValue();
    }
    
    //When suit doesn't matter
    public Card (Rank rank){
        this.suit = Suit.SPADE;
        this.rank = rank;
        this.value = suit.getValue() * 13 + rank.faceValue();
    }
    
    public int rankValue(){
        return rank.realValue();
    }
    
    public boolean isAce(){
        return rank == Rank.ACE;
    }
    
    public boolean isTen(){
        return rank.realValue() == 10;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rank == null) ? 0 : rank.hashCode());
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
        Card other = (Card) obj;
        if (rankValue() != other.rankValue())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Card [suit=" + suit + ", rank=" + rank + ", value=" + value + "]";
    }
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card other) {
        return rankValue() - other.rankValue();
    }    
    
}
