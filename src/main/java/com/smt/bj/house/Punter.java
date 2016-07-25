package com.smt.bj.house;

public class Punter extends Player {
    
    private final PunterHand punterHand;

    public Punter(Shoe shoe) {
        super(shoe);
        punterHand = new PunterHand(shoe);
        punterHand.hit(2);
    }
    
    public void hitOrStand(int faceUpCard){
        punterHand.hitOrStand(faceUpCard);
    }

    public PunterHand getPunterHand() {
        return punterHand;
    }

}
