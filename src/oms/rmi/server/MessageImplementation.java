/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oms.rmi.server;

import helpers.Func;
import helpers.J;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DBConn;

/**
 *
 * @author End User
 */
public class MessageImplementation extends UnicastRemoteObject implements Message {    
    
    public MessageImplementation() throws RemoteException {
        super(Registry.REGISTRY_PORT);
        System.out.println("[" + Func.getTodayDate() + "] Start Constructor Impl ..");
    }

    @Override
    public void sendMessageUMP(String msg) throws RemoteException {
        
//        J.o("message", msg, 1);
        System.out.println("[" + Func.getTodayDate() + "] Message Received: "+msg);
    }
    
    private static boolean isSelect(String query) {
        if (query.toUpperCase().contains("INSERT INTO")) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String setQueryUMP(String query, String data[]) throws RemoteException {
        String key = "0";
        DBConn dBConn = new DBConn();
        PreparedStatement ps = null;
        try {
            
                ps = dBConn.getOracleConn().prepareStatement(query);
          
            for (int i = 0; i < data.length; i++) {
                ps.setString(i+1, data[i]);
            }
            ps.execute();
                key = "0";
            
        } catch (Exception e) {
            key = e.getMessage();
            System.out.println("[" + Func.getTodayDate() + "] Error:"+e.getMessage());
            //e.printStackTrace();
        }
        try {
            ps.close();
            dBConn.getOracleConn().close();
        } catch (SQLException ex) {
            System.out.println("[" + Func.getTodayDate() + "] Error:"+ex.getMessage());
        }
        return key;
    }
    
    @Override
    public String setQueryUMP(String query, String data[], String priKey) throws RemoteException {
        String key = "0";
        DBConn dBConn = new DBConn();
        PreparedStatement ps = null;
        try {
            ps = dBConn.getOracleConn().prepareStatement(query, new String[]{priKey});   
            for (int i = 0; i < data.length; i++) {
                ps.setString(i+1, data[i]);
            }
            ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    key = rs.getString(1);
                }
        } catch (Exception e) {
            key = e.getMessage();
            System.out.println("[" + Func.getTodayDate() + "] Error:"+e.getMessage());
            //e.printStackTrace();
        }
        try {
            ps.close();
            dBConn.getOracleConn().close();
        } catch (SQLException ex) {
            System.out.println("[" + Func.getTodayDate() + "] Error:"+ex.getMessage());
        }
        return key;
    }
    
    @Override
    public ArrayList<ArrayList<String>> getQueryUMP(String query, String data[]) throws RemoteException {
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        DBConn dBConn = new DBConn();
        PreparedStatement ps = null;
        try {
            
            ps = dBConn.getOracleConn().prepareStatement(query);
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
            System.out.println("[" + Func.getTodayDate() + "] Error:"+e.getMessage());
            //e.printStackTrace();
        }
        try {
            ps.close();
            dBConn.getOracleConn().close();
        } catch (SQLException ex) {
            System.out.println("[" + Func.getTodayDate() + "] Error:"+ex.getMessage());
        }
        return output;
    }
    
    
}
