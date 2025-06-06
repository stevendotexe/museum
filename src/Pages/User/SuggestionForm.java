package Pages.User;

import Components.Utilities.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SuggestionForm extends javax.swing.JFrame {

    public SuggestionForm() {
        initComponents();
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create header
        JLabel headerLabel = new JLabel("Submit Suggestion");
        headerLabel.setFont(new Font("DM Sans", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        
        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);
        
        // Description field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        descriptionField = new JTextField(20);
        formPanel.add(descriptionField, gbc);
        
        // Location field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        locationField = new JTextField(20);
        formPanel.add(locationField, gbc);
        
        // Category field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        categoryField = new JTextField(20);
        formPanel.add(categoryField, gbc);
        
        // Price field
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        priceField = new JTextField(20);
        formPanel.add(priceField, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        
        submitButton.addActionListener(e -> submitSuggestion());
        clearButton.addActionListener(e -> clearForm());
        
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    private void submitSuggestion() {
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        String location = locationField.getText().trim();
        String category = categoryField.getText().trim();
        String price = priceField.getText().trim();
        
        if (name.isEmpty() || description.isEmpty() || location.isEmpty() || 
            category.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO suggested_item (requested_item, description, location, category, price, status) " +
                        "VALUES (?, ?, ?, ?, ?, 'pending')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, description);
            pst.setString(3, location);
            pst.setString(4, category);
            pst.setString(5, price);
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Suggestion submitted successfully");
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error submitting suggestion: " + e.getMessage());
        }
    }
    
    private void clearForm() {
        nameField.setText("");
        descriptionField.setText("");
        locationField.setText("");
        categoryField.setText("");
        priceField.setText("");
        nameField.requestFocus();
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
            new SuggestionForm().setVisible(true);
        });
    }

    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField locationField;
    private JTextField categoryField;
    private JTextField priceField;
} 