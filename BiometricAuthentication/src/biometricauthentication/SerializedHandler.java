/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Lahiru
 */
public class SerializedHandler {
    
    public static OutputStream serializeHandList(Hand list, String fileName) {

        try  {
            ObjectOutputStream objOut
                = new ObjectOutputStream(new FileOutputStream(fileName));
            objOut.writeObject(list);
            return objOut;
        } catch (IOException ex) {
            System.out.println("Problem occured during serilalization");
            ex.printStackTrace();
            return null;
        }
        
    }

    public static ArrayList<Hand> deserializeHandList(String fileName) 
            throws FileNotFoundException, IOException, ClassNotFoundException {
        
        ArrayList<Hand> list;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        list = (ArrayList<Hand>) objIn.readObject();
        return list;
    }
    
}
