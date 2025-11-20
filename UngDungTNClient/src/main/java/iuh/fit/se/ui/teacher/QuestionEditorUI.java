package iuh.fit.se.ui.teacher;


import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class QuestionEditorUI extends JFrame {

    private ClientController controller;
    private JTextArea txtQuestion;
    private JTextField txtA, txtB, txtC, txtD;
    private JComboBox<String> cbCorrect;

    private Integer editingId = null;

    public QuestionEditorUI(ClientController controller, String questionData) {
        this.controller = controller;

        setTitle(questionData == null ? "Thêm câu hỏi" : "Sửa câu hỏi");
        setSize(600, 600);
        setLocationRelativeTo(null);

        txtQuestion = new JTextArea(4, 20);
        txtA = new JTextField();
        txtB = new JTextField();
        txtC = new JTextField();
        txtD = new JTextField();
        cbCorrect = new JComboBox<>(new String[]{"A", "B", "C", "D"});

        if (questionData != null) {
            loadEditingQuestion(questionData);
        }

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
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

    private void loadEditingQuestion(String data) {
        String[] parts = data.split(";");

        editingId = Integer.parseInt(parts[0].split(":")[0]);
        txtQuestion.setText(parts[1]);
        txtA.setText(parts[2]);
        txtB.setText(parts[3]);
        txtC.setText(parts[4]);
        txtD.setText(parts[5]);
        cbCorrect.setSelectedItem(parts[6]);
    }

    private void save() {

        String msg =
                (editingId == null ? "ADD_QUESTION;" : "UPDATE_QUESTION;") +
                        (editingId == null ? "" : editingId + ";") +
                        txtQuestion.getText() + ";" +
                        txtA.getText() + ";" +
                        txtB.getText() + ";" +
                        txtC.getText() + ";" +
                        txtD.getText() + ";" +
                        cbCorrect.getSelectedItem();

        controller.send(msg);

        JOptionPane.showMessageDialog(this, "Đã lưu!");
        dispose();
    }
}
