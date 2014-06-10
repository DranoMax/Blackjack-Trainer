package com.hatstick.blackjacktrainer.entity;

import com.badlogic.gdx.math.Vector2;
import com.hatstick.blackjacktrainer.BlackjackTrainer;

/**
 * Created by Alex on 5/31/2014.
 */
public class Card {

	private final String suit;
	private final String name;
	private final int value;
	private int size = 0;
	
	private Vector2 position;

	enum SUIT {HEARTS, SPADES, CLUBS, DIAMONDS};
	enum NAME {KING, QUEEN, JACK, ACE, NONE};

	public Card(SUIT suit, NAME name, int value) {
		this.suit = getSuitString(suit);
		this.name = getNameString(name);
		this.value = value;
		position = new Vector2(BlackjackTrainer.SCREEN_WIDTH/3, BlackjackTrainer.SCREEN_HEIGHT);
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

	/**
	 * Handles case that face name is "Ace"
	 * @param total
	 * @return
	 */
	public int getValue(int total) {
		if( name != null && name.equals("ace") && total+11 <= 21 ) {
			return 11;
		} else {
			return value;
		}
	}

	public void setSize(int i) {
		size = i;
	}

	public int getSize() {
		return size;
	}
	
	public void setPosition(Vector2 pos) {
		position.set(pos);
	}
	
	public void setPosition(float x, float y) {
		position.set(x,y);
	}
	
	public Vector2 getPosition() {
		return position;
	}

}
