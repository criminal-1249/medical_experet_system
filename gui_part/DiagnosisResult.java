package gui_part;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DiagnosisResult extends JPanel {

    private JLabel diseaseLabel;
    private JTextArea descriptionArea;

    private static final String url = "jdbc:mysql://localhost:3306/medical_diagnosis_db";
    private static final String user = "root";
    private static final String password = "xxxxxxxx"; // enter ur sql password


    public DiagnosisResult(String disease) {

        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setOpaque(false);

        // 🔹 DISEASE LABEL
        diseaseLabel = new JLabel("Disease: " + disease);
        diseaseLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        diseaseLabel.setForeground(new Color(20, 40, 80));
        diseaseLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // ✅ LEFT ALIGN

        // 🔹 DESCRIPTION TITLE
        JLabel descTitle = new JLabel("Description:");
        descTitle.setForeground(new Color(20, 40, 80));
        descTitle.setAlignmentX(Component.LEFT_ALIGNMENT); // ✅ LEFT ALIGN

        // 🔹 TEXT AREA
        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setBorder(null);
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT); // ✅ LEFT ALIGN

        // 🔥 VERY IMPORTANT
        descriptionArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        box.add(diseaseLabel);
        box.add(Box.createVerticalStrut(10));
        box.add(descTitle);
        box.add(Box.createVerticalStrut(5));
        box.add(descriptionArea);

        add(box, BorderLayout.NORTH); // ✅ START FROM TOP LEFT

        loadData(disease);
    }

    private void loadData(String disease) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement(
                    "SELECT description FROM diseases WHERE disease_name=?");

            ps.setString(1, disease);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                descriptionArea.setText(rs.getString(1));
            } else {
                // 🔥 FIXED MESSAGE ALIGNMENT
                diseaseLabel.setText("Disease: Not Found");
                descriptionArea.setText("No description available.");
            }

        } catch (Exception e) {
            descriptionArea.setText("Error loading data.");
        }
    }
}