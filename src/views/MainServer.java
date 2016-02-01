/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.DBConn;
import models.RMIConn;

/**
 *
 * @author umarmukhtar
 */
public class MainServer {
    
    public static void main(String[] args) throws SQLException {
        
        String ipPublic1 = "";
        String ipDB = "";
        String port = "1521";
        String serviceName = "UMPDB";
        String username = "cmsadmin";
        String password = "7";
        
        try { ipPublic1 = (args[0] != null) ? (args[0]) : (RMIConn.ipPublic); } catch (Exception e) { ipPublic1 = RMIConn.ipPublic; }
        try { ipDB = (args[1] != null) ? (args[1]) : (DBConn.getIpDB2()); } catch (Exception e) { ipDB = DBConn.getIpDB2(); }
        try { port = (args[2] != null) ? (args[2]) : (DBConn.getPort()); } catch (Exception e) { port = DBConn.getPort(); }
        try { serviceName = (args[3] != null) ? (args[3]) : (DBConn.getServiceName()); } catch (Exception e) { serviceName = DBConn.getServiceName(); }
        try { username = (args[4] != null) ? (args[4]) : (DBConn.getUsername()); } catch (Exception e) { username = DBConn.getUsername(); }
        try { password = (args[5] != null) ? (args[5]) : (DBConn.getPassword()); } catch (Exception e) { password = DBConn.getPassword(); }
        
        System.out.println("Starting server ...");
        RMIConn.startServer(ipPublic1);
        DBConn.setConnAll(ipDB, port, serviceName, username, password);
        System.out.println("Server started.");
        
        DBConn dBConn = new DBConn(DBConn.getIpDB2(), DBConn.getPort(), DBConn.getServiceName(), DBConn.getUsername(), DBConn.getPassword());
        String sql = "SELECT * FROM login1 ";
        //String sql = "SELECT * FROM warrant_main_head ";
        PreparedStatement ps = dBConn.getOracleConn(ipDB, port, serviceName, username, password).prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            try {
                for (int i = 0; ; i++) {
                    System.out.print(rs.getString(i+1)+" | ");
                }
            } catch (Exception e) {
            }
            System.out.println("");
        }
    }
}
