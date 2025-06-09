package Pages.User;

import Components.Utilities.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;

public class SuggestionForm extends javax.swing.JFrame {

    public SuggestionForm() {
        initComponents();
        setSize(1280, 720);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create header panel with title and back button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Back button
        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("DM Sans", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            this.dispose();
            new HomePage().setVisible(true);
        });
        headerPanel.add(backButton, BorderLayout.WEST);
        
        // Title
        JLabel headerLabel = new JLabel("Submit Suggestion");
        headerLabel.setFont(new Font("DM Sans", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
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
        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        descriptionScroll.setPreferredSize(new Dimension(300, 100));
        formPanel.add(descriptionScroll, gbc);
        
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

        // Date Discovered field
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Date Discovered (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        dateField = new JTextField(20);
        formPanel.add(dateField, gbc);

        // Image URL field
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Image URL:"), gbc);
        gbc.gridx = 1;
        imageUrlField = new JTextField(20);
        formPanel.add(imageUrlField, gbc);

        // Phone Number field
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        formPanel.add(phoneField, gbc);

        // Contact information label
        gbc.gridx = 1;
        gbc.gridy = 7;
        JLabel contactLabel = new JLabel("*We will contact you regarding the item if the museum is interested.");
        contactLabel.setFont(new Font("DM Sans", Font.ITALIC, 8));
        contactLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(contactLabel, gbc);
        
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
    
    private boolean isValidDate(String date) {
        // Regex pattern for YYYY-MM-DD format
        String pattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        if (!Pattern.matches(pattern, date)) {
            return false;
        }
        
        // Additional validation for valid dates (e.g., February 30th)
        try {
            java.sql.Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidImageUrl(String url) {
        if (url.isEmpty()) {
            return true; // Allow empty URLs
        }
        // Basic URL validation
        String pattern = "^(https?://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
        return Pattern.matches(pattern, url);
    }

    private boolean isValidPhoneNumber(String phone) {
        if (phone.isEmpty()) {
            return true; // Allow empty phone numbers
        }
        // Basic phone number validation (allows various formats)
        String pattern = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$";
        return Pattern.matches(pattern, phone);
    }
    
    private void submitSuggestion() {
        String name = nameField.getText().trim();
        String description = descriptionArea.getText().trim();
        String location = locationField.getText().trim();
        String category = categoryField.getText().trim();
        String dateDiscovered = dateField.getText().trim();
        String imageUrl = imageUrlField.getText().trim();
        String phoneNumber = phoneField.getText().trim();
        
        if (name.isEmpty() || description.isEmpty() || location.isEmpty() || 
            category.isEmpty() || dateDiscovered.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields");
            return;
        }

        if (!isValidDate(dateDiscovered)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid date in YYYY-MM-DD format");
            return;
        }

        if (!isValidImageUrl(imageUrl)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid image URL or leave it empty");
            return;
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number or leave it empty");
            return;
        }
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO suggested_item (item_name, item_description, location_found, category, date_discovered, image_url, phone_number, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, 'pending')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, description);
            pst.setString(3, location);
            pst.setString(4, category);
            pst.setString(5, dateDiscovered);
            pst.setString(6, imageUrl);
            pst.setString(7, phoneNumber);
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Suggestion submitted successfully");
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error submitting suggestion: " + e.getMessage());
        }
    }
    
    private void clearForm() {
        nameField.setText("");
        descriptionArea.setText("");
        locationField.setText("");
        categoryField.setText("");
        dateField.setText("");
        imageUrlField.setText("");
        phoneField.setText("");
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
    private JTextArea descriptionArea;
    private JTextField locationField;
    private JTextField categoryField;
    private JTextField dateField;
    private JTextField imageUrlField;
    private JTextField phoneField;
} 