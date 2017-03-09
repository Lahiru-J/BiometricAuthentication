package biometricauthentication;

import java.text.DecimalFormat;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

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

            for (Iterator<Hand> iterator
                    = BiometricAuthentication.HandList.iterator(); iterator.hasNext();) {
                Hand storedHand = iterator.next();

                if (hand.getUserId().equals(storedHand.getUserId())) {
                    JOptionPane.showMessageDialog(null, "User Id is already exists!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            // if either one of a finger is zero 
            if (hand.getIndexFingerLen() == 0
                    || hand.getRingFingerLen() == 0
                    || hand.getMiddleFingerLen() == 0
                    || hand.getIndexFingerLen() == 0
                    || hand.getThumbFingerLen() == 0) {
                JOptionPane.showMessageDialog(null, "Finger length cannot be zero!",
                        "Error", JOptionPane.ERROR_MESSAGE);
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

    public Hand getHand(JComponent[] coms) {
        double[] fingerlen = new double[5];
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        for (int i = 0; i < 5; i++) {
            fingerlen[i] = Math.sqrt(Math.pow((coms[i].getX() - coms[i + 1].getX()), 2)
                    + Math.pow((coms[i].getY() - coms[i + 1].getY()), 2));
        }
        // if there is no finger measurement
        if (fingerlen[0] == 0) {
            return null;
        }

        // make the little finger to be unit 1 and the rest of the fingers to be
        // product of the little finger
        double lf = fingerlen[0];
        for (int i = 0; i < 5; i++) {
            fingerlen[i] = fingerlen[i] / lf;
            fingerlen[i] = Double.parseDouble(numberFormat.format(fingerlen[i]));
        }
        return new Hand(fingerlen[0], fingerlen[1], fingerlen[2], fingerlen[3], fingerlen[4]);
    }
}
