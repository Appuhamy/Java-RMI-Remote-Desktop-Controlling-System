/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import lk.ilemo.viewer.service.ServiceFactory;
import lk.ilemo.viewer.service.SuperService;
import lk.ilemo.viewer.service.custom.impl.UserServiceImpl;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{
    private static ServiceFactoryImpl factoryImpl;
    public ServiceFactoryImpl()throws RemoteException{
        
    }

    public static ServiceFactory getInstance() throws RemoteException{
        if(factoryImpl==null){
            factoryImpl=new ServiceFactoryImpl();
        }
        return factoryImpl;
    }
    @Override
    public SuperService getService(ServiceType serviceType) throws RemoteException {
        switch(serviceType){
            case USER:return new UserServiceImpl();
            default:return null;
        }
    }
    
}
