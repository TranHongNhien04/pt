package iuh.fit.se.ui.teacher;


import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ExamQuestionSelectorUI extends JFrame {

    private ClientController controller;
    private int examId;

    private DefaultListModel<String> questionModel;

    public ExamQuestionSelectorUI(ClientController controller, int examId) {
        this.controller = controller;
        this.examId = examId;

        setTitle("Chọn câu hỏi cho đề thi");
        setSize(650, 500);
        setLocationRelativeTo(null);

        questionModel = new DefaultListModel<>();
        JList<String> list = new JList<>(questionModel);

        JButton btnAdd = new JButton("Thêm vào đề");
        btnAdd.addActionListener(e -> addToExam(list.getSelectedValue()));

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnAdd, BorderLayout.SOUTH);

        loadQuestions();
    }

    private void loadQuestions() {
        controller.send("GET_ALL_QUESTIONS");
        try {
            String resp = controller.receive();
            questionModel.clear();
            for (String q : resp.split("\\|")) questionModel.addElement(q);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tải được câu hỏi");
        }
    }

    private void addToExam(String q) {
        if (q == null) return;

        int qid = Integer.parseInt(q.split(":")[0]);

        controller.send("ADD_QUESTION_TO_EXAM;" + examId + ";" + qid);

        JOptionPane.showMessageDialog(this, "Đã thêm vào đề!");
    }
}
