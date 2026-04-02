package admin_gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class admin_panel extends JFrame {

    static final String URL = "jdbc:mysql://localhost:3306/medical_diagnosis_db";
    static final String USER = "root";
    static final String PASS = "xxxxxxx"; // update ur my sql password 

    CardLayout cl;
    JPanel mainPanel;

    public admin_panel() {
        setTitle("Admin Panel");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Sidebar
        JPanel side = new JPanel(new GridLayout(5, 1, 5, 5));
        side.setBackground(new Color(20, 40, 80));
        side.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton b1 = new JButton("View Symptoms");
        JButton b2 = new JButton("View Diseases");
        JButton b3 = new JButton("View Treatments");
        JButton b4 = new JButton("View Patient Records");
        JButton b5 = new JButton("Exit");

        JButton[] buttons = {b1, b2, b3, b4, b5};
        for (JButton b : buttons) {
            b.setBackground(new Color(0, 102, 204));
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setFocusPainted(false);
            side.add(b);
        }

        add(side, BorderLayout.WEST);

        // Main Panel with CardLayout
        cl = new CardLayout();
        mainPanel = new JPanel(cl);

        mainPanel.add(symptomsPanel(), "symptoms");
        mainPanel.add(diseasesPanel(), "diseases");
        mainPanel.add(treatmentsPanel(), "treatments");
        mainPanel.add(patientsPanel(), "patients");

        add(mainPanel, BorderLayout.CENTER);

        // Button Actions
        b1.addActionListener(e -> cl.show(mainPanel, "symptoms"));
        b2.addActionListener(e -> cl.show(mainPanel, "diseases"));
        b3.addActionListener(e -> cl.show(mainPanel, "treatments"));
        b4.addActionListener(e -> cl.show(mainPanel, "patients"));
        b5.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Database connection
    private Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // 🔹 Symptoms Panel
    private JPanel symptomsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Symptoms List");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(20, 40, 80));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        model.addColumn("Symptom ID");
        model.addColumn("Symptom Name");

        JButton load = new JButton("Load Symptoms");
        load.setBackground(new Color(0, 102, 204));
        load.setForeground(Color.WHITE);
        load.setFont(new Font("Segoe UI", Font.BOLD, 14));
        load.setFocusPainted(false);
        load.addActionListener(e -> {
            try (Connection con = connect()) {
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM symptoms");
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("symptom_id"),
                            rs.getString("symptom_name")
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(load, BorderLayout.SOUTH);
        return panel;
    }

    // 🔹 Diseases Panel
    private JPanel diseasesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Diseases List");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(20, 40, 80));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        model.addColumn("Disease ID");
        model.addColumn("Disease Name");
        model.addColumn("Description");

        JButton load = new JButton("Load Diseases");
        load.setBackground(new Color(0, 102, 204));
        load.setForeground(Color.WHITE);
        load.setFont(new Font("Segoe UI", Font.BOLD, 14));
        load.setFocusPainted(false);
        load.addActionListener(e -> {
            try (Connection con = connect()) {
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM diseases");
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("disease_id"),
                            rs.getString("disease_name"),
                            rs.getString("description")
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(load, BorderLayout.SOUTH);
        return panel;
    }

    // 🔹 Treatments Panel
    private JPanel treatmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Treatments List");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(20, 40, 80));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        model.addColumn("Treatment ID");
        model.addColumn("Disease Name");
        model.addColumn("Medication");
        model.addColumn("Advice");

        JButton load = new JButton("Load Treatments");
        load.setBackground(new Color(0, 102, 204));
        load.setForeground(Color.WHITE);
        load.setFont(new Font("Segoe UI", Font.BOLD, 14));
        load.setFocusPainted(false);
        load.addActionListener(e -> {
            try (Connection con = connect()) {
                String sql = "SELECT t.treatment_id, d.disease_name, t.medication, t.advice " +
                        "FROM treatments t JOIN diseases d ON t.disease_id = d.disease_id";
                ResultSet rs = con.createStatement().executeQuery(sql);
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("treatment_id"),
                            rs.getString("disease_name"),
                            rs.getString("medication"),
                            rs.getString("advice")
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(load, BorderLayout.SOUTH);
        return panel;
    }

    // 🔹 Patients Panel
    private JPanel patientsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Patient Records");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(20, 40, 80));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        model.addColumn("Patient ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Phone");

        JButton load = new JButton("Load Patients");
        load.setBackground(new Color(0, 102, 204));
        load.setForeground(Color.WHITE);
        load.setFont(new Font("Segoe UI", Font.BOLD, 14));
        load.setFocusPainted(false);
        load.addActionListener(e -> {
            try (Connection con = connect()) {
                String sql = "SELECT patient_id, name, age, gender, phone FROM patient_details";
                ResultSet rs = con.createStatement().executeQuery(sql);
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("phone")
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(load, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(admin_panel::new);
    }
}