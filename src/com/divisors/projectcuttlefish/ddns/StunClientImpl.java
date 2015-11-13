package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class StunClientImpl implements StunClient {
	private URL server;
	private final String username;
	private final char[] credential;
	public StunClientImpl(String server, String username, char[] credential) throws MalformedURLException {
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
		SSLSocketFactory sslsocketfactory=(SSLSocketFactory) SSLSocketFactory.getDefault();
		
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(server.getHost(), 443);
		try (OutputStream os = sslsocket.getOutputStream()) {
			byte[] b = new byte[160];
			
		}
		try (InputStream is = sslsocket.getInputStream()) {
			byte[] b = new byte[160];
			is.read(b);
			System.out.println("Recieved:");
			System.out.println(StunMessage.formatBytes(b));
		}
		return "1234";
	}

}
