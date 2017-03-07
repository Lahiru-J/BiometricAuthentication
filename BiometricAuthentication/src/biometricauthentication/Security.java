/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Lahiru
 */
public class Security {
    
    private static int MARGIN = 1;
    private static final byte[] key = "MyDifficultPassw".getBytes();;
    private static final String transformation = "Blowfish";

    public static void encrypt(Serializable object) 
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, 
            InvalidKeyException, IllegalBlockSizeException {
        
        
        try {
            // Length is 16 byte
            SecretKeySpec sks = new SecretKeySpec(key, transformation);

            // Create cipher
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            SealedObject sealedObject = new SealedObject(object, cipher);

            // Wrap the output stream
            CipherOutputStream cos = 
                    new CipherOutputStream(new ObjectOutputStream(
                            new FileOutputStream("src/SerializedObjects/handList.ser")), cipher);
            
            ObjectOutputStream outputStream = new ObjectOutputStream(cos);
            outputStream.writeObject(sealedObject);
            outputStream.close();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static Object decrypt() 
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, 
            InvalidKeyException, BadPaddingException {

        SecretKeySpec sks = new SecretKeySpec(key, transformation);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, sks);

        CipherInputStream cipherInputStream = 
                new CipherInputStream(new ObjectInputStream(new FileInputStream("src/SerializedObjects/handList.ser")), cipher);
        ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
        SealedObject sealedObject;

        try {
            sealedObject = (SealedObject) inputStream.readObject();
            return sealedObject.getObject(cipher);
        } catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean checkForMatch(Hand hand){
        for (Iterator<Hand> iterator = 
                BiometricAuthentication.HandList.iterator(); iterator.hasNext();) {
            Hand storedHand = iterator.next();
            
            double slf = storedHand.getLittleFingerLen();
            double srf = storedHand.getRingFingerLen();
            double smf = storedHand.getMiddleFingerLen();
            double sinf = storedHand.getIndexFingerLen();
            double stf = storedHand.getThumbFingerLen();
            
            double lf = hand.getLittleFingerLen();
            double rf = hand.getRingFingerLen();
            double mf = hand.getMiddleFingerLen();
            double inf = hand.getIndexFingerLen();
            double tf = hand.getThumbFingerLen();
            
            if(
                (lf >= slf-MARGIN && lf <= slf+MARGIN)
                && (rf >= srf-MARGIN && rf <= srf+MARGIN)
                && (mf >= smf-MARGIN && mf <= smf+MARGIN)
                && (inf >= sinf-MARGIN && inf <= sinf+MARGIN)
                && (tf >= stf-MARGIN && tf <= stf+MARGIN) 
                    ){
                return true;
            }
        }
        return false;
    }
}
