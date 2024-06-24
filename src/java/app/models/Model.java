package app.models;

import java.sql.Connection;
import java.sql.Statement;

import app.db.DB;

public abstract class Model {
    protected Connection con;
    protected String table;

    public Model(String table) {
        this.table = table;
        this.checkTable();
        con = DB.getConnection();
    }    

    private void checkTable() {
        if (table == null || table.isEmpty()) {
            throw new RuntimeException("Table name is required");
        }
        Statement stmt = DB.getStatement();
        try {
            stmt.executeQuery("SELECT * FROM " + table + " LIMIT 1");
        } catch (Exception e) {
            this.createTable();
        }
    }

    public void delete(int id) {
        try {
            Statement stmt = DB.getStatement();
            stmt.executeUpdate("DELETE FROM " + table + " WHERE id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete the record");
        }
    }

    protected abstract void createTable();
}
