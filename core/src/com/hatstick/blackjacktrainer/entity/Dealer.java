package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alex on 5/31/2014.
 */
public class Dealer {

    private Deck deck;
    private Hand hand;
    private Vector2 position;

    public Dealer() {
        this.deck = new Deck();
        this.hand = new Hand();
        this.position = new Vector2();
    }

    public void shuffle() {
        deck.shuffle();
    }

    // Start of the game dealing
    public void deal(ArrayList<Player> players) {
        for( int i = 0; i < 2; i++ ) {
            for (Player player : players) {
                hit(player);
            }
            // Draw card for dealer
            hand.getHand().add(deck.drawCard());
        }
    }

    // "Hit me"
    public void hit(Player player) {
        player.getHandArray().add(deck.drawCard());
    }
    
    public Hand getHand() {
    	return hand;
    }
    
    public ArrayList<Card> getHandArray() {
    	return hand.getHand();
    }
    
    public void setPosition(Vector2 pos) {
    	position = pos;
    }
    
    public Vector2 getPosition() {
    	return position;
    }
    
}
