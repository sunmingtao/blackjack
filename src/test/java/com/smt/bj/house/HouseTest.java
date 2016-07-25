package com.smt.bj.house;

import static com.smt.bj.card.Rank.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.smt.bj.card.Card;
import com.smt.bj.house.House;
import com.smt.bj.house.Shoe;

@RunWith(MockitoJUnitRunner.class)
public class HouseTest {

    @Mock
    private Shoe shoe;
    
    private House house;
    
    @Before
    public void setUp() throws Exception {
        house = new House();
        house.setShoe(shoe);
    }

    @Test
    public void dealerBlackJackVsPunterBlackJack() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(ACE), new Card(ACE), new Card(TEN));
        assertThat(house.duel(),  is(0));
    }
    
    @Test
    public void dealerNonBlackJackVsPunterBlackJack() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(KING), new Card(ACE), new Card(TEN));
        assertThat(house.duel(),  is(150));
    }
    
    @Test
    public void dealerBlackJackVsPunterNonBlackJack() {
        when(shoe.deal()).thenReturn(new Card(ACE), new Card(KING), new Card(TEN), new Card(TEN));
        assertThat(house.duel(),  is(-100));
    }
    
    @Test
    public void hitAndBust() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(KING), new Card(TEN), new Card(THREE), new Card(TEN));
        assertThat(house.duel(),  is(-100));
    }
    
    @Test
    public void hitAndNotBustButStillLose() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(KING), new Card(TEN), new Card(THREE), new Card(SIX));
        assertThat(house.duel(),  is(-100));
    }
    
    @Test
    public void hitAndWin() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(KING), new Card(TEN), new Card(THREE), new Card(EIGHT));
        assertThat(house.duel(),  is(100));
    }
    
    @Test
    public void hitAndPush() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(KING), new Card(TEN), new Card(THREE), new Card(SEVEN));
        assertThat(house.duel(),  is(0));
    }
    
    @Test
    public void standAndWin() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(SEVEN), new Card(TEN), new Card(EIGHT));
        assertThat(house.duel(),  is(100));
    }
    
    @Test
    public void standAndPush() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(SEVEN), new Card(TEN), new Card(SEVEN));
        assertThat(house.duel(),  is(0));
    }
    
    @Test
    public void standAndLose() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(EIGHT), new Card(TEN), new Card(SEVEN));
        assertThat(house.duel(),  is(-100));
    }
    
    @Test
    public void doubleAndWin() {
        when(shoe.deal()).thenReturn(new Card(SIX), new Card(TEN), new Card(FIVE), new Card(SIX), new Card(SEVEN), new Card(ACE));
        assertThat(house.duel(),  is(200));
    }
    
    @Test
    public void doubleAndLose() {
        when(shoe.deal()).thenReturn(new Card(SIX), new Card(TEN), new Card(FIVE), new Card(SIX), new Card(ACE), new Card(ACE));
        assertThat(house.duel(),  is(-200));
    }
    
    @Test
    public void doubleAndPush() {
        when(shoe.deal()).thenReturn(new Card(SIX), new Card(TEN), new Card(FIVE), new Card(SIX), new Card(SIX), new Card(ACE));
        assertThat(house.duel(),  is(0));
    }
    
    @Test
    public void doubleNotAllowAfterHit() {
        when(shoe.deal()).thenReturn(new Card(SIX), new Card(TEN), new Card(TWO), new Card(THREE), new Card(SIX), new Card(NINE), new Card(ACE));
        assertThat(house.duel(),  is(100));
    }
    
    @Test
    public void surrender() {
        when(shoe.deal()).thenReturn(new Card(NINE), new Card(TEN), new Card(SIX), new Card(TEN));
        assertThat(house.duel(),  is(-50));
    }
    
    @Test
    public void surrenderNotAllowAfterHit() {
        when(shoe.deal()).thenReturn(new Card(NINE), new Card(TEN), new Card(TWO), new Card(TEN), new Card(FOUR), new Card(ACE));
        assertThat(house.duel(),  is(-100));
    }
    
    @Test
    public void surrenderNotAllowAfterSplit() {
        when(shoe.deal()).thenReturn(new Card(KING), new Card(TEN), new Card(EIGHT), new Card(EIGHT), new Card(SEVEN), new Card(TEN), new Card(TEN));
        assertThat(house.duel(),  is(-200));
    }
    
    @Test
    public void splitBlackJackNotCountAsNatural() {
        when(shoe.deal()).thenReturn(new Card(TEN), new Card(KING), new Card(ACE), new Card(ACE), new Card(TEN), new Card(TEN));
        assertThat(house.duel(),  is(200));
    }
    
    @Test
    public void dealerBlackJackNotAllowSplit() {
        when(shoe.deal()).thenReturn(new Card(ACE), new Card(KING), new Card(ACE), new Card(ACE));
        assertThat(house.duel(),  is(-100));
    }
    
    @Test
    public void notAllowReSplitOrHitSplitAces() {
        when(shoe.deal()).thenReturn(new Card(QUEEN), new Card(KING), new Card(ACE), new Card(ACE), new Card(ACE), new Card(ACE));
        assertThat(house.duel(),  is(-200));
    }
    
    @Test
    public void notAllowReReSplit() {
        when(shoe.deal()).thenReturn(new Card(ACE), new Card(NINE), new Card(EIGHT), new Card(EIGHT), 
                new Card(EIGHT), new Card(EIGHT), new Card(ACE), new Card(TEN), new Card(TEN));
        assertThat(house.duel(),  is(-300));
    }

}
