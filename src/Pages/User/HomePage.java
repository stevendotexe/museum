package Pages.User;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import Components.Utilities.DatabaseConnection;
import Components.Cards.NavigationBar;
import Components.Utilities.Item;
import Components.Cards.Collection.ItemCard;

public class HomePage extends javax.swing.JFrame {

    public HomePage() {
        initComponents();
        setSize(1280, 720);
        setLocationRelativeTo(null);
        
        // Set DM Sans font for all components
        setFont(new java.awt.Font("DM Sans", 0, 14));
        
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Add NavigationBar at the top
        NavigationBar navigationBar = new NavigationBar();
        mainPanel.add(navigationBar, BorderLayout.NORTH);
        
        // Create center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        // Add museum name label
        jLabel1 = new JLabel("Museum Name");
        jLabel1.setFont(new java.awt.Font("DM Sans", 1, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(jLabel1, BorderLayout.NORTH);
        
        // Create grid panel for items
        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        
        // Add panels to grid
        gridPanel.add(jPanel1);
        gridPanel.add(jPanel2);
        gridPanel.add(jPanel3);
        gridPanel.add(jPanel5);
        gridPanel.add(jPanel6);
        gridPanel.add(jPanel7);
        
        centerPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Set the main panel as content pane
        setContentPane(mainPanel);
        
        // Add sample items
        addSampleItems();
    }

    private void addSampleItems() {
        // Sample items data
        Item[] sampleItems = {
            new Item(1, "Ancient Egyptian Vase", "https://images.unsplash.com/photo-1518998053901-5348d3961a04", 
                    "A beautiful ancient vase from Egypt", "Cairo, Egypt", "Pottery", "2024-01-15", "2024-03-15", "2024-03-15", true),
            new Item(2, "Roman Coin", "https://images.unsplash.com/photo-1518998053901-5348d3961a04", 
                    "Ancient Roman coin from 100 AD", "Rome, Italy", "Coins", "2024-02-01", "2024-03-15", "2024-03-15", true),
            new Item(3, "Greek Statue", "https://images.unsplash.com/photo-1518998053901-5348d3961a04", 
                    "Marble statue from ancient Greece", "Athens, Greece", "Sculpture", "2024-02-15", "2024-03-15", "2024-03-15", true),
            new Item(4, "Chinese Pottery", "https://images.unsplash.com/photo-1518998053901-5348d3961a04", 
                    "Ming Dynasty pottery piece", "Beijing, China", "Pottery", "2024-02-20", "2024-03-15", "2024-03-15", true),
            new Item(5, "Mayan Artifact", "https://images.unsplash.com/photo-1518998053901-5348d3961a04", 
                    "Ancient Mayan ceremonial object", "Mexico City, Mexico", "Artifact", "2024-02-25", "2024-03-15", "2024-03-15", true),
            new Item(6, "Viking Sword", "https://images.unsplash.com/photo-1518998053901-5348d3961a04", 
                    "Iron age Viking sword", "Oslo, Norway", "Weapon", "2024-03-01", "2024-03-15", "2024-03-15", true)
        };

        // Update panels with sample items
        updatePanel(jPanel1, sampleItems[0]);
        updatePanel(jPanel2, sampleItems[1]);
        updatePanel(jPanel3, sampleItems[2]);
        updatePanel(jPanel5, sampleItems[3]);
        updatePanel(jPanel6, sampleItems[4]);
        updatePanel(jPanel7, sampleItems[5]);
    }

    private void updatePanel(JPanel panel, Item item) {
        JLabel nameLabel = (JLabel) panel.getComponent(0);
        JLabel categoryLabel = (JLabel) panel.getComponent(1);
        JLabel statusLabel = (JLabel) panel.getComponent(2);

        nameLabel.setText(item.getName());
        categoryLabel.setText(item.getCategory());
        statusLabel.setText("On Display");
    }

    private void initComponents() {
        // Initialize all components first
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnMyBookmarks = new javax.swing.JButton();
        btnInventory = new javax.swing.JButton();
        btnAddNewItem = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtCategoryFilter = new javax.swing.JComboBox<>();
        txtSearchField = new javax.swing.JTextField();
        btnSearchItems = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        // Set window properties
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // Set DM Sans font for all components
        setFont(new java.awt.Font("DM Sans", 0, 14));
        jLabel1.setFont(new java.awt.Font("DM Sans", 1, 24));
        jLabel1.setText("Museum Name");

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Add NavigationBar at the top
        NavigationBar navigationBar = new NavigationBar();
        mainPanel.add(navigationBar, BorderLayout.NORTH);
        
        // Create center panel with BorderLayout
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        // Add museum name label
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(jLabel1);
        centerPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Create grid panel for items
        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        
        // Initialize all panels with their components
        initializePanel(jPanel1, jLabel2, jLabel3, jLabel4, jLabel5);
        initializePanel(jPanel2, jLabel6, jLabel7, jLabel8, jLabel9);
        initializePanel(jPanel3, jLabel10, jLabel11, jLabel12, jLabel13);
        initializePanel(jPanel5, jLabel14, jLabel15, jLabel16, jLabel17);
        initializePanel(jPanel6, jLabel18, jLabel19, jLabel20, jLabel21);
        initializePanel(jPanel7, jLabel22, jLabel23, jLabel24, jLabel25);
        
        // Add panels to grid
        gridPanel.add(jPanel1);
        gridPanel.add(jPanel2);
        gridPanel.add(jPanel3);
        gridPanel.add(jPanel5);
        gridPanel.add(jPanel6);
        gridPanel.add(jPanel7);
        
        centerPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Set the main panel as content pane
        setContentPane(mainPanel);
        
        // Now that all panels are initialized, add sample items
        addSampleItems();
    }

    private void initializePanel(JPanel panel, JLabel... labels) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (JLabel label : labels) {
            panel.add(label);
        }
    }

    private void updatePanel(JPanel panel, String name, String description, String date, String category) {
        if (panel.getComponentCount() >= 4) {
            JLabel nameLabel = (JLabel) panel.getComponent(0);
            JLabel descLabel = (JLabel) panel.getComponent(1);
            JLabel dateLabel = (JLabel) panel.getComponent(2);
            JLabel catLabel = (JLabel) panel.getComponent(3);
            
            nameLabel.setText(name);
            descLabel.setText(description);
            dateLabel.setText(date);
            catLabel.setText(category);
        }
    }

    private void btnInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventoryActionPerformed
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    // Sembunyikan semua panel dulu
    jPanel1.setVisible(false);
    jPanel3.setVisible(false);
    jPanel5.setVisible(false);
    jPanel6.setVisible(false);
    jPanel7.setVisible(false);
    jPanel8.setVisible(false);

    try {
        conn = DatabaseConnection.getConnection();
        String query = "SELECT i.item_id, i.item_name, i.category, s.status_desc " +
                       "FROM item i " +
                       "LEFT JOIN inventory inv ON i.item_id = inv.item_id " +
                       "LEFT JOIN item_status s ON inv.item_status_id = s.item_status_id " +
                       "WHERE i.is_exhibited = 1";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        int panelCount = 1;
        while (rs.next() && panelCount <= 6) {
            String itemName = rs.getString("item_name");
            String category = rs.getString("category");
            String status = rs.getString("status_desc");

            switch (panelCount) {
                case 1:
                    jLabel2.setText(itemName);
                    jLabel3.setText(category);
                    jLabel4.setText(status);
                    jPanel1.setVisible(true);
                    break;
                case 2:
                    jLabel6.setText(itemName);
                    jLabel7.setText(category);
                    jLabel8.setText(status);
                    jPanel3.setVisible(true);
                    break;
                case 3:
                    jLabel10.setText(itemName);
                    jLabel11.setText(category);
                    jLabel12.setText(status);
                    jPanel5.setVisible(true);
                    break;
                case 4:
                    jLabel14.setText(itemName);
                    jLabel15.setText(category);
                    jLabel16.setText(status);
                    jPanel6.setVisible(true);
                    break;
                case 5:
                    jLabel18.setText(itemName);
                    jLabel19.setText(category);
                    jLabel20.setText(status);
                    jPanel7.setVisible(true);
                    break;
                case 6:
                    jLabel22.setText(itemName);
                    jLabel23.setText(category);
                    jLabel24.setText(status);
                    jPanel8.setVisible(true);
                    break;
            }
            panelCount++;
        }

        if (panelCount == 1) {
            JOptionPane.showMessageDialog(this, "Tidak ada item yang bisa ditampilkan.");
        }
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(this, "Database driver not found: " + e.getMessage());
        e.printStackTrace();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading inventory: " + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) DatabaseConnection.closeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnInventoryActionPerformed

    private void btnAddNewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewItemActionPerformed
    String itemName = JOptionPane.showInputDialog(this, "Masukkan nama item:");
    if (itemName == null || itemName.trim().isEmpty()) return;
    
    String description = JOptionPane.showInputDialog(this, "Masukkan deskripsi item:");
    if (description == null) return;
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO suggested_item (request_time, status, asking_user, requested_item) " +
                      "VALUES (NOW(), 1, ?, ?)";
        
        pstmt = conn.prepareStatement(query);
        int currentUserId = 0;
        pstmt.setInt(1, currentUserId);
        pstmt.setString(2, "Nama: " + itemName + "\nDeskripsi: " + description);
        
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Item berhasil diajukan untuk ditambahkan", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(this, "Database driver not found: " + e.getMessage());
        e.printStackTrace();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error submitting item: " + ex.getMessage(), 
                                    "Database Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) DatabaseConnection.closeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnAddNewItemActionPerformed

    private void txtSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchFieldActionPerformed
    
}//GEN-LAST:event_txtSearchFieldActionPerformed
                                         
    private void txtCategoryFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryFilterActionPerformed
      
    }//GEN-LAST:event_txtCategoryFilterActionPerformed

    private void btnSearchItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchItemsActionPerformed
                                           
    String searchTerm = txtSearchField.getText().trim();
    String categoryFilter = (String) txtCategoryFilter.getSelectedItem();

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    jPanel1.setVisible(false);
    jPanel3.setVisible(false);
    jPanel5.setVisible(false);
    jPanel6.setVisible(false);
    jPanel7.setVisible(false);
    jPanel8.setVisible(false);

    try {
        conn = DatabaseConnection.getConnection();
        StringBuilder query = new StringBuilder(
            "SELECT i.item_id, i.item_name, i.category, s.status_desc " +
            "FROM item i " +
            "LEFT JOIN inventory inv ON i.item_id = inv.item_id " +
            "LEFT JOIN item_status s ON inv.item_status_id = s.item_status_id " +
            "WHERE i.is_exhibited = 1 AND (i.item_name LIKE ? OR i.item_description LIKE ?)"
        );
        if (categoryFilter != null && !categoryFilter.equalsIgnoreCase("All")) {
            query.append(" AND i.category = ?");
        }

        pstmt = conn.prepareStatement(query.toString());
        pstmt.setString(1, "%" + searchTerm + "%");
        pstmt.setString(2, "%" + searchTerm + "%");
        if (categoryFilter != null && !categoryFilter.equalsIgnoreCase("All")) {
            pstmt.setString(3, categoryFilter);
        }

        rs = pstmt.executeQuery();

        int panelCount = 1;
        while (rs.next() && panelCount <= 6) {
            String itemName = rs.getString("item_name");
            String category = rs.getString("category");
            String status = rs.getString("status_desc");

            switch (panelCount) {
                case 1:
                    jLabel2.setText(itemName);
                    jLabel3.setText(category);
                    jLabel4.setText(status);
                    jPanel1.setVisible(true);
                    break;
                case 2:
                    jLabel6.setText(itemName);
                    jLabel7.setText(category);
                    jLabel8.setText(status);
                    jPanel3.setVisible(true);
                    break;
                case 3:
                    jLabel10.setText(itemName);
                    jLabel11.setText(category);
                    jLabel12.setText(status);
                    jPanel5.setVisible(true);
                    break;
                case 4:
                    jLabel14.setText(itemName);
                    jLabel15.setText(category);
                    jLabel16.setText(status);
                    jPanel6.setVisible(true);
                    break;
                case 5:
                    jLabel18.setText(itemName);
                    jLabel19.setText(category);
                    jLabel20.setText(status);
                    jPanel7.setVisible(true);
                    break;
                case 6:
                    jLabel22.setText(itemName);
                    jLabel23.setText(category);
                    jLabel24.setText(status);
                    jPanel8.setVisible(true);
                    break;
            }
            panelCount++;
        }
        if (panelCount == 1) {
            JOptionPane.showMessageDialog(this, "Tidak ditemukan item dengan kriteria tersebut.");
        }
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(this, "Database driver not found: " + e.getMessage());
        e.printStackTrace();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error searching items: " + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) DatabaseConnection.closeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnSearchItemsActionPerformed

    private void btnMyBookmarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyBookmarksActionPerformed
                                        
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int currentUserId = 1; // <-- ubah jika ingin dinamis

    jPanel1.setVisible(false);
    jPanel3.setVisible(false);
    jPanel5.setVisible(false);
    jPanel6.setVisible(false);
    jPanel7.setVisible(false);
    jPanel8.setVisible(false);

    try {
        conn = DatabaseConnection.getConnection();
        String query = "SELECT i.item_id, i.item_name, i.category, s.status_desc " +
                       "FROM item i " +
                       "JOIN user_bookmarks b ON i.item_id = b.bookmark_object " +
                       "LEFT JOIN inventory inv ON i.item_id = inv.item_id " +
                       "LEFT JOIN item_status s ON inv.item_status_id = s.item_status_id " +
                       "WHERE b.user_id = ? AND i.is_exhibited = 1";
        pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, currentUserId);
        rs = pstmt.executeQuery();

        int panelCount = 1;
        while (rs.next() && panelCount <= 6) {
            String itemName = rs.getString("item_name");
            String category = rs.getString("category");
            String status = rs.getString("status_desc");

            switch (panelCount) {
                case 1:
                    jLabel2.setText(itemName);
                    jLabel3.setText(category);
                    jLabel4.setText(status);
                    jPanel1.setVisible(true);
                    break;
                case 2:
                    jLabel6.setText(itemName);
                    jLabel7.setText(category);
                    jLabel8.setText(status);
                    jPanel3.setVisible(true);
                    break;
                case 3:
                    jLabel10.setText(itemName);
                    jLabel11.setText(category);
                    jLabel12.setText(status);
                    jPanel5.setVisible(true);
                    break;
                case 4:
                    jLabel14.setText(itemName);
                    jLabel15.setText(category);
                    jLabel16.setText(status);
                    jPanel6.setVisible(true);
                    break;
                case 5:
                    jLabel18.setText(itemName);
                    jLabel19.setText(category);
                    jLabel20.setText(status);
                    jPanel7.setVisible(true);
                    break;
                case 6:
                    jLabel22.setText(itemName);
                    jLabel23.setText(category);
                    jLabel24.setText(status);
                    jPanel8.setVisible(true);
                    break;
            }
            panelCount++;
        }

        if (panelCount == 1) {
            JOptionPane.showMessageDialog(this, "Tidak ada item di bookmark.");
        }
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(this, "Database driver not found: " + e.getMessage());
        e.printStackTrace();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading bookmarks: " + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) DatabaseConnection.closeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnMyBookmarksActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewItem;
    private javax.swing.JButton btnInventory;
    private javax.swing.JButton btnMyBookmarks;
    private javax.swing.JButton btnSearchItems;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JComboBox<String> txtCategoryFilter;
    private javax.swing.JTextField txtSearchField;
    // End of variables declaration//GEN-END:variables
}