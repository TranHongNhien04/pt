package iuh.fit.se.ui.teacher;


import entity.Exam;
import iuh.fit.se.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;

public class ExamManagerUI extends JFrame {

    private final ClientController controller;
    private final DefaultListModel<Exam> model;
    private final JList<Exam> list;

    public ExamManagerUI(ClientController controller) {
        this.controller = controller;

        setTitle("Quản lý đề thi");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnAdd = new JButton("Tạo đề thi");
        btnAdd.addActionListener(e -> new ExamEditorUI(controller, null, this::loadData).setVisible(true));

        JButton btnEdit = new JButton("Sửa đề thi");
        btnEdit.addActionListener(e -> {
            Exam selected = list.getSelectedValue();
            if (selected != null)
                new ExamEditorUI(controller, selected, this::loadData).setVisible(true);
            else
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một đề thi để sửa.");
        });

        JButton btnDelete = new JButton("Xóa đề thi");
        btnDelete.addActionListener(e -> deleteExam());

        JButton btnManageQuestions = new JButton("Quản lý câu hỏi");
        btnManageQuestions.addActionListener(e -> {
            Exam selected = list.getSelectedValue();
            if (selected != null) {
                try {
                    new ExamQuestionSelectorUI(controller, selected.getId()).setVisible(true);
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi mở màn hình quản lý câu hỏi: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một đề thi.");
            }
        });

        JButton btnRefresh = new JButton("Làm mới");
        btnRefresh.addActionListener(e -> loadData());

        JPanel btnPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.add(btnManageQuestions);
        btnPanel.add(btnRefresh);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        try {
            model.clear();
            List<Exam> exams = controller.getAllExams();
            for (Exam ex : exams) model.addElement(ex);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tải được danh sách đề thi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteExam() {
        Exam selected = list.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đề thi để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa đề thi này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            controller.deleteExam(selected.getId());
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa đề thi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

