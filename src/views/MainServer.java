/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.RMIConn;

/**
 *
 * @author umarmukhtar
 */
public class MainServer {
    
    public static void main(String[] args) {
        
        String ipPublic1 = (args[0] != null) ? (args[0]) : (RMIConn.ipPublic);
        
        System.out.println("Starting server ...");
        RMIConn.startServer(ipPublic1);
        System.out.println("Server started.");
    }
}
