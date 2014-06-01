package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

/**
 * Created by Alex on 5/31/2014.
 */
public class Player {

    private int chips;
    private Hand hand;

    public Player() {
        this.chips = 100;
    }

    public ArrayList<Card> getHand() {
        return hand.getHand();
    }
}
