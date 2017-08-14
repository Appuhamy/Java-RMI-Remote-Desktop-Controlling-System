/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.service.custom.impl;

import java.awt.AWTException;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import lk.ilemo.viewer.dao.DAOFactory;
import lk.ilemo.viewer.dao.SuperDAO;
import lk.ilemo.viewer.dto.ConnectDetailDTO;
import lk.ilemo.viewer.dto.UserDTO;
import lk.ilemo.viewer.observer.Subject;
import lk.ilemo.viewer.observer.UserObserver;
import lk.ilemo.viewer.service.custom.Broker;
import lk.ilemo.viewer.service.custom.UserService;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService, Subject<UserObserver> {

    SuperDAO<UserDTO> userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USERDAO);
    
    public UserServiceImpl() throws RemoteException {
        System.out.println("runnig Constructer");
    }

    @Override
    public boolean loginToViewerServer(UserDTO t, UserObserver observer) throws RemoteException, IOException {
        String generatePassword = userDAO.loginToViewerServer(t, observer);
        return true;
    }

    @Override
    public UserDTO getLoginDetails() throws RemoteException, IOException {
        return userDAO.getLoginDetails();
    }

    @Override
    public Broker connectToPartner(ConnectDetailDTO t) throws RemoteException, IOException {
        //System.out.println("User service..");
        return userDAO.connectToPartner(t);
    }

    @Override
    public byte[] startSharingDesktop(byte[] bytes) throws RemoteException {
        return bytes;
    }

    @Override
    public void notifyAllObservers(String senderName, String message) throws RemoteException {
//        for (UserObserver observer : observerList) {
//            new Thread() {
//                public void run() {
//                    try {
//                        observer.update(senderName, message);
//                    } catch (RemoteException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//
//            }.start();
//        }
    }

    @Override
    public void registerObserver(UserObserver observer) throws RemoteException {

    }

    @Override
    public void unregisterObserver(UserObserver observer) throws RemoteException {
//        observerList.remove(observer);
    }

    @Override
    public boolean driverUpload(HashMap<String, byte[]> files,String details) throws RemoteException, AWTException, IOException {
        return userDAO.driverUpload(files, details);
    }

    @Override
    public HashMap<String, byte[]> driverDownload(String details) throws RemoteException, AWTException, IOException {
        return userDAO.driverDownload(details);
    }
    

}
