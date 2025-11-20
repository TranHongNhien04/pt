package ui.student;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ExamTakingUI extends JFrame {

    private ClientController controller;
    private JTextArea txtQuestion;
    private JRadioButton[] options;
    private ButtonGroup group;

    private int currentIndex = 0;
    private String[] questions;

    public ExamTakingUI(ClientController controller, String examName) {
        this.controller = controller;

        setTitle("Làm bài thi: " + examName);
        setSize(600, 500);
        setLocationRelativeTo(null);

        txtQuestion = new JTextArea();
        txtQuestion.setEditable(false);

        options = new JRadioButton[4];
        group = new ButtonGroup();

        JPanel pnlOptions = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton("Option " + (i + 1));
            group.add(options[i]);
            pnlOptions.add(options[i]);
        }

        JButton btnNext = new JButton("Câu tiếp");
        btnNext.addActionListener(e -> nextQuestion());

        add(new JScrollPane(txtQuestion), BorderLayout.NORTH);
        add(pnlOptions, BorderLayout.CENTER);
        add(btnNext, BorderLayout.SOUTH);

        loadExam(examName);
        showQuestion();
    }

    private void loadExam(String examName) {
        try {
            controller.send("START_EXAM;" + examName);
            String data = controller.receive();
            questions = data.split("#");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tải được bài thi!");
        }
    }

    private void showQuestion() {
        String q = questions[currentIndex];
        String[] parts = q.split(";");

        txtQuestion.setText(parts[0]);
        for (int i = 1; i <= 4; i++) {
            options[i - 1].setText(parts[i]);
        }
    }

    private void nextQuestion() {
        if (currentIndex < questions.length - 1) {
            currentIndex++;
            showQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "Đã hết câu hỏi!");
        }
    }
}
