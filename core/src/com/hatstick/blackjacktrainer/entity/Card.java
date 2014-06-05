package com.hatstick.blackjacktrainer.entity;

/**
 * Created by Alex on 5/31/2014.
 */
public class Card {

    private final String suit;
    private final String name;
    private final int value;

    enum SUIT {HEARTS, SPADES, CLUBS, DIAMONDS};
    enum NAME {KING, QUEEN, JACK, ACE, NONE};

    public Card(SUIT suit, NAME name, int value) {
        this.suit = getSuitString(suit);
        this.name = getNameString(name);
        this.value = value;

    }

    private String getSuitString(SUIT suit) {
        switch(suit) {
            case HEARTS:
                return "hearts";
            case SPADES:
                return "spades";
            case CLUBS:
                return "clubs";
            case DIAMONDS:
                return "diamonds";
            default:
                return null;
        }
    }

    private String getNameString(NAME name) {
        switch(name) {
            case ACE:
                return "ace";
            case JACK:
                return "jack";
            case QUEEN:
                return "queen";
            case KING:
                return "king";
            default:
                return null;
        }
    }

    public String getCard() {
    	if( name != null ) {
            return (name + "_of_" + suit);
        }
        else {
            return (value + "_of_" + suit);
        }
    }
    
    public void printCard() {
        if( name != null ) {
            System.out.print(name + "_of_" + suit);
        }
        else {
            System.out.print(value + "_of_" + suit);
        }
    }

    public String getSuit() {
        return suit;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
