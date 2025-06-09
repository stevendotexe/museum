package Pages.Login;

import Components.Utilities.DatabaseConnection;
import Pages.User.HomePage;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

public class LoginFrame extends javax.swing.JFrame {
    public LoginFrame() {
        initComponents();
        setSize(1280, 720);
        setLocationRelativeTo(null);
    }
   
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Create a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(255, 255, 255));

        // Create a center panel for the form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        centerPanel.setBackground(new Color(255, 255, 255));

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        jCshowPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("DM Sans", 1, 24));
        jLabel1.setText("Login Museum Inventory");
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel1.setBackground(new Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("DM Sans", 0, 18));
        jLabel2.setText("Email");
        jLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel2.setBackground(new Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("DM Sans", 0, 18));
        jLabel3.setText("Password");
        jLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel3.setBackground(new Color(255, 255, 255));

        emailField.setMaximumSize(new Dimension(300, 30));
        emailField.setPreferredSize(new Dimension(300, 30));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setBackground(Color.WHITE);

        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setPreferredSize(new Dimension(300, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setBackground(Color.WHITE);

        btnLogin.setMaximumSize(new Dimension(300, 35));
        btnLogin.setPreferredSize(new Dimension(300, 35));
        btnLogin.setText("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setBackground(Color.WHITE);

        btnRegister.setMaximumSize(new Dimension(300, 35));
        btnRegister.setPreferredSize(new Dimension(300, 35));
        btnRegister.setText("Register");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setBackground(Color.WHITE);

        jCshowPassword.setText("Show Password");
        jCshowPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jCshowPassword.setBackground(new Color(255, 255, 255));

        // Add components to center panel with spacing
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(jLabel1);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(jLabel2);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(emailField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(jLabel3);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(jCshowPassword);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnLogin);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(btnRegister);
        centerPanel.add(Box.createVerticalGlue());

        // Add center panel to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);

        // Add action listeners
        emailField.addActionListener(e -> emailFieldActionPerformed(e));
        passwordField.addActionListener(e -> passwordFieldActionPerformed(e));
        btnLogin.addActionListener(e -> btnLoginActionPerformed(e));
        btnRegister.addActionListener(e -> btnRegisterActionPerformed(e));
        jCshowPassword.addActionListener(e -> jCshowPasswordActionPerformed(e));
    }

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        RegisterFrame registerFrame = new RegisterFrame();
        com.app.Main.showWindow(registerFrame);
    }

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // No action needed
    }

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // No action needed
    }

    private void jCshowPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCshowPassword.isSelected()) {
            passwordField.setEchoChar((char)0);
        } else {
            passwordField.setEchoChar('*');
        }
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email dan password tidak boleh kosong!");
            return;
        }

        // Disable login button while processing
        btnLogin.setEnabled(false);

        // Use SwingWorker to handle the login process
        SwingUtilities.invokeLater(() -> {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                Thread.sleep(500); // Small delay

                conn = DatabaseConnection.getConnection();
                String query = "SELECT id, is_admin FROM users WHERE email = ? AND password = ?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, email);
                pstmt.setString(2, password);

                rs = pstmt.executeQuery();

                if (rs.next()) {
                    int userId = rs.getInt("id");
                    boolean isAdmin = rs.getBoolean("is_admin");
                    Thread.sleep(500); // Small delay
                    
                    // Clear the window stack before showing new window
                    com.app.Main.clearStack();
                    
                    if (isAdmin) {
                        // Open Dashboard for admin users
                        Pages.Admin.Dashboard dashboard = new Pages.Admin.Dashboard();
                        com.app.Main.showWindow(dashboard);
                    } else {
                        // Open HomePage for regular users
                        Pages.User.HomePage homePage = new Pages.User.HomePage();
                        com.app.Main.showWindow(homePage);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Email atau password salah!");
                }

            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Database driver not found: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) DatabaseConnection.closeConnection(conn);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                // Re-enable login button
                btnLogin.setEnabled(true);
            }
        });
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JTextField emailField;
    private javax.swing.JCheckBox jCshowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField passwordField;
    // End of variables declaration
}
