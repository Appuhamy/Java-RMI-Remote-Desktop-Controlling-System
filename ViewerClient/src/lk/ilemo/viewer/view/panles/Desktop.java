/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.view.panles;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.TrayIcon;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import lk.ilemo.viewer.connecter.ServerConnecter;
import lk.ilemo.viewer.controller.ClientController;
import lk.ilemo.viewer.dto.ConnectDetailDTO;
import lk.ilemo.viewer.service.custom.Broker;
import lk.ilemo.viewer.service.custom.UserService;
import lk.ilemo.viewer.service.other.ViewerFileWriter;
import lk.ilemo.viewer.view.Viewer;
import lk.ilemo.viewer.view.other.SysTray;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class Desktop extends javax.swing.JPanel {

    /**
     * Creates new form Desktop
     */
    public Desktop() {
        initComponents();
    }

    public Desktop(String myId, String password) {
        initComponents();
        myID.setText(myId);
        myPassword.setText(password);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("ToolTip.background", new Color(0, 0, 0, 200));
                    UIManager.put("ToolTip.border", new EmptyBorder(5, 5, 5, 5));
                    UIManager.put("ToolTip.foreground", new Color(255, 255, 255));//ToollTipConfugeration
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        myPassword = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        myID = new javax.swing.JLabel();
        seperator = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        partnerIdField = new javax.swing.JTextField();
        partnerPasswordField = new javax.swing.JPasswordField();
        connectPartnerBtn = new javax.swing.JButton();
        pswrdfiledErr = new javax.swing.JLabel();
        IdFieldErr = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(72, 158, 231));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Your ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(72, 158, 231));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        myPassword.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        myPassword.setText("6y7t5rew");

        myID.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        myID.setText("1234567");

        seperator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/partnerlarge.png"))); // NOI18N

        partnerIdField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        partnerIdField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        partnerIdField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), "Partner ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        partnerIdField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                partnerIdFieldKeyPressed(evt);
            }
        });

        partnerPasswordField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        partnerPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        partnerPasswordField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Password", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        partnerPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                partnerPasswordFieldKeyPressed(evt);
            }
        });

        connectPartnerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/wifi.png"))); // NOI18N
        connectPartnerBtn.setText("Connect to Partner");
        connectPartnerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectPartnerBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(myID))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(myPassword)))
                .addGap(95, 95, 95)
                .addComponent(seperator, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(191, 191, 191)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(connectPartnerBtn)))
                        .addContainerGap(158, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(partnerIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(partnerPasswordField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pswrdfiledErr, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IdFieldErr, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(seperator))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myID))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel5)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(partnerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partnerPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pswrdfiledErr, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(connectPartnerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(IdFieldErr, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void partnerIdFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_partnerIdFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!partnerIdField.getText().isEmpty()) {
                partnerPasswordField.requestFocus();
                IdFieldErr.setIcon(new ImageIcon(getClass().getResource("")));
            } else {
                final ToolTipManager ttm = ToolTipManager.sharedInstance();
                final int oldDelay = ttm.getInitialDelay();
                ttm.setInitialDelay(0);
                ttm.mouseMoved(new MouseEvent(IdFieldErr, 0, 0, 0,
                        0, 0, // X-Y of the mouse for the tool tip
                        0, false));//ToolTip Auto DisplayMode
                IdFieldErr.setToolTipText("You Cannot Skip This Field Without Fill....");
                IdFieldErr.setIcon(new ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/question.png")));

            }
        }
    }//GEN-LAST:event_partnerIdFieldKeyPressed

    private void partnerPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_partnerPasswordFieldKeyPressed
        String partnerID;
        String partnerPassword;
        String myId = myID.getText();
        String myPassword = this.myPassword.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!partnerPasswordField.getText().isEmpty()) {
                try {
                    pswrdfiledErr.setIcon(new ImageIcon(getClass().getResource("")));
                    partnerID = partnerIdField.getText();
                    partnerPassword = String.valueOf(partnerPasswordField.getText());
                    ConnectDetailDTO connectedDTO = new ConnectDetailDTO(myId, Inet4Address.getLocalHost().toString(), myPassword, partnerID, partnerPassword);
                    Broker broker = ClientController.connectToPartner(connectedDTO);//Connect to the Viewer Server and connected to the partner and return Broker
                    ViewerFileWriter.callLoggerFileWriter("",partnerID, ViewerFileWriter.callType.PARTNERLOGIN);
                    System.out.println("Broker " + broker);
                    //Create Viewer Class including broker cuz Broker is the Middle Handler it has all function 
                    if (broker != null) {
                        Viewer v = new Viewer(broker);
                        v.setVisible(true);
                    } else {
                        final ToolTipManager ttm = ToolTipManager.sharedInstance();
                        final int oldDelay = ttm.getInitialDelay();
                        ttm.setInitialDelay(0);
                        ttm.mouseMoved(new MouseEvent(seperator, 0, 0, 0,
                                0, 0, // X-Y of the mouse for the tool tip
                                0, false));//ToolTip Auto DisplayMode
                        seperator.setToolTipText("Sorry We Didnt Have any Details about What you Enterd...." + " " + "Please Check Your Partners Id and Password");
                    }

                } catch (NotBoundException ex) {
                    Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    final ToolTipManager ttm = ToolTipManager.sharedInstance();
                    final int oldDelay = ttm.getInitialDelay();
                    ttm.setInitialDelay(0);
                    ttm.mouseMoved(new MouseEvent(seperator, 0, 0, 0,
                            0, 0, // X-Y of the mouse for the tool tip
                            0, false));//ToolTip Auto DisplayMode
                    seperator.setToolTipText("You Are Not Connected To The Internet.." + "\n" + "Please Check Your Internet Connection");
                    try {
                        SysTray.displayTray("Viewer", "Not Connected To The Internet Please Check...", "", TrayIcon.MessageType.NONE);
                    } catch (AWTException ex1) {
                        Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                final ToolTipManager ttm = ToolTipManager.sharedInstance();
                final int oldDelay = ttm.getInitialDelay();
                ttm.setInitialDelay(0);
                ttm.mouseMoved(new MouseEvent(pswrdfiledErr, 0, 0, 0,
                        0, 0, // X-Y of the mouse for the tool tip
                        0, false));
                pswrdfiledErr.setToolTipText("You Cannot Skip ThiS Field Without Fill........");
                pswrdfiledErr.setIcon(new ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/question.png")));
            }
        }
    }//GEN-LAST:event_partnerPasswordFieldKeyPressed

    private void connectPartnerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectPartnerBtnActionPerformed
        String partnerID;
        String partnerPassword;
        String myId = myID.getText();
        String myPassword = this.myPassword.getText();

        if (!partnerPasswordField.getText().isEmpty()) {
            try {
                pswrdfiledErr.setIcon(new ImageIcon(getClass().getResource("")));
                partnerID = partnerIdField.getText();
                partnerPassword = String.valueOf(partnerPasswordField.getText());
                ConnectDetailDTO connectedDTO = new ConnectDetailDTO(myId, Inet4Address.getLocalHost().toString(), myPassword, partnerID, partnerPassword);
                Broker broker = ClientController.connectToPartner(connectedDTO);//Connect to the Viewer Server and connected to the partner and return Broker
                System.out.println("Broker " + broker);
                ViewerFileWriter.callLoggerFileWriter("",partnerID, ViewerFileWriter.callType.PARTNERLOGIN);
                //Create Viewer Class including broker cuz Broker is the Middle Handler it has all function 
                if (broker != null) {
                    Viewer v = new Viewer(broker);
                    v.setVisible(true);
                } else {
                    final ToolTipManager ttm = ToolTipManager.sharedInstance();
                    final int oldDelay = ttm.getInitialDelay();
                    ttm.setInitialDelay(0);
                    ttm.mouseMoved(new MouseEvent(seperator, 0, 0, 0,
                            0, 0, // X-Y of the mouse for the tool tip
                            0, false));//ToolTip Auto DisplayMode
                    seperator.setToolTipText("Sorry We Didnt Have any Details about What you Enterd...." + " " + "Please Check Your Partners Id and Password");
                }

            } catch (NotBoundException ex) {
                Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                final ToolTipManager ttm = ToolTipManager.sharedInstance();
                final int oldDelay = ttm.getInitialDelay();
                ttm.setInitialDelay(0);
                ttm.mouseMoved(new MouseEvent(seperator, 0, 0, 0,
                        0, 0, // X-Y of the mouse for the tool tip
                        0, false));//ToolTip Auto DisplayMode
                seperator.setToolTipText("You Are Not Connected To The Internet.." + "\n" + "Please Check Your Internet Connection");
                try {
                    SysTray.displayTray("Viewer", "Not Connected To The Internet Please Check...", "", TrayIcon.MessageType.NONE);
                } catch (AWTException ex1) {
                    Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            final ToolTipManager ttm = ToolTipManager.sharedInstance();
            final int oldDelay = ttm.getInitialDelay();
            ttm.setInitialDelay(0);
            ttm.mouseMoved(new MouseEvent(pswrdfiledErr, 0, 0, 0,
                    0, 0, // X-Y of the mouse for the tool tip
                    0, false));
            pswrdfiledErr.setToolTipText("You Cannot Skip ThiS Field Without Fill........");
            pswrdfiledErr.setIcon(new ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/question.png")));
        }

    }//GEN-LAST:event_connectPartnerBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IdFieldErr;
    private javax.swing.JButton connectPartnerBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel myID;
    private javax.swing.JLabel myPassword;
    private javax.swing.JTextField partnerIdField;
    private javax.swing.JPasswordField partnerPasswordField;
    private javax.swing.JLabel pswrdfiledErr;
    private javax.swing.JSeparator seperator;
    // End of variables declaration//GEN-END:variables
    private void setIdandPassword(String id, String password) {
        myID.setText(id);
        myPassword.setText(password);
    }
}
