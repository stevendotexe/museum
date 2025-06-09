package Pages.Admin;

import Components.Utilities.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddItem extends JDialog {
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField locationField;
    private JTextArea descriptionArea;
    private JCheckBox isExhibitedCheck;
    private JTextField imageUrlField;
    private JTextField dateDiscoveredField;
    private JButton saveButton;
    private JButton cancelButton;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public AddItem(Frame parent) {
        super(parent, "Add New Item", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(255, 255, 255));
        
        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBackground(new Color(255, 255, 255));
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        nameField = new JTextField(20);
        nameField.setBackground(Color.WHITE);
        formPanel.add(nameField, gbc);
        
        // Category field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBackground(new Color(255, 255, 255));
        formPanel.add(categoryLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        categoryField = new JTextField(20);
        categoryField.setBackground(Color.WHITE);
        formPanel.add(categoryField, gbc);
        
        // Location field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBackground(new Color(255, 255, 255));
        formPanel.add(locationLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        locationField = new JTextField(20);
        locationField.setBackground(Color.WHITE);
        formPanel.add(locationField, gbc);
        
        // Description area
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBackground(new Color(255, 255, 255));
        formPanel.add(descriptionLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBackground(Color.WHITE);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        formPanel.add(descriptionScroll, gbc);
        
        // Is Exhibited checkbox
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        isExhibitedCheck = new JCheckBox("Item is currently exhibited");
        isExhibitedCheck.setBackground(new Color(255, 255, 255));
        formPanel.add(isExhibitedCheck, gbc);

        // Image URL field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        JLabel imageUrlLabel = new JLabel("Image URL:");
        imageUrlLabel.setBackground(new Color(255, 255, 255));
        formPanel.add(imageUrlLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        imageUrlField = new JTextField(20);
        imageUrlField.setBackground(Color.WHITE);
        formPanel.add(imageUrlField, gbc);

        // Date Discovered field
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        JLabel dateDiscoveredLabel = new JLabel("Date Discovered (yyyy/mm/dd):");
        dateDiscoveredLabel.setBackground(new Color(255, 255, 255));
        formPanel.add(dateDiscoveredLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        dateDiscoveredField = new JTextField(20);
        dateDiscoveredField.setBackground(Color.WHITE);
        formPanel.add(dateDiscoveredField, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        
        saveButton.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.WHITE);
        
        saveButton.addActionListener(e -> saveItem());
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    private boolean isValidDateFormat(String dateStr) {
        return dateStr.matches("\\d{4}/\\d{2}/\\d{2}");
    }

    private void saveItem() {
        // Validate required fields
        if (nameField.getText().trim().isEmpty() ||
            categoryField.getText().trim().isEmpty() ||
            locationField.getText().trim().isEmpty() ||
            descriptionArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill in all required fields.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate date format
        String dateStr = dateDiscoveredField.getText().trim();
        if (!dateStr.isEmpty() && !isValidDateFormat(dateStr)) {
            JOptionPane.showMessageDialog(this,
                "Invalid date format. Please use yyyy/mm/dd format.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "INSERT INTO item (item_name, category, location_found, item_description, is_exhibited, image_url, date_discovered) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, nameField.getText().trim());
            pstmt.setString(2, categoryField.getText().trim());
            pstmt.setString(3, locationField.getText().trim());
            pstmt.setString(4, descriptionArea.getText().trim());
            pstmt.setBoolean(5, isExhibitedCheck.isSelected());
            pstmt.setString(6, imageUrlField.getText().trim());
            
            // Parse and set date discovered
            if (!dateStr.isEmpty()) {
                try {
                    java.util.Date dateDiscovered = dateFormat.parse(dateStr);
                    pstmt.setTimestamp(7, new java.sql.Timestamp(dateDiscovered.getTime()));
                } catch (ParseException e) {
                    pstmt.setTimestamp(7, null);
                }
            } else {
                pstmt.setTimestamp(7, null);
            }
            
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this,
                "Item added successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "Error adding item: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 