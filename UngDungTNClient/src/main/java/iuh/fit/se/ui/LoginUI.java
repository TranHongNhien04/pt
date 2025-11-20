package iuh.fit.se.ui;

import entity.User;
import iuh.fit.se.controller.ClientController;
import iuh.fit.se.ui.student.StudentDashboardUI;
import iuh.fit.se.ui.teacher.TeacherDashboardUI;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private ClientController controller;

    public LoginUI() {
        setTitle("Đăng nhập hệ thống");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            // It's better to have a central place for config like host and port
            controller = new ClientController("localhost", 1262);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không thể kết nối server: " + ex.getMessage());
            // Exit if no connection can be established
            System.exit(1);
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

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            User loggedInUser = controller.login(user, pass);

            if (loggedInUser != null) {
                if ("TEACHER".equalsIgnoreCase(loggedInUser.getRole())) {
                    dispose();
                    new TeacherDashboardUI(controller).setVisible(true);
                } else if ("STUDENT".equalsIgnoreCase(loggedInUser.getRole())) {
                    dispose();
                    new StudentDashboardUI(controller).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Vai trò người dùng không hợp lệ!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sai thông tin đăng nhập!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi đăng nhập: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
