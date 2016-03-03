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
import model.numero;

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
                String sql = "insert into TEMPO (TEM_id,TEM_tempo) values (?,?)";
           
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
                        tempo.setId(res.getInt(1));
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
                String sql = "select * from tempo where TEM_id=(select max(TEM_id) from TEMPO)";
           
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ResultSet res = ps.executeQuery();
                    while(res.next()){
                        tempo.setId(res.getInt(1));
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
   
     //Selecionar os Numeros do banco
    public ArrayList<numero> selectN(){ 
		numero t = new numero();
		ArrayList<numero> Numero = new ArrayList<numero>();

		ResultSet resultado = null;
		Object[][] r = null;
		try{

			Statement stmt = conn.createStatement();
			//ResultSet resultado;
			String query = "SELECT * FROM atleta";

			stmt.executeQuery(query);
			resultado = stmt.getResultSet();


			while (resultado.next()){ 
				numero novo_t = new numero();
				novo_t.setNumero( resultado.getString("ATL_numero"));
				novo_t.setSintuacao("correndo");
				Numero.add(novo_t);
			} 
			conn.close();
		}catch ( Exception e) {}  


		return  Numero;
	}
	//Insere numero do atleta pela ordem de chegada 
	public boolean insereN(String numero) throws SQLException {
		boolean sucesso = true;
		if (conn != null) {            
			try {
				String sql = "insert into numero (NUM_numero) values (?)";

				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setString(1,numero);
					return ps.execute();                        
				}

			} catch (SQLException e) {
				System.out.println("InformaÃ§Ãµes sobre o erro: "+e.getMessage());
			}finally{
				conn.close();
			}
		} else {
			System.out.println("VocÃª precisa criar uma conexÃ£o com o banco de dados!");
		}

		return sucesso;
	}


}
