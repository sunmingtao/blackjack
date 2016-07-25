package com.smt.bj.strategy;

import com.smt.bj.house.Hand;

public class Key {
    private final Hand hand;
    private final int faceUpCard;
    
    public Key(Hand hand, int faceUpCard) {
        super();
        this.hand = hand;
        this.faceUpCard = faceUpCard;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + faceUpCard;
        result = prime * result + ((hand == null) ? 0 : hand.hashCode());
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
        Key other = (Key) obj;
        if (faceUpCard != other.faceUpCard)
            return false;
        if (hand == null) {
            if (other.hand != null)
                return false;
        } else if (!hand.equals(other.hand))
            return false;
        return true;
    }
    
}
