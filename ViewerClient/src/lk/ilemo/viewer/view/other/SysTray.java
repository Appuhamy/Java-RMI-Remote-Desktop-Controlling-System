package lk.ilemo.viewer.view.other;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import javax.swing.ImageIcon;

public class SysTray {


    public static void displayTray(String Title, String Message, String iconPath, MessageType type) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();
        //Creating a tray icon
        ImageIcon icon = new ImageIcon(iconPath);
        Image image = icon.getImage();
        //System.out.println(image);
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resizes the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Viewer Notification...");
        tray.add(trayIcon);
        
        trayIcon.displayMessage(Title, Message, type);

        trayIcon.isImageAutoSize();
    }
}
