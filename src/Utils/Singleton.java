package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {

    public Connection cnx;

    private String dsn = "jdbc:mariadb://10.30.103.63:3306/Gestion_Parking";
    private String username = "root";
    private String mdp = "";
    private static Singleton instance;

    private Singleton() throws SQLException{
        cnx = DriverManager.getConnection(dsn, username, mdp);
    }

    public static Singleton getInstance() throws SQLException {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }
}
