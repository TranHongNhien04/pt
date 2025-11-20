package ui.teacher;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ExamManagerUI extends JFrame {

    private ClientController controller;
    private DefaultListModel<String> model;

    public ExamManagerUI(ClientController controller) {
        this.controller = controller;

        setTitle("Quản lý đề thi");
        setSize(600, 500);
        setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);

        JButton btnAdd = new JButton("Tạo đề thi");
        btnAdd.addActionListener(e -> new ExamEditorUI(controller, null).setVisible(true));

        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null)
                new ExamEditorUI(controller, selected).setVisible(true);
        });

        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected == null) return;
            int id = Integer.parseInt(selected.split(":")[0]);

            controller.send("DELETE_EXAM;" + id);
            loadData();
        });

        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        try {
            controller.send("GET_ALL_EXAMS");
            String response = controller.receive();

            model.clear();
            for (String ex : response.split("\\|")) model.addElement(ex);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tải được danh sách!");
        }
    }

    public ExamManagerUI() throws HeadlessException {
        setTitle("Quản lý đề thi");
        setSize(600, 500);
        setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);

        JButton btnAdd = new JButton("Tạo đề thi");
        btnAdd.addActionListener(e -> new ExamEditorUI(controller, null).setVisible(true));

        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null)
                new ExamEditorUI(controller, selected).setVisible(true);
        });

        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected == null) return;
            int id = Integer.parseInt(selected.split(":")[0]);

            controller.send("DELETE_EXAM;" + id);
            loadData();
        });

        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        loadData();
    }


}

