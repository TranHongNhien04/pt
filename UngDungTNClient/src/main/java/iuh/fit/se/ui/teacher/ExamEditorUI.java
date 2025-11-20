package iuh.fit.se.ui.teacher;


import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ExamEditorUI extends JFrame {

    private ClientController controller;

    private JTextField txtName;
    private JTextField txtTime;

    private Integer editingId = null;

    public ExamEditorUI(ClientController controller, String examData) {
        this.controller = controller;

        setTitle(examData == null ? "Tạo đề thi" : "Sửa đề thi");
        setSize(400, 300);
        setLocationRelativeTo(null);

        txtName = new JTextField();
        txtTime = new JTextField();

        if (examData != null) {
            String[] p = examData.split(";");
            editingId = Integer.parseInt(p[0].split(":")[0]);
            txtName.setText(p[1]);
            txtTime.setText(p[2]);
        }

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> save());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Tên đề thi:")); panel.add(txtName);
        panel.add(new JLabel("Thời gian:")); panel.add(txtTime);

        add(panel, BorderLayout.CENTER);
        add(btnSave, BorderLayout.SOUTH);
    }

    private void save() {
        String msg = (editingId == null ? "ADD_EXAM;" : "UPDATE_EXAM;" + editingId + ";")
                + txtName.getText() + ";"
                + txtTime.getText();

        controller.send(msg);

        JOptionPane.showMessageDialog(this, "Đã lưu!");
        dispose();
    }
}
