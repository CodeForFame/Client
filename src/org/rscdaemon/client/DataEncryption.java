package org.rscdaemon.client;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class DataEncryption {

	public DataEncryption(byte abyte0[]) {
		packet = abyte0;
		offset = 0;
	}

	public void addByte(int i) {
		packet[offset++] = (byte) i;
	}

	public void add4ByteInt(int i) {
		packet[offset++] = (byte) (i >> 24);
		packet[offset++] = (byte) (i >> 16);
		packet[offset++] = (byte) (i >> 8);
		packet[offset++] = (byte) i;
	}

	public void addString(String s) {
		byte[] data = s.getBytes();
		for(byte b : data) {
			packet[offset++] = b;
		}
		
	}

	public void addBytes(byte abyte0[], int i, int j) {
		for (int k = i; k < i + j; k++)
			packet[offset++] = abyte0[k];

	}

	public int getByte() {
		return packet[offset++] & 0xff;
	}

	public int get2ByteInt() {
		offset += 2;
		return ((packet[offset - 2] & 0xff) << 8) + (packet[offset - 1] & 0xff);
	}

	public int get4ByteInt() {
		offset += 4;
		return ((packet[offset - 4] & 0xff) << 24)
				+ ((packet[offset - 3] & 0xff) << 16)
				+ ((packet[offset - 2] & 0xff) << 8)
				+ (packet[offset - 1] & 0xff);
	}

	public void getBytes(byte abyte0[], int i, int j) {
		for (int k = i; k < i + j; k++)
			abyte0[k] = packet[offset++];

	}

	
	private static KeyFactory keyFactory;
	private static PublicKey pubKey;
	static {
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(new BigInteger("258483531987721813854435365666199783121097212864526576114955744050873252978581213214062885665119329089273296913884093898593877564098511382732309048889240854054459372263273672334107564088395710980478911359605768175143527864461996266529749955416370971506195317045377519645018157466830930794446490944537605962330090699836840861268493872513762630835769942133970804813091619416385064187784658945").toByteArray()));
			}
		catch(Exception e) {}
	}
	public static byte[] encrypt(byte[] text) throws Exception
	{

	      byte[] cipherText = null;
	      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, pubKey);
	      cipherText = cipher.doFinal(text);
	      return cipherText;

	}
	public byte packet[];
	public int offset = 0;
}
