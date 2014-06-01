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
                return "Hearts";
            case SPADES:
                return "Spades";
            case CLUBS:
                return "Clubs";
            case DIAMONDS:
                return "Diamonds";
            default:
                return null;
        }
    }

    private String getNameString(NAME name) {
        switch(name) {
            case ACE:
                return "Ace";
            case JACK:
                return "Jack";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            default:
                return null;
        }
    }

    public void printCard() {
        if( name != null ) {
            System.out.println(name + " of " + suit);
        }
        else {
            System.out.println(value + " of " + suit);
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
