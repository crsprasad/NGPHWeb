package com.logica.ngph.utils;



import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


/**
 * -----------------------------------------------------------------------------
 * The following example implements a class for encrypting and decrypting
 * strings using several Cipher algorithms. The class is created with a key and
 * can be used repeatedly to encrypt and decrypt strings using that key.
 * Some of the more popular algorithms are:
 *      Blowfish
 *      DES
 *      DESede
 *      PBEWithMD5AndDES
 *      PBEWithMD5AndTripleDES
 *      TripleDES
 * 
 * @version 1.0
 * @author  Jeffrey M. Hunter  (jhunter@idevelopment.info)
 * @author  http://www.idevelopment.info
 * -----------------------------------------------------------------------------
 */

public class StringEncrypter {

    Cipher ecipher;
    Cipher dcipher;


    /**
     * Constructor used to create this object.  Responsible for setting
     * and initializing this object's encrypter and decrypter Chipher instances
     * given a Secret Key and algorithm.
     * @param key        Secret Key used to initialize both the encrypter and
     *                   decrypter instances.
     * @param algorithm  Which algorithm to use for creating the encrypter and
     *                   decrypter instances.
     */
 


    /**
     * Constructor used to create this object.  Responsible for setting
     * and initializing this object's encrypter and decrypter Chipher instances
     * given a Pass Phrase and algorithm.
     * @param passPhrase Pass Phrase used to initialize both the encrypter and
     *                   decrypter instances.
     */
    StringEncrypter(String passPhrase) {

        // 8-bytes Salt
        byte[] salt = {
            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
            (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
        };

        // Iteration count
        int iterationCount = 19;

        try {

            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameters to the cipthers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("EXCEPTION: InvalidAlgorithmParameterException");
        } catch (InvalidKeySpecException e) {
            System.out.println("EXCEPTION: InvalidKeySpecException");
        } catch (NoSuchPaddingException e) {
            System.out.println("EXCEPTION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("EXCEPTION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            System.out.println("EXCEPTION: InvalidKeyException");
        }
    }


    /**
     * Takes a single String as an argument and returns an Encrypted version
     * of that String.
     * @param str String to be encrypted
     * @return <code>String</code> Encrypted version of the provided String
     */
    public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);

        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }


    /**
     * Takes a encrypted String as an argument, decrypts and returns the 
     * decrypted String.
     * @param str Encrypted String to be decrypted
     * @return <code>String</code> Decrypted version of the provided String
     */
    public String decrypt(String str) {

        try {

            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");

        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }


    /**
     * The following method is used for testing the String Encrypter class.
     * This method is responsible for encrypting and decrypting a sample
     * String using several symmetric temporary Secret Keys.
     */
   

    /**
     * The following method is used for testing the String Encrypter class.
     * This method is responsible for encrypting and decrypting a sample
     * String using using a Pass Phrase.
     */
    public static String encryptURL(String passPhrase,String secretString) {

        // Create encrypter/decrypter class
        StringEncrypter desEncrypter = new StringEncrypter(passPhrase);

        // Encrypt the string
        String desEncrypted  = desEncrypter.encrypt(secretString);

        return desEncrypted;
    }
    public static String decryptURL(String passPhrase,String secretString) {

        // Create encrypter/decrypter class
        StringEncrypter desEncrypter = new StringEncrypter(passPhrase);
        //if string contains space in between,replace it with plus sign.By Ashu
        secretString = secretString.replaceAll(" ","+");

        // Decrypt the string
        String desDecrypted       = desEncrypter.decrypt(secretString);
        return desDecrypted;
    }


    /**
     * Sole entry point to the class and application used for testing the
     * String Encrypter class.
     * @param args Array of String arguments.
     */
  
    public static String convertHexToString(String hex){
    	 
  	  StringBuilder sb = new StringBuilder();
  	  StringBuilder temp = new StringBuilder();
   
  	  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
  	  for( int i=0; i<hex.length()-1; i+=2 ){
   
  	      //grab the hex in pairs
  	      String output = hex.substring(i, (i + 2));
  	      //convert hex to decimal
  	      int decimal = Integer.parseInt(output, 16);
  	      //convert the decimal to character
  	      sb.append((char)decimal);
   
  	      temp.append(decimal);
  	  }
  	  System.out.println("Decimal : " + temp.toString());
   
  	  return sb.toString();
    }
    
public static void main(String[] args) {
	try
	{
	String str1=StringEncrypter.encryptURL("rajat", "sid");
	System.out.println(str1);
	str1 = new String(new sun.misc.BASE64Encoder().encode(str1.getBytes()));
	
	System.out.println(str1);
	
	String s2 = new String(new sun.misc.BASE64Decoder().decodeBuffer(str1));
	System.out.println("S2 : - "+s2);
	
	String Str2 = StringEncrypter.decryptURL("rajat", new String(new sun.misc.BASE64Decoder().decodeBuffer("dUNmeTJ2dEhxVHc9")));
	System.out.println("Decode : - "+Str2);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
}


