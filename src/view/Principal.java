/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.NumeroDao;
import db.TempoDao;
import java.awt.Component;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Tempo;

/**
 * Classe principal da aplicação
 *
 * @author Flavio
 */
public class Principal extends javax.swing.JFrame {

    //variáveis do cronometro
    private Timer timer;
    private int segundoAtual = 0;
    private int minutoAtual = 0;
    private int horaAtual = 0;
    private final int velocidade = 1000;
    boolean iniciou = false;
    Cronometro cronometro;

    Tempo tempo;
    int numero = 1;
    int linha = 0;
    int corredor = 0;
    ArrayList<model.Numero> numeroList = new ArrayList<model.Numero>();

    public Principal() throws ClassNotFoundException, SQLException {
        initComponents();
        setLocationRelativeTo(null);
        tempo = new Tempo();
        //cronometro = new Cronometro();

        NumeroDao numreroDao = new NumeroDao();
        numeroList = numreroDao.select();
        lisAtletas.setModel(CarregarNumero());

        tbTempo.setRowHeight(20);
        ((DefaultTableCellRenderer) tbTempo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tbTempo.setDefaultRenderer(Object.class, new CellRenderer());

        capturaTecla();
        
        //Menu suspenso ao clicar com o botÃ£o direito
        //ainda não está pronto
       // tbTempo.addMouseListener(new MenuSuspenso());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem5 = new javax.swing.JMenuItem();
        scrollTabela = new javax.swing.JScrollPane();
        tbTempo = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lisAtletas = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        txtAddNum = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btIniciar = new javax.swing.JToggleButton();
        lbCronometro = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        resultadoGeral = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CHRONOS 0.1");
        setResizable(false);

        tbTempo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTempo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHEGADA", "TEMPO", "NÚMERO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTempo.getTableHeader().setReorderingAllowed(false);
        scrollTabela.setViewportView(tbTempo);
        if (tbTempo.getColumnModel().getColumnCount() > 0) {
            tbTempo.getColumnModel().getColumn(0).setResizable(false);
            tbTempo.getColumnModel().getColumn(0).setPreferredWidth(25);
            tbTempo.getColumnModel().getColumn(1).setResizable(false);
            tbTempo.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lisAtletas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(lisAtletas);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("ATLETAS RESTANTES");

        txtAddNum.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        txtAddNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAddNum.setEnabled(false);
        txtAddNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAddNumKeyTyped(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NÚMERO DO ATLETA");

        btIniciar.setText("INICIAR");
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarActionPerformed(evt);
            }
        });

        lbCronometro.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbCronometro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCronometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddNum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lbCronometro, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addGap(42, 42, 42)
                .addComponent(btIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("Arquivo");

        jMenuItem6.setText("Carregar Tempos");
        jMenuItem6.setEnabled(false);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem4.setText("Carregar Atletas");
        jMenuItem4.setEnabled(false);
        jMenu1.add(jMenuItem4);

        jMenuItem3.setText("Sair");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Resultados");

        resultadoGeral.setText("Resultado Geral");
        resultadoGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadoGeralActionPerformed(evt);
            }
        });
        jMenu2.add(resultadoGeral);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Sobre");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resultadoGeralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoGeralActionPerformed
        new Relatorio().setVisible(true);

    }//GEN-LAST:event_resultadoGeralActionPerformed

    private void btIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarActionPerformed
        // TODO add your handling code here:
        if (!iniciou) {
            iniciarContagem();
            txtAddNum.setEnabled(true);
            txtAddNum.grabFocus();
            btIniciar.setEnabled(false);
            iniciou = true;
        }
    }//GEN-LAST:event_btIniciarActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked

    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        try {
            // TODO add your handling code here:
            carregarTabela();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void txtAddNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddNumKeyTyped
        //Aceita somente números
        String somenteNumeros = "0123456789";
        if (!somenteNumeros.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAddNumKeyTyped

    public DefaultListModel CarregarNumero() throws ClassNotFoundException, SQLException {
        DefaultListModel dlm = new DefaultListModel();
        for (model.Numero n : numeroList) {
            dlm.addElement(n.getNumero());
        }

        return dlm;
    }

    public void capturaTecla() {
        KeyboardFocusManager
                .getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        //Captura a teclar Enter
                        if (e.getID() == e.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_ENTER) {
                            try {
                                if (linha > corredor) {
                                    if (inserirAtleta(txtAddNum.getText())) {
                                        txtAddNum.setText("");
                                    }
                                }

                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        //Captura a tecla M
                        if (e.getID() == e.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_M && iniciou) {
                            tempo.setTempo(getLbCronometro());
                            try {
                                new TempoDao().insere(tempo);
                            } catch (SQLException ex) {
                                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {
                                tempo = new TempoDao().getUltimoTempo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            addLinha();
                            moverScroll();

                            tbTempo.setValueAt(tempo.getCodigo(), linha, 0);
                            tbTempo.setValueAt(tempo.getTempo(), linha++, 1);

                            return true;
                        } else if (e.getID() == e.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_M && !iniciou) {
                            JOptionPane.showMessageDialog(null, "Você precisa iniciar o cronometro antes de marcar o tempo!");
                        }
                        return false;
                    }
                });
    }

    public String getLbCronometro() {
        return lbCronometro.getText();
    }

    /**
     * Move o scroll para o final da tabela a medida que novas linhas são
     * inseridas
     */
    public void moverScroll() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JScrollBar bar = scrollTabela.getVerticalScrollBar();
                bar.setValue(bar.getMaximum());
            }
        });
    }

    /**
     * Adiciona uma nova linha vazia na tabela
     */
    public void addLinha() {
        DefaultTableModel dtm = (DefaultTableModel) tbTempo.getModel();
        String[] linha = {""};
        dtm.addRow(linha);
    }

    public void carregarTabela() throws SQLException {
        //define um novo modelo para a tabela
        tbTempo.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "CHEGADA", "TEMPO", "NÚMERO"
                }
        ));

        ArrayList<Tempo> tempos = new TempoDao().getTempos();
        if (tempos != null) {
            linha = 0;
            for (int i = 0; i < tempos.size(); i++) {
                addLinha();
                tbTempo.setValueAt(tempos.get(i).getCodigo(), linha, 0);
                tbTempo.setValueAt(tempos.get(i).getTempo(), linha++, 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "A tabela de tempos está vazia!");
        }

    }

    public boolean inserirAtleta(String n) throws ClassNotFoundException, SQLException {
        boolean r = false;

        for (int i = 0; i < lisAtletas.getModel().getSize(); i++) {
            if (!n.equals("0")) {
                if (n.equals(lisAtletas.getModel().getElementAt(i))) {
                    NumeroDao numeroDao = new NumeroDao();
                    numeroDao.insereN(n);
                    tbTempo.setValueAt(n, corredor++, 2);
                    numeroList.remove(i);
                    for (int a = 0; a < numeroList.size(); a++) {
                        System.out.println(numeroList.get(a).getNumero());
                    }
                    lisAtletas.setModel(CarregarNumero());
                    return r = true;
                }
            } else {
                tbTempo.setValueAt("0000", corredor++, 2);
                return r = true;
            }
        }

        return false;
    }

    /**
     * Inicia a Contagem do cronometro
     */
    public void iniciarContagem() {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundoAtual++;

                if (segundoAtual == 60) {
                    minutoAtual++;
                    segundoAtual = 0;
                }

                if (minutoAtual == 60) {
                    horaAtual++;
                    minutoAtual = 0;
                }

                String hr = horaAtual <= 9 ? "0" + horaAtual : horaAtual + "";
                String min = minutoAtual <= 9 ? "0" + minutoAtual : minutoAtual + "";
                String seg = segundoAtual <= 9 ? "0" + segundoAtual : segundoAtual + "";

                lbCronometro.setText(hr + ":" + min + ":" + seg);
            }
        };
        timer = new Timer(velocidade, action);
        timer.start();
    }

    /**
     * Classe para personalizar a tabela
     */
    private class CellRenderer extends DefaultTableCellRenderer {

        public CellRenderer() {
            super();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            this.setHorizontalAlignment(CENTER);
            return super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
        }
    }
    
    /**
     * Classe para gerar o menu ao clicar na tabela com o botÃ£o direito
     * Os números das linhas selecionadas são apagadas
     */
    private class MenuSuspenso extends MouseAdapter{
      @Override
      public void mouseClicked(MouseEvent me) {
                //Verificando se o botão direito do mouse foi clicado
                if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem item = new JMenuItem("Apagar Atletas");
                    menu.add(item);
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            int numbers[] = tbTempo.getSelectedRows();                            
                            for (int i = 0; i < numbers.length; i++) {                                                                                                
                                tbTempo.setValueAt("", numbers[i], 2);
                                corredor--;
                            }    
                            try {
                                new NumeroDao().deletaNumAtletas(numbers);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                    });
                    menu.show(tbTempo, me.getX(), me.getY());
                }
            }  
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btIniciar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCronometro;
    private javax.swing.JList lisAtletas;
    private javax.swing.JMenuItem resultadoGeral;
    private javax.swing.JScrollPane scrollTabela;
    private javax.swing.JTable tbTempo;
    private javax.swing.JTextField txtAddNum;
    // End of variables declaration//GEN-END:variables

}
