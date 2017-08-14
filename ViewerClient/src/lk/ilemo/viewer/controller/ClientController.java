/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.controller;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Observer;
import lk.ilemo.viewer.connecter.ServerConnecter;
import lk.ilemo.viewer.dto.ConnectDetailDTO;
import lk.ilemo.viewer.dto.UserDTO;
import lk.ilemo.viewer.service.custom.Broker;
import lk.ilemo.viewer.service.custom.UserService;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class ClientController {

    public static  boolean loginToViewerServer(UserDTO t,lk.ilemo.viewer.observer.ObsaverImpl impl) throws NotBoundException, MalformedURLException, RemoteException, IOException{
        UserService userService = ServerConnecter.getServerConnecter().getUserService();
        return userService.loginToViewerServer(t,impl);
    }
    
    public static UserDTO getLoginDetails()throws NotBoundException, MalformedURLException, RemoteException, IOException{
        UserService userService = ServerConnecter.getServerConnecter().getUserService();
        return userService.getLoginDetails();
    }
    
    public static Broker connectToPartner(ConnectDetailDTO t)throws NotBoundException, MalformedURLException, RemoteException, IOException{
        UserService userService = ServerConnecter.getServerConnecter().getUserService();
        return userService.connectToPartner(t);
    }
    
    public static byte[] startSharingDesktop(byte[] bytes)throws NotBoundException, MalformedURLException, RemoteException, IOException{
        UserService userService = ServerConnecter.getServerConnecter().getUserService();
        return userService.startSharingDesktop(bytes);
    }
    public static boolean driverUplaod(HashMap<String, byte[]> files,String details) throws RemoteException, AWTException, IOException, NotBoundException {
        UserService userService=ServerConnecter.getServerConnecter().getUserService();
        return userService.driverUpload(files,details);
    }
    public static HashMap<String, byte[]>  driverDownload(String details) throws RemoteException, AWTException, IOException, NotBoundException {
        UserService userService = ServerConnecter.getServerConnecter().getUserService();
        return userService.driverDownload(details);
    }
}
