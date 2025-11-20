package iuh.fit.se.ui.student;

import entity.Exam;
import entity.Question;
import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class ExamTakingUI extends JFrame {

    private final ClientController controller;
    private final JTextArea txtQuestion;
    private final JRadioButton[] options;
    private final ButtonGroup group;

    private int currentIndex = 0;
    private List<Question> questions = Collections.emptyList();
    // We can add logic to store answers here
    // private Map<Integer, String> studentAnswers = new HashMap<>();

    public ExamTakingUI(ClientController controller, Exam exam) {
        this.controller = controller;

        setTitle("Làm bài thi: " + exam.getTitle());
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtQuestion = new JTextArea(5, 20);
        txtQuestion.setEditable(false);
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);


        options = new JRadioButton[4];
        group = new ButtonGroup();

        JPanel pnlOptions = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            pnlOptions.add(options[i]);
        }

        JButton btnNext = new JButton("Câu tiếp");
        btnNext.addActionListener(e -> nextQuestion());

        // TODO: Add submit button and timer
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnNext);

        add(new JScrollPane(txtQuestion), BorderLayout.NORTH);
        add(pnlOptions, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadExam(exam.getTitle());
    }

    private void loadExam(String examName) {
        try {
            Exam fullExam = controller.startExam(examName);
            if (fullExam != null && fullExam.getQuestions() != null && !fullExam.getQuestions().isEmpty()) {
                this.questions = fullExam.getQuestions();
                showQuestion();
            } else {
                JOptionPane.showMessageDialog(this, "Không thể tải câu hỏi cho đề thi này hoặc đề thi không có câu hỏi.");
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tải được bài thi: " + e.getMessage());
            e.printStackTrace();
            dispose();
        }
    }

    private void showQuestion() {
        if (currentIndex < questions.size()) {
            Question q = questions.get(currentIndex);
            txtQuestion.setText("Câu " + (currentIndex + 1) + ": " + q.getContent());
            options[0].setText(q.getOptionA());
            options[1].setText(q.getOptionB());
            options[2].setText(q.getOptionC());
            options[3].setText(q.getOptionD());
            group.clearSelection();
        }
    }

    private void nextQuestion() {
        // TODO: save the selected answer for the current question
        if (currentIndex < questions.length - 1) {
            currentIndex++;
            showQuestion();
        } else {
            // TODO: Submit the exam
            JOptionPane.showMessageDialog(this, "Đã hết câu hỏi! Nộp bài.");
            dispose();
            // new ExamResultUI(controller, studentAnswers).setVisible(true);
        }
    }
}
