package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class StunMessage {
	public static String formatBytes(byte[] array) {
		char[] hex = "0123456789ABCDEF".toCharArray();
		StringBuffer result = new StringBuffer();
		for (int i=0; i<array.length; i++) {
			int b = array[i] & 0xFF;
			result.append(hex[b & 0xF]);
			result.append(hex[(b & 0xF0)>>4]);
			result.append(" ");
			if (i % 4 == 0)
				result.append(" ");
			if (i % 32 == 0)
				result.append("\n");
		}
		return result.toString();
	}
	public static final long MAGIC_VALUE = 0x2112A442;
	public static StunMessage fromStream(InputStream is) throws IOException {
		byte[] buffer = new byte[20];
		if (is.read(buffer) < buffer.length)
			throw new IOException("Not enough bytes!");
		if ((buffer[0] & 0b11000000) != 0) //first 2 bytes weren't 0
			throw new IOException("The most significant 2 bits of every STUN message MUST be zeroes.");
		
		ByteBuffer header = ByteBuffer.wrap(buffer);
		
		short msgType = (short) (header.getShort() & 0x3FFF);
		
		int length = header.getShort() & 0xFFFF;
		
		if ((header.getInt() & 0xFFFFFFFF) != MAGIC_VALUE)
			throw new IOException("Magic was incorrect value!");
		
		
		
		return null;
	}
	protected final short msgType;
	protected int length;
	public StunMessage(short msgType) {
		this.msgType = msgType;
	}
	public short getMessageType() {
		return msgType;
	}
	public boolean isRequest() {
		return (msgType & 0b0110) == 0;
	}
	public boolean isIndication() {
		return false;//(msgType & 0x0110) == 0x0010;
	}
	public boolean isSuccessResponse() {
		return false;//(msgType & 0x0110) == 0x0100;
	}
	public boolean isErrorResponse() {
		return false;//(msgType & 0x0110) == 0x0110;
	}
}
