package com.company.proyectoSubidaNota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Info extends JFrame {

    protected JPanel panel1;
    private JTextField tfCod;
    private JTextArea taNumEmple;
    private JTextArea taJefes;
    private JTextArea taEmple;
    private JButton btConsulta;

    public Info() {
        setPreferredSize(new Dimension(400,400));
        btConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                info();
            }
        });
    }

    public void info() {
        taEmple.setText("");
        taJefes.setText("");
        taNumEmple.setText("");

        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();


            String sql = "SELECT * FROM offices where officeCode = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);


            //Establezco los parámetros
            statement.setString(1, tfCod.getText());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                String sql1 = "SELECT COUNT(*) FROM employees where officeCode = ? ORDER BY officeCode;";
                PreparedStatement statement1 = connection.prepareStatement(sql1);

                //Establezco los parámetros
                statement1.setString(1, tfCod.getText());
                ResultSet rs1 = statement1.executeQuery();


                if (rs1.next()) {
                    String cant = rs1.getString("COUNT(*)");
                    taNumEmple.append(cant);

                    String sql2 = "SELECT employeeNumber FROM employees WHERE officeCode = ?";
                    PreparedStatement statement2 = connection.prepareStatement(sql2);


                    //Establezco los parámetros
                    statement2.setString(1, tfCod.getText());
                    ResultSet rs2 = statement2.executeQuery();

                    while (rs2.next()) {

                        String emple = rs2.getString("employeeNumber");

                        String sql3 = "SELECT firstName, lastName, reportsTo FROM employees WHERE employeeNumber = ?";
                        PreparedStatement statement3 = connection.prepareStatement(sql3);


                        //Establezco los parámetros
                        statement3.setString(1, emple);
                        ResultSet rs3 = statement3.executeQuery();
                        if (rs3.next()) {
                            String nombre = rs3.getString("firstName");
                            String apellido = rs3.getString("lastName");
                            String jefe = rs3.getString("reportsTo");
                            taEmple.append(nombre + " " + apellido + "\n");

                            String sql4 = "SELECT firstName, lastName FROM employees WHERE employeeNumber = ?";
                            PreparedStatement statement4 = connection.prepareStatement(sql4);


                            //Establezco los parámetros
                            statement4.setString(1, jefe);
                            ResultSet rs4 = statement4.executeQuery();
                            if (rs4.next()) {
                                String nombreJefe = rs4.getString("firstName");
                                String apellidoJefe = rs4.getString("lastName");
                                taJefes.append(nombreJefe + " " + apellidoJefe + "\n");
                            }
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null,"La oficina introducida no existe! Inténtelo de nuevo.");
                }
            } else {
                JOptionPane.showMessageDialog(null,"La oficina introducida no existe! Inténtelo de nuevo.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
