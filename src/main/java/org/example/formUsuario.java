package org.example;

import javax.swing.*;
import java.sql.*;

public class formUsuario {
    private JTabbedPane tabbedPane1;
    private JList<String> list1; // Lista de todos los productos
    private JList<String> list2; // Lista de productos con stock < 20
    private JPanel formUsuario;
    private JFrame frame;
    private JFrame loginFrame;

    public formUsuario(JFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    // Método para cargar productos en las listas
    private void cargarProductos() {
        DefaultListModel<String> productosTodos = new DefaultListModel<>();
        DefaultListModel<String> productosPocoStock = new DefaultListModel<>();

        String query = "SELECT * FROM productos";

        try (Connection conn = conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Obtener los datos del producto
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");

                String producto = "ID: " + id + " - " + nombre + " - Precio: $" + precio + " - Stock: " + stock;

                productosTodos.addElement(producto);

                if (stock < 20) {
                    productosPocoStock.addElement(producto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        list1.setModel(productosTodos);
        list2.setModel(productosPocoStock);
    }

    public void ventanaUsuario() {
        frame = new JFrame("Usuario");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(formUsuario);
        frame.setSize(800, 600);

        cargarProductos();

        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                loginFrame.setVisible(true);
            }
        });
    }
}

