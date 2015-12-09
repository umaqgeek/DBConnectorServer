/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oms.rmi.server;

import helpers.J;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author End User
 */
public class MessageImplementation extends UnicastRemoteObject implements Message {    
    
    public MessageImplementation() throws RemoteException {
        
    }

    @Override
    public void sendMessage(String msg) throws RemoteException {
        
        J.o("message", msg, 1);
    }
    
    @Override
    public boolean setQuery(String query, String data[]) throws RemoteException {
        boolean status = false;
        try {
            
        } catch (Exception e) {
        }
        return status;
    }
    
    @Override
    public ArrayList<ArrayList<String>> getQuery(String query, String data[]) throws RemoteException {
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        try {
            
        } catch (Exception e) {
        }
        return output;
    }
    
    
}
