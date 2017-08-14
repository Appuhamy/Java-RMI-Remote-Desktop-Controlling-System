/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ilemo.viewer.service.custom.impl.UserServiceImpl;
import lk.ilemo.viewer.service.impl.ServiceFactoryImpl;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class StartServer {
    public static void main(String[] args) {
        try {
            Registry ob=LocateRegistry.createRegistry(5050);
            System.out.println("Viewer Server is Start...");
            ob.rebind("Viewer-Server",new ServiceFactoryImpl());
        } catch (RemoteException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
