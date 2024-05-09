// File: VulnerableServlet.java
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VulnerableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"); // User-supplied input
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "password");
            Statement statement = connection.createStatement();
            // Vulnerable to SQL Injection
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = " + id);
            while (resultSet.next()) {
                response.getWriter().println("User ID: " + resultSet.getInt("id"));
                response.getWriter().println("Username: " + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}