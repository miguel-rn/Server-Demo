package com.miguelrn.serverdemo;

import java.net.InetAddress;
import java.util.ArrayList;

public class ClientHandler {
	
	private static ArrayList<Client> activeClients = new ArrayList<Client>();
	
	public static void addClient(int socketid, int accountid, InetAddress ip, int port, String token) {
		activeClients.add(new Client(socketid, accountid, ip, port, token));
		Server.logOutput("Client ("+ip+":"+port+") authenticated account ID "+accountid+" with token"+token, 3);
		Server.logOutput("Account ID: " + getAccountID(socketid), 3);
	}
	
	public static void removeClient(int socketid) {
		for(Client _item : activeClients)
        {
            if(_item.getSocketID() == socketid)
                activeClients.remove(activeClients.indexOf(_item));
        }
	}
	
	public static int getAccountID(int socketid) {
		for(Client _item : activeClients)
        {
            if(_item.getSocketID() == socketid)
                return activeClients.get(activeClients.indexOf(_item)).getAccountID();
        }
		return -1;
	}

}
