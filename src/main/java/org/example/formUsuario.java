package org.example;

import javax.swing.*;

public class formUsuario {
    private JTabbedPane tabbedPane1;
    private JList list1;
    private JList list2;
    private JPanel formUsuario;
    private JFrame frame;
    private JFrame loginFrame;

    public formUsuario(JFrame loginFrame){
        this.loginFrame = loginFrame;
    }

    public void ventanaUsuario(){
        frame = new JFrame("Usuario");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(formUsuario);
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                loginFrame.setVisible(true);
            }
        });
        }
}
