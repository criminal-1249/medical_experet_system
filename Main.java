import javax.swing.*;
import admin_gui.*;
import java.awt.*;
import java.util.ArrayList;
import java.net.*; //api calls
import java.io.*;
import gui_part.*;
import java.sql.*;
import java.awt.event.*;

public class Main extends JFrame {

    private JScrollPane resultScroll; // shows disease result
    private JScrollPane treatmentScroll; // for treatmnt
    private SelectSymptoms selectSymptoms; // selecting symps
    private StatsPanel statsPanel; // status


    private static final String url = "jdbc:mysql://localhost:3306/medical_diagnosis_db";
    private static final String user = "root";
    private static final String password = "xxxxxxxx"; // enter ur sql password

    
    // glass color --- >transparent card effect
    // accent -- > button color 
    private Color glassColor = new Color(100, 180, 255, 170);
    private Color accent = new Color(0, 123, 255);

    // constructor
    public Main() {

        setTitle("Medical Diagnosis System");// title
        setSize(1200, 780); // size of the title
        setDefaultCloseOperation(EXIT_ON_CLOSE); // exxit omn close
        setLayout(null); // layout

        // BACKGROUND IMAGE
        // This is called an anonymous class
        // You are creating a JPanel
        // AND modifying its behavior (overriding a method)
        JPanel background = new JPanel() {
            Image bg = new ImageIcon(getClass().getResource("1.png")).getImage();

            // This method is automatically called whenever the panel is drawn
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); //Clears the panel before drawing
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        background.setLayout(null); // layout for the background panel
        setContentPane(background); // setting it into main panel


        // main title 
        JLabel title = new JLabel("Medical Diagnosis Dashboard");
        //changed
        title.setBounds(540, 15, 700, 40); // adjusting 
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE); // color
        background.add(title); //adding it to the background panel


        // admin button 
        JButton admin = modernButton("Admin", new Color(40, 167, 69));
        //changed
        admin.setBounds(1150, 20, 180, 40);// adjusting
        background.add(admin);// adding to background
        admin.addActionListener(e -> new AdminLogin()); // action listenier

        // LEFT PANEL -- select symptoms panel
        JPanel leftCard = glassCard();
        //changed
        leftCard.setBounds(90, 90, 300, 550);// adjusting
        leftCard.add(sectionTitle("Select Symptoms"), BorderLayout.NORTH);

        selectSymptoms = new SelectSymptoms();// creating new class 
        selectSymptoms.setOpaque(false);

        // iterates all components (mainly checkboxes)
        for (Component comp : selectSymptoms.getComponents()) {
            if (comp instanceof JCheckBox cb) { //check type + cast + assign in one line
                cb.setOpaque(false);
                cb.setForeground(Color.BLACK);
                cb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            }
        }
        // adding those into the scroll bar 
        JScrollPane symptomScroll = new JScrollPane(selectSymptoms);
        makeTransparent(symptomScroll);

        leftCard.add(symptomScroll, BorderLayout.CENTER);
        background.add(leftCard);

        // RESULT PANEL
        JPanel resultCard = glassCard();
        //changed
        resultCard.setBounds(510, 90, 420, 240);
        resultCard.add(sectionTitle("Diagnosis Result"), BorderLayout.NORTH);

        resultScroll = new JScrollPane(new JLabel("No result yet"));
        makeTransparent(resultScroll);

        resultCard.add(resultScroll, BorderLayout.CENTER);
        background.add(resultCard);

        // TREATMENT PANEL
        JPanel treatmentCard = glassCard();
        //changed
        treatmentCard.setBounds(510, 370, 420, 270);
        treatmentCard.add(sectionTitle("Treatment & Advice"), BorderLayout.NORTH);

        treatmentScroll = new JScrollPane(new JLabel("No treatment info yet"));
        makeTransparent(treatmentScroll);

        treatmentCard.add(treatmentScroll, BorderLayout.CENTER);
        background.add(treatmentCard);

        JButton predictButton = modernButton("DIAGNOSE", accent);
        //changed
        predictButton.setBounds(110, 660, 220, 45);
        background.add(predictButton);

        // STATS PANEL
        JPanel statsCard = glassCard();
        statsCard.setBounds(1000, 250, 350, 250);
        statsCard.add(sectionTitle("System Info"), BorderLayout.NORTH);

        statsPanel = new StatsPanel();
        statsCard.add(statsPanel, BorderLayout.CENTER);

        background.add(statsCard);

        predictButton.addActionListener(e -> {

            ArrayList<Integer> input = selectSymptoms.getSelectedSymptoms();

            resultScroll.setViewportView(new JLabel("Analyzing... ⏳"));
            treatmentScroll.setViewportView(new JLabel("Fetching... ⏳"));

            new Thread(() -> {

                String disease = getPredictionFromAPI(input);
                int diseaseId = getDiseaseId(disease);

                SwingUtilities.invokeLater(() -> {
                    resultScroll.setViewportView(new DiagnosisResult(disease));
                    treatmentScroll.setViewportView(new TreatmentMethod(diseaseId));
                });

            }).start();
        });

        setVisible(true);
    }

    private void makeTransparent(JScrollPane sp) {
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setBorder(null);
    }

    private JPanel glassCard() {
        JPanel panel = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(glassColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return panel;
    }

    private JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(new Color(20, 40, 80));
        return label;
    }

    private JButton modernButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(color.darker()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(color); }
        });

        return btn;
    }

    

    private String getPredictionFromAPI(ArrayList<Integer> input) {
        try {
            URL url = new URL("http://127.0.0.1:8000/predict");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String json = "{\"features\": " + input.toString() + "}";
            con.getOutputStream().write(json.getBytes());

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String res = br.readLine();

            return res.split("\"prediction\":\"")[1].split("\"")[0];

        } catch (Exception e) {
            return "API Error";
        }
    }

    private int getDiseaseId(String diseaseName) {
        try {
            Connection con = DriverManager.getConnection(url, user, password );

            PreparedStatement ps = con.prepareStatement(
                    "SELECT disease_id FROM diseases WHERE LOWER(disease_name)=LOWER(?)");

            ps.setString(1, diseaseName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {}
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}