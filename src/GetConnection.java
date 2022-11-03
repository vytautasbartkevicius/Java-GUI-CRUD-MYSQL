import java.sql.*;
import java.sql.SQLException;
public class GetConnection {

    public static Connection connection;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}