/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.service.custom;

import java.awt.AWTException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import lk.ilemo.viewer.dto.UserDTO;
import lk.ilemo.viewer.observer.Subject;
import lk.ilemo.viewer.observer.UserObserver;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class Broker implements Serializable{

    private UserDTO serverClientDto;
    private UserDTO controllerClient;
    ArrayList<UserObserver> observerList = new ArrayList<>();

    public Broker() throws RemoteException{
    }

    public Broker(UserDTO serverClientDto, UserDTO controllerClient) throws RemoteException{
        this.serverClientDto = serverClientDto;
        this.controllerClient = controllerClient;
    }


    public void registerObserver(UserObserver observer) throws RemoteException {
        observerList.add(observer);
    }


    public void unregisterObserver(UserObserver observer) throws RemoteException {
        observerList.remove(observer);
    }


    public void notifyAllObservers(String senderName, String message) throws RemoteException {
        for (UserObserver observer : observerList) {
            
            new Thread() {
                public void run() {
                    try {
                        observer.update(senderName, message);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }              

            }.start();
        }
    }

    public byte[] startSharingDesktop(byte[] bytes) throws RemoteException {
        System.out.println(observerList);
        UserObserver get = observerList.get(1);//get Server Clients Observer
        //System.out.println("get"+get.toString());
        
        return get.shareDesktop(bytes);
    }
    public UserDTO getServerClientDto(){
        return serverClientDto;
    }
    public UserDTO getControllerClientDto(){
        return controllerClient;
    }
    public boolean setDesktop() throws RemoteException{
        UserObserver get = observerList.get(1);//get Server Clients Observer
        get.notifyConnection(serverClientDto,this);
        return get.setDesktop();
        
    }
    public void mouseMoveAction(int x,int y) throws RemoteException, AWTException{
        UserObserver get = observerList.get(1);
        get.mouseMoveAction(x, y);
    }
    public void keyBoardAction(char key) throws RemoteException, AWTException{
        UserObserver get = observerList.get(1);
        get.keyBoardAction(key);
    }
    public void mouseClickAction(int press) throws RemoteException, AWTException{
        UserObserver get = observerList.get(1);
        get.mouseClickAction(press);
    }
    public void mouseWheelAction(int wheel) throws RemoteException, AWTException{
        UserObserver get = observerList.get(1);
        get.mouseWheelAction(wheel);
    }
    public void mouseDragAction(int x, int y,int modyfier) throws RemoteException, AWTException{
        UserObserver get = observerList.get(1);
        get.mouseDragAction(x, y,modyfier);
    }
    public boolean fileTransfering(HashMap<String,byte[]> files) throws RemoteException, AWTException, IOException{
        UserObserver get = observerList.get(1);
        return get.fileTransfering(files);
    }
    public byte[] exchangeImage(byte[] image,int obj) throws RemoteException{
        UserObserver get = observerList.get(obj);
        return get.exchangeImage(image,this,obj);
    }
    public int notifyViewerVoiceCall(int obj) throws RemoteException{
        UserObserver get = observerList.get(obj);
        return get.notifyViewerVoiceCall(this,obj);
    }
    public void chatOFF() throws RemoteException{
        for (UserObserver Observer : observerList) {
            Observer.chatOFF();
        }
    }
    public boolean chatON() throws RemoteException{
        for (UserObserver Observer : observerList) {
            Observer.chatON(this);
        }
        return true;
    }
    public boolean callOFF(int obj) throws RemoteException{
        UserObserver get = observerList.get(obj);
        return get.callOFF(obj);
    }
    

}
