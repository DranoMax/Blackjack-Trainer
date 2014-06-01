package com.hatstick.blackjacktrainer.entity;

/**
 * Created by Alex on 5/31/2014.
 */
public class Dealer {

    private Deck deck;

    public Dealer() {
        this.deck = new Deck();
    }

    public void shuffle() {
        deck.shuffle();
    }

    // Start of the game dealing
    public void deal(Player[] players) {
        for( int i = 0; i < 2; i++ ) {
            for (Player player : players) {
                hit(player);
            }
        }
    }

    // "Hit me"
    public void hit(Player player) {
        player.getHand().add(deck.drawCard());
    }
}
