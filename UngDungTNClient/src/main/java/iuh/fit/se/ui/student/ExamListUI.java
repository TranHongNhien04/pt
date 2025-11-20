package iuh.fit.se.ui.student;


import entity.Exam;
import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExamListUI extends JFrame {

    private final ClientController controller;
    private final DefaultListModel<Exam> model;
    private final JList<Exam> list;

    public ExamListUI(ClientController controller) {
        this.controller = controller;

        setTitle("Danh sách đề thi");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnStart = new JButton("Bắt đầu thi");
        btnStart.addActionListener(e -> startExam(list.getSelectedValue()));

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);

        loadExams();
    }

    private void loadExams() {
        try {
            model.clear();
            List<Exam> exams = controller.getExamsForStudent();
            if (exams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có đề thi nào.");
            } else {
                for (Exam exam : exams) {
                    model.addElement(exam);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tải được danh sách đề thi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void startExam(Exam exam) {
        if (exam == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đề thi!");
            return;
        }
        dispose();
        new ExamTakingUI(controller, exam).setVisible(true);
    }
}
