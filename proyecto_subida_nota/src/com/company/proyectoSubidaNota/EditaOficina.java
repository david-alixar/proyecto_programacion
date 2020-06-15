package com.company.proyectoSubidaNota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditaOficina extends JFrame {

    protected JPanel panel1;
    private JTextField tfCod;
    private JTextField tfDir1;
    private JTextField tfEstado;
    private JTextField tfDir2;
    private JTextField tfCiudad;
    private JTextField tfTlf;
    private JTextField tfPais;
    private JTextField tfCp;
    private JTextField tfTerritorio;
    private JButton btEdita;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EditaOficina");
        frame.setContentPane(new EditaOficina().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    public EditaOficina() {

        btEdita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                edita();
            }
        });
    }

    public void edita() {
        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();


            String sql1 = "SELECT * FROM offices WHERE officeCode = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);


            //Establezco los parámetros
            statement1.setString(1, tfCod.getText());
            ResultSet rs1 = statement1.executeQuery();


            if (rs1.next()) {

                String officeCode = tfCod.getText();
                String city = rs1.getString("city");
                String phone = rs1.getString("phone");
                String addressLine1 = rs1.getString("addressLine1");
                String addressLine2 = rs1.getString("addressLine2");
                String state = rs1.getString("state");
                String country = rs1.getString("country");
                String postalCode = rs1.getString("postalCode");
                String territory = rs1.getString("territory");

                if (!"".equalsIgnoreCase(tfCiudad.getText())) {
                    city = tfCiudad.getText();
                }

                if (!"".equalsIgnoreCase(tfTlf.getText())) {
                    phone = tfTlf.getText();
                }

                if (!"".equalsIgnoreCase(tfDir1.getText())) {
                    addressLine1 = tfDir1.getText();
                }

                if (!"".equalsIgnoreCase(tfDir2.getText())) {
                    addressLine2 = tfDir2.getText();
                }

                if (!"".equalsIgnoreCase(tfEstado.getText())) {
                    state = tfEstado.getText();
                }

                if (!"".equalsIgnoreCase(tfPais.getText())) {
                    country = tfPais.getText();
                }

                if (!"".equalsIgnoreCase(tfCp.getText())) {
                    postalCode = tfCp.getText();
                }

                if (!"".equalsIgnoreCase(tfTerritorio.getText())) {
                    territory = tfTerritorio.getText();
                }

                String sql2 = "UPDATE offices SET city = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, state = ?, country = ?, postalCode = ?, territory = ? WHERE officeCode = ?;";
                PreparedStatement statement2 = connection.prepareStatement(sql2);

                //Establezco los parámetros
                statement2.setString(1, city);
                statement2.setString(2, phone);
                statement2.setString(3, addressLine1);
                statement2.setString(4, addressLine2);
                statement2.setString(5, state);
                statement2.setString(6, country);
                statement2.setString(7, postalCode);
                statement2.setString(8, territory);
                statement2.setString(9, officeCode);

                //ResultSet rs2 = statement2.executeQuery();

                if (statement2.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(null,"Se han actualizado los datos de la oficina con código: " +officeCode+".");                }


            }else {
                JOptionPane.showMessageDialog(null,"La oficina introducida no existe! Inténtelo de nuevo.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    }




