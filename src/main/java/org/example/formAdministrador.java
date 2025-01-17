package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formAdministrador {
    private JTabbedPane agregarUsuario;
    private JTextField lblNombre;
    private JTextField lblDescripcion;
    private JTextField lblPrecio;
    private JTextField lblStock;
    private JButton registrarProducto;
    private JTextField lblNombreUsuario;
    private JPasswordField lblPassword;
    private JComboBox comboBox1;
    private JButton registrarUsuario_administrador;
    private JList list1;
    private JList list2;
    private  JFrame frame;
    private JPanel formAdminis;
    private JPanel ventAgregarProd;
    private JPanel agregarUsuarios;
    private JPanel ListarProductos;
    private JPanel ListarUsuarios;
    private  JFrame loginFrame;

    public formAdministrador(JFrame loginFrame){
        this.loginFrame = loginFrame;
        registrarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = lblNombre.getText();
                String descripcion = lblDescripcion.getText();
                double precio = Double.parseDouble(lblPrecio.getText());
                int stock = Integer.parseInt(lblStock.getText());

                // Llamamos al método registrarProducto
                boolean registrado = conexion.registrarProducto(nombre, descripcion, precio, stock);

                if (registrado) {
                    JOptionPane.showMessageDialog(frame, "Producto registrado exitosamente");
                    // Llenamos nuevamente la lista de productos
                    list1.setModel(conexion.obtenerProductos());
                } else {
                    JOptionPane.showMessageDialog(frame, "Error al registrar el producto");
                }

            }
        });

        registrarUsuario_administrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = lblNombreUsuario.getText();
                String password = new String(lblPassword.getPassword());
                String rol = (String) comboBox1.getSelectedItem();

                // Llamamos al método registrarUsuario
                boolean registrado = conexion.registrarUsuario(nombreUsuario, password, rol);

                if (registrado) {
                    JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente");
                    // Llenamos nuevamente la lista de usuarios
                    list2.setModel(conexion.obtenerUsuarios());
                } else {
                    JOptionPane.showMessageDialog(frame, "Error al registrar el usuario");
                }

            }
        });
    }

    public void ventanaAdministrador(){
        frame = new JFrame("Administrador");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(formAdminis);
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                loginFrame.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


