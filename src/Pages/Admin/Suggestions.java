package Pages.Admin;

import Components.Utilities.DatabaseConnection;
import Components.Utilities.StatsCounter;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.*;

public class Suggestions extends javax.swing.JFrame {
    private JLabel totalSuggestionsLabel;
    private JLabel totalAcceptedLabel;
    private JLabel totalRejectedLabel;

    public Suggestions() {
        initComponents();
        setSize(1280, 720);
        setLocationRelativeTo(null);
        view_data();
        updateStats();
    }

    private void updateStats() {
        int[] stats = StatsCounter.getSuggestedItemStats();
        totalSuggestionsLabel.setText(String.format("%,d", stats[0] + stats[1] + stats[2]));
        totalAcceptedLabel.setText(String.format("%,d", stats[1]));
        totalRejectedLabel.setText(String.format("%,d", stats[2]));
    }

    private void view_data() {
        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("ID");
        tabel.addColumn("Name");
        tabel.addColumn("Location");
        tabel.addColumn("Category");
        tabel.addColumn("Description");
        tabel.addColumn("Date Discovered");
        tabel.addColumn("Suggested At");
        tabel.addColumn("Modified At");
        tabel.addColumn("Image URL");
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM suggested_item";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getString("location_found"),
                    rs.getString("category"),
                    rs.getString("item_description"),
                    rs.getDate("date_discovered"),
                    rs.getTimestamp("suggested_at"),
                    rs.getTimestamp("modified_at"),
                    rs.getString("image_url")
                });
            }
            jTable1.setModel(tabel);
            updateStats(); // Update stats after loading data
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Create main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create header panel with back button
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            this.dispose();
        });
        headerPanel.add(backButton, BorderLayout.WEST);
        
        JLabel headerLabel = new JLabel("Suggested Items");
        headerLabel.setFont(new Font("DM Sans", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Create stats panels
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Create panels for stats
        JPanel totalSuggestionsPanel = new JPanel();
        totalSuggestionsPanel.setBackground(new Color(255, 255, 255));
        totalSuggestionsPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        totalSuggestionsPanel.setLayout(new BoxLayout(totalSuggestionsPanel, BoxLayout.Y_AXIS));
        
        JLabel totalSuggestionsTitle = new JLabel("Total Suggestions");
        totalSuggestionsTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalSuggestionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        totalSuggestionsLabel = new JLabel("0");
        totalSuggestionsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        totalSuggestionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        totalSuggestionsPanel.add(Box.createVerticalStrut(10));
        totalSuggestionsPanel.add(totalSuggestionsTitle);
        totalSuggestionsPanel.add(Box.createVerticalStrut(5));
        totalSuggestionsPanel.add(totalSuggestionsLabel);
        totalSuggestionsPanel.add(Box.createVerticalStrut(10));
        
        // Create panel for accepted suggestions
        JPanel acceptedSuggestionsPanel = new JPanel();
        acceptedSuggestionsPanel.setBackground(new Color(255, 255, 255));
        acceptedSuggestionsPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        acceptedSuggestionsPanel.setLayout(new BoxLayout(acceptedSuggestionsPanel, BoxLayout.Y_AXIS));
        
        JLabel acceptedSuggestionsTitle = new JLabel("Accepted Suggestions");
        acceptedSuggestionsTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        acceptedSuggestionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        totalAcceptedLabel = new JLabel("0");
        totalAcceptedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        totalAcceptedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        acceptedSuggestionsPanel.add(Box.createVerticalStrut(10));
        acceptedSuggestionsPanel.add(acceptedSuggestionsTitle);
        acceptedSuggestionsPanel.add(Box.createVerticalStrut(5));
        acceptedSuggestionsPanel.add(totalAcceptedLabel);
        acceptedSuggestionsPanel.add(Box.createVerticalStrut(10));
        
        // Create panel for rejected suggestions
        JPanel rejectedSuggestionsPanel = new JPanel();
        rejectedSuggestionsPanel.setBackground(new Color(255, 255, 255));
        rejectedSuggestionsPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        rejectedSuggestionsPanel.setLayout(new BoxLayout(rejectedSuggestionsPanel, BoxLayout.Y_AXIS));
        
        JLabel rejectedSuggestionsTitle = new JLabel("Rejected Suggestions");
        rejectedSuggestionsTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        rejectedSuggestionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        totalRejectedLabel = new JLabel("0");
        totalRejectedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        totalRejectedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        rejectedSuggestionsPanel.add(Box.createVerticalStrut(10));
        rejectedSuggestionsPanel.add(rejectedSuggestionsTitle);
        rejectedSuggestionsPanel.add(Box.createVerticalStrut(5));
        rejectedSuggestionsPanel.add(totalRejectedLabel);
        rejectedSuggestionsPanel.add(Box.createVerticalStrut(10));
        
        // Add panels to stats panel
        statsPanel.add(totalSuggestionsPanel);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(acceptedSuggestionsPanel);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(rejectedSuggestionsPanel);
        
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Create table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Create table
        jTable1 = new JTable();
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Name", "Location", "Category", "Description", "Date Discovered", 
                "Suggested At", "Modified At", "Image URL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.sql.Date.class,
                java.sql.Timestamp.class, java.sql.Timestamp.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        
        // Set column widths
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150); // Name
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100); // Location
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Category
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(200); // Description
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(100); // Date Discovered
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(150); // Suggested At
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(150); // Modified At
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(150); // Image URL
        
        JScrollPane scrollPane = new JScrollPane(jTable1);
        scrollPane.setPreferredSize(new Dimension(1200, 300));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton acceptButton = new JButton("Accept");
        JButton rejectButton = new JButton("Reject");
        JButton refreshButton = new JButton("Refresh");
        
        acceptButton.addActionListener(e -> acceptSuggestion());
        rejectButton.addActionListener(e -> rejectSuggestion());
        refreshButton.addActionListener(e -> view_data());
        
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(refreshButton);
        
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);
        
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
            String sql = "UPDATE suggested_item SET status = 'accepted' WHERE item_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, (Integer)jTable1.getValueAt(selectedRow, 0));
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
            String sql = "UPDATE suggested_item SET status = 'rejected' WHERE item_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, (Integer)jTable1.getValueAt(selectedRow, 0));
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
            new Suggestions().setVisible(true);
        });
    }

    private JTable jTable1;
} 