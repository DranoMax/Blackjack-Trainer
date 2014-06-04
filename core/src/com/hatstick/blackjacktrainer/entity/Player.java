package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

/**
 * Created by Alex on 5/31/2014.
 */
public class Player {

    private int chips;
    private String name;
    private Hand hand;

    public Player(String name) {
        this.chips = 100;
        this.name = name;
        this.hand = new Hand();
    }

    public ArrayList<Card> getHand() {
        return hand.getHand();
    }

    public void printHand() {
        System.out.print(name + " has ");
        hand.printHand();
        System.out.println();
    }
}
