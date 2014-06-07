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

	// If it's player turn, show buttons for interacting
	private boolean playerTurn = false;

	public Dealer() {
		this.deck = new Deck();
		this.hand = new Hand();
		this.position = new Vector2();
	}

	/**
	 * Shuffle and deal
	 * @param players
	 */
	public void beginRound(ArrayList<Player> players) {
		shuffle();
		deal(players);
	}

	/**
	 * Perform each player's turn (humanplayer in a loop)
	 * @param players
	 */
	public void continueRound(ArrayList<Player> players) {
		// Now each player takes their turn
		for (Player player : players) {
			if (player instanceof ComputerPlayer) {
				playerTurn((ComputerPlayer)player);
			}
			else {
				playerTurn((HumanPlayer)player);
			}
		}

		// Dealer's turn
		if (playerTurn == false) {
			dealerTurn();
			finishRound(players);
		}
	}

	/**
	 * Check to see who won.
	 * @param players
	 */
	private void finishRound(ArrayList<Player> players) {
		for (Player player : players) {
			// Pay 3:2 for blackjack
			switch(player.getHand().getStatus()) {

			case Hand.BLACKJACK:
				blackjack(player);
				break;
			case Hand.STAND:
				checkForWin(player);
				break;
			case Hand.BUST:
				bustedHand(player);
				break;
			}
		}
	}

	/**
	 * Player won blackjack!  Recieve bet 3:2
	 * @param player
	 */
	private void blackjack(Player player) {
		player.getHand().printHand();
		System.out.println("BLACKJACK!!");
	}

	private void checkForWin(Player player) {

		// Check if Dealer busted
		if (hand.getStatus() == Hand.BUST && player.getHand().getStatus() != Hand.BUST) {
			System.out.println("Player wins!");
		}
		// Else check the totals
		else {
			if (player.getHand().getTotal() > hand.getTotal()) {
				System.out.println("Player wins!");
			}
			else if (player.getHand().getTotal() == hand.getTotal()) {
				System.out.println("PUSH");
			}
			else {
				System.out.println("LOST!");
			}
		}
	}

	private void bustedHand(Player player) {
		System.out.println("BUST!");
	}

	public void playerTurn(ComputerPlayer player) {
		// Player logic - simple - draw till >= 17
		playerTurn = false;
		Hand hand = player.getHand();
		while (hand.getTotal() < 17 && hand.getStatus() != Hand.BUST) {
			hit(player);
		}
		// If we didn't bust, stand.
		if (hand.getStatus() != Hand.BUST) {
			hand.setStatus(Hand.STAND);
		}

	}

	public void playerTurn(HumanPlayer player) {
		playerTurn = true;
		if (player.getHand().getStatus() != Hand.OPEN) {
			playerTurn = false;
		}
	}

	public void dealerTurn() {

		while (hand.getTotal() < 17 && hand.getStatus() != Hand.BUST) {
			hand.addCard(deck.drawCard());
		}
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

	public boolean isPlayerTurn() {
		return playerTurn;
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
