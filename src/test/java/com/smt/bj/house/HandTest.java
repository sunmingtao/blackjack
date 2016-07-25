package com.smt.bj.house;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.smt.bj.card.Card;
import com.smt.bj.card.Rank;
import com.smt.bj.card.Suit;
import com.smt.bj.house.Hand;
import com.smt.bj.result.BlackJack;
import com.smt.bj.result.Busted;
import com.smt.bj.result.Numeral;
import com.smt.bj.result.Result;

public class HandTest {

    @Test
    public void blackJack() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLUB, Rank.ACE));
        hand.add(new Card(Suit.CLUB, Rank.TEN));
        assertThat(hand.result() instanceof BlackJack, is(true));
    }

    @Test
    public void busted() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLUB, Rank.TEN));
        hand.add(new Card(Suit.CLUB, Rank.TEN));
        hand.add(new Card(Suit.CLUB, Rank.TWO));
        assertThat(hand.result() instanceof Busted, is(true));
    }
    
    @Test
    public void hasAceSoft() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLUB, Rank.ACE));
        hand.add(new Card(Suit.CLUB, Rank.ACE));
        hand.add(new Card(Suit.CLUB, Rank.TWO));
        Result exp = new Numeral(14, true);
        assertThat(hand.result(), is(exp));
    }
    
    @Test
    public void hasAceHard() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLUB, Rank.ACE));
        hand.add(new Card(Suit.CLUB, Rank.ACE));
        hand.add(new Card(Suit.CLUB, Rank.TEN));
        Result exp = new Numeral(12, false);
        assertThat(hand.result(), is(exp));
    }
    
    @Test
    public void hasNoAce() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLUB, Rank.JACK));
        hand.add(new Card(Suit.CLUB, Rank.TEN));
        Result exp = new Numeral(20, false);
        assertThat(hand.result(), is(exp));
    }
    
    @Test
    public void equalsNonPair(){
        Hand hand1 = new Hand();
        hand1.add(new Card(Rank.JACK));
        hand1.add(new Card(Rank.TWO));
        Hand hand2 = new Hand();
        hand2.add(new Card(Rank.TWO));
        hand2.add(new Card(Rank.KING));
        assertTrue(hand1.equals(hand2));
    }
    
    @Test
    public void equalsPairAndNoPair(){
        Hand hand1 = new Hand();
        hand1.add(new Card(Rank.EIGHT));
        hand1.add(new Card(Rank.EIGHT));
        Hand hand2 = new Hand();
        hand2.add(new Card(Rank.QUEEN));
        hand2.add(new Card(Rank.SIX));
        assertFalse(hand1.equals(hand2));
    }
    
    @Test
    public void equalsPairPair(){
        Hand hand1 = new Hand();
        hand1.add(new Card(Rank.SIX));
        hand1.add(new Card(Rank.SIX));
        Hand hand2 = new Hand();
        hand2.add(new Card(Rank.SIX));
        hand2.add(new Card(Rank.SIX));
        assertTrue(hand1.equals(hand2));
    }
    
    @Test
    public void equalsBlackJacks(){
        Hand hand1 = new Hand();
        hand1.add(new Card(Rank.ACE));
        hand1.add(new Card(Rank.TEN));
        Hand hand2 = new Hand();
        hand2.add(new Card(Rank.JACK));
        hand2.add(new Card(Rank.ACE));
        assertTrue(hand1.equals(hand2));
    }
    
    @Test
    public void equalsBusted(){
        Hand hand1 = new Hand();
        hand1.add(new Card(Rank.TEN));
        hand1.add(new Card(Rank.TEN));
        hand1.add(new Card(Rank.TWO));
        Hand hand2 = new Hand();
        hand2.add(new Card(Rank.TEN));
        hand2.add(new Card(Rank.KING));
        hand2.add(new Card(Rank.FOUR));
        assertTrue(hand1.equals(hand2));
    }
    
    @Test
    public void equalsBlackJackAndNonNatural21(){
        Hand hand1 = new Hand();
        hand1.add(new Card(Rank.ACE));
        hand1.add(new Card(Rank.TEN));
        Hand hand2 = new Hand();
        hand2.add(new Card(Rank.JACK));
        hand2.add(new Card(Rank.QUEEN));
        hand2.add(new Card(Rank.ACE));
        assertFalse(hand1.equals(hand2));
    }
    
    @Test
    public void equalsSoftAndHard(){
        Hand hand1 = new Hand();
        hand1.add(new Card(Rank.ACE));
        hand1.add(new Card(Rank.FIVE));
        Hand hand2 = new Hand();
        hand2.add(new Card(Rank.JACK));
        hand2.add(new Card(Rank.SIX));
        assertFalse(hand1.equals(hand2));
    }
    
    @Test
    public void equalsDifferentSize(){
        Hand hand1 = new Hand();
        hand1.add(new Card(Rank.JACK));
        hand1.add(new Card(Rank.TWO));
        Hand hand2 = new Hand();
        hand2.add(new Card(Rank.JACK));
        hand2.add(new Card(Rank.ACE));
        hand2.add(new Card(Rank.ACE));
        assertTrue(hand1.equals(hand2));
    }
    
    
}
