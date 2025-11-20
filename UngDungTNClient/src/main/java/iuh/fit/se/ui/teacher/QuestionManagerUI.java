package iuh.fit.se.ui.teacher;

import entity.Question;
import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuestionManagerUI extends JFrame {

    private final ClientController controller;
    private final DefaultListModel<Question> model;
    private final JList<Question> list;

    public QuestionManagerUI(ClientController controller) {
        this.controller = controller;

        setTitle("Quản lý câu hỏi");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnAdd = new JButton("Thêm câu hỏi");
        btnAdd.addActionListener(e -> {
            new QuestionEditorUI(controller, null, this::loadData).setVisible(true);
        });

        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(e -> edit());

        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> delete());
        
        JButton btnRefresh = new JButton("Làm mới");
        btnRefresh.addActionListener(e -> loadData());

        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 10));
        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnDelete);
        panel.add(btnRefresh);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        try {
            model.clear();
            List<Question> questions = controller.getAllQuestions();
            for (Question q : questions) {
                model.addElement(q);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể tải danh sách câu hỏi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void edit() {
        Question q = list.getSelectedValue();
        if (q == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một câu hỏi để sửa.");
            return;
        }
        new QuestionEditorUI(controller, q, this::loadData).setVisible(true);
    }

    private void delete() {
        Question selected = list.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một câu hỏi để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa câu hỏi này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            controller.deleteQuestion(selected.getId());
            JOptionPane.showMessageDialog(this, "Đã xóa thành công!");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa câu hỏi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
