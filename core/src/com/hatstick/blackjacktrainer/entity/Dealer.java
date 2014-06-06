package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alex on 5/31/2014.
 */
public class Dealer {

    private Deck deck;
    private Vector2 position;

    public Dealer() {
        this.deck = new Deck();
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
        }
    }

    // "Hit me"
    public void hit(Player player) {
        player.getHandArray().add(deck.drawCard());
    }
    
}
