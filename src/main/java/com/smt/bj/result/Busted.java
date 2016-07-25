package com.smt.bj.result;

public class Busted implements Result {

    @Override
    public int value() {
        return -1;
    }

    @Override
    public boolean isSoft() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Busted";
    }

}
