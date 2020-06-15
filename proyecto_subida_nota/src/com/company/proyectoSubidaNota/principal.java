package com.company.proyectoSubidaNota;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        //
        Scanner teclado = new Scanner(System.in);
        String frase_menu = ("Elija una opción:\n"
                + "\n"
                + "1. Insertar cliente (solicitará los datos del cliente y lo insertará en la base de datos)\n"
                + "\n"
                + "2. Asignar empleado a cliente (solicitará los identificadores de cada uno)\n"
                + "\n"
                + "3. Añadir producto a pedido (solicitará los identificadores de cada uno y la cantidad del producto)..\n"
                + "\n"
                + "4. Mostrar con detalle un pedido. Solicitará el identificador del pedido y mostrará todos los datos del pedido, los productos que tiene (nombre), la cantidad de los mismos, el precio y el total del coste del pedido.\n"
                + "\n"
                + "5. Salir de la aplicación");
        System.out.println("Bienvenido!\n" + frase_menu);
        int menu = teclado.nextInt();
        teclado.nextLine();
        while (menu != 5) {
            switch (menu) {
                case 1:
                    System.out.println("Introduzca en este orden: customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, creditLimit");
                    int customerNumber = teclado.nextInt();
                    teclado.nextLine();
                    String customerName = teclado.nextLine();
                    String contactLastName = teclado.nextLine();
                    String contactFirstName = teclado.nextLine();
                    String phone = teclado.nextLine();
                    String addressLine1 = teclado.nextLine();
                    String addressLine2 = teclado.nextLine();
                    String city = teclado.nextLine();
                    String state = teclado.nextLine();
                    String postalCode = teclado.nextLine();
                    String country = teclado.nextLine();
                    Double creditLimit =teclado.nextDouble();
                    teclado.nextLine();

                    insertarCliente(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, creditLimit);

                    System.out.println(frase_menu);
                    menu = teclado.nextInt();
                    teclado.nextLine();
                    break;
                case 2:
                    System.out.println("Introduzca el identificador de un empleado y el identificador del cliente al que debe ser asignado:");
                    int salesRepEmployeeNumber = teclado.nextInt();
                    teclado.nextLine();
                    int customerNumber2 = teclado.nextInt();
                    teclado.nextLine();

                    asignarEmpleado(customerNumber2 ,salesRepEmployeeNumber);

                    System.out.println(frase_menu);
                    menu = teclado.nextInt();
                    teclado.nextLine();
                    break;
                case 3:
                    System.out.println("Introduzca el identificador de un producto, la cantidad y el identificador del pedido donde debe ser añadido:");
                    String productCode = teclado.nextLine();
                    int quantityOrdered = teclado.nextInt();
                    teclado.nextLine();
                    int orderNumber = teclado.nextInt();
                    teclado.nextLine();

                    anadirProducto(productCode, quantityOrdered, orderNumber);

                    System.out.println(frase_menu);
                    menu = teclado.nextInt();
                    teclado.nextLine();
                    break;
                case 4:
                    System.out.println("Introduzca el identificador de un pedido para conocer los detalles de éste");
                    int orderNumber2 = teclado.nextInt();
                    teclado.nextLine();

                    detallePedido(orderNumber2);

                    System.out.println(frase_menu);
                    menu = teclado.nextInt();
                    teclado.nextLine();
                    break;
                default:
                    System.out.println("Opción no válida! Elija otra");
                    System.out.println(frase_menu);
                    menu = teclado.nextInt();
                    teclado.nextLine();
                    break;
            }
        }
        ConexionDB.close();
        System.out.println("Hasta pronto!");
    }
    public static void insertarCliente(int customerNumber, String customerName, String contactLastName, String contactFirstName, String phone, String addressLine1, String addressLine2, String city, String state, String postalCode, String country, Double creditLimit){

        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();


            //Creo el objeto para ejecutar la sentencias SQL
            String sql = "INSERT INTO customers VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            //Establezco los parámetros
            statement.setInt(1, customerNumber);
            statement.setString(2,customerName);
            statement.setString(3,contactLastName);
            statement.setString(4,contactFirstName);
            statement.setString(5,phone);
            statement.setString(6,addressLine1);
            statement.setString(7,addressLine2);
            statement.setString(8,city);
            statement.setString(9,state);
            statement.setString(10,postalCode);
            statement.setString(11,country);
            statement.setString(12,null);
            statement.setDouble(13,creditLimit);


            //Mostrar la consulta
            System.out.println("LA CONSULTA CONSTRUIDA ES: " + statement.toString());

            //ResultSet rs =
            if(statement.executeUpdate()==1){
                System.out.println("Cliente insertado correctamente");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void asignarEmpleado(int customerNumber, int salesRepEmployeeNumber){

        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();

            //Creo el objeto para ejecutar la sentencias SQL
            String sql = "UPDATE customers SET salesRepEmployeeNumber = ? where customerNumber = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            //Establezco los parámetros
            statement.setInt(1, salesRepEmployeeNumber);
            statement.setInt(2, customerNumber);

            String sql1 = "select employeeNumber from employees where employeeNumber = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);

            //Establezco los parámetros
            statement1.setInt(1, salesRepEmployeeNumber);

            ResultSet rs = statement1.executeQuery();


            String sql2 = "select customerNumber from customers where customerNumber = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);

            //Establezco los parámetros
            statement2.setInt(1, customerNumber);

            ResultSet rs1 = statement2.executeQuery();
            if (rs.next()) {
                if (rs1.next()) {
                    if (statement.executeUpdate() == 1) {
                        System.out.println("LA CONSULTA CONSTRUIDA ES: " + statement.toString());
                        System.out.println("Empleado asignado correctamente");
                    }
                }else {
                    System.out.println("El cliente introducido no existe! Revíselo e inténtelo de nuevo");
                }
            } else {
                System.out.println("El empleado introducido no existe! Revíselo e inténtelo de nuevo");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void anadirProducto(String productCode, int quantityOrdered, int orderNumber){

        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();

            String sql = "SELECT buyPrice FROM products WHERE productCode = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            //Establezco los parámetros
            statement.setString(1, productCode);
            ResultSet rs = statement.executeQuery();

            // Creo una variable que almacenará el precio del producto
            Double precio;

            String sql1 = "SELECT orderLineNumber FROM orderdetails WHERE orderNumber = ? ORDER BY orderLineNumber DESC";
            PreparedStatement statement1 = connection.prepareStatement(sql1);

            // Creo una variable que almacenará el último orderLineNumber
            int ultimoOrderLine;

            //Establezco los parámetros
            statement1.setInt(1, orderNumber);
            ResultSet rs1 = statement1.executeQuery();

            if (rs.next()) {
                //Asigno el precio
                precio = rs.getDouble("buyPrice");
                if (rs1.next()) {
                    //Asigno el último orderLine
                    ultimoOrderLine = rs1.getInt("orderLineNumber");
                    //Ahora lo incremento para tenerlo listo para indicar el que voy a crear
                    ultimoOrderLine ++;

                    String sql2 = "INSERT INTO orderdetails VALUES (?,?,?,?,?)";
                    PreparedStatement statement2 = connection.prepareStatement(sql2);

                    //Establezco los parámetros
                    statement2.setInt(1, orderNumber);
                    statement2.setString(2, productCode);
                    statement2.setInt(3, quantityOrdered);
                    statement2.setDouble(4, precio);
                    statement2.setInt(5, ultimoOrderLine);
                    if (statement2.executeUpdate() == 1) {
                        System.out.println("LA CONSULTA CONSTRUIDA ES: " + statement2.toString());
                        System.out.println("Producto añadido correctamente");
                    }
                }else {
                    System.out.println("El nº de pedido introducido no existe! Revíselo e inténtelo de nuevo");
                }
            } else {
                System.out.println("El producto introducido no existe! Revíselo e inténtelo de nuevo");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void detallePedido(int orderNumber){

        Connection connection = null;


        try {

            connection = ConexionDB.getConnection();


            String sql1 = "SELECT orderNumber FROM orderdetails WHERE orderNumber = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);

            // Creo una variable que almacenará el último orderLineNumber
            int ultimoOrderLine;

            //Establezco los parámetros
            statement1.setInt(1, orderNumber);
            ResultSet rs1 = statement1.executeQuery();

            Double total = 0.0;

            if (rs1.next()) {

                String sql2 = "SELECT o.orderNumber, o.productCode, o.quantityOrdered, o.priceEach, o.orderLineNumber, p.productName FROM orderdetails o INNER JOIN products p ON o.productCode=p.productCode WHERE o.orderNumber = ? ORDER BY orderLineNumber";
                PreparedStatement statement2 = connection.prepareStatement(sql2);

                //Establezco los parámetros
                statement2.setInt(1, orderNumber);

                ResultSet rs2 = statement2.executeQuery();
                while (rs2.next()) {
                    int number = rs2.getInt("orderNumber");
                    String producto = rs2.getString("productCode");
                    int cantidad = rs2.getInt("quantityOrdered");
                    Double precio = rs2.getDouble("priceEach");
                    int orderLineNumber = rs2.getInt("orderLineNumber");
                    String nombreProducto = rs2.getString("productName");
                    Double subtotal = precio * cantidad;

                    System.out.println("-------------------------");
                    System.out.println("Número de pedido: " + number);
                    System.out.println("Código producto: " + producto);
                    System.out.println("Cantidad: " + cantidad);
                    System.out.println("Precio unitario: " + precio);
                    System.out.println("orderLineNumber: " + orderLineNumber);
                    System.out.println("Nombre del producto: " + nombreProducto);
                    System.out.println("Subtotal: " + subtotal);
                    System.out.println("-------------------------\n");
                    total = subtotal + total;
                }
                System.out.println("LA CONSULTA CONSTRUIDA ES: " + statement2.toString());
                System.out.println("Importe total del pedido: " + total);

            }else {
                System.out.println("El nº de pedido introducido no existe! Revíselo e inténtelo de nuevo");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
