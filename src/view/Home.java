package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.Conexao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Home extends JFrame {

    private JPanel contentPane;
    public boolean btMudar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Home() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 486);
        setResizable(false);        
        setTitle("CHRONOS 1.0");
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        JButton btnIniciar;

        btnIniciar = new JButton("Preparar");
        btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnIniciar.setBounds(254, 400, 175, 36);

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Principal novo;
                try {
                    setVisible(false);

                    novo = new Principal();
                    novo.setVisible(true);

                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        contentPane.setLayout(null);
        contentPane.add(btnIniciar);

        JButton btnLimpaNumeros = new JButton("Limpa numeros ");
        btnLimpaNumeros.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnLimpaNumeros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = new Conexao().getConnection();
                    String sql = "truncate numero";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        if (!ps.execute()) {
                            JOptionPane.showMessageDialog(null, "Tabela de numeros limpa");
                        }

                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        btnLimpaNumeros.setBounds(249, 179, 175, 36);
        contentPane.add(btnLimpaNumeros);

        JButton btnLimparTempo = new JButton("Limpar tempo [ S\u00F3 pra teste]");
        btnLimparTempo.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimparTempo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection conn = new Conexao().getConnection();                   
                    String sql = "truncate tempo";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        if (!ps.execute()) {
                            JOptionPane.showMessageDialog(null, "Tabelas de tempos limpa");
                        }

                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnLimparTempo.setBounds(23, 179, 216, 36);
        contentPane.add(btnLimparTempo);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 255));
        panel.setBounds(10, 11, 414, 157);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblDicasDeComo = new JLabel("Dicas de como usar o Programa");
        lblDicasDeComo.setBounds(10, 11, 283, 25);
        panel.add(lblDicasDeComo);
        lblDicasDeComo.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblUseATecla = new JLabel("- Use a tecla M na janela da lista de chegada  para  marca o tempo de ");
        lblUseATecla.setHorizontalAlignment(SwingConstants.LEFT);
        lblUseATecla.setVerticalAlignment(SwingConstants.TOP);
        lblUseATecla.setBounds(10, 39, 394, 15);
        panel.add(lblUseATecla);
        lblUseATecla.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUseATecla.setBackground(Color.GRAY);

        JLabel lblChegadaDoCorredor = new JLabel("chegada do corredor.");
        lblChegadaDoCorredor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblChegadaDoCorredor.setBounds(20, 54, 159, 14);
        panel.add(lblChegadaDoCorredor);

        JLabel lbluseONmero = new JLabel("- Use o n\u00FAmero 0 como coringa quando marca um tempo indesejado.");
        lbluseONmero.setVerticalAlignment(SwingConstants.TOP);
        lbluseONmero.setHorizontalAlignment(SwingConstants.LEFT);
        lbluseONmero.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lbluseONmero.setBackground(Color.GRAY);
        lbluseONmero.setBounds(10, 75, 394, 15);
        panel.add(lbluseONmero);
    }

    public boolean isBtMudar() {
        return btMudar;
    }

    public void setBtMudar(boolean btMudar) {
        this.btMudar = btMudar;
    }

}
