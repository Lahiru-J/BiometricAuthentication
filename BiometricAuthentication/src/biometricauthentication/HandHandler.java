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

    public boolean insertHand(Hand hand) {

        try {

            if (hand == null) {
                return false;
            }
            // if either one of a finger is zero 
            if (hand.getIndexFingerLen() == 0
                    || hand.getRingFingerLen() == 0
                    || hand.getMiddleFingerLen() == 0
                    || hand.getIndexFingerLen() == 0
                    || hand.getThumbFingerLen() == 0) {
                return false;
            }
            // Add to the list
            BiometricAuthentication.HandList.add(hand);
            SecurityHandler.encrypt(BiometricAuthentication.HandList);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
