/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Chamindu_Appuhamy
 */
public interface ServiceFactory extends Remote{
    enum ServiceType{
        USER;
    }
    public SuperService getService(ServiceType serviceType)throws RemoteException;
}
