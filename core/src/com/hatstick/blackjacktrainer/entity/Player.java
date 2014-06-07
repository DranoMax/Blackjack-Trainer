package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alex on 5/31/2014.
 */
public abstract class Player {

    private int chips;
    private String name;
    private Hand hand;
    // Used for rendering sprite locations according to seat
 	private Vector2 seatPosition;
 	
 	private int currentBet;

    public Player(String name) {
        this.chips = 100;
        this.name = name;
        this.hand = new Hand();
        this.currentBet = 0;
    }

    public ArrayList<Card> getHandArray() {
        return hand.getHand();
    }
    
    public Hand getHand() {
    	return hand;
    }

    public void printHand() {
        System.out.print(name + " has ");
        hand.printHand();
        System.out.println();
    }
    
    public void setPosition(Vector2 pos) {
    	seatPosition = pos;
    }
    
    public Vector2 getPosition() {
    	return seatPosition;
    }
    
    public int getChips() {
    	return chips;
    }
    
    public int getBet() {
    	return currentBet;
    }
    
    public void increaseBet(int increase) {
    	if (currentBet+increase <= chips) {
    		currentBet += increase;
    	}
    	else {
    		currentBet = chips;
    	}
    }
    
    public void decreaseBet(int decrease) {
    	if (currentBet-decrease >= 0) {
    		currentBet -= decrease;
    	}
    	else {
    		currentBet = 0;
    	}
    }
}
