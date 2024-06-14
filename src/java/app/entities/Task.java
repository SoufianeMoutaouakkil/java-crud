package app.entities;


public class Task {
    int id;
    String name;
    String description;
    String status;
    int userId;

    public static final String STATUS_TODO = "todo";
    public static final String STATUS_DONE = "done";

    public Task() {
    }

    public Task(int id, String name, String description, String status, int userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals(STATUS_TODO) || status.equals(STATUS_DONE)) {
            this.status = status;
        } else {
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Task [id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", status=" + status +
                ", userId=" + userId +
                "]";
    }
}
