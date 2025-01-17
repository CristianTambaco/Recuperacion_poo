package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form1 {
    private JTextField textField1;
    private JButton btnIngresar;
    private JComboBox comboBox1;
    private JPanel formLogin;
    private JPasswordField passwordField1;
    private JFrame frame;


    public form1() {
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtener los datos del formulario
                String usuario = textField1.getText();  // Nombre de usuario
                char[] contra = passwordField1.getPassword();  // Contraseña (char[])
                String rol = comboBox1.getSelectedItem().toString();  // Rol (Usuario o Administrador)

                // Convertir la contraseña a String si es necesario
                String contrasenaString = new String(contra);

                // Verificar las credenciales usando la clase Conexion
                conexion Conexion = new conexion();
                boolean esValido = Conexion.verificarCredenciales(usuario, contrasenaString, rol);

                if (esValido) {
                    // Si las credenciales son válidas, abrir el formulario correspondiente según el rol
                    if (rol.equals("Administrador")) {
                        // Abrir formulario de Administrador
                        JOptionPane.showMessageDialog(frame,"Inicio de sesion exitoso");
                        formAdministrador administrador  = new formAdministrador(frame);
                        administrador.ventanaAdministrador();
                    } else {
                        JOptionPane.showMessageDialog(frame,"Inicio de sesion exitoso");
                        formUsuario usuariox  = new formUsuario(frame);
                        usuariox.ventanaUsuario();
                    }
                } else {
                    // Mostrar mensaje de error si las credenciales son incorrectas
                    JOptionPane.showMessageDialog(frame, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public void ventanaLogin(){
        frame = new JFrame("Login");
        frame.setContentPane(formLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
    }


}
