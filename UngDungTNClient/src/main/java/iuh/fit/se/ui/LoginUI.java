package iuh.fit.se.ui;

import iuh.fit.se.controller.ClientController;
import iuh.fit.se.ui.student.StudentDashboardUI;
import iuh.fit.se.ui.teacher.TeacherDashboardUI;
import service.UserService;


import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;

public class LoginUI extends JFrame{

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private ClientController controller;



    public LoginUI() throws Exception {
        setTitle("Đăng nhập hệ thống");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

         UserService userService = (UserService) Naming.lookup("rmi://localhost:1262/userService");

        try {
            controller = new ClientController("localhost", 1262 );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không thể kết nối server!");
        }

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.addActionListener(e -> login());

        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);
    }

    private void login() {
        try {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());

            controller.send("LOGIN;" + user + ";" + pass);

            String response = controller.receive();

            if (response.startsWith("SUCCESS;TEACHER")) {
                dispose();
                new TeacherDashboardUI(controller).setVisible(true);
            } else if (response.startsWith("SUCCESS;STUDENT")) {
                dispose();
                new StudentDashboardUI(controller).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Sai thông tin đăng nhập!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi đăng nhập!");
        }
    }
}
