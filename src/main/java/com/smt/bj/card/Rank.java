package com.smt.bj.card;

public enum Rank {
    ACE(1,1), TWO(2,2), THREE(3,3), FOUR(4,4), FIVE(5,5), SIX(6,6), SEVEN(7,7), EIGHT(8,8), NINE(9,9), TEN(10,10), JACK(11,10), QUEEN(12,10), KING(13,10);
    
    private final int faceValue;
    private final int realValue;

    private Rank(int faceValue, int realValue) {
        this.faceValue = faceValue;
        this.realValue = realValue;
    }
    
    public int faceValue() {
        return faceValue;
    }
    
    public int realValue() {
        return realValue;
    }

    public String toString(){
        return this.name()+"("+faceValue+")";
    }
}
