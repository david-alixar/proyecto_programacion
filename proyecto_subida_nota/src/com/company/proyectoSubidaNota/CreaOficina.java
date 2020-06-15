package com.company.proyectoSubidaNota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreaOficina extends JFrame{
    protected JPanel panel1;
    private JTextField tfCod;
    private JTextField tfCiudad;
    private JTextField tfTlf;
    private JTextField tfDir1;
    private JTextField tfDir2;
    private JTextField tfEstado;
    private JTextField tfPais;
    private JTextField tfCp;
    private JTextField tfTerritorio;
    private JButton btCrear;

    public CreaOficina() {
        btCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                crea();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CreaOficina");
        frame.setContentPane(new CreaOficina().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void crea() {
        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();


            String sql1 = "SELECT * FROM offices WHERE officeCode = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);


            //Establezco los parámetros
            statement1.setString(1, tfCod.getText());
            ResultSet rs1 = statement1.executeQuery();


            if (rs1.next()) {

                JOptionPane.showMessageDialog(null,"La oficina introducida ya existe! Inténtelo de nuevo con otro código de oficina.");

            }else {

                String sql2 = "INSERT INTO offices VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement statement2 = connection.prepareStatement(sql2);

                //Establezco los parámetros
                statement2.setString(1, tfCod.getText());
                statement2.setString(2, tfCiudad.getText());
                statement2.setString(3, tfTlf.getText());
                statement2.setString(4, tfDir1.getText());
                statement2.setString(5, tfDir2.getText());
                statement2.setString(6, tfEstado.getText());
                statement2.setString(7, tfPais.getText());
                statement2.setString(8, tfCp.getText());
                statement2.setString(9, tfTerritorio.getText());


                //ResultSet rs2 = statement2.executeQuery();
                if(statement2.executeUpdate()==1){
                    JOptionPane.showMessageDialog(null,"Se ha añadido correctamenta la oficina con código: " +tfCod.getText()+".");
                }


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
