package com.company.proyectoSubidaNota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static String IP;
    private static String puerto;
    private static String nombreBD;
    private static String usuario;
    private static String password;
    private static Connection con = null;

    public static Connection getConnection(){
        try{
            if( con == null ){
                String driver="com.mysql.cj.jdbc.Driver";
                Class.forName(driver);
                con = DriverManager.getConnection("jdbc:mysql://"+IP+":"+puerto+"/"+nombreBD+"?user="+usuario+"&password="+password+"");
            }
        }
        catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
        return con;
    }

    public static void close(){
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getIP() {
        return IP;
    }

    public static String getPuerto() {
        return puerto;
    }

    public static String getNombreBD() {
        return nombreBD;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getPassword() {
        return password;
    }

    public static void setIP(String IP) {
        ConexionDB.IP = IP;
    }

    public static void setPuerto(String puerto) {
        ConexionDB.puerto = puerto;
    }

    public static void setNombreBD(String nombreBD) {
        ConexionDB.nombreBD = nombreBD;
    }

    public static void setUsuario(String usuario) {
        ConexionDB.usuario = usuario;
    }

    public static void setPassword(String password) {
        ConexionDB.password = password;
    }
}

