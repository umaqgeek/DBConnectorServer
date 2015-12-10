/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oms.rmi.server;

import helpers.J;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.DBConn;

/**
 *
 * @author End User
 */
public class MessageImplementation extends UnicastRemoteObject implements Message {    
    
    public MessageImplementation() throws RemoteException {
        
    }

    @Override
    public void sendMessage(String msg) throws RemoteException {
        
//        J.o("message", msg, 1);
        System.out.println("Message Received: "+msg);
    }
    
    @Override
    public boolean setQuery(String query, String data[]) throws RemoteException {
        boolean status = false;
        try {
            DBConn dBConn = new DBConn();
            PreparedStatement ps = dBConn.getOracleConn().prepareStatement(query);
            for (int i = 0; i < data.length; i++) {
                ps.setString(i+1, data[i]);
            }
            ps.execute();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
    
    @Override
    public ArrayList<ArrayList<String>> getQuery(String query, String data[]) throws RemoteException {
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        try {
            DBConn dBConn = new DBConn();
            PreparedStatement ps = dBConn.getOracleConn().prepareStatement(query);
            for (int i = 0; i < data.length; i++) {
                ps.setString(i+1, data[i]);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<String> mini = new ArrayList<String>();
                try {
                    for (int i = 0; ; i++) {
                        mini.add(rs.getString(i+1));
                    }
                } catch (Exception e) {
                }
                output.add(mini);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
    
    
}
