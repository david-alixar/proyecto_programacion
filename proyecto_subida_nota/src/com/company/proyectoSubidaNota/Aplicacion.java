package com.company.proyectoSubidaNota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Aplicacion extends JFrame{
    private JPanel panel1;
    private JButton btConfigura;
    private JButton btAnade;
    private JButton btInformacion;
    private JButton btAsigna;
    private JButton btBorra;
    private JButton btEdita;
    private JButton btSalir;

    public Aplicacion() {
        setTitle("Mi Aplicación");

        btSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Hasta pronto!");
                ConexionDB.close();
                System.exit(0);
            }
        });
        btConfigura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("ConfiguraBD");
                frame.setContentPane(new ConfiguraBD().panel1);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
        btEdita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("EditaOficina");
                frame.setContentPane(new EditaOficina().panel1);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        btAnade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("CreaOficina");
                frame.setContentPane(new CreaOficina().panel1);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        btBorra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("BorraOficina");
                frame.setContentPane(new BorraOficina().panel1);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        btAsigna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("AsignaEmple");
                frame.setContentPane(new AsignaEmple().panel1);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        btInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("Info");
                frame.setContentPane(new Info().panel1);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplicacion");
        frame.setContentPane(new Aplicacion().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
