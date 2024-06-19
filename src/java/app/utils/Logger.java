package app.utils;

public class Logger {
    public static void log(String message) {
        System.out.println("#".repeat(100));
        System.out.println(message);
        System.out.println("#".repeat(100));
    }
    public static void log(String title , String message) {
        System.out.println("#".repeat(100));
        System.out.println(title + " : ");
        System.out.println(message);
        System.out.println("#".repeat(100));
    }

}
