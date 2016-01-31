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
        
        try {
            ipPublic1 = (args[0] != null) ? (args[0]) : (RMIConn.ipPublic);
        } catch (Exception e) {
            ipPublic1 = RMIConn.ipPublic;
        }
        
        System.out.println("Starting server ...");
        RMIConn.startServer(ipPublic1);
        System.out.println("Server started.");
        
        DBConn dBConn = new DBConn();
        String sql = "SELECT * FROM login1 ";
        //String sql = "SELECT * FROM warrant_main_head ";
        PreparedStatement ps = dBConn.getOracleConn().prepareStatement(sql);
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
