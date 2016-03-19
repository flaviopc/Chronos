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
    public ResultSet rsTipoCat;
    public ResultSet rsCate;
    public ResultSet rsGeralFM;
    public ResultSet rsGeralPrimeiros;
    public ResultSet rsPosi;
    public RelatorioDao() {
        this.conn = new Conexao().getConnection();
    }

    public Connection getConn() {
        return conn;
    }

    public ResultSet selectTipCat() {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                Statement stmt = conn.createStatement();
                String query = "select distinct(ATL_categoria) from atleta order by ATL_categoria";
                rsTipoCat = stmt.executeQuery(query);
                return rsTipoCat;
                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }
        return rs;
    }

    public void selectGeral() {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                Statement stmt = conn.createStatement();
                String queryGeral = "select TEM_codigo as Colocação, ATL_numero, ATL_nome, ATL_categoria, TEM_tempo as Tempo from numero, tempo, atleta where NUM_codigo = TEM_codigo and NUM_numero = ATL_numero";
                rsGeral = stmt.executeQuery(queryGeral);
                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }

    }

    public void SelectCat(String cat) {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                Statement stmt = conn.createStatement();
                String query = "select TEM_codigo as ColocacaoGeral, ATL_numero, ATL_nome, ATL_categoria, TEM_tempo as Tempo from numero, tempo, atleta "
                        + "where NUM_codigo = TEM_codigo and NUM_numero = ATL_numero and ATL_categoria like \"" + cat + "\"";
                rsCate = stmt.executeQuery(query);

                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }
    }

    //muitos teste encontrar um jeito mais facil
    public void SelectPosi(char cat) {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                if (cat == 'F') {
                    Statement stmt = conn.createStatement();
                    String query = "select @rownum:=@rownum+1 AS classificação, ATL_numero from numero, tempo, atleta, (SELECT @rownum:=0) r "
                            + "where NUM_codigo = TEM_codigo and NUM_numero = ATL_numero and ATL_categoria like \"" + cat + "%\" ";
                    rsPosi = stmt.executeQuery(query);
                }

                if (cat == 'M') {
                    Statement stmt = conn.createStatement();
                    String query = "select @rownum:=@rownum+1 AS classificação, ATL_numero from numero, tempo, atleta, (SELECT @rownum:=0) r "
                            + "where NUM_codigo = TEM_codigo and NUM_numero = ATL_numero and ATL_categoria like \"" + cat + "%\" ";
                    rsPosi = stmt.executeQuery(query);
                }

                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }
    }

    public void SelectGeral_F_M(String cat) {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                if (cat.equals("GERAL F")) {
                    Statement stmt = conn.createStatement();
                    String query = "select @rownum:=@rownum+1 AS classificação, ATL_numero, ATL_nome, ATL_categoria, TEM_tempo as Tempo "
                            + "from numero, tempo, atleta, (SELECT @rownum:=0) r "
                            + "where NUM_codigo = TEM_codigo and NUM_numero = ATL_numero and ATL_categoria like \"F%\"";
                    rsGeralFM = stmt.executeQuery(query);
                } else if (cat.equals("GERAL M")) {
                    Statement stmt = conn.createStatement();
                    String query = "select @rownum:=@rownum+1 AS classificação, ATL_numero, ATL_nome, ATL_categoria, TEM_tempo as Tempo "
                            + "from numero, tempo, atleta, (SELECT @rownum:=0) r "
                            + "where NUM_codigo = TEM_codigo and NUM_numero = ATL_numero and ATL_categoria like \"M%\"";
                    rsGeralFM = stmt.executeQuery(query);
                }
                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }
    }

    public void SelectGeralPrimeiros(char cat) {
        ResultSet rs = null;
        if (conn != null) {
            System.out.println("Conexão Java-MySQL efetuada com sucesso!\n");
            try {
                if (cat == 'F') {
                    Statement stmt = conn.createStatement();
                    String query = "select  ATL_numero from numero, tempo, atleta \n"
                            + "where NUM_codigo = TEM_codigo and NUM_numero = ATL_numero and ATL_categoria like \"F%\" limit 3";
                    rsGeralPrimeiros = stmt.executeQuery(query);
                } else if (cat == 'M') {
                    Statement stmt = conn.createStatement();
                    String query = "select  ATL_numero from numero, tempo, atleta \n"
                            + "where NUM_codigo = TEM_codigo and NUM_numero = ATL_numero and ATL_categoria like \"M%\" limit 3";
                    rsGeralPrimeiros = stmt.executeQuery(query);
                }
                //conn.close();
            } catch (Exception e) {
            }
        } else {
            System.out.println("Problemas!");
        }
    }

}
