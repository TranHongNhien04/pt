import ui.LoginUI;

public class Runner {
    public static void main(String[] args) {
        // Thiết lập giao diện đẹp cho Swing (Look and Feel)
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            System.err.println("Cannot set look & feel: " + e.getMessage());
        }

        // Chạy giao diện trên EDT thread (chuẩn của Swing)
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginUI().setVisible(true);
        });
    }
}
