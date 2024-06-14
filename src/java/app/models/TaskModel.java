package app.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.db.DB;
import app.entities.Task;

public class TaskModel extends Model {
    public TaskModel() {
        super("tasks");
    }

    public Task create(Task task) throws RuntimeException {
        String sql = "INSERT INTO tasks (name, description, user_id, status) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = DB.getPreparedStatement(sql);
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setInt(3, task.getUserId());
            // set the default status to 'todo' if not provided
            pstmt.setString(4, task.getStatus() != null ? task.getStatus() : Task.STATUS_TODO);
            pstmt.executeUpdate();
            // return the id of the new task
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
            }
            return getById(task.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the task");
        }
    }

    public Task getById(int id) throws RuntimeException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        Task task = null;

        try {
            PreparedStatement pstmt = DB.getPreparedStatement(sql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("user_id"));
            }

            resultSet.close();
            return task;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get the task");
        }
    }

    public List<Task> getAll() throws RuntimeException {
        String sql = "SELECT * FROM tasks";
        List<Task> tasks = new ArrayList<>();

        try {
            Statement stmt = DB.getStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("user_id"));
                tasks.add(task);
            }

            resultSet.close();
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get the tasks");
        }
    }

    @Override
    protected void createTable() {
        String sql = "CREATE TABLE tasks (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "description TEXT, " +
                "status VARCHAR(255) DEFAULT 'todo' NOT NULL, " +
                "user_id INT NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES users(id)" +
                ")";
        try {
            Statement stmt = DB.getStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create the tasks table");
        }
    }
}
