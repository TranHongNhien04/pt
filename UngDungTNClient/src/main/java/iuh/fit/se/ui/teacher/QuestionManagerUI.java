package iuh.fit.se.ui.teacher;

import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class QuestionManagerUI extends JFrame {

    private iuh.fit.se.controller.ClientController controller;
    private DefaultListModel<String> model;
    private JList<String> list;

    public QuestionManagerUI(ClientController controller) {
        this.controller = controller;

        setTitle("Quản lý câu hỏi");
        setSize(600, 500);
        setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        list = new JList<>(model);

        JButton btnAdd = new JButton("Thêm câu hỏi");
        btnAdd.addActionListener(e -> new QuestionEditorUI(controller, null).setVisible(true));

        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(e -> edit());

        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> delete());

        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnDelete);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        try {
            controller.send("GET_ALL_QUESTIONS");
            String response = controller.receive();

            model.clear();

            for (String q : response.split("\\|")) {
                model.addElement(q);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể tải danh sách câu hỏi");
        }
    }

    private void edit() {
        String q = list.getSelectedValue();
        if (q == null) return;

        new QuestionEditorUI(controller, q).setVisible(true);
    }

    private void delete() {
        String selected = list.getSelectedValue();
        if (selected == null) return;

        int id = Integer.parseInt(selected.split(":")[0]);

        controller.send("DELETE_QUESTION;" + id);
        JOptionPane.showMessageDialog(this, "Đã xóa");

        loadData();
    }

    public QuestionManagerUI() throws HeadlessException {
        setTitle("Quản lý câu hỏi");
        setSize(600, 500);
        setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        list = new JList<>(model);

        JButton btnAdd = new JButton("Thêm câu hỏi");
        btnAdd.addActionListener(e -> new QuestionEditorUI(controller, null).setVisible(true));

        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(e -> edit());

        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> delete());

        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnDelete);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        loadData();
    }




}
