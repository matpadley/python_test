// File: Demo.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {
    public static void main(String[] args) {
        String id = args[0]; // User-supplied input
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "password");
            Statement statement = connection.createStatement();
            // Vulnerable to SQL Injection
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = " + id);
            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id"));
                System.out.println("Username: " + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
