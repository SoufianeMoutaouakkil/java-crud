package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    static final String dbUrl = "jdbc:mysql://localhost:3306/";
    static final String dbName = "crud_demo";
    static final String dbUsername = "root";
    static final String dbPassword = "";
    static private boolean isConnected = false;

    static Connection con;

    public static void connect() {
        try {
            con = DriverManager.getConnection(dbUrl + dbName, dbUsername, dbPassword);
            isConnected = true;
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    public static Connection getConnection() throws RuntimeException {
        if (!isConnected) {
            DB.connect();
        }
        return con;
    }

    public static Statement getStatement() {
        if (!isConnected) {
            DB.connect();
        }
        try {
            return con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a statement");
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        if (!isConnected) {
            DB.connect();
        }
        try {
            return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a prepared statement");
        }
    }

    public static void close() {
        if (!isConnected) {
            return;
        }
        try {
            con.close();
            System.out.println("Disconnected from the database");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close the connection");
        }
    }

}
