/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.service;

import java.awt.AWTException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import lk.ilemo.viewer.dto.ConnectDetailDTO;
import lk.ilemo.viewer.dto.SuperDTO;
import lk.ilemo.viewer.dto.UserDTO;
import lk.ilemo.viewer.observer.UserObserver;
import lk.ilemo.viewer.service.custom.Broker;

/**
 *
 * @author Chamindu_Appuhamy
 * @param <T>
 */
public interface SuperService<T extends SuperDTO> extends Remote{
    
    public boolean loginToViewerServer(T t,UserObserver observer)throws RemoteException,IOException;
    
    public T getLoginDetails()throws RemoteException,IOException;
    
    public Broker connectToPartner(ConnectDetailDTO t)throws RemoteException,IOException;
    
    public byte[] startSharingDesktop(byte[] bytes)throws RemoteException,IOException;
    
    public boolean driverUpload(HashMap<String, byte[]> files,String details) throws RemoteException, AWTException, IOException ;
    
    public HashMap<String, byte[]>  driverDownload(String details) throws RemoteException, AWTException, IOException ;
}
