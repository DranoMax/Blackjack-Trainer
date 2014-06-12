package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.hatstick.blackjacktrainer.BlackjackTrainer;
import com.hatstick.blackjacktrainer.screens.PlayBlackjackScreen;

public class CardTable {

	private ArrayList<Player> seats;
	private final int TABLE_SIZE = 4;
	private Dealer dealer;

	public CardTable(Dealer dealer) {
		// 4 seats at the table
		seats = new ArrayList<Player>();
		this.dealer = dealer;
		
		// Sit the dealer down
		this.dealer.setPosition(new Vector2(BlackjackTrainer.SCREEN_WIDTH/2-PlayBlackjackScreen.cardSize.x*2,0));
	}

	public void sitDown(Player player) {

		if( seats.size() < TABLE_SIZE ) {
			seats.add(player);

			// Use spacer+TABLE_SIZE to divide us up some even spacing
			int spacer = 0;
			for( Player plyr : seats ) {
				PlayBlackjackScreen.cardSize.set(BlackjackTrainer.SCREEN_WIDTH/(getNumberPlayers()+5),BlackjackTrainer.SCREEN_HEIGHT/(getNumberPlayers()+1));
				plyr.setPosition(new Vector2(BlackjackTrainer.SCREEN_WIDTH/(getNumberPlayers()+1)-PlayBlackjackScreen.cardSize.x/4+spacer,0));
				spacer += PlayBlackjackScreen.cardSize.x*2;
			}
			dealer.setPosition(new Vector2(BlackjackTrainer.SCREEN_WIDTH/2-PlayBlackjackScreen.cardSize.x/4,BlackjackTrainer.SCREEN_HEIGHT-PlayBlackjackScreen.cardSize.y));
		}
	}

	public int getNumberPlayers() {
		return seats.size();
	}

}
