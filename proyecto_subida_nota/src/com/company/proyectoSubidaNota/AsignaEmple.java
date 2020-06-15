package com.company.proyectoSubidaNota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AsignaEmple extends JFrame{

    protected JPanel panel1;
    private JTextField tfEmple;
    private JTextField tfCod;
    private JButton btAsigna;

    public AsignaEmple() {
        btAsigna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            asigna();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AsignaEmple");
        frame.setContentPane(new AsignaEmple().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void asigna() {
        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();


            String sql = "SELECT * FROM offices WHERE officeCode = ?";
            PreparedStatement statement = connection.prepareStatement(sql);


            //Establezco los parámetros
            statement.setString(1, tfCod.getText());
            ResultSet rs = statement.executeQuery();


            if (rs.next()) {

                String sql1 = "SELECT * FROM employees WHERE employeeNumber = ?";
                PreparedStatement statement1 = connection.prepareStatement(sql1);


                //Establezco los parámetros
                statement1.setString(1, tfEmple.getText());
                ResultSet rs1 = statement1.executeQuery();

                if (rs1.next()) {

                    String sql2 = "UPDATE employees SET officeCode = ? WHERE employeeNumber = ?";
                    PreparedStatement statement2 = connection.prepareStatement(sql2);


                    //Establezco los parámetros
                    statement2.setString(1, tfCod.getText());
                    statement2.setString(2, tfEmple.getText());
                    if (statement2.executeUpdate() == 1) {
                        JOptionPane.showMessageDialog(null, "Se ha añadido el empleado " + tfEmple.getText() + " correctamente a la oficina con código: " + tfCod.getText() + ".");
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null,"El empleado introducido no existe! Inténtelo de nuevo.");
                }

            }else {
                JOptionPane.showMessageDialog(null,"La oficina introducida no existe! Inténtelo de nuevo.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
