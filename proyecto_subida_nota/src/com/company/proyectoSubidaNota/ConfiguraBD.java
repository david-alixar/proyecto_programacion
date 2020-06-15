package com.company.proyectoSubidaNota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ConfiguraBD extends JFrame{
    protected JPanel panel1;
    private JTextField tfIp;
    private JTextField tfPuerto;
    private JTextField tfNombre;
    private JTextField tfUsuario;
    private JTextField tfContra;
    private JButton btConfigurar;
    private JButton btCancelar;

    public ConfiguraBD() {
        btConfigurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conecta();
                JOptionPane.showMessageDialog(null,"Conexión efectuada con los datos proporcionados.");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ConfiguraBD");
        frame.setContentPane(new ConfiguraBD().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void conecta() {
        ConexionDB.setIP(tfIp.getText());
        ConexionDB.setPuerto(tfPuerto.getText());
        ConexionDB.setNombreBD(tfNombre.getText());
        ConexionDB.setUsuario(tfUsuario.getText());
        ConexionDB.setPassword(tfContra.getText());
    }
}
