/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.observer;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import lk.ilemo.viewer.dto.UserDTO;
import lk.ilemo.viewer.service.custom.Broker;

/**
 *
 * @author Chamindu_Appuhamy
 */
public interface UserObserver extends Remote{
    public void update(String senderName,String message)throws RemoteException;
    public byte[] shareDesktop(byte bite [])throws RemoteException;
    public boolean setDesktop()throws RemoteException;
    public void mouseMoveAction(int x,int y)throws RemoteException,AWTException;
    public void keyBoardAction(char key)throws RemoteException,AWTException;
    public void mouseClickAction(int press)throws RemoteException,AWTException;
    public void mouseWheelAction(int wheel)throws RemoteException,AWTException;
    public void mouseDragAction(int x , int y,int modyfier)throws RemoteException,AWTException;
    public boolean fileTransfering(HashMap<String,byte[]> files)throws RemoteException,FileNotFoundException,IOException;
    public byte[] exchangeImage(byte[] rawImage,Broker broker,int obj) throws RemoteException ;
    public void notifyConnection(UserDTO dto,Broker broker)throws RemoteException ;
    public int notifyViewerVoiceCall(Broker broker,int obj)throws RemoteException ;
    public boolean callOFF(int obj)throws RemoteException ;
    public boolean chatON(Broker broker)throws RemoteException ;
    public boolean chatOFF()throws RemoteException ;
   
}
