package org.example;

import java.sql.*;
import javax.swing.DefaultListModel;

public class conexion {
    private static final String URL = "jdbc:mysql://ucmz8nvgtzx1jydz:vN8hLlajWSTKvzwxfy66@bxnknwo9rvftkuqw84n2-mysql.services.clever-cloud.com:3306/bxnknwo9rvftkuqw84n2";
    private static final String USER = "ucmz8nvgtzx1jydz";
    private static final String PASSWORD = "vN8hLlajWSTKvzwxfy66";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean verificarCredenciales (String usuario, String contra, String rol) {
        boolean esValido = false;

        String query = "SELECT * FROM usuarios WHERE nombre_usuario  = ? AND password = ? AND rol = ?";

        // Uso de try-with-resources para garantizar el cierre de los recursos
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contra);
            stmt.setString(3, rol);

            ResultSet rs = stmt.executeQuery();

            // Si el resultado tiene una fila, las credenciales son válidas
            if (rs.next()) {
                esValido = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return esValido;
    }


    public static boolean registrarProducto(String nombre, String descripcion, double precio, int stock) {
        String query = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, stock);

            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;  // Devuelve true si la inserción fue exitosa

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
    public static DefaultListModel<String> obtenerProductos() {
        String query = "SELECT nombre FROM productos";  // Seleccionamos solo el nombre del producto

        DefaultListModel<String> model = new DefaultListModel<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String producto = rs.getString("nombre");
                model.addElement(producto);  // Añadimos cada producto al modelo de lista
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;  // Retornamos el modelo con los productos
    }

    public static DefaultListModel<String> obtenerUsuarios() {
        String query = "SELECT nombre_usuario FROM usuarios";  // Seleccionamos solo el nombre de usuario

        DefaultListModel<String> model = new DefaultListModel<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String usuario = rs.getString("nombre_usuario");
                model.addElement(usuario);  // Añadimos cada usuario al modelo de lista
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;  // Retornamos el modelo con los usuarios
    }
    public static boolean registrarUsuario(String nombreUsuario, String password, String rol) {
        String query = "INSERT INTO usuarios (nombre_usuario, password, rol) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, password);
            stmt.setString(3, rol);

            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;  // Devuelve true si la inserción fue exitosa

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }







}

