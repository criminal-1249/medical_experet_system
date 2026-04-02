package gui_part;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;

public class SelectSymptoms extends JPanel {

    private static final String url = "jdbc:mysql://localhost:3306/medical_diagnosis_db";
    private static final String user = "root";
    private static final String password = "xxxxxxxx"; // enter ur sql password


    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<String> orderedSymptoms = new ArrayList<>();

    public SelectSymptoms() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT symptom_name FROM symptoms ORDER BY symptom_name ASC");

            while (rs.next()) {
                String symptom = rs.getString("symptom_name");
                JCheckBox cb = new JCheckBox(symptom);
                checkBoxes.add(cb);
                this.add(cb);
            }

            ResultSet rs2 = st.executeQuery("SELECT symptom_name FROM symptoms ORDER BY symptom_id ASC");

            while (rs2.next()) {
                orderedSymptoms.add(rs2.getString("symptom_name"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getSelectedSymptoms() {

        ArrayList<Integer> input = new ArrayList<>();
        HashSet<String> selected = new HashSet<>();

        for (JCheckBox cb : checkBoxes) {
            if (cb.isSelected()) {
                selected.add(cb.getText());
            }
        }

        for (String symptom : orderedSymptoms) {
            input.add(selected.contains(symptom) ? 1 : 0);
        }

        return input;
    }

   
}