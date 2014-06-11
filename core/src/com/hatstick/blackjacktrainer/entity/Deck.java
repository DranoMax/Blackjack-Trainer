package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex on 5/31/2014.
 */
public class Deck {

	static final int MAXIMUM_CARDS = 52;
	private List<Card> deck = new ArrayList<Card>();

	public Deck() {
		createDeck();
	}

	private void createDeck() {
		for (Card.SUIT suit : Card.SUIT.values()) {
			deck.add(new Card(suit, Card.NAME.ACE, 1));
			deck.add(new Card(suit, Card.NAME.NONE, 2));
			deck.add(new Card(suit, Card.NAME.NONE, 3));
			deck.add(new Card(suit, Card.NAME.NONE, 4));
			deck.add(new Card(suit, Card.NAME.NONE, 5));
			deck.add(new Card(suit, Card.NAME.NONE, 6));
			deck.add(new Card(suit, Card.NAME.NONE, 7));
			deck.add(new Card(suit, Card.NAME.NONE, 8));
			deck.add(new Card(suit, Card.NAME.NONE, 9));
			deck.add(new Card(suit, Card.NAME.NONE, 10));
			deck.add(new Card(suit, Card.NAME.JACK, 10));
			deck.add(new Card(suit, Card.NAME.QUEEN, 10));
			deck.add(new Card(suit, Card.NAME.KING, 10));
		}
	}

	public void shuffle() {
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		Collections.shuffle(deck);
	}

	public Card drawCard() {
		try {
			Card card = deck.get(0);
			deck.remove(0);
			return card;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public List getDeck() {
		return deck;
	}
}
