/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication;

/**
 *
 * @author Lahiru
 */
public class HandHandler {

    public void insertHand(Hand hand) {

        try {
            // Add to the list
            BiometricAuthentication.HandList.add(hand);
            Security.encrypt(BiometricAuthentication.HandList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
