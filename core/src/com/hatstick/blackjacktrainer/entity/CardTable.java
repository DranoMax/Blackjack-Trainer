package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class CardTable {

	private ArrayList<Player> seats;
	private final int TABLE_SIZE = 4;
	private int screenWidth;
	private int screenHeight;
	private Dealer dealer;
	
	private Vector2 cardSize;

	public CardTable(Dealer dealer, int screenWidth, int screenHeight, Vector2 cardSize) {
		// 4 seats at the table
		seats = new ArrayList<Player>();
		this.dealer = dealer;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		this.cardSize = cardSize;
		
		// Sit the dealer down
		this.dealer.setPosition(new Vector2(screenWidth/2-cardSize.x*2,0));
	}

	public void sitDown(Player player) {

		if( seats.size() < TABLE_SIZE ) {
			seats.add(player);

			// Use spacer+TABLE_SIZE to divide us up some even spacing
			// TODO: 1.75f is a number found to look good - should be something independent instead.
			int spacer = 0;
			for( Player plyr : seats ) {
				plyr.setPosition(new Vector2(spacer,0));
				spacer += screenWidth/TABLE_SIZE;
				cardSize.set(screenWidth/(getNumberPlayers()+3),screenHeight/(getNumberPlayers()+1));
				dealer.setPosition(new Vector2(screenWidth/2-cardSize.x/4,screenHeight-cardSize.y/1.5f));
				System.out.println("SIZE: " + cardSize);
			}
		}
	}

	public int getNumberPlayers() {
		return seats.size();
	}

}
