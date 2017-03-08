package biometricauthentication;

import java.io.File;
import java.util.ArrayList;
import javax.swing.UIManager;
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

            // if only the file is existing get the data
            File f = new File("src/SerializedObjects/handList.ser");
            if (f.exists() && !f.isDirectory()) {
                //  Get the data
                HandList = (ArrayList<Hand>) SecurityHandler.decrypt();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new AuthenticationUi().setVisible(true);

    }

}
