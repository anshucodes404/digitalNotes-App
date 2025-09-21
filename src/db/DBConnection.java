package db;
import java.sql.*;
public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/digitalnotes";
    private static final String user = "root";
    private static final String password = <your-password here>;

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
           return DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
