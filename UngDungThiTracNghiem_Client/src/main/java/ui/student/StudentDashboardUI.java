package ui.student;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class StudentDashboardUI extends JFrame {

    public StudentDashboardUI(ClientController controller) {
        setTitle("Trang học sinh");
        setSize(450, 250);
        setLocationRelativeTo(null);

        JButton btnExamList = new JButton("Danh sách bài thi");
        //btnExamList.addActionListener(e -> new ExamListUI(controller).setVisible(true));

        JButton btnLogout = new JButton("Đăng xuất");
//        btnLogout.addActionListener(e -> {
//            dispose();
//            new client.ui.LoginUI().setVisible(true);
//        });

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnExamList);
        add(btnLogout);
    }

    public StudentDashboardUI() {
        setTitle("Trang học sinh");
        setSize(450, 250);
        setLocationRelativeTo(null);

        JButton btnExamList = new JButton("Danh sách bài thi");
        //btnExamList.addActionListener(e -> new ExamListUI(controller).setVisible(true));

        JButton btnLogout = new JButton("Đăng xuất");
//        btnLogout.addActionListener(e -> {
//            dispose();
//            new client.ui.LoginUI().setVisible(true);
//        });

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnExamList);
        add(btnLogout);
    }
}
