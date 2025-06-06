package Pages.User;

import Components.Utilities.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class SuggestionTable extends javax.swing.JFrame {

    public SuggestionTable() {
        initComponents();
        setSize(1280, 720);
        setLocationRelativeTo(null);
        view_data();
    }

    private void view_data() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Location");
        model.addColumn("Category");
        model.addColumn("Price");
        model.addColumn("Status");
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM suggested_item WHERE asking_user = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, getCurrentUserId()); // You'll need to implement this method
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("requested_item"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("category"),
                    rs.getString("price"),
                    rs.getString("status")
                });
            }
            jTable1.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create header
        JLabel headerLabel = new JLabel("My Suggestions");
        headerLabel.setFont(new Font("DM Sans", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        
        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        
        searchButton.addActionListener(e -> searchSuggestions());
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        
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
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton newSuggestionButton = new JButton("New Suggestion");
        JButton refreshButton = new JButton("Refresh");
        
        newSuggestionButton.addActionListener(e -> openNewSuggestion());
        refreshButton.addActionListener(e -> view_data());
        
        buttonPanel.add(newSuggestionButton);
        buttonPanel.add(refreshButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    private void searchSuggestions() {
        String searchTerm = searchField.getText().trim();
        if (searchTerm.isEmpty()) {
            view_data();
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM suggested_item WHERE asking_user = ? AND " +
                        "(requested_item LIKE ? OR description LIKE ? OR category LIKE ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, getCurrentUserId());
            pst.setString(2, "%" + searchTerm + "%");
            pst.setString(3, "%" + searchTerm + "%");
            pst.setString(4, "%" + searchTerm + "%");
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("requested_item"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("category"),
                    rs.getString("price"),
                    rs.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching suggestions: " + e.getMessage());
        }
    }
    
    private void openNewSuggestion() {
        SuggestionForm form = new SuggestionForm();
        form.setVisible(true);
        this.dispose();
    }
    
    private int getCurrentUserId() {
        // TODO: Implement this method to get the current user's ID
        // For now, return a default value
        return 1;
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
            new SuggestionTable().setVisible(true);
        });
    }

    private JTable jTable1;
    private JTextField searchField;
} 