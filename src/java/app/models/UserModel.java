package app.models;

import app.db.DB;
import app.entities.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserModel extends Model {

    public UserModel() {
        super("users");
    }

    public User create(User user) throws RuntimeException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = DB.getPreparedStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
            // return the id of the new user
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            return getById(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the user");
        }
    }

    public User getById(int id) throws RuntimeException {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try {
            PreparedStatement pstmt = DB.getPreparedStatement(sql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }

            resultSet.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get the user");
        }
    }

    public List<User> getAll() throws RuntimeException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try {
            Statement stmt = DB.getStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                users.add(user);
            }

            resultSet.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get the users");
        }
    }

    public void update(User user) throws RuntimeException {
        String sql = "UPDATE users SET";
        if (user.getName() != null) {
            sql += " name = ?,";
        }
        if (user.getEmail() != null) {
            sql += " email = ?,";
        }
        if (user.getPassword() != null) {
            sql += " password = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE id = ?";

        try {
            PreparedStatement pstmt = DB.getPreparedStatement(sql);
            int index = 1;
            if (user.getName() != null) {
                pstmt.setString(index, user.getName());
                index++;
            }
            if (user.getEmail() != null) {
                pstmt.setString(index, user.getEmail());
                index++;
            }
            if (user.getPassword() != null) {
                pstmt.setString(index, user.getPassword());
                index++;
            }
            pstmt.setInt(index, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update the user");
        }
    }

    public void delete(int id) throws RuntimeException {
        String sql = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement pstmt = DB.getPreparedStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete the user");
        }
    }

    protected void createTable() {
        String sql = "CREATE TABLE users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL" +
                ")";
        try {
            Statement stmt = DB.getStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create the users table");
        }
    }

    public int getLastId() throws RuntimeException {
        String sql = "SELECT MAX(id) AS id FROM users";
        int lastId = 0;

        try {
            Statement stmt = DB.getStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                lastId = resultSet.getInt("id");
            }

            resultSet.close();
            return lastId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get the last id");
        }
    }

}
