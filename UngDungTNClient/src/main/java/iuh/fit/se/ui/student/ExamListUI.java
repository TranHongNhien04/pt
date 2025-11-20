package iuh.fit.se.ui.student;


import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ExamListUI extends JFrame {

    private ClientController controller;
    private DefaultListModel<String> model;

    public ExamListUI(ClientController controller) {
        this.controller = controller;

        setTitle("Danh sách đề thi");
        setSize(400, 400);
        setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);

        JButton btnStart = new JButton("Bắt đầu thi");
        btnStart.addActionListener(e -> startExam(list.getSelectedValue()));

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);

        loadExams();
    }

    private void loadExams() {
        try {
            controller.send("GET_EXAMS");
            String result = controller.receive();

            for (String exam : result.split("\\|")) {
                model.addElement(exam);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tải được danh sách đề thi");
        }
    }

    private void startExam(String examName) {
        if (examName == null) return;
        new ExamTakingUI(controller, examName).setVisible(true);
    }
}
