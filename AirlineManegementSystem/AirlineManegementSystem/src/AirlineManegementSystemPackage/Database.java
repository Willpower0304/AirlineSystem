package AirlineManegementSystemPackage;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Database {
    
    private String url = "jdbc:mysql://localhost/airline management system";
    private String user = "root";
    private String pass = "";
    private Statement statement;
    
    public Database() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, pass);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
    }
    
   public Statement getStatement() {
       return statement;
   }
   
}
