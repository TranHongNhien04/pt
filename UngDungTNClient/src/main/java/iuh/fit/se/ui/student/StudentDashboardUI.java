package iuh.fit.se.ui.student;


import iuh.fit.se.controller.ClientController;
import iuh.fit.se.ui.LoginUI;

import javax.swing.*;
import java.awt.*;

public class StudentDashboardUI extends JFrame {

    public StudentDashboardUI(ClientController controller) {
        setTitle("Trang học sinh");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnExamList = new JButton("Danh sách bài thi");
        btnExamList.addActionListener(e -> new ExamListUI(controller).setVisible(true));

        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginUI().setVisible(true);
        });

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(btnExamList);
        panel.add(btnLogout);

        add(panel);
    }
}
