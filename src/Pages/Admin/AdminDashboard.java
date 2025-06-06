package Pages.Admin;

import Components.Utilities.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.*;

public class AdminDashboard extends javax.swing.JFrame {

    public AdminDashboard() {
        initComponents();
        setSize(1280, 720);
        setLocationRelativeTo(null);
        view_data();
    }

    private void view_data() {
        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("ID");
        tabel.addColumn("Name");
        tabel.addColumn("Description");
        tabel.addColumn("Location");
        tabel.addColumn("Category");
        tabel.addColumn("Price");
        tabel.addColumn("Status");
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM suggested_item";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("requested_item"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("category"),
                    rs.getString("price"),
                    rs.getString("status")
                });
            }
            jTable1.setModel(tabel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create header
        JLabel headerLabel = new JLabel("Admin Dashboard");
        headerLabel.setFont(new Font("DM Sans", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        
        // Create stats panels
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        
        // Total Requests Panel
        JPanel totalRequestsPanel = new JPanel(new BorderLayout());
        JLabel totalRequestsLabel = new JLabel("Total Requests");
        JLabel totalRequestsValue = new JLabel("0");
        totalRequestsValue.setHorizontalAlignment(SwingConstants.CENTER);
        totalRequestsPanel.add(totalRequestsLabel, BorderLayout.NORTH);
        totalRequestsPanel.add(totalRequestsValue, BorderLayout.CENTER);
        statsPanel.add(totalRequestsPanel);
        
        // Total Accepted Panel
        JPanel totalAcceptedPanel = new JPanel(new BorderLayout());
        JLabel totalAcceptedLabel = new JLabel("Total Accepted");
        JLabel totalAcceptedValue = new JLabel("0");
        totalAcceptedValue.setHorizontalAlignment(SwingConstants.CENTER);
        totalAcceptedPanel.add(totalAcceptedLabel, BorderLayout.NORTH);
        totalAcceptedPanel.add(totalAcceptedValue, BorderLayout.CENTER);
        statsPanel.add(totalAcceptedPanel);
        
        // Total Rejected Panel
        JPanel totalRejectedPanel = new JPanel(new BorderLayout());
        JLabel totalRejectedLabel = new JLabel("Total Rejected");
        JLabel totalRejectedValue = new JLabel("0");
        totalRejectedValue.setHorizontalAlignment(SwingConstants.CENTER);
        totalRejectedPanel.add(totalRejectedLabel, BorderLayout.NORTH);
        totalRejectedPanel.add(totalRejectedValue, BorderLayout.CENTER);
        statsPanel.add(totalRejectedPanel);
        
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Create table
        jTable1 = new JTable();
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Name", "Description", "Category", "Location", "Price", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, 
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(jTable1);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
        // Add action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton acceptButton = new JButton("Accept");
        JButton rejectButton = new JButton("Reject");
        JButton refreshButton = new JButton("Refresh");
        
        acceptButton.addActionListener(e -> acceptSuggestion());
        rejectButton.addActionListener(e -> rejectSuggestion());
        refreshButton.addActionListener(e -> view_data());
        
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(refreshButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    private void acceptSuggestion() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a suggestion to accept");
            return;
        }
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "UPDATE suggested_item SET status = 'accepted' WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, jTable1.getValueAt(selectedRow, 0).toString());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Suggestion accepted successfully");
            view_data();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error accepting suggestion: " + e.getMessage());
        }
    }
    
    private void rejectSuggestion() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a suggestion to reject");
            return;
        }
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "UPDATE suggested_item SET status = 'rejected' WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, jTable1.getValueAt(selectedRow, 0).toString());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Suggestion rejected successfully");
            view_data();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error rejecting suggestion: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            new AdminDashboard().setVisible(true);
        });
    }

    private JTable jTable1;
} 