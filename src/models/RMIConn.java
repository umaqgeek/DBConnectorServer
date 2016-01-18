/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import helpers.J;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import oms.rmi.server.Message;
import oms.rmi.server.MessageImplementation;

/**
 *
 * @author umarmukhtar
 */
public class RMIConn {
    
    private static Message impl;
    private static String localRMIFlag = "DBConn";
    public final static String ipPublic = "175.142.10.173";
    //private static String ipPublic = "192.168.1.5";
    
    public static boolean startRMI() {
        
        boolean status = true;
        try {
            // set fixed ip
            System.setProperty("java.rmi.server.hostname", ipPublic);
            Registry myRegistry = LocateRegistry.getRegistry(DBConn.getHost(), DBConn.getPort_rmi());
            impl = (Message) myRegistry.lookup(localRMIFlag);
            status = true;

        } catch (Exception e) {
            
            status = false;
            J.o("Network Error", "Network Error!\nPlease check with your administrator ..", 0);
            e.printStackTrace();
        }
        return status;
    }
    
    public static void startServer(String ipPub) {
        try {
            
            // create on port 1099
            Registry registry = LocateRegistry.createRegistry(DBConn.getPort_rmi());
            
            // set fixed ip
            System.setProperty("java.rmi.server.hostname", ipPublic);

            // create a new service named myMessage
            registry.rebind(localRMIFlag, new MessageImplementation());
        } catch (RemoteException ex) {
            //ex.printStackTrace();
            System.out.println("Error in RMI Start Server: "+ex.getMessage());
        }
    }
    
    public static Message getImpl() {
        return impl;
    }
}
