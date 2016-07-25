package com.smt.bj.house;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.smt.bj.card.Card;
import com.smt.bj.card.Rank;
import com.smt.bj.result.Busted;

@RunWith(MockitoJUnitRunner.class)
public class DealerHandTest {
    
    @Mock
    private Shoe shoe;
    
    private PunterHand punterHand = new PunterHand(shoe);
    
    @Test
    public void hit14StandHard17() {
        when(shoe.deal()).thenReturn(new Card(Rank.FOUR), new Card(Rank.KING), new Card(Rank.THREE));
        DealerHand dealerHand = new DealerHand(shoe);
        new HouseRule(dealerHand, punterHand);
        dealerHand.hitOrStand();
        assertThat(dealerHand.result().value(), is(17));
    }
    
    @Test
    public void hit16StandBusted() {
        when(shoe.deal()).thenReturn(new Card(Rank.SIX), new Card(Rank.KING), new Card(Rank.KING));
        DealerHand dealerHand = new DealerHand(shoe);
        new HouseRule(dealerHand, punterHand);
        dealerHand.hitOrStand();
        assertThat(dealerHand.result() instanceof Busted, is(true));
    }
    
    @Test
    public void standSoft17() {
        when(shoe.deal()).thenReturn(new Card(Rank.ACE), new Card(Rank.SIX), new Card(Rank.TWO));
        DealerHand dealerHand = new DealerHand(shoe);
        new HouseRule(dealerHand, punterHand);
        dealerHand.hitOrStand();
        assertThat(dealerHand.result().value(), is(17));
    }
}
