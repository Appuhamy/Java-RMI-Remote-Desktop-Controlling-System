/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.connecter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import lk.ilemo.viewer.service.ServiceFactory;
import lk.ilemo.viewer.service.custom.UserService;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class ServerConnecter {
    private static ServerConnecter connecter;
    private UserService service;
    private ServiceFactory factory;
    public ServerConnecter() throws NotBoundException, MalformedURLException, RemoteException {
        factory=(ServiceFactory) Naming.lookup("rmi://192.168.8.100:5050/Viewer-Server");
        service=(UserService) factory.getService(ServiceFactory.ServiceType.USER);
        
    }
    public static ServerConnecter getServerConnecter() throws NotBoundException, MalformedURLException, RemoteException{
        if(connecter==null){
            connecter=new ServerConnecter();
        }
        return connecter;
    }
    public UserService getUserService(){
        return service;
    }
    
    
}
