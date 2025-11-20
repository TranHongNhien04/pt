package iuh.fit.se.ui.teacher;


import entity.Question;
import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;

public class ExamQuestionSelectorUI extends JFrame {

    private final ClientController controller;
    private final int examId;

    private final DefaultListModel<Question> questionModel;
    private final JList<Question> list;

    public ExamQuestionSelectorUI(ClientController controller, int examId) throws RemoteException {
        this.controller = controller;
        this.examId = examId;

        setTitle("Chọn câu hỏi cho đề thi (ID: " + examId + ")");
        setSize(650, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        questionModel = new DefaultListModel<>();
        list = new JList<>(questionModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton btnAdd = new JButton("Thêm vào đề");
        btnAdd.addActionListener(e -> addToExam(list.getSelectedValuesList()));
        
        JButton btnRefresh = new JButton("Làm mới");
        btnRefresh.addActionListener(e -> loadQuestions());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnAdd);
        bottomPanel.add(btnRefresh);


        add(new JScrollPane(list), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadQuestions();
    }

    private void loadQuestions() {
        try {
            questionModel.clear();
            List<Question> questions = controller.getAllQuestions();
            for (Question q : questions) {
                questionModel.addElement(q);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tải được danh sách câu hỏi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void addToExam(List<Question> questions) {
        if (questions == null || questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một câu hỏi để thêm.");
            return;
        }

        try {
            int successCount = 0;
            for(Question q : questions) {
                controller.addQuestionToExam(examId, q.getId());
                successCount++;
            }
            JOptionPane.showMessageDialog(this, "Đã thêm " + successCount + " câu hỏi vào đề thi!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm câu hỏi vào đề thi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
