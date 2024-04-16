import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;

public class SQLInjectionExample {
    private static BasicDataSource dataSource;

    public static void main(String[] args) {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydatabase");
        dataSource.setUsername("username");
        dataSource.setPassword("password");

        String userInput = "attackerControlledInput"; // This should be validated and sanitized before use
        executeQuery("SELECT * FROM users WHERE username = '" + userInput + "'");
    }

    private static void executeQuery(String query) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            // Process the result set...
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
