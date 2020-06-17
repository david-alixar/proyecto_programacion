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

    public ConfiguraBD() {
        btConfigurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conecta();
                JOptionPane.showMessageDialog(null,"Conexión efectuada con los datos proporcionados.");
            }
        });
    }

    public void conecta() {

        String IP = "127.0.0.1";
        String puerto = "3336";
        String nombre = "classicmodels";
        String usuario = "root";
        String contra = "123456";

        if (!"".equalsIgnoreCase(tfIp.getText())) {
            IP = tfIp.getText();
        }

        if (!"".equalsIgnoreCase(tfPuerto.getText())) {
            puerto = tfPuerto.getText();
        }

        if (!"".equalsIgnoreCase(tfNombre.getText())) {
            nombre = tfNombre.getText();
        }

        if (!"".equalsIgnoreCase(tfUsuario.getText())) {
            usuario = tfUsuario.getText();
        }

        if (!"".equalsIgnoreCase(tfContra.getText())) {
            contra = tfContra.getText();
        }

        ConexionDB.setIP(IP);
        ConexionDB.setPuerto(puerto);
        ConexionDB.setNombreBD(nombre);
        ConexionDB.setUsuario(usuario);
        ConexionDB.setPassword(contra);
    }
}
