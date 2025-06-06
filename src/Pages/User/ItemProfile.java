package Pages.User;

import Components.Utilities.DatabaseConnection;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ItemProfile extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JLabel categoryLabel;
    private JLabel dateLabel;
    private JLabel locationLabel;
    private JLabel descriptionLabel;
    private JButton backButton;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

    public ItemProfile(int itemId) {
        setTitle("Item Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create main panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize labels
        nameLabel = new JLabel();
        categoryLabel = new JLabel();
        dateLabel = new JLabel();
        locationLabel = new JLabel();
        descriptionLabel = new JLabel();

        // Set fonts
        Font titleFont = new Font("DM Sans", Font.BOLD, 24);
        Font labelFont = new Font("DM Sans", Font.PLAIN, 16);
        nameLabel.setFont(titleFont);
        categoryLabel.setFont(labelFont);
        dateLabel.setFont(labelFont);
        locationLabel.setFont(labelFont);
        descriptionLabel.setFont(labelFont);

        // Add labels to content panel
        contentPanel.add(nameLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(categoryLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(dateLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(locationLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(descriptionLabel);

        // Create back button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());

        // Add components to main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        loadItemData(itemId);
    }

    private void loadItemData(int itemId) {
        String query = "SELECT * FROM item WHERE item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nameLabel.setText(rs.getString("item_name"));
                categoryLabel.setText("Category: " + rs.getString("category"));
                
                Timestamp dateDiscovered = rs.getTimestamp("date_discovered");
                dateLabel.setText("Date Discovered: " + 
                    (dateDiscovered != null ? dateFormat.format(dateDiscovered) : "N/A"));
                
                locationLabel.setText("Location: " + rs.getString("location_found"));
                descriptionLabel.setText("Description: " + rs.getString("description"));
            } else {
                JOptionPane.showMessageDialog(this,
                    "Item not found",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading item data: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            dispose();
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
            new ItemProfile(1).setVisible(true);
        });
    }
} 