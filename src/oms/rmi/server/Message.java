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
    
    public void sendMessage(String msg) throws RemoteException;
    
    public boolean setQuery(String query, String data[]) throws RemoteException;
    public ArrayList<ArrayList<String>> getQuery(String query, String data[]) throws RemoteException;
}
