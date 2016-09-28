package com.miguelrn.serverdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server extends Thread {
	
	private String version = "development 0.1.0";
	private int port = 1188;
	private static DatagramSocket socket;
	private int maxPacketSize = 1024;
	private boolean listening = true;
	
	public Server() {
		logOutput("Starting server version " + version, 3);
		try {
			logOutput("Attempting to bind socket on port " + port, 3);
			socket = new DatagramSocket(port);
			logOutput("Server started!", 3);
		} catch (Exception e) {
			logOutput(e.toString(), 0);
		}
	}
	
	public void run() {
		while (listening) {
			byte[] data = new byte[maxPacketSize];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch(Exception e) {
				logOutput(e.toString(), 0);
			}
			packetHandler(packet);
		}
		socket.close();
	}
	
	public void halt() {
		logOutput("Disconnecting connected clients...", 3);
		//Send global disconnect
		ClientHandler.sendGlobally("!disconenct"); //implement handler in client to process !disconnect command to close socket connection with server
		//Stop while(listening) to close socket
		listening = false;
		logOutput("Server stopped!", 1);
	}
	
	private void packetHandler(DatagramPacket packet) {
		String data = new String(packet.getData()).trim();
		logOutput("CLIENT ("+packet.getSocketAddress().hashCode()+") > " + data, 3);
		switch(data.toLowerCase()){
			case "ping":
				sendPacket(packet.getAddress(), packet.getPort(), "pong!");
				logOutput("sent pong", 3);
				break;
			case "tokencheck":
				//TODO add class to interface w MySQL db to verify token
				logOutput("tokenCheck requested", 3);
				ClientHandler.addClient(packet.getSocketAddress().hashCode(), 6, packet.getAddress(), packet.getPort(), "faketoken123");
				sendPacket(packet.getAddress(), packet.getPort(), "You are now authenticated!");
				break;
		}
	}
	
	public static void sendPacket(InetAddress clientip, int port, String message) {
		byte[] d = message.getBytes();
		try {
		    socket.send(new DatagramPacket(d, d.length, clientip, port));
		} catch (IOException e) {
		    logOutput(e.toString(), 0);
		}
    	}
	
	public static void logOutput(String output, Integer type) {
		String Type;
		if (type == 0) {
			Type = "[ERROR] ";
		}else if (type == 1) {
			Type = "[WARNING] ";
		}else if (type == 2) {
			Type = "[CMD] ";
		}else  {
			Type = "[INFO] ";
		}
		System.out.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ").format(new Date()) + Type + output);
	}		
}
