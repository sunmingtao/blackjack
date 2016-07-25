package com.smt.bj.house;

public class House {
    
    private Shoe shoe = new Shoe();
    
    public House(Shoe shoe) {
        super();
        this.shoe = shoe;
    }

    public House() {
        super();
    }

    
    public int duel(){
        Dealer dealer = new Dealer(shoe);
        Punter punter = new Punter(shoe);
        new HouseRule(dealer.getDealerHand(), punter.getPunterHand());
        punter.hitOrStand(dealer.faceUpCard());
        dealer.hitOrStand();
        Showdown showdown = new Showdown(dealer.getDealerHand(), punter.getPunterHand());
        //showdown.print();
        return showdown.result();
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public Shoe getShoe() {
        return shoe;
    }
    
}
