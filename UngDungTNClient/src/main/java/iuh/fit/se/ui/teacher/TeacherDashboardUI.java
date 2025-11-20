package iuh.fit.se.ui.teacher;

import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class TeacherDashboardUI extends JFrame {

    public TeacherDashboardUI(ClientController controller) {
        setTitle("Trang giáo viên");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnQuestions = new JButton("Quản lý câu hỏi");
        btnQuestions.addActionListener(e -> new QuestionManagerUI(controller).setVisible(true));

        JButton btnExams = new JButton("Quản lý đề thi");
        btnExams.addActionListener(e -> new ExamManagerUI(controller).setVisible(true));

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(btnQuestions);
        panel.add(btnExams);

        add(panel);
    }
}
