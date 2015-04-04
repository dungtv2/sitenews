/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Truong Van Dung
 */
public class ConnectionManager {

    private static ConnectionManager instance;
    private Connection con;

    private final String USER = "sa";
    private final String PASSWORD = "loveyoubabypx";
    private final String ST_CON = "jdbc:sqlserver://localhost:1433;databaseName=news";
    private final String URL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private ConnectionManager() {
    }

    public static ConnectionManager newInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.con;
    }

    public void openConnection() {
        if (con == null) {
            try {
                Class.forName(URL);
                con = DriverManager.getConnection(ST_CON, USER, PASSWORD);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
