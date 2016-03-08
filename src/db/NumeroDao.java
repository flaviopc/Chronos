package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Numero;

public class NumeroDao {

    private Connection conn;

    public NumeroDao() throws ClassNotFoundException, SQLException {
        this.conn = new Conexao().getConnection();
    }

    //Selecionar os Numeros do banco
    public ArrayList<Numero> select() {
        Numero t = new Numero();
        ArrayList<Numero> Numero = new ArrayList<Numero>();

        ResultSet resultado = null;
        Object[][] r = null;
        try {

            Statement stmt = conn.createStatement();
            //ResultSet resultado;
            String query = "SELECT * FROM atleta";

            stmt.executeQuery(query);
            resultado = stmt.getResultSet();

            while (resultado.next()) {
                Numero novo_t = new Numero();
                novo_t.setNumero(resultado.getString("ATL_numero"));
                novo_t.setSintuacao("correndo");
                Numero.add(novo_t);
            }
            conn.close();
        } catch (Exception e) {
        }

        return Numero;
    }

    public void deletaNumAtletas(int atletas[]) {
        if (conn != null) {
            try {
                if (atletas.length > 1) {
                    String sql = "delete from numero where NUM_codigo > ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, atletas[0]);
                        ps.execute();
                        atualizarNumeros();
                    }
                } else {
                    String sql = "delete from numero where NUM_codigo=?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, atletas[0] + 1);
                        ps.execute();
                        atualizarNumeros();
                    }
                }
            } catch (SQLException e) {

            }
        } else {
            System.out.println("Você precisa criar uma conexão com o banco de dados!");
        }
    }

    public void atualizarNumeros() {
        if (conn != null) {
            try {
                String sql = "SET @count = 0";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.execute();
                }
                sql = "UPDATE numero SET NUM_codigo = @count:= @count + 1";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.execute();
                }
            } catch (SQLException e) {

            }
        } else {
            System.out.println("Você precisa criar uma conexão com o banco de dados!");
        }
    }

    //Insere numero do atleta pela ordem de chegada 
    public boolean insereN(String numero) throws SQLException {
        boolean sucesso = true;
        if (conn != null) {
            try {
                String sql = "insert into numero (NUM_numero) values (?)";                
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, numero);
                    return ps.execute();
                }

            } catch (SQLException e) {
                System.out.println("Informações sobre o erro: " + e.getMessage());
            } finally {
                atualizarNumeros();
                conn.close();
            }
        } else {
            System.out.println("Você precisa criar uma conexão com o banco de dados!");
        }

        return sucesso;
    }
}
