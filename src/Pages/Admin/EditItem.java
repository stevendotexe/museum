package Pages.Admin;

import Components.Utilities.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class EditItem extends JDialog {
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField locationField;
    private JTextArea descriptionArea;
    private JCheckBox isExhibitedCheck;
    private JTextField imageUrlField;
    private JTextField dateDiscoveredField;
    private JButton saveButton;
    private JButton cancelButton;
    private int itemId;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public EditItem(Frame parent, int itemId) {
        super(parent, "Edit Item", true);
        this.itemId = itemId;
        initComponents();
        loadItemData();
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);
        
        // Category field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        categoryField = new JTextField(20);
        formPanel.add(categoryField, gbc);
        
        // Location field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        locationField = new JTextField(20);
        formPanel.add(locationField, gbc);
        
        // Description area
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        formPanel.add(descriptionScroll, gbc);
        
        // Is Exhibited checkbox
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        isExhibitedCheck = new JCheckBox("Item is currently exhibited");
        formPanel.add(isExhibitedCheck, gbc);

        // Image URL field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Image URL:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        imageUrlField = new JTextField(20);
        formPanel.add(imageUrlField, gbc);

        // Date Discovered field
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Date Discovered (yyyy/mm/dd):"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        dateDiscoveredField = new JTextField(20);
        formPanel.add(dateDiscoveredField, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        
        saveButton.addActionListener(e -> saveChanges());
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    private boolean isValidDateFormat(String dateStr) {
        return dateStr.matches("\\d{4}/\\d{2}/\\d{2}");
    }

    private void loadItemData() {
        String query = "SELECT * FROM item WHERE item_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                nameField.setText(rs.getString("item_name"));
                categoryField.setText(rs.getString("category"));
                locationField.setText(rs.getString("location_found"));
                descriptionArea.setText(rs.getString("item_description"));
                isExhibitedCheck.setSelected(rs.getBoolean("is_exhibited"));
                imageUrlField.setText(rs.getString("image_url"));
                
                // Load date discovered
                Timestamp dateDiscovered = rs.getTimestamp("date_discovered");
                if (dateDiscovered != null) {
                    dateDiscoveredField.setText(dateFormat.format(dateDiscovered));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading item data: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void saveChanges() {
        // Validate date format
        String dateStr = dateDiscoveredField.getText().trim();
        if (!dateStr.isEmpty() && !isValidDateFormat(dateStr)) {
            JOptionPane.showMessageDialog(this,
                "Invalid date format. Please use yyyy/mm/dd format.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "UPDATE item SET item_name = ?, category = ?, location_found = ?, " +
                      "item_description = ?, is_exhibited = ?, image_url = ?, date_discovered = ? WHERE item_id = ?";
        
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
                    Date dateDiscovered = dateFormat.parse(dateStr);
                    pstmt.setTimestamp(7, new Timestamp(dateDiscovered.getTime()));
                } catch (ParseException e) {
                    pstmt.setTimestamp(7, null);
                }
            } else {
                pstmt.setTimestamp(7, null);
            }
            
            pstmt.setInt(8, itemId);
            
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this,
                "Item updated successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "Error updating item: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 