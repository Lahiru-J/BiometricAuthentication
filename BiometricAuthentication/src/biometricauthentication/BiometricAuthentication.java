/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


/**
 *
 * @author Lahiru
 */
public class BiometricAuthentication {

    public static ArrayList<Hand> HandList = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            //  Get the data
            HandList = (ArrayList<Hand>) Security.decrypt();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new AuthenticationUi().setVisible(true);
    }
    
}
