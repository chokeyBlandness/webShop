package com.jingdong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase_webShop {
    private Connection connection=null;
    private Statement statement=null;
    ConnectDatabase_webShop(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection= DriverManager.getConnection("jdbc:sqlserver://HASEE-PC:1433;DatabaseNamE=webShop",
                    "sa","111");
            statement=connection.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void closeConnect(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public Statement getStatement() {
        return statement;
    }
}
