package com.miguelrn.serverdemo.entity;

import java.util.HashMap;

public class Player {
	private String socketid; //which client does this player belong to
	private String name; //what is the name set in db for this player
	private int namecolour; //set name colour based on permission
	private int id; //what is the UID of the player
	private int clanid; //what clanid does the player belong to? null if none
	private int permission; //what permission does the player have 0=player 1=vip 255=admin
	
	public int x;
	public int y;
	public int rotation;
	public int health;
	//public HashMap<itemid, amount> inventory;
	
	public void setPosition(int x, int y) {
		this.x  = x;
		this.y = y;
	}

}
