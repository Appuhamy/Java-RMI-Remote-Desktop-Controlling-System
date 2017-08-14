/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.observer;

import java.awt.AWTException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author Chamindu_Appuhamy
 */
public interface Subject<T> extends Remote{
    
    public void registerObserver(T observer) throws RemoteException;
    
    public void unregisterObserver(T observer) throws RemoteException;
    
    public void notifyAllObservers(String senderName,String message)throws RemoteException;
    
}
