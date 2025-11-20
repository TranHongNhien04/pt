package iuh.fit.se.ui.teacher;


import entity.Exam;
import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ExamEditorUI extends JFrame {

    private final ClientController controller;
    private final JTextField txtName;
    private final JTextField txtTime;
    private final Runnable onSaveCallback;

    private Exam editingExam = null;

    public ExamEditorUI(ClientController controller, Exam exam, Runnable onSaveCallback) {
        this.controller = controller;
        this.editingExam = exam;
        this.onSaveCallback = onSaveCallback;

        setTitle(exam == null ? "Tạo đề thi" : "Sửa đề thi");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtName = new JTextField();
        txtTime = new JTextField();

        if (editingExam != null) {
            txtName.setText(editingExam.getName());
            txtTime.setText(String.valueOf(editingExam.getTimeLimit()));
        }

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> save());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.add(new JLabel("Tên đề thi:"));
        panel.add(txtName);
        panel.add(new JLabel("Thời gian (phút):"));
        panel.add(txtTime);
        panel.add(new JLabel()); // placeholder
        panel.add(btnSave);

        add(panel, BorderLayout.CENTER);
    }

    private void save() {
        String name = txtName.getText().trim();
        String timeStr = txtTime.getText().trim();

        if (name.isEmpty() || timeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            int time = Integer.parseInt(timeStr);
            if (time <= 0) {
                JOptionPane.showMessageDialog(this, "Thời gian phải là số dương.");
                return;
            }

            Exam examToSave;
            if (editingExam == null) {
                examToSave = new Exam();
            } else {
                examToSave = editingExam;
            }

            examToSave.setName(name);
            examToSave.setTimeLimit(time);

            if (editingExam == null) {
                controller.addExam(examToSave);
                JOptionPane.showMessageDialog(this, "Đã tạo đề thi thành công!");
            } else {
                controller.updateExam(examToSave);
                JOptionPane.showMessageDialog(this, "Đã cập nhật đề thi thành công!");
            }

            if (onSaveCallback != null) {
                onSaveCallback.run();
            }
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Thời gian phải là một con số.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu đề thi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
