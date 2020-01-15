package upravljalko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class MyConnector{  
    public static Connection getConnection() throws ClassNotFoundException{
        Connection con = null; 
        try { 
            String ipAddress = ""; //!!! Add your IP
            String url = "jdbc:mysql://" +  ipAddress + "/Upravljalko"; 
            Properties info = new Properties(); 
            info.put("user", "user"); 
            info.put("password", "password");
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con = DriverManager.getConnection(url, info); 
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return con; 
    }
}