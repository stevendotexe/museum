package Pages.Login;

import Components.Utilities.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

public class RegisterFrame extends javax.swing.JFrame {

    public RegisterFrame() {
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
        jLabel4 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        txt_konfirmPassword = new javax.swing.JPasswordField();
        jCshowpassword = new javax.swing.JCheckBox();
        btn_register = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel1.setText("Email");
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel1.setBackground(new Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Sitka Banner", 1, 24));
        jLabel2.setText("Register Museum Inventory");
        jLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel2.setBackground(new Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel3.setText("Password");
        jLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel3.setBackground(new Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel4.setText("Konfirm Password");
        jLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel4.setBackground(new Color(255, 255, 255));

        txt_email.setMaximumSize(new Dimension(300, 30));
        txt_email.setPreferredSize(new Dimension(300, 30));
        txt_email.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt_email.setBackground(Color.WHITE);

        txt_password.setMaximumSize(new Dimension(300, 30));
        txt_password.setPreferredSize(new Dimension(300, 30));
        txt_password.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt_password.setBackground(Color.WHITE);

        txt_konfirmPassword.setMaximumSize(new Dimension(300, 30));
        txt_konfirmPassword.setPreferredSize(new Dimension(300, 30));
        txt_konfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt_konfirmPassword.setBackground(Color.WHITE);

        jCshowpassword.setText("Show Password");
        jCshowpassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn_register.setMaximumSize(new Dimension(300, 35));
        btn_register.setPreferredSize(new Dimension(300, 35));
        btn_register.setText("Register");
        btn_register.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_register.setBackground(Color.WHITE);

        btn_cancel.setMaximumSize(new Dimension(300, 35));
        btn_cancel.setPreferredSize(new Dimension(300, 35));
        btn_cancel.setText("Cancel");
        btn_cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_cancel.setBackground(Color.WHITE);

        // Add components to center panel with spacing
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(jLabel2);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(jLabel1);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(txt_email);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(jLabel3);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(txt_password);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(jLabel4);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(txt_konfirmPassword);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(jCshowpassword);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btn_register);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(btn_cancel);
        centerPanel.add(Box.createVerticalGlue());

        // Add center panel to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);

        // Add action listeners
        txt_email.addActionListener(e -> txt_emailActionPerformed(e));
        txt_password.addActionListener(e -> txt_passwordActionPerformed(e));
        txt_konfirmPassword.addActionListener(e -> txt_konfirmPasswordActionPerformed(e));
        jCshowpassword.addActionListener(e -> jCshowpasswordActionPerformed(e));
        btn_register.addActionListener(e -> btn_registerActionPerformed(e));
        btn_cancel.addActionListener(e -> btn_cancelActionPerformed(e));
    }

    private void jCshowpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCshowpasswordActionPerformed
        // TODO add your handling code here:
        if (jCshowpassword.isSelected()) {
            txt_password.setEchoChar((char)0);
            txt_konfirmPassword.setEchoChar((char)0);
        } else {
            txt_password.setEchoChar('•');
            txt_konfirmPassword.setEchoChar('•');
        }
    }//GEN-LAST:event_jCshowpasswordActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
        txt_password.requestFocus();
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
        txt_konfirmPassword.requestFocus();
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_konfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_konfirmPasswordActionPerformed
        // TODO add your handling code here:
        btn_registerActionPerformed(evt);
    }//GEN-LAST:event_txt_konfirmPasswordActionPerformed

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
                                   
    String email = txt_email.getText().trim();
    String password = new String(txt_password.getPassword()).trim();
    String confirm = new String(txt_konfirmPassword.getPassword()).trim();

    if (email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
        return;
    }
    if (!password.equals(confirm)) {
        JOptionPane.showMessageDialog(this, "Password tidak sama!");
        return;
    }

    Connection conn = null;
    PreparedStatement checkStmt = null;
    PreparedStatement insertStmt = null;
    ResultSet rs = null;

    try {
        conn = DatabaseConnection.getConnection();
        // 1. Cek apakah email sudah terdaftar
        String checkQuery = "SELECT id FROM users WHERE email = ?";
        checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, email);
        rs = checkStmt.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Email sudah terdaftar!");
            return;
        }
        // 2. Insert user baru
        String insertQuery = "INSERT INTO users (email, password) VALUES (?, ?)";
        insertStmt = conn.prepareStatement(insertQuery);
        insertStmt.setString(1, email);
        insertStmt.setString(2, password);
        int success = insertStmt.executeUpdate();
        if (success > 0) {
            JOptionPane.showMessageDialog(this, "Register sukses, silakan login!");
            // Kembali ke login
            new LoginFrame().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Register gagal!");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (checkStmt != null) checkStmt.close();
            if (insertStmt != null) insertStmt.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_btn_registerActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed

        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed


    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_register;
    private javax.swing.JCheckBox jCshowpassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_konfirmPassword;
    private javax.swing.JPasswordField txt_password;
    // End of variables declaration//GEN-END:variables
}
