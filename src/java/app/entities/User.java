package app.entities;

import app.utils.Logger;
import app.utils.Security;
import app.utils.Validator;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    // Constructor
    public User() {
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) throws Exception {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
    }

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public void setPassword(String password) throws Exception {
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.password = Security.hashPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String password) {
        try {

            if (Security.verifyPassword(password, this.password)) {
                return true;
            }
        } catch (Exception e) {
            Logger.log("User checkPassword: error", e.getMessage());
        }
        return false;
    }

    // Override toString() for easy printing
    @Override
    public String toString() {
        return "User [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}
