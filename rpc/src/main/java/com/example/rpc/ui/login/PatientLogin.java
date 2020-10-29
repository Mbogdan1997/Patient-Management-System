package com.example.rpc.ui.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

@Component
public class PatientLogin {

    private JFrame frame;
    private JTextField username;
    private JPasswordField password;
    private JButton login;





    /**
     * Launch the application.
     */
    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */

    /**
     * Create the application.
     */
    @Autowired
    public PatientLogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 354);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        username = new JTextField();
        username.setBounds(27, 64, 215, 37);
        frame.getContentPane().add(username);
        username.setColumns(10);

        JLabel lblNewLabel = new JLabel("Login:");
        lblNewLabel.setBounds(27, 14, 87, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("UserName:");
        lblNewLabel_1.setBounds(27, 39, 87, 14);
        frame.getContentPane().add(lblNewLabel_1);

        password = new JPasswordField();
        password.setBounds(27, 154, 215, 35);
        frame.getContentPane().add(password);

        JLabel lblNewLabel_2 = new JLabel("Password:");
        lblNewLabel_2.setBounds(27, 129, 87, 14);
        frame.getContentPane().add(lblNewLabel_2);

        login = new JButton("Login");
        login.setBounds(27, 213, 215, 37);
        frame.getContentPane().add(login);

    }
    
    public void setVisible(boolean visible) {
    	frame.setVisible(visible);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public void addActionListenerOnLogin(ActionListener loginButtonController) {
        this.login.addActionListener(loginButtonController);
    }
}

