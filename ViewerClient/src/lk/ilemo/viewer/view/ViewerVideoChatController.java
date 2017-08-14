/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.view;

import com.github.sarxos.webcam.Webcam;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import lk.ilemo.viewer.service.custom.Broker;
import lk.ilemo.viewer.service.other.ViewerFileWriter;
import static lk.ilemo.viewer.view.ViewerVideoChatServer.getAudioFormat;
import lk.ilemo.viewer.view.utils.PlayThread;
import lk.ilemo.viewer.view.utils.RecordThread;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class ViewerVideoChatController extends javax.swing.JFrame {

    private int lastX, lastY;
    private BufferedImage img = null;
    private BufferedImage myImg = null;
    private Broker broker;
    private Thread myThread;
    private int ThreadSignal = 0;
    /**
     * **********Client Utils***********************
     */
    public int port_Srrver = 8888;
    public String add_Server = "192.168.8.100";
    TargetDataLine audio_in;
    RecordThread r = new RecordThread();//This Object is Used to send Voice Data to the connected Server(Partner) 
    /**
     * **********Server Utils************************
     */
    public int port = 8888;//BroadCasting Port
    public SourceDataLine audio_in2;
    PlayThread p = new PlayThread();//When Partner Send Data this class convert Sent Data to the Voice Data 

    public int obj = 1;

    public static AudioFormat getAudioFormat() {
        float sampleRate = 8000.0f;
        int sampleSizeInBit = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBit, channel, signed, bigEndian);
    }
    private ViewerDashBoard dsh = null;
    private ViewerVideoChatController thisObject = this;
    private String caller;

    /**
     * Creates new form ViewerVideoChatController
     */
    public ViewerVideoChatController() {
        initComponents();

        getMyImage();
    }

    public ViewerVideoChatController(Broker broker, int onj, ViewerDashBoard dsh) {
        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.broker = broker;
        this.dsh = dsh;
        this.obj = onj;
        if (obj == 1) {
            this.add_Server = broker.getServerClientDto().getIPAddress();
//            String[] split = ipAddress.split("/");
//            this.add_Server = split[1];
            caller = broker.getServerClientDto().getUserComputerName();
        } else if (obj == 0) {
            String ipAddress = broker.getControllerClientDto().getIPAddress();
            String[] split = ipAddress.split("/");
            this.add_Server = split[1];
            caller = broker.getControllerClientDto().getUserComputerName();
        }
        setVisible(true);
        Webcam.getDefault().open();
        connectToServerCam();
        getMyImage();
        initAudio();
        init_Audio_Server();

    }

    public ViewerVideoChatController(Broker broker, int onj) {
        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.broker = broker;
        this.dsh = dsh;
        if (obj == 1) {
            this.add_Server = broker.getServerClientDto().getIPAddress();
//            String[] split = ipAddress.split("/");
//            this.add_Server = split[1];
        } else if (obj == 0) {
            String ipAddress = broker.getControllerClientDto().getIPAddress();
            String[] split = ipAddress.split("/");
            this.add_Server = split[1];
        }
        setVisible(true);
        Webcam.getDefault().open();
        connectToServerCam();
        getMyImage();
        initAudio();
        init_Audio_Server();

    }

    private void connectToServerCam() {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (ThreadSignal == 0) {
                        //Connected to the Server Cam and get Server Image set it to the Partner Pnl and get my image and send it to the Server Client
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();

                        BufferedImage image = webcam.getImage();
                        if (image != null) {
                            ImageIO.write(image, "jpg", baos);
                            byte[] serverCamByteStream = broker.exchangeImage(baos.toByteArray(), obj);
                            if (serverCamByteStream != null) {
                                img = javax.imageio.ImageIO.read(new ByteArrayInputStream(serverCamByteStream));
                                partnerPnl.repaint();
                            }

                        } else {
                            System.out.println("Cam Not Ready..");
                        }
                    }
                    Webcam.getDefault().close();

                } catch (RemoteException ex) {
                    Logger.getLogger(ViewerVideoChatController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ViewerVideoChatController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        myThread.start();

    }

    public void getMyImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (ThreadSignal == 0) {
                    //get My Images and set It to the MyPanl
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    Webcam webCam = Webcam.getDefault();
                    webCam.open();
                    myImg = webCam.getImage();
                    myPanl.repaint();
                    System.out.println("get MyImage");
                }
                Webcam.getDefault().close();
            }

        }).start();

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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        shutDownLbl = new javax.swing.JLabel();
        partnerPnl = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                if(img!=null){
                    g.drawImage(img,0,0,partnerPnl.getWidth(),partnerPnl.getHeight(),null);
                }
            }
        };
        myPanl = new javax.swing.JPanel(){

            public void paintComponent(Graphics g){
                super.paintComponent(g);
                if(myImg!=null){
                    g.drawImage(myImg,0,0,myPanl.getWidth(),myPanl.getHeight(),null);
                }
            }
        }
        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 51, 255));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/videoFram.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Viewer Vchat");
        jLabel2.setToolTipText("");

        shutDownLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/no_vide0.png"))); // NOI18N
        shutDownLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shutDownLblMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                shutDownLblMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                shutDownLblMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 617, Short.MAX_VALUE)
                        .addComponent(shutDownLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(shutDownLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        partnerPnl.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout myPanlLayout = new javax.swing.GroupLayout(myPanl);
        myPanl.setLayout(myPanlLayout);
        myPanlLayout.setHorizontalGroup(
            myPanlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        myPanlLayout.setVerticalGroup(
            myPanlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout partnerPnlLayout = new javax.swing.GroupLayout(partnerPnl);
        partnerPnl.setLayout(partnerPnlLayout);
        partnerPnlLayout.setHorizontalGroup(
            partnerPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partnerPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myPanl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        partnerPnlLayout.setVerticalGroup(
            partnerPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partnerPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myPanl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(359, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(partnerPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partnerPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        lastX = evt.getXOnScreen() - getX();
        lastY = evt.getYOnScreen() - getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        setLocation(evt.getXOnScreen() - lastX, evt.getYOnScreen() - lastY);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void shutDownLblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shutDownLblMousePressed
        shutDownLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/no_video_filled.png"))); // NOI18N
    }//GEN-LAST:event_shutDownLblMousePressed

    private void shutDownLblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shutDownLblMouseReleased
        shutDownLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ilemo/viewer/view/icons/no_vide0.png"))); // NOI18N
    }//GEN-LAST:event_shutDownLblMouseReleased

    private void shutDownLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shutDownLblMouseClicked
        r.byte_buffer = new byte[0];
        if (dsh != null) {
            dsh.Vchat = null;
        }
        disposeMe();
    }//GEN-LAST:event_shutDownLblMouseClicked

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
            java.util.logging.Logger.getLogger(ViewerVideoChatController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewerVideoChatController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewerVideoChatController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewerVideoChatController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewerVideoChatController().setVisible(true);
            }
        });
    }

    public void initAudio() {
        AudioFormat format = getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("NOT SUPPORT..");
            System.exit(0);
        }
        try {
            audio_in = (TargetDataLine) AudioSystem.getLine(info);
            audio_in.open();
            audio_in.start();

            InetAddress inet = InetAddress.getByName(add_Server);
            r.audio_in = audio_in;
            r.dout = new DatagramSocket();
            r.server_ip = inet;
            r.server_port = port_Srrver;
            r.Client_Voice = true;
            r.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ViewerVideoChatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ViewerVideoChatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(ViewerVideoChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init_Audio_Server() {
        AudioFormat format = getAudioFormat();
        DataLine.Info info_out = new DataLine.Info(SourceDataLine.class, format);
        if (!AudioSystem.isLineSupported(info_out)) {
            System.out.println("NOT SUPPORT....");
            System.exit(0);
        }
        try {
            audio_in2 = (SourceDataLine) AudioSystem.getLine(info_out);
            audio_in2.open();
            audio_in2.start();
            p.VideoController = this;
            p.din = new DatagramSocket(port);
            p.audio_out = audio_in2;
            p.Server_Voice = true;
            p.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ViewerVideoChatServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(ViewerVideoChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disposeMe() {
        ThreadSignal = 1;
        r.Client_Voice = false;
        p.Server_Voice = false;
        Webcam.getDefault().close();
        try {
            ViewerFileWriter.callLoggerFileWriter("", caller, ViewerFileWriter.callType.GETCALL);
        } catch (IOException ex) {
            Logger.getLogger(ViewerVideoChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //dsh.Vchat = null;
        //this.dispose();
        ThreadSignal=0;
        this.setVisible(false);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel myPanl;
    private javax.swing.JPanel partnerPnl;
    private javax.swing.JLabel shutDownLbl;
    // End of variables declaration//GEN-END:variables
}
