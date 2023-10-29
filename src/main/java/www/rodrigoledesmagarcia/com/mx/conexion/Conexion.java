package www.rodrigoledesmagarcia.com.mx.conexion;

import java.sql.*;
public class Conexion {

    private static final String url = "jdbc:mysql://localhost:3306/estudiantes_db?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "12345";
    private static Connection connection;

    public static  Connection getConnection() throws SQLException {
        if (connection == null) {connection = DriverManager.getConnection(url, username, password);}
        return connection;
    }
}