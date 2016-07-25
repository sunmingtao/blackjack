package com.smt.bj.card;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.smt.bj.card.Card;
import com.smt.bj.card.Rank;

public class CardTest {

    @Test
    public void equals() {
        Card card1 = new Card(Rank.KING);
        Card card2 = new Card(Rank.TEN);
        assertTrue(card1.equals(card2));
    }
    
    @Test
    public void compareEqual() {
        Card card1 = new Card(Rank.KING);
        Card card2 = new Card(Rank.TEN);
        assertThat(card1.compareTo(card2), is(0));
    }
    
    @Test
    public void compareGreater() {
        Card card1 = new Card(Rank.KING);
        Card card2 = new Card(Rank.TWO);
        assertThat(card1.compareTo(card2) > 0, is(true));
    }

}
