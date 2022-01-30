package datos;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
/*subiendo git*/
    /*cadena de conexion hacia  mysql*/
    private static final String JDBC_UR ="jdbc:mysql://localhost:3306/test?useSSl=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
                                           
    private static final String JDBC_USER ="root";
    private static final String JDBC_PASSWORD ="root";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(JDBC_UR, JDBC_USER, JDBC_PASSWORD);

    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close (Statement smtm) {
        try {
            smtm.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
    
     public static void close (PreparedStatement smtm) {
        try {
            smtm.close();
        } catch (SQLException ex) {
          ex.printStackTrace(System.out);
        }
    }
     
    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
          ex.printStackTrace(System.out);
        }
    }
    
    

}
