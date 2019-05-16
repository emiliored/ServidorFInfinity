/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payata.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * 
 * @author usuario
 */
public class Cifrado {
    
    	// Get a encrypted password using PBKDF2 hash algorithm
	public static final String getEncryptedPassword(String password,String salt) throws Exception{
		String algoritmo="PBKDF2WithHmacSHA512";
		int derivedKeyLength = 64; //Produce un hash de 512 bits / 8 bits por cada byte = 64 bytes
		int iterations=200000;//StableBit uses 200,000 iterations of SHA-512
		KeySpec spec= new PBEKeySpec(password.toCharArray(),Base64.getDecoder().decode(salt),iterations,derivedKeyLength);
		SecretKeyFactory f=SecretKeyFactory.getInstance(algoritmo);
		byte[] encBytes=f.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(encBytes);
	}//Bien
	
	// Returns base64 encoded salt
	public static final String getNewSalt() throws Exception{
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt=new byte[8];
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	} //Bien

	// Algoritmo para comprobar la integridad de un archivo.
	public static final String createSha1(File file) throws Exception  {
		//Preparaciones.
	    MessageDigest digest = MessageDigest.getInstance("SHA-1");
	    InputStream fis = new FileInputStream(file);
	    //Comienzo a calcular el hash.
	    byte[] buffer = new byte[8192];
	    for(int n = fis.read(buffer);n>0;n=fis.read(buffer)) 
	    	digest.update(buffer, 0, n);
	    fis.close();
	    return Base64.getEncoder().encodeToString(digest.digest());
	} //Bien
    
}
