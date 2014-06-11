package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.hatstick.blackjacktrainer.BlackjackTrainer;

public class CardTable {

	private ArrayList<Player> seats;
	private final int TABLE_SIZE = 4;
	private Dealer dealer;
	
	private Vector2 cardSize;

	public CardTable(Dealer dealer, Vector2 cardSize) {
		// 4 seats at the table
		seats = new ArrayList<Player>();
		this.dealer = dealer;
		
		this.cardSize = cardSize;
		
		// Sit the dealer down
		this.dealer.setPosition(new Vector2(BlackjackTrainer.SCREEN_WIDTH/2-cardSize.x*2,0));
	}

	public void sitDown(Player player) {

		if( seats.size() < TABLE_SIZE ) {
			seats.add(player);

			// Use spacer+TABLE_SIZE to divide us up some even spacing
			// TODO: 1.75f is a number found to look good - should be something independent instead.
			int spacer = 0;
			for( Player plyr : seats ) {
				plyr.setPosition(new Vector2(BlackjackTrainer.SCREEN_WIDTH/(getNumberPlayers()+1)-spacer,0));
				System.out.println("PLAYER POS: " + plyr.getPosition());
				spacer += BlackjackTrainer.SCREEN_WIDTH/TABLE_SIZE;
				cardSize.set(BlackjackTrainer.SCREEN_WIDTH/(getNumberPlayers()+5),BlackjackTrainer.SCREEN_HEIGHT/(getNumberPlayers()+1));
				dealer.setPosition(new Vector2(BlackjackTrainer.SCREEN_WIDTH/2-cardSize.x/4,BlackjackTrainer.SCREEN_HEIGHT-cardSize.y/1.5f));
				System.out.println("SIZE: " + cardSize);
			}
		}
	}

	public int getNumberPlayers() {
		return seats.size();
	}

}
