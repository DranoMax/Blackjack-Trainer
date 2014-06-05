package com.hatstick.blackjacktrainer.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Table {

	private ArrayList<Player> seats;
	private final int TABLE_SIZE = 4;
	private int screenWidth;

	public Table(int screenWidth, int screenHeight) {
		// 4 seats at the table
		seats = new ArrayList<Player>();
		this.screenWidth = screenWidth;
	}

	public void sitDown(Player player) {

		if( seats.size() < TABLE_SIZE ) {
			seats.add(player);

			// Use spacer+TABLE_SIZE to divide us up some even spacing
			int spacer = 0;
			for( Player plyr : seats ) {
				plyr.setPosition(new Vector2(spacer,50));
				spacer += screenWidth/TABLE_SIZE;
			}
		}
	}

	public int getNumberPlayers() {
		return seats.size();
	}

}
