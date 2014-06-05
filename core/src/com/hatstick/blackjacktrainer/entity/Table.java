package com.hatstick.blackjacktrainer.entity;

public class Table {
	
	private Player[] seats;
	
	public Table() {
		// 4 seats at the table
		seats = new Player[3];
	}
	
	public void sitDown(Player player, int seat) {
		seats[seat] = player;
	}

}
