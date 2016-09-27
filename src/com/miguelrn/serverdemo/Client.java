package com.miguelrn.serverdemo;

import java.net.InetAddress;

public class Client {
	private int socketid;
	private int accountid;
	private InetAddress ip;
	private int port;
	private String token;

	public Client(int socketid, int accountid, InetAddress ip, int port, String token) {
		this.socketid = socketid;
		this.accountid = accountid;
		this.ip = ip;
		this.port = port;
		this.token = token;
	}
	
	public int getSocketID() {
		return socketid;
	}
	
	public int getAccountID() {
		return accountid;
	}
	
	public InetAddress getIP() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}


}
