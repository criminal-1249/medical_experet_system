package gui_part;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TreatmentMethod extends JPanel {

    private JLabel medLabel;
    private JTextArea adviceArea;

    private static final String url = "jdbc:mysql://localhost:3306/medical_diagnosis_db";
    private static final String user = "root";
    private static final String password = "xxxxxxxx"; // enter ur sql password


    public TreatmentMethod(int diseaseId) {

        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setOpaque(false);

        // 🔹 MEDICATION LABEL
        medLabel = new JLabel("Medications:");
        medLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        medLabel.setForeground(new Color(20, 40, 80));
        medLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // ✅ LEFT

        // 🔹 ADVICE TITLE
        JLabel adviceTitle = new JLabel("Advice:");
        adviceTitle.setForeground(new Color(20, 40, 80));
        adviceTitle.setAlignmentX(Component.LEFT_ALIGNMENT); // ✅ LEFT

        // 🔹 TEXT AREA
        adviceArea = new JTextArea();
        adviceArea.setLineWrap(true);
        adviceArea.setWrapStyleWord(true);
        adviceArea.setEditable(false);
        adviceArea.setOpaque(false);
        adviceArea.setBorder(null);
        adviceArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        adviceArea.setAlignmentX(Component.LEFT_ALIGNMENT); // ✅ LEFT

        // 🔥 IMPORTANT (prevents center stretching)
        adviceArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        box.add(medLabel);
        box.add(Box.createVerticalStrut(10));
        box.add(adviceTitle);
        box.add(Box.createVerticalStrut(5));
        box.add(adviceArea);

        add(box, BorderLayout.NORTH); // ✅ START FROM TOP LEFT

        loadData(diseaseId);
    }

    private void loadData(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement(
                    "SELECT medication, advice FROM treatments WHERE disease_id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                medLabel.setText("Medications: " + rs.getString("medication"));
                adviceArea.setText(rs.getString("advice"));
            } else {
                // 🔥 FIXED LEFT ALIGN EMPTY STATE
                medLabel.setText("Medications: Not Found");
                adviceArea.setText("No advice available.");
            }

        } catch (Exception e) {
            medLabel.setText("Error");
            adviceArea.setText("Failed to load treatment.");
        }
    }
}