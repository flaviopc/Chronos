/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Tempo;
import model.Numero;

/**
 *
 * @author Flavio
 */
public class TempoDao {

    private Connection conn;

    public TempoDao() {
        this.conn = new Conexao().getConnection();
    }

    public boolean insere(Tempo tempo) throws SQLException {
        boolean sucesso = true;
        if (conn != null) {            
            try {
                String sql = "insert into TEMPO (TEM_codigo,TEM_tempo) values (?,?)";
           
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, null);
                    ps.setString(2, tempo.getTempo());
                    return ps.execute();                        
                }
                
            } catch (SQLException e) {
                System.out.println("Informações sobre o erro: "+e.getMessage());
            }finally{
                conn.close();
            }
        } else {
            System.out.println("Você precisa criar uma conexão com o banco de dados!");
        }
        
        return sucesso;
    }
    
    public ArrayList<Tempo> getTempos() throws SQLException{
        ArrayList<Tempo> tempos = new ArrayList<Tempo>();       
        
        
        if (conn != null) {            
            try {
                String sql = "select * from tempo";
           
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ResultSet res = ps.executeQuery();                   
                    while(res.next()){
                        Tempo tempo = new Tempo();
                        tempo.setCodigo(res.getInt(1));
                        tempo.setTempo(res.getString(2));
                        tempos.add(tempo);                       
                    }
                    res.close();
                    return tempos;                        
                }
                
            } catch (SQLException e) {
                System.out.println("Informações sobre o erro: "+e.getMessage());
            }finally{
                conn.close();
            }
        } else {
            System.out.println("Você precisa criar uma conexão com o banco de dados!");
        }
        return null;
    }
    
    public Tempo getUltimoTempo() throws SQLException{
        Tempo tempo = new Tempo();
        if (conn != null) {            
            try {
                String sql = "select * from tempo where TEM_codigo=(select max(TEM_codigo) from TEMPO)";
           
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ResultSet res = ps.executeQuery();
                    while(res.next()){
                        tempo.setCodigo(res.getInt(1));
                        tempo.setTempo(res.getString(2));
                    }
                    res.close();
                    return tempo;                        
                }
                
            } catch (SQLException e) {
                System.out.println("Informações sobre o erro: "+e.getMessage());
            }finally{
                conn.close();
            }
        } else {
            System.out.println("Você precisa criar uma conexão com o banco de dados!");
        }
        return null;
    }

    public Connection getConn() {
        return conn;
    }    
    
    public void limpaTabela() throws SQLException{
        if (conn != null) {            
            try {
                String sql = "truncate TEMPO";
           
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.execute();                                                               
                }
                
            } catch (SQLException e) {
                System.out.println("Informações sobre o erro: "+e.getMessage());
            }finally{
                conn.close();
            }
        } else {
            System.out.println("Você precisa criar uma conexão com o banco de dados!");
        }
    }       
}
