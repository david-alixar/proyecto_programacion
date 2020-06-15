package com.company.proyectoSubidaNota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorraOficina extends JFrame{

    protected JPanel panel1;
    private JTextField tfCod;
    private JButton BORRARButton;
    private JCheckBox cbConfirmo;

    public BorraOficina() {
        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            borra();
            }
        });
    }


    public void borra() {
        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();


            String sql1 = "SELECT * FROM offices WHERE officeCode = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);


            //Establezco los parámetros
            statement1.setString(1, tfCod.getText());
            ResultSet rs1 = statement1.executeQuery();


            if (rs1.next()) {

                if (cbConfirmo.isSelected()) {
                    String sql2 = "DELETE FROM offices WHERE officeCode = ?;";
                    PreparedStatement statement2 = connection.prepareStatement(sql2);

                    //Establezco los parámetros
                    statement2.setString(1, tfCod.getText());


                    //ResultSet rs2 = statement2.executeQuery();
                    if (statement2.executeUpdate() == 1) {
                        JOptionPane.showMessageDialog(null, "Se ha eliminado correctamenta la oficina con código: " + tfCod.getText() + ".");
                    }

                } else {
                    JOptionPane.showMessageDialog(null,"Error! Debe marcar la casilla de confirmación para eliminar la oficina " + tfCod.getText() );
                }

            }else {
                JOptionPane.showMessageDialog(null,"La oficina " + tfCod.getText() + " no existe! Inténtelo de nuevo con otro código de oficina.");


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
