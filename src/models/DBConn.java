/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author End User
 */
public class DBConn {
    private static String host = "192.168.1.7";
    private static int netTime = 2000;
    private static String user = "dba1";
    private static String pass = "qwerty";
    private static String database = "servercis";
    private static String dbUrl = "jdbc:mysql://" + getHost() + "/" + getDatabase();
    private static int port_rmi = 1099;
    private static String stringConn = "jdbc:oracle:thin:@localhost:1521/XE";
    private Connection oracleConn;
    
    public DBConn() {
        startOracleConn();
    }

    public static String getHost() {
        return host;
    }

    public static int getNetTime() {
        return netTime;
    }

    public static String getUser() {
        return user;
    }

    public static String getDatabase() {
        return database;
    }

    public static int getPort_rmi() {
        return port_rmi;
    }
    public Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, getUser(), pass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private boolean startOracleConn() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.oracleConn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)"
                            + "(HOST=192.168.1.7)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=UMPDB)))", "cmsadmin", "7");
            //this.oracleConn = DriverManager.getConnection(
            //        "jdbc:oracle:thin:@localhost:1521/XE", "dba1", "qwerty");
            return true;
        } catch (Exception ex) {
            //ex.printStackTrace();
            //System.exit(1);
            System.out.println("Error Oracle: "+ex.getMessage());
            return startOracleConn();
        }
    }
    
    public Connection getOracleConn() {
        try {
            boolean isConn = true;
            if (this.oracleConn.isClosed() || this.oracleConn == null) {
                isConn = startOracleConn();
            }
            if (isConn) {
                return this.oracleConn;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("f");
            e.printStackTrace();
        }
        return null;
    }
}
