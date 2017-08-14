/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.observer;

import lk.ilemo.viewer.view.other.WallpaperChanger;
import com.github.sarxos.webcam.Webcam;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import lk.ilemo.viewer.dto.UserDTO;
import lk.ilemo.viewer.observer.UserObserver;
import lk.ilemo.viewer.service.custom.Broker;
import lk.ilemo.viewer.view.ViewerDashBoard;
import lk.ilemo.viewer.view.ViewerMain;
import lk.ilemo.viewer.view.ViewerTextChat;
import lk.ilemo.viewer.view.ViewerVideoChatServer;
import lk.ilemo.viewer.view.ViewerVoiceChatServer;
import lk.ilemo.viewer.view.other.SysTray;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class ObsaverImpl extends UnicastRemoteObject implements UserObserver {

    private ViewerMain viewer;
    public ViewerVideoChatServer Vchat = null;
    ViewerDashBoard VDash = null;
    public ViewerVoiceChatServer VCall = null;
    ViewerTextChat VtxtChat = null;
    public int callerCount = 0;
    Webcam webCam = Webcam.getDefault();

    public ObsaverImpl(ViewerMain viewer) throws RemoteException {
        this.viewer = viewer;//When we Connected to the Viewer Server Client Automattically send the ObserverImpl to the Server ..Server get It and put into the HashMap
    }

    @Override
    public void update(String senderName, String message) throws RemoteException {
        if (VtxtChat != null) {
            VtxtChat.update(senderName, message);
        }
    }

    @Override
    public byte[] shareDesktop(byte[] bite) throws RemoteException {
        try {
            //Capturing Server Clients Desktop Capture if Your are Server Client
            Rectangle screenRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage capture = new Robot().createScreenCapture(screenRectangle);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(capture, "png", bao);
            return bao.toByteArray();//Capture Convert to Byte Array..
        } catch (AWTException ex) {
            Logger.getLogger(ObsaverImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObsaverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean setDesktop() throws RemoteException {
        //Changing Server Clients Desktop Background if your are a Server Client
        WallpaperChanger changer = new WallpaperChanger();
        changer.main(new String[2]);
        changer = null;
        return true;

    }

    @Override
    public void mouseMoveAction(int x, int y) throws RemoteException, AWTException {
        Robot robot = new Robot();
        robot.mouseMove(x, y);
    }

    @Override
    public void keyBoardAction(char key) throws RemoteException, AWTException {
        Robot robot = new Robot();
        robot.keyPress(key);
        robot.keyRelease(key);
    }

    @Override
    public void mouseClickAction(int press) throws RemoteException, AWTException {
        Robot robot = new Robot();
        if (press == 16) {
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } else {
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
        }

    }

    @Override
    public void mouseWheelAction(int wheel) throws RemoteException, AWTException {
        Robot robot = new Robot();
        robot.mouseWheel(wheel);
    }

    @Override
    public void mouseDragAction(int x, int y, int modyfier) throws RemoteException, AWTException {
        Robot robot = new Robot();
        if (modyfier != 16) {
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseMove(x, y);
        } else {
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }
    }

    @Override
    public boolean fileTransfering(HashMap<String, byte[]> files) throws RemoteException, FileNotFoundException, IOException {
        String home = System.getProperty("user.home") + "\\Desktop\\ViewerRecieves";
        File target = new File(home);
        if (!target.exists()) {
            target.mkdir();
            System.out.println("File Is Created");
        }
        int count = 0;
        Set<String> keySet = files.keySet();
        for (String key : keySet) {
            byte[] get = files.get(key);
            String saveAs = home + "\\" + key;
            File file = new File(saveAs);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(get);
            bos.flush();
            bos.close();
            count++;
            System.out.println("Counting Fils:" + count);
            System.out.println("");
        }
        return true;
    }

    @Override
    public byte[] exchangeImage(byte[] rawImage, Broker broker, int obj) throws RemoteException {
        /*
            When controller Client Call to this methos first,
            Viewer Vchat object's Connected to the Server method call and send it to the byte array 
            Then Vchat visible ...The Object is Created by Using Facade
            if Vchat Visible then run the Thread which is in GetMyImageMethod..
            Finnally get Server Client Image and Send it to The Controller Client
         */
        if (Vchat == null) {
            Vchat = new ViewerVideoChatServer(broker, obj, this);
            webCam.open();
        }
        Vchat.connectedControllerCam(rawImage);
        System.out.println("VCHAT fjdaksjfslk dsklf jdfkf Exhange Image");
        if (!Vchat.isVisible()) {
            Vchat.setVisible(true);
            webCam.open();
            Vchat.getMyImage();
            Vchat.init_Audio();
            Vchat.initAudio_Client();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (webCam.isOpen()) {
            BufferedImage image = webCam.getImage();
            if (image != null) {
                try {
                    javax.imageio.ImageIO.write(image, "jpg", baos);
                    return baos.toByteArray();
                } catch (IOException ex) {
                    Logger.getLogger(ObsaverImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            webCam.open();
        }
        return null;
    }

    @Override
    public void notifyConnection(UserDTO dto, Broker broker) throws RemoteException {
        if (VDash == null) {
            VDash = new ViewerDashBoard(broker);
        }
        if (!VDash.isVisible()) {
            VDash.setVisible(true);
        }
        try {
            SysTray.displayTray("Viewer", dto.getUserComputerName(), "Connecting....", TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(ObsaverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int notifyViewerVoiceCall(Broker broker, int obj) throws RemoteException {
        System.out.println("Notifying...");
        if (VCall == null) {
            VCall = new ViewerVoiceChatServer(broker, obj, this);

        }
        if (!VCall.isVisible()) {
            VCall.setVisible(true);
            VCall.play();
            System.out.println("Signal2:" + VCall.Signal);
            System.out.println("Visible....");
        }
        System.out.println("Object Vcall:" + VCall);
        if (VCall.Signal == 1) {
            return 1;
        } else if (VCall.Signal == 0) {
            VCall = null;
            return 0;
        } else if (VCall.Signal == 2) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public boolean chatON(Broker broker) throws RemoteException {
        if (VtxtChat == null) {
            VtxtChat = new ViewerTextChat(broker);
        }
        if (!VtxtChat.isVisible()) {
            VtxtChat.setVisible(true);
            return true;
        }
        return true;
    }

    @Override
    public boolean chatOFF() throws RemoteException {
        VtxtChat.disposeMe();
        VtxtChat = null;
        return true;
    }

    @Override
    public boolean callOFF(int obj) throws RemoteException {
        if (VCall.isVisible()) {
            VCall.disposeMe();
            VCall = null;
        }
        return true;
    }

}
