package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.math.Vector2;
import com.hatstick.blackjacktrainer.BlackjackTrainer;
import com.hatstick.blackjacktrainer.tween.CardAccessor;

/**
 * Created by Alex on 5/31/2014.
 */
public class Dealer {

	private Deck deck;
	private Hand hand;
	private Vector2 position;

	// If it's player turn, show buttons for interacting
	private boolean playerTurn = false;
	// Has round started?
	private boolean startedRound = false;

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
		startedRound = true;
		shuffle();

		// Reset player hands if haven't already
		for (Player player : players) {
			player.discardHand();
			player.setFinishedTurn(false);
		}
		// New dealer hand
		hand = new Hand();
		deal(players);
	}

	/**
	 * Perform each player's turn (HumanPlayer in a loop)
	 * @param players
	 */
	public void continueRound(ArrayList<Player> players) {
		// Now each player takes their turn
		for (Player player : players) {
			// Have they already finished their turn?
			if (!player.isTurnFinished()) {
				if (player instanceof ComputerPlayer) {
					playerTurn((ComputerPlayer)player);
				}
				else {
					playerTurn((HumanPlayer)player);
				}
			}
		}

		// Dealer's turn
		if (!playerTurn && startedRound) {
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
		startedRound = false;
	}

	/**
	 * Player won blackjack!  Receive bet 3:2
	 * @param player
	 */
	private void blackjack(Player player) {
		player.getHand().printHand();
		System.out.println("BLACKJACK!!");
		// Round down
		player.setChips((int)Math.floor(player.getBet()*1.5f));
	}

	private void checkForWin(Player player) {

		// Check if Dealer busted
		if (hand.getStatus() == Hand.BUST && player.getHand().getStatus() != Hand.BUST) {
			System.out.println("Player wins!");
			player.setChips(player.getBet());
		}
		// Else check the totals
		else {
			if (player.getHand().getTotal() > hand.getTotal()) {
				System.out.println("Player wins!");
				player.setChips(player.getBet());
			}
			else if (player.getHand().getTotal() == hand.getTotal()) {
				System.out.println("PUSH");
			}
			else {
				System.out.println("LOST!");
				player.setChips(-player.getBet());
			}
		}
	}

	private void bustedHand(Player player) {
		player.getHand().setStatus(Hand.BUST);
		player.setChips(-player.getBet());
		System.out.println("BUST!");
	}

	public void playerTurn(ComputerPlayer player) {
		// Player logic - simple - draw till >= 17
		Hand hand = player.getHand();
		while (hand.getTotal() < 17 && hand.getStatus() != Hand.BUST) {
			hit(player);
		}
		// If we didn't bust, stand.
		if (hand.getStatus() != Hand.BUST) {
			hand.setStatus(Hand.STAND);
		}
		player.setFinishedTurn(true);
	}

	public void playerTurn(HumanPlayer player) {
		playerTurn = true;
		if (player.getHand().getStatus() != Hand.OPEN) {
			playerTurn = false;
			player.setFinishedTurn(true);
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
			Card card = deck.drawCard();
			tweenCard(card,getPosition());
			hand.getHand().add(card);
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

			Card card = deck.drawCard();
			tweenCard(card, player.getPosition());
			hand.getHand().add(card);

			if (hand.getTotal() > 21) {
				hand.setStatus(Hand.BUST);
			}
		}
	}	

	private void tweenCard(Card card, Vector2 pos) {
		// Animate card
		Tween.to(card,CardAccessor.POSITION_XY, BlackjackTrainer.tweenSpeed)
		.target(pos.x,pos.y)
		.start(BlackjackTrainer.tweenManager);
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

	public boolean isRoundStarted() {
		return startedRound;
	}

}
