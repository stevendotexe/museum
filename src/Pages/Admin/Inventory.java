package Pages.Admin;

import Components.Utilities.DatabaseConnection;
import Components.Cards.NavigationBar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Inventory extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

    public Inventory() {
        setTitle("Inventory Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // Create main panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add NavigationBar at the top
        NavigationBar navigationBar = new NavigationBar();
        mainPanel.add(navigationBar, BorderLayout.NORTH);

        // Create table model
        String[] columns = {"ID", "Name", "Category", "Location", "Date Discovered", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create table
        inventoryTable = new JTable(tableModel);
        inventoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        refreshButton = new JButton("Refresh");
        addButton = new JButton("Add Item");
        editButton = new JButton("Edit Item");
        deleteButton = new JButton("Delete Item");

        refreshButton.addActionListener(e -> loadInventoryData());
        addButton.addActionListener(e -> showAddItemDialog());
        editButton.addActionListener(e -> showEditItemDialog());
        deleteButton.addActionListener(e -> deleteSelectedItem());

        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
        loadInventoryData();
    }

    private void loadInventoryData() {
        tableModel.setRowCount(0);
        String query = "SELECT * FROM item ORDER BY item_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getString("location_found"),
                    rs.getTimestamp("date_discovered") != null ? 
                        dateFormat.format(rs.getTimestamp("date_discovered")) : "N/A",
                    rs.getBoolean("is_exhibited") ? "Exhibited" : "Stored"
                };
                tableModel.addRow(row);
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading inventory data: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAddItemDialog() {
        // TODO: Implement add item dialog
        JOptionPane.showMessageDialog(this,
            "Add Item functionality to be implemented",
            "Coming Soon",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void showEditItemDialog() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select an item to edit",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // TODO: Implement edit item dialog
        JOptionPane.showMessageDialog(this,
            "Edit Item functionality to be implemented",
            "Coming Soon",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteSelectedItem() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select an item to delete",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int itemId = (int) tableModel.getValueAt(selectedRow, 0);
        String itemName = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete " + itemName + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM item WHERE item_id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setInt(1, itemId);
                pstmt.executeUpdate();
                loadInventoryData();

                JOptionPane.showMessageDialog(this,
                    "Item deleted successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this,
                    "Error deleting item: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            }
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
            new Inventory().setVisible(true);
        });
    }
} 