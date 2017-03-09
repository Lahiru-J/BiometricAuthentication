package biometricauthentication;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lahiru
 */
public class AuthenticationUi extends javax.swing.JFrame {

    JComponent[] coms = new JComponent[12];
    private boolean isImageSelected = false;

    public AuthenticationUi() {
        initComponents();
        fileSelect.setVisible(false);

        for (int i = 0; i < 12; i++) {
            JComponent com = new DraggableComponent();
            com.setBorder(new LineBorder(Color.white));
            com.setSize(18, 18);
            com.setBackground(Color.BLACK);
            com.setLocation(i + 20 * i, 0);
            
            JLabel num = new JLabel(i+1+"");
            num.setForeground(Color.white);
            num.setSize(18,18);
            num.setHorizontalTextPosition(0);
            com.add(num);
            coms[i] = com;
            this.add(com, 10);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileSelect = new javax.swing.JFileChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        TbtnSelectImage = new javax.swing.JToggleButton();
        lblHand = new javax.swing.JLabel();
        BtnInsert = new javax.swing.JButton();
        BtnValidate = new javax.swing.JButton();
        txtUname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 650));
        setMinimumSize(new java.awt.Dimension(900, 650));
        setSize(new java.awt.Dimension(900, 650));
        getContentPane().setLayout(null);

        fileSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileSelectActionPerformed(evt);
            }
        });
        getContentPane().add(fileSelect);
        fileSelect.setBounds(470, 40, 470, 360);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(20, 40, 900, 12);

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(450, 60, 10, 520);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hand2.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(490, 60, 408, 513);

        TbtnSelectImage.setText("Select Image");
        TbtnSelectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TbtnSelectImageActionPerformed(evt);
            }
        });
        getContentPane().add(TbtnSelectImage);
        TbtnSelectImage.setBounds(450, 10, 123, 29);

        lblHand.setMaximumSize(new java.awt.Dimension(408, 513));
        lblHand.setMinimumSize(new java.awt.Dimension(408, 513));
        lblHand.setPreferredSize(new java.awt.Dimension(408, 513));
        getContentPane().add(lblHand);
        lblHand.setBounds(20, 60, 410, 500);

        BtnInsert.setText("Sign Up");
        BtnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInsertActionPerformed(evt);
            }
        });
        getContentPane().add(BtnInsert);
        BtnInsert.setBounds(370, 10, 80, 29);

        BtnValidate.setText("Validate");
        BtnValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnValidateActionPerformed(evt);
            }
        });
        getContentPane().add(BtnValidate);
        BtnValidate.setBounds(350, 590, 94, 29);

        txtUname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnameKeyTyped(evt);
            }
        });
        getContentPane().add(txtUname);
        txtUname.setBounds(220, 590, 120, 26);

        jLabel11.setText("User Name");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(130, 595, 80, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TbtnSelectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TbtnSelectImageActionPerformed
        if (TbtnSelectImage.isSelected() == true) {
            fileSelect.setVisible(true);
        } else {
            fileSelect.setVisible(false);
        }
    }//GEN-LAST:event_TbtnSelectImageActionPerformed

    private void fileSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileSelectActionPerformed

        File file = fileSelect.getSelectedFile();
        if (file != null) {

            try {
                BufferedImage image = ImageIO.read(file);
                BufferedImage resizedImage = ResizeImage.resize(image, 408, 513);
                lblHand.setIcon(new ImageIcon(resizedImage));
                isImageSelected = true;
            } catch (IOException e) {
                e.printStackTrace();
                isImageSelected = false;
            }
        }

    }//GEN-LAST:event_fileSelectActionPerformed

    private void BtnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInsertActionPerformed
        if (isImageSelected) {
            Hand hand = new HandHandler().getHand(coms);
            if (hand == null) {
                JOptionPane.showMessageDialog(this, "Mark the points as shown in the image", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            new SignupPage(hand, this, true).setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Select Image!", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_BtnInsertActionPerformed

    private void BtnValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnValidateActionPerformed
        if (!txtUname.getText().isEmpty()) {
            Hand hand = new HandHandler().getHand(coms);
            if (hand == null) {
                return;
            }
            hand.setUserId(txtUname.getText());
            boolean b = SecurityHandler.checkForMatch(hand);
            if (b) {
                JOptionPane.showMessageDialog(this, "Permission Granted", "Succesfull",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No matched hand is found!", "Invalid",
                        JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "User name cannot be empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnValidateActionPerformed

    private void txtUnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnameKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            // do not allow to enter space
            evt.consume();
        }
    }//GEN-LAST:event_txtUnameKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AuthenticationUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthenticationUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthenticationUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthenticationUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthenticationUi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInsert;
    private javax.swing.JButton BtnValidate;
    private javax.swing.JToggleButton TbtnSelectImage;
    private javax.swing.JFileChooser fileSelect;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblHand;
    private javax.swing.JTextField txtUname;
    // End of variables declaration//GEN-END:variables

}
