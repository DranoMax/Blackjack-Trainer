package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

/**
 * Created by Alex on 5/31/2014.
 */
public class Hand {

    private ArrayList<Card> hand;
    public static final int OPEN = 0;
    public static final int STAND = 1;
    public static final int BUST = 2;
    public static final int BLACKJACK = 3;
    private int status = OPEN;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void printHand() {
        for( Card card : hand ) {
            card.printCard();
            System.out.print(" ");
        }
    }
    
    public void addCard(Card card) {
    	hand.add(card);
    	if( getTotal() > 21 ) {
    		setStatus(Hand.BUST);
    	}
    }
    
    public int getTotal() {
    	int total = 0;
    	for( Card card : hand ) { 
    		total += card.getValue(total);
    	}
    	return total;
    }
    
    public void setStatus(int status) {
    	this.status = status;
    }
    
    public int getStatus() {
    	return status;
    }
    
}
