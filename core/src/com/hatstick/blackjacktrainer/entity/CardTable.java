package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class CardTable {

	private ArrayList<Player> seats;
	private final int TABLE_SIZE = 4;
	private int screenWidth;
	private int screenHeight;

	public CardTable(int screenWidth, int screenHeight) {
		// 4 seats at the table
		seats = new ArrayList<Player>();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public void sitDown(Player player) {

		if( seats.size() < TABLE_SIZE ) {
			seats.add(player);

			// Use spacer+TABLE_SIZE to divide us up some even spacing
			// TODO: 1.75f is a number found to look good - should be something independent instead.
			int spacer = 0;
			for( Player plyr : seats ) {
				plyr.setPosition(new Vector2(spacer,-screenHeight/3f));
				spacer += screenWidth/TABLE_SIZE;
			}
		}
	}

	public int getNumberPlayers() {
		return seats.size();
	}

}
