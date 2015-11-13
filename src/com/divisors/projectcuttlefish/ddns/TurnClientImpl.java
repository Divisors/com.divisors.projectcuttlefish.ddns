package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TurnClientImpl implements TurnClient {
	private URL server;
	private final String username;
	private final String credential;
	public TurnClientImpl(String server, String username, String credential) throws MalformedURLException {
		this.server = new URL(server);
		this.username = username;
		this.credential = credential;
	}
	
	public String getServer() {
		return server.toString();
	}
	
	public String toString() {
		return server.toString();
	}

	@Override
	public String getIp() throws IOException {
		//URLConnection conn = server.openConnection();
		
		
		return "1234";
	}

}
