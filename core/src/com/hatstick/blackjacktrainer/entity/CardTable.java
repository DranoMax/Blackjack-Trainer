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
			int spacer = 0;
			for( Player plyr : seats ) {
				cardSize.set(BlackjackTrainer.SCREEN_WIDTH/(getNumberPlayers()+5),BlackjackTrainer.SCREEN_HEIGHT/(getNumberPlayers()+1));
				plyr.setPosition(new Vector2(BlackjackTrainer.SCREEN_WIDTH/(getNumberPlayers()+1)-cardSize.x/4-spacer,0));
				spacer += BlackjackTrainer.SCREEN_WIDTH/TABLE_SIZE;
				dealer.setPosition(new Vector2(BlackjackTrainer.SCREEN_WIDTH/2-cardSize.x/4,BlackjackTrainer.SCREEN_HEIGHT-cardSize.y/1.5f));
			}
		}
	}

	public int getNumberPlayers() {
		return seats.size();
	}

}
