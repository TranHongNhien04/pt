package ui;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

import auth.FakeAuthService;
import ui.student.StudentDashboardUI;
import ui.teacher.TeacherDashboardUI;

public class LoginUI extends JFrame {

//    private JTextField txtUsername;
//    private JPasswordField txtPassword;
//    private ClientController controller;
//
//    public LoginUI() {
//        setTitle("Đăng nhập hệ thống");
//        setSize(400, 250);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        try {
//            controller = new ClientController("localhost", 1999);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Không thể kết nối server!");
//        }
//
//        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
//        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        panel.add(new JLabel("Username:"));
//        txtUsername = new JTextField();
//        panel.add(txtUsername);
//
//        panel.add(new JLabel("Password:"));
//        txtPassword = new JPasswordField();
//        panel.add(txtPassword);
//
//        JButton btnLogin = new JButton("Đăng nhập");
//        btnLogin.addActionListener(e -> login());
//
//        panel.add(new JLabel());
//        panel.add(btnLogin);
//
//        add(panel);
//    }
//
//    private void login() {
//        try {
//            String user = txtUsername.getText();
//            String pass = new String(txtPassword.getPassword());
//
//            controller.send("LOGIN;" + user + ";" + pass);
//
//            String response = controller.receive();
//
//            if (response.startsWith("SUCCESS;TEACHER")) {
//                dispose();
//                new teacher.TeacherDashboardUI(controller).setVisible(true);
//            } else if (response.startsWith("SUCCESS;STUDENT")) {
//                dispose();
//                new student.StudentDashboardUI(controller).setVisible(true);
//            } else {
//                JOptionPane.showMessageDialog(this, "Sai thông tin đăng nhập!");
//            }
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Lỗi đăng nhập!");
//        }
//    }


    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginUI() {
        setTitle("Đăng nhập hệ thống");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        String user = txtUsername.getText().trim();
        String pass = new String(txtPassword.getPassword());

        String response = FakeAuthService.login(user, pass);

        if (response.equals("SUCCESS;TEACHER")) {
            dispose();
            new TeacherDashboardUI().setVisible(true);
        }
        else if (response.equals("SUCCESS;STUDENT")) {
            dispose();
            new StudentDashboardUI().setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Sai username hoặc password!");
        }
    }


}
