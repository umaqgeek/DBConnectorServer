/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author End User
 */
public interface Message extends Remote {
    
    public void sendMessageUMP(String msg) throws RemoteException;
    
    public String setQueryUMP(String query, String data[]) throws RemoteException;
    public String setQueryUMP(String query, String data[], String priKey) throws RemoteException;
    public ArrayList<ArrayList<String>> getQueryUMP(String query, String data[]) throws RemoteException;
}
