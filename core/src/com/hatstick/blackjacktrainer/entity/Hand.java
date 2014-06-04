package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

/**
 * Created by Alex on 5/31/2014.
 */
public class Hand {

    private ArrayList<Card> hand;

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
}
