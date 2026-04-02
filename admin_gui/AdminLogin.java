package admin_gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AdminLogin extends JFrame {

    private static final String user_name = "xxxxxx"; // change it
    private static final String password = "xxxxxx"; // change it 


    public AdminLogin() {
        setTitle("Admin Login");
        setSize(450, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(false); // keeps window borders

        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(135, 206, 250)); // light sky blue
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Admin Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(20, 40, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        userLabel.setForeground(new Color(20, 40, 80));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(userLabel, gbc);

        JTextField userField = new JTextField();
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userField.setPreferredSize(new Dimension(250, 30));
        userField.setBorder(new LineBorder(Color.GRAY, 1, true));
        userField.setBackground(new Color(230, 240, 250)); // light blue background
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(userField, gbc);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        passLabel.setForeground(new Color(20, 40, 80));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passField.setPreferredSize(new Dimension(250, 30));
        passField.setBorder(new LineBorder(Color.GRAY, 1, true));
        passField.setBackground(new Color(230, 240, 250));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(passField, gbc);

        // Login Button
        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setBackground(new Color(0, 102, 204));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(loginBtn, gbc);

        // LOGIN LOGIC
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());


            //// change is required 
            if (username.equals(username) && password.equals(password)) { 
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new admin_panel(); // open admin panel
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminLogin::new);
    }
}