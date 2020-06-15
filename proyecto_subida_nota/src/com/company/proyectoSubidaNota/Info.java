package com.company.proyectoSubidaNota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Info extends JFrame {

    protected JPanel panel1;
    private JTextField tfCod;
    private JTextArea taNumEmple;
    private JTextArea taJefes;
    private JTextArea taEmple;
    private JButton btConsulta;

    public Info() {
        btConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Info");
        frame.setContentPane(new Info().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
