/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.RelatorioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;
import model.Posicao;

/**
 *
 * @author Renan
 */
public class Relatorio extends javax.swing.JFrame {

    /**
     * Creates new form Relatorio
     */
    int linha = 0;
    int KM = 1;   
    

    DecimalFormat formatter = new DecimalFormat("#0.000");


    public Relatorio() {
        initComponents();        
        tb_relatorio.setRowHeight(20);    
        RelatorioDao conn = new RelatorioDao();
        conn.selectTipCat();
        comboCat.removeAllItems();
        comboCat.addItem("GERAL");
        comboCat.addItem("GERAL F");
        comboCat.addItem("GERAL M");
        //dados combo box
        try {
            while (conn.rsTipoCat.next()) {
                String cat = conn.rsTipoCat.getString("ATL_categoria");
                comboCat.addItem(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                linha = 0;
                DefaultTableModel dtm = (DefaultTableModel) tb_relatorio.getModel();
                dtm.setNumRows(0);

                if (comboCat.getSelectedItem().toString().equals("GERAL")) {
                    conn.selectGeral();
                    try {
                        while (conn.rsGeral.next()) {
                            tb_relatorio.getColumnModel().getColumn(3).setHeaderValue("Categoria");
                            addLinha();

                            String codigo = conn.rsGeral.getString("Colocação");
                            String numero = conn.rsGeral.getString("ATL_numero");
                            String nome = conn.rsGeral.getString("ATL_nome");
                            String categoria = conn.rsGeral.getString("ATL_categoria");
                            String tempo = conn.rsGeral.getString("Tempo");

                            String tempoMedio = velo(tempo);

                            tb_relatorio.setValueAt(codigo, linha, 0);
                            tb_relatorio.setValueAt(numero, linha, 1);
                            tb_relatorio.setValueAt(nome, linha, 2);
                            tb_relatorio.setValueAt(categoria, linha, 3);
                            tb_relatorio.setValueAt(tempo, linha, 4);
                            tb_relatorio.setValueAt(tempoMedio, linha++, 5);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (comboCat.getSelectedItem().toString().equals("GERAL F") || comboCat.getSelectedItem().toString().equals("GERAL M")) {
                    tb_relatorio.removeAll();
                    conn.SelectGeral_F_M(comboCat.getSelectedItem().toString());

                    try {
                        while (conn.rsGeralFM.next()) {
                            tb_relatorio.getColumnModel().getColumn(3).setHeaderValue("Categoria");
                            addLinha();

                            String codigo = conn.rsGeralFM.getString("classificação");
                            String numero = conn.rsGeralFM.getString("ATL_numero");
                            String nome = conn.rsGeralFM.getString("ATL_nome");
                            String categoria = conn.rsGeralFM.getString("ATL_categoria");
                            String tempo = conn.rsGeralFM.getString("Tempo");

                            String tempoMedio = velo(tempo);

                            tb_relatorio.setValueAt(codigo, linha, 0);
                            tb_relatorio.setValueAt(numero, linha, 1);
                            tb_relatorio.setValueAt(nome, linha, 2);
                            tb_relatorio.setValueAt(categoria, linha, 3);
                            tb_relatorio.setValueAt(tempo, linha, 4);
                            tb_relatorio.setValueAt(tempoMedio, linha++, 5);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (!(comboCat.getSelectedItem().toString().equals("GERAL") || comboCat.getSelectedItem().toString().equals("GERAL F") || comboCat.getSelectedItem().toString().equals("GERAL M"))) {
                    tb_relatorio.removeAll();
                    tb_relatorio.getColumnModel().getColumn(3).setHeaderValue("Classificação Geral");
                    char cat = comboCat.getSelectedItem().toString().charAt(0);
                    conn.SelectGeralPrimeiros(cat);
                    int[] primeiros = new int[3];
                    int n = 0;

                    //pego os tres primeiros colocados da categoria Masculino ou feminino
                    try {
                        while (conn.rsGeralPrimeiros.next()) {
                            primeiros[n] = Integer.parseInt(conn.rsGeralPrimeiros.getString("ATL_numero"));
                            //System.out.println(primeiros[n]);
                            n++;

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    int cont = 1;
                    conn.SelectPosi(cat);
                    //Arrumar isso depois;
                    Posicao[] posicoes = new Posicao[400];
                    n = 0;
                    try {
                        while (conn.rsPosi.next()) {
                            String clas = conn.rsPosi.getString("classificação");
                            String num = conn.rsPosi.getString("ATL_numero");
                            posicoes[n] = new Posicao(num, clas);
                            n++;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    conn.SelectCat(comboCat.getSelectedItem().toString());
                    try {
                        while (conn.rsCate.next()) {

                            if (!conn.rsCate.getString("ATL_numero").equals(String.valueOf(primeiros[0]))
                                    && !conn.rsCate.getString("ATL_numero").equals(String.valueOf(primeiros[1]))
                                    && !conn.rsCate.getString("ATL_numero").equals(String.valueOf(primeiros[2]))) {

                                addLinha();
                                boolean sair = true;
                                String codigo = null;
                                int ind = 0;
                                while (sair) {
                                    String num = conn.rsCate.getString("ATL_numero");
                                    if (num.equals(posicoes[ind].getNumero())) {
                                        codigo = posicoes[ind].getPosicao();
                                        sair = false;
                                    }
                                    ind++;
                                }

                                String numero = conn.rsCate.getString("ATL_numero");
                                String nome = conn.rsCate.getString("ATL_nome");
                                String colocacaoGeral = conn.rsCate.getString("ColocacaoGeral");
                                String tempo = conn.rsCate.getString("Tempo");

                               String tempoMedio = velo(tempo);

                                tb_relatorio.setValueAt(cont, linha, 0);
                                tb_relatorio.setValueAt(numero, linha, 1);
                                tb_relatorio.setValueAt(nome, linha, 2);
                                tb_relatorio.setValueAt(colocacaoGeral, linha, 3);
                                tb_relatorio.setValueAt(tempo, linha, 4);
                                tb_relatorio.setValueAt(tempoMedio, linha++, 5);
                                cont++;
                            }

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        );

        //dados tabela geral 
        conn.selectGeral();

        try {
            while (conn.rsGeral.next()) {

                addLinha();

                String codigo = conn.rsGeral.getString("Colocação");
                String numero = conn.rsGeral.getString("ATL_numero");
                String nome = conn.rsGeral.getString("ATL_nome");
                String categoria = conn.rsGeral.getString("ATL_categoria");
                String tempo = conn.rsGeral.getString("Tempo");

                String tempoMedio = velo(tempo);

                tb_relatorio.setValueAt(codigo, linha, 0);
                tb_relatorio.setValueAt(numero, linha, 1);
                tb_relatorio.setValueAt(nome, linha, 2);
                tb_relatorio.setValueAt(categoria, linha, 3);
                tb_relatorio.setValueAt(tempo, linha, 4);
                tb_relatorio.setValueAt(tempoMedio, linha++, 5);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //calcula o tempo medio gasto do atleta
    public String velo(String temp) {
        System.out.println(temp);
        DecimalFormat formatter2 = new DecimalFormat("#00");
        String array[] = new String[3];
        char array2[] = new char[4];

        array = temp.split(":");
        int velo[] = new int[3];
        velo[0] = Integer.parseInt(array[0]);
        velo[1] = Integer.parseInt(array[1]);
        velo[2] = Integer.parseInt(array[2]);
        double veloMin = (velo[0] * 60) + velo[1];
        double veloSec = (veloMin * 60) + velo[2];
        double veloTotal = veloSec / KM;
        veloTotal = veloTotal / 60;

        String dec = String.valueOf(veloTotal);

        if (dec.charAt(2) == '.') {
            array2[0] = dec.charAt(0);
            array2[1] = dec.charAt(1);
            array2[2] = dec.charAt(3);
            array2[3] = '0';
            if (dec.length() >= 5) {
                array2[3] = dec.charAt(4);
            }
            dec = String.valueOf(array2[2]) + array2[3];

            veloTotal = Double.parseDouble(dec);
            veloTotal = (veloTotal * 60) / 100;
            dec = formatter2.format(veloTotal);
            System.out.println(array2[1]);
            String minu = String.valueOf(array2[0]) + array2[1];
            dec = (minu + ":" + dec);
            return dec;
        }

        array2[0] = dec.charAt(0);
        array2[1] = dec.charAt(2);
        array2[2] = '0';
        if (dec.length() > 3) {
            array2[2] = dec.charAt(3);
        }
        dec = String.valueOf(array2[1]) + array2[2];

        veloTotal = Double.parseDouble(dec);
        veloTotal = (veloTotal * 60) / 100;
        dec = formatter2.format(veloTotal);
        dec = (array2[0] + ":" + dec);
        return dec;
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
        jLabel1 = new javax.swing.JLabel();
        btImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório da Corrida");
        setResizable(false);

        tb_relatorio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tb_relatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Colocação", "Número", "Nome", "Categoria", "Tempo", "Ritmo (M/KM)"
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Selecione a Categoria");

        btImprimir.setText("Imprimir");
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(comboCat, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btImprimir))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(comboCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btImprimir))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();        
        job.setPrintable(tb_relatorio.getPrintable(PrintMode.FIT_WIDTH, null, null));               
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btImprimir;
    private javax.swing.JComboBox<String> comboCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_relatorio;
    // End of variables declaration//GEN-END:variables

}
