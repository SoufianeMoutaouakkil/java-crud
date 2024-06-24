package app.utils;

public class Validator {
    public static String RX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static String RX_EMAIL_MESSAGE = "Invalid email address";
    public static String RX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";
    public static String RX_PASSWORD_MESSAGE = "Password must contain at least 4 characters, including UPPER/lowercase and numbers";
    public static String RX_ID = "^[0-9]+$";
    public static String RX_ID_MESSAGE = "Invalid ID";

    public static boolean isValidEmail(String email) {
        return email.matches(RX_EMAIL);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(RX_PASSWORD);
    }

    public static boolean isValidId(String id) {
        return id.matches(RX_ID);
    }
}
