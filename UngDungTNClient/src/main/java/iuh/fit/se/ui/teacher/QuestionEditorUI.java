package iuh.fit.se.ui.teacher;


import entity.Question;
import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class QuestionEditorUI extends JFrame {

    private final ClientController controller;
    private final JTextArea txtQuestion;
    private final JTextField txtA, txtB, txtC, txtD;
    private final JComboBox<String> cbCorrect;
    private final Runnable onSaveCallback;

    private Question editingQuestion = null;

    public QuestionEditorUI(ClientController controller, Question question, Runnable onSaveCallback) {
        this.controller = controller;
        this.editingQuestion = question;
        this.onSaveCallback = onSaveCallback;

        setTitle(question == null ? "Thêm câu hỏi" : "Sửa câu hỏi");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtQuestion = new JTextArea(4, 20);
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);
        txtA = new JTextField();
        txtB = new JTextField();
        txtC = new JTextField();
        txtD = new JTextField();
        cbCorrect = new JComboBox<>(new String[]{"A", "B", "C", "D"});

        if (editingQuestion != null) {
            loadEditingQuestion();
        }

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nội dung:"));
        panel.add(new JScrollPane(txtQuestion));

        panel.add(new JLabel("Đáp án A:")); panel.add(txtA);
        panel.add(new JLabel("Đáp án B:")); panel.add(txtB);
        panel.add(new JLabel("Đáp án C:")); panel.add(txtC);
        panel.add(new JLabel("Đáp án D:")); panel.add(txtD);
        panel.add(new JLabel("Đáp án đúng:")); panel.add(cbCorrect);

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> save());

        add(panel, BorderLayout.CENTER);
        add(btnSave, BorderLayout.SOUTH);
    }

    private void loadEditingQuestion() {
        txtQuestion.setText(editingQuestion.getContent());
        txtA.setText(editingQuestion.getOptionA());
        txtB.setText(editingQuestion.getOptionB());
        txtC.setText(editingQuestion.getOptionC());
        txtD.setText(editingQuestion.getOptionD());
        cbCorrect.setSelectedItem(editingQuestion.getCorrectAnswer());
    }

    private void save() {
        String content = txtQuestion.getText().trim();
        String optA = txtA.getText().trim();
        String optB = txtB.getText().trim();
        String optC = txtC.getText().trim();
        String optD = txtD.getText().trim();

        if (content.isEmpty() || optA.isEmpty() || optB.isEmpty() || optC.isEmpty() || optD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            Question questionToSave;
            if (editingQuestion == null) {
                // Creating a new question
                questionToSave = new Question();
            } else {
                // Updating an existing question
                questionToSave = editingQuestion;
            }

            questionToSave.setContent(content);
            questionToSave.setOptionA(optA);
            questionToSave.setOptionB(optB);
            questionToSave.setOptionC(optC);
            questionToSave.setOptionD(optD);
            questionToSave.setCorrectAnswer((String) cbCorrect.getSelectedItem());

            if (editingQuestion == null) {
                controller.addQuestion(questionToSave);
                JOptionPane.showMessageDialog(this, "Đã thêm câu hỏi thành công!");
            } else {
                controller.updateQuestion(questionToSave);
                JOptionPane.showMessageDialog(this, "Đã cập nhật câu hỏi thành công!");
            }
            
            // Trigger refresh in QuestionManagerUI
            if(onSaveCallback != null) {
                onSaveCallback.run();
            }

            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu câu hỏi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
