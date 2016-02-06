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

    public static String getIpDB2() {
        return ipDB2;
    }

    public static String getPort() {
        return port;
    }

    public static String getServiceName() {
        return serviceName;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
    private Connection oracleConn;
    
    private static String ipDB2 = "192.168.1.7";
    private static String port = "1521";
    private static String serviceName = "UMPDB";
    private static String username = "cmsadmin";
    private static String password = "7";
    
    public static void setConnAll(String ipDB1, String port, String serviceName, String username, String password) {
        DBConn.ipDB2 = ipDB1;
        DBConn.port = port;
        DBConn.serviceName = serviceName;
        DBConn.username = username;
        DBConn.password = password;
    }
    
    public DBConn(String ipDB1, String port, String serviceName, String username, String password) {
        DBConn.ipDB2 = ipDB1;
        DBConn.port = port;
        DBConn.serviceName = serviceName;
        DBConn.username = username;
        DBConn.password = password;
        startOracleConn(ipDB1, port, serviceName, username, password);
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
    
    private boolean startOracleConn(String ipDB2, String port, String serviceName, String username, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            this.oracleConn = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)"
//                            + "(HOST=192.168.1.7)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=UMPDB)))", "cmsadmin", "7");
            this.oracleConn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)"
                            + "(HOST="+ipDB2+")(PORT="+port+"))(CONNECT_DATA=(SERVICE_NAME="+serviceName+")))", username, password);
//            System.out.println("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)"
//                            + "(HOST="+ipDB2+")(PORT="+port+"))(CONNECT_DATA=(SERVICE_NAME="+serviceName+")))");
//            System.out.println(username+"|"+password);
//            this.oracleConn = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)"
//                            + "(HOST=172.16.30.48)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=umpdev.ump.edu.my)))", "cmsadmin", "cmsadmin12");
//            System.out.println("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)"
//                            + "(HOST=172.16.30.48)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=umpdev.ump.edu.my)))");
//            System.out.println("cmsadmin"+"|"+"cmsadmin12");
            //this.oracleConn = DriverManager.getConnection(
            //        "jdbc:oracle:thin:@localhost:1521/XE", "dba1", "qwerty");
            return true;
        } catch (Exception ex) {
            //ex.printStackTrace();
            //System.exit(1);
            System.out.println("Error Oracle: "+ex.getMessage());
            return startOracleConn(ipDB2, port, serviceName, username, password);
        }
    }
    
    public Connection getOracleConn(String ipDB1, String port, String serviceName, String username, String password) {
        try {
            boolean isConn = true;
            if (this.oracleConn.isClosed() || this.oracleConn == null) {
                isConn = startOracleConn(ipDB1, port, serviceName, username, password);
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
