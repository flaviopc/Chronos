/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Flavio
 */
public class RelatorioDao {

    private Connection conn;
    public ResultSet rsGeral;
    public ResultSet rsCat;
    public ResultSet rsCate;

    public RelatorioDao() {
        this.conn = new Conexao().getConnection();
    }

    public Connection getConn() {
        return conn;
    }

    public ResultSet conexao() {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                Statement stmt = conn.createStatement();
                String query = "select distinct(ATL_categoria) from atleta order by ATL_categoria";
                rsCat = stmt.executeQuery(query);
                return rsCat;
                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }
        return rs;
    }

    public void conexao2() {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                Statement stmt = conn.createStatement();
                String queryGeral = "select TEM_id as Colocação, ATL_numero, ATL_nome, ATL_categoria, TEM_tempo as Tempo from numero, tempo, atleta where NUM_chave = TEM_id and NUM_numero = ATL_numero";
                rsGeral = stmt.executeQuery(queryGeral);
               
                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }
        
    }

    public void conexaoCat(String cat) {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                Statement stmt = conn.createStatement();
                String query = "select TEM_id as ColocaçãoGeral, ATL_numero, ATL_nome, ATL_categoria, TEM_id as Tempo from numero, tempo, atleta "
                        + "where NUM_chave = TEM_id and NUM_numero = ATL_numero and ATL_categoria like \"" + cat + "\"";
                rsCate = stmt.executeQuery(query);
               
                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }
        
    }
}
