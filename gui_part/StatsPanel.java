package gui_part;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    public StatsPanel() {

        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setOpaque(false);

        // 🎯 TITLE (Dark Purple instead of Blue)
        JLabel title = new JLabel("Model Information");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22)); // ⬆ bigger
        title.setForeground(new Color(90, 0, 120)); // 🔥 Purple tone
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ⚠️ WARNING (Strong Red + bigger)
        JLabel warning = new JLabel(
                "<html><b>Note:</b> This is only a prediction system.<br>" +
                "Results <b>may be inaccurate</b>.</html>"
        );
        warning.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // ⬆ bigger
        warning.setForeground(new Color(180, 0, 0));
        warning.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 📊 ACCURACY (Green highlight instead of blue)
        JLabel accuracy = new JLabel(
                "<html>Model Accuracy: <b>98%</b></html>"
        );
        accuracy.setFont(new Font("Segoe UI", Font.BOLD, 17)); // ⬆ bigger
        accuracy.setForeground(new Color(250, 50, 50)); // ✅ Green
        accuracy.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 🔐 ADMIN INFO (Darker & slightly bold black)
        JLabel adminInfo = new JLabel(
                "<html><b>Admin Access Required:</b><br>" +
                "Login to view full database.</html>"
        );
        adminInfo.setFont(new Font("Segoe UI", Font.BOLD, 15)); // 🔥 thicker
        adminInfo.setForeground(new Color(20, 20, 20)); // ⚫ deeper black
        adminInfo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ✨ SPACING + ADD
        box.add(title);
        box.add(Box.createVerticalStrut(20));
        box.add(warning);
        box.add(Box.createVerticalStrut(20));
        box.add(accuracy);
        box.add(Box.createVerticalStrut(20));
        box.add(adminInfo);

        add(box, BorderLayout.NORTH);
    }
}