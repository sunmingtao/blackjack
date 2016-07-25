package com.smt.bj.result;

public class BlackJack implements Result {

    @Override
    public int value() {
        return 21;
    }

    @Override
    public boolean isSoft() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "BlackJack";
    }
    
    

}
