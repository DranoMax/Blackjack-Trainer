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
	
	public void beginRound(ArrayList<Player> players) {
		shuffle();
    	deal(players);
	}

	public void shuffle() {
		deck.shuffle();
	}

	// Start of the game dealing
	public void deal(ArrayList<Player> players) {
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				hit(player);
			}
			// Draw card for dealer
			hand.getHand().add(deck.drawCard());
		}
		checkForBlackjack(players);
	}
	
	/**
	 * Checks for blackjack on first hand sets status
	 */
	public void checkForBlackjack(ArrayList<Player> players) {
		for (Player player : players) {
			Hand hand = player.getHand();
			if (hand.getTotal() == 21) {
				hand.setStatus(Hand.BLACKJACK);
			}
		}
	}

	// "Hit me"
	public void hit(Player player) {
		Hand hand = player.getHand();
		if (hand.getStatus() == Hand.OPEN) {
			hand.addCard(deck.drawCard());
			if (hand.getTotal() > 21) {
				hand.setStatus(Hand.BUST);
			}
		}
	}	

	// Check for winners
	public void checkHands(ArrayList<Player> players) {
		for (Player player : players) {
			// If
			if (player.getHand().getStatus() != Hand.OPEN) {

			}
		}
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
