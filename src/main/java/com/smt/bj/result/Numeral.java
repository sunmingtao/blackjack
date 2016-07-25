package com.smt.bj.result;

public class Numeral implements Result {

    private final int value;
    private final boolean soft;
    
    public Numeral(int value) {
        super();
        this.value = value;
        this.soft = false;
    }
    
    public Numeral(int value, boolean soft) {
        super();
        this.value = value;
        this.soft = soft;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (soft ? 1231 : 1237);
        result = prime * result + value;
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
        Numeral other = (Numeral) obj;
        if (soft != other.soft)
            return false;
        if (value != other.value)
            return false;
        return true;
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public boolean isSoft() {
        return soft;
    }

    @Override
    public String toString() {
        return "Numeral [value=" + value + ", soft=" + soft + "]";
    }

}
