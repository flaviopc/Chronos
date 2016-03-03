/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.RelatorioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Renan
 */
public class Relatorio extends javax.swing.JFrame {

    /**
     * Creates new form Relatorio
     */
    int linha = 0;

    public Relatorio() {
        initComponents();
        RelatorioDao conn = new RelatorioDao();        
        conn.conexao();
        comboCat.removeAllItems();
        comboCat.addItem("GERAL");
        //dados combo box
        try {
            while (conn.rsCat.next()) {
                String cat = conn.rsCat.getString("ATL_categoria");
                comboCat.addItem(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(comboCat.getSelectedItem());

        comboCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                linha = 0;
                DefaultTableModel dtm = (DefaultTableModel) tb_relatorio.getModel();
                dtm.setNumRows(0);
                if (comboCat.getSelectedItem().toString().equals("GERAL")) {
                    conn.conexao2();
                    try {
                        while (conn.rsGeral.next()) {
                            tb_relatorio.getColumnModel().getColumn(3).setHeaderValue("Categoria");
                            addLinha();

                            String codigo = conn.rsGeral.getString("Colocação");
                            String numero = conn.rsGeral.getString("ATL_numero");
                            String nome = conn.rsGeral.getString("ATL_nome");
                            String categoria = conn.rsGeral.getString("ATL_categoria");
                            String tempo = conn.rsGeral.getString("Tempo");

                            String array[] = new String[2];
                            array = tempo.split(":");
                            int velo[] = new int[2];
                            velo[0] = Integer.parseInt(array[0]);
                            velo[1] = Integer.parseInt(array[1]);
                            double veloMin = (velo[0] * 60) + velo[1];

                            tb_relatorio.setValueAt(codigo, linha, 0);
                            tb_relatorio.setValueAt(numero, linha, 1);
                            tb_relatorio.setValueAt(nome, linha, 2);
                            tb_relatorio.setValueAt(categoria, linha, 3);
                            tb_relatorio.setValueAt(tempo, linha, 4);
                           
                            double veloTotal = veloMin / 0.5;
                            tb_relatorio.setValueAt(veloTotal, linha++, 5);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(comboCat.getSelectedItem().toString());
                tb_relatorio.removeAll();
                int cont = 1;
                conn.conexaoCat(comboCat.getSelectedItem().toString());

                try {
                    while (conn.rsCate.next()) {
                        tb_relatorio.getColumnModel().getColumn(3).setHeaderValue("Classificação Geral");
                        if (!conn.rsCate.getString("ColocaçãoGeral").equals("1") && !conn.rsCate.getString("ColocaçãoGeral").equals("2") && !conn.rsCate.getString("ColocaçãoGeral").equals("3")) {
                            addLinha();

                            String codigo = conn.rsCate.getString("ColocaçãoGeral");
                            String numero = conn.rsCate.getString("ATL_numero");
                            String nome = conn.rsCate.getString("ATL_nome");
                            String categoria = conn.rsCate.getString("ATL_categoria");
                            String tempo = conn.rsCate.getString("Tempo");
                            System.out.println(codigo);
                            System.out.println(numero);
                            System.out.println(nome);
                            System.out.println(categoria);
                            System.out.println(tempo);

                            String array[] = new String[2];
                            array = tempo.split(":");
                            int velo[] = new int[2];
                            velo[0] = Integer.parseInt(array[0]);
                            velo[1] = Integer.parseInt(array[1]);
                            double veloMin = (velo[0] * 60) + velo[1];

                            tb_relatorio.setValueAt(cont, linha, 0);
                            tb_relatorio.setValueAt(numero, linha, 1);
                            tb_relatorio.setValueAt(nome, linha, 2);
                            tb_relatorio.setValueAt(codigo, linha, 3);
                            tb_relatorio.setValueAt(tempo, linha, 4);

                            double veloTotal = veloMin / 5;
                            tb_relatorio.setValueAt(veloTotal, linha++, 5);
                            cont++;
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        //dados tabela geral 
        conn.conexao2();
        try {
            while (conn.rsGeral.next()) {

                addLinha();

                String codigo = conn.rsGeral.getString("Colocação");
                String numero = conn.rsGeral.getString("ATL_numero");
                String nome = conn.rsGeral.getString("ATL_nome");
                String categoria = conn.rsGeral.getString("ATL_categoria");
                String tempo = conn.rsGeral.getString("Tempo");

                String array[] = new String[2];
                array = tempo.split(":");
                int velo[] = new int[2];
                velo[0] = Integer.parseInt(array[0]);
                velo[1] = Integer.parseInt(array[1]);
                double veloMin = (velo[0] * 60) + velo[1];

                tb_relatorio.setValueAt(codigo, linha, 0);
                tb_relatorio.setValueAt(numero, linha, 1);
                tb_relatorio.setValueAt(nome, linha, 2);
                tb_relatorio.setValueAt(categoria, linha, 3);
                tb_relatorio.setValueAt(tempo, linha, 4);
                System.out.println(veloMin);
                double veloTotal = veloMin / 5;
                tb_relatorio.setValueAt(veloTotal, linha++, 5);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addLinha() {
        DefaultTableModel dtm = (DefaultTableModel) tb_relatorio.getModel();
        String[] linha = {""};
        dtm.addRow(linha);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_relatorio = new javax.swing.JTable();
        comboCat = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tb_relatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Colocação", "Número", "Nome", "CAT", "Tempo", "M/KM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_relatorio.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_relatorio);
        if (tb_relatorio.getColumnModel().getColumnCount() > 0) {
            tb_relatorio.getColumnModel().getColumn(0).setResizable(false);
            tb_relatorio.getColumnModel().getColumn(0).setPreferredWidth(5);
            tb_relatorio.getColumnModel().getColumn(1).setResizable(false);
            tb_relatorio.getColumnModel().getColumn(2).setResizable(false);
            tb_relatorio.getColumnModel().getColumn(2).setPreferredWidth(199);
            tb_relatorio.getColumnModel().getColumn(3).setResizable(false);
            tb_relatorio.getColumnModel().getColumn(4).setResizable(false);
            tb_relatorio.getColumnModel().getColumn(5).setResizable(false);
        }

        comboCat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(comboCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(comboCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Relatorio().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCat;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_relatorio;
    // End of variables declaration//GEN-END:variables

}
