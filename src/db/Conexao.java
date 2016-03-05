package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private String banco = "jdbc:mysql://localhost/chronos";
    private String usuario = "root";
    private String senha = "f16cent@";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(banco, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
}
