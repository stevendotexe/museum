package Pages.Login;

import Components.Utilities.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
        
public class RegisterFrame extends javax.swing.JFrame {

     public RegisterFrame() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Email");

        jLabel2.setFont(new java.awt.Font("Sitka Banner", 1, 24)); // NOI18N
        jLabel2.setText("Register Museum Inventory");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Konfirm Password");

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });

        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });

        txt_konfirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_konfirmPasswordActionPerformed(evt);
            }
        });

        jCshowpassword.setText("Show Password");
        jCshowpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCshowpasswordActionPerformed(evt);
            }
        });

        btn_register.setText("Register");
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCshowpassword)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_konfirmPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_register, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_konfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCshowpassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_register)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancel)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
