package iuh.fit.se.ui.teacher;

import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class TeacherDashboardUI extends JFrame {

    public TeacherDashboardUI(ClientController controller) {
        setTitle("Trang giáo viên");
        setSize(450, 250);
        setLocationRelativeTo(null);

        JButton btnQuestions = new JButton("Quản lý câu hỏi");
        btnQuestions.addActionListener(e -> new QuestionManagerUI(controller).setVisible(true));

        JButton btnExams = new JButton("Quản lý đề thi");
        btnExams.addActionListener(e -> new ExamManagerUI(controller).setVisible(true));

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnQuestions);
        add(btnExams);
    }

    public TeacherDashboardUI() throws HeadlessException {
        setTitle("Trang giáo viên");
        setSize(450, 250);
        setLocationRelativeTo(null);

        JButton btnQuestions = new JButton("Quản lý câu hỏi");
        btnQuestions.addActionListener(e -> new QuestionManagerUI().setVisible(true));

        JButton btnExams = new JButton("Quản lý đề thi");
        btnExams.addActionListener(e -> new ExamManagerUI().setVisible(true));

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnQuestions);
        add(btnExams);
    }


}
