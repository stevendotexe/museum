package Pages.Admin;

import Components.Utilities.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
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
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Inventory() {
        setTitle("Inventory Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // Create main panel with padding
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create header panel with back button
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            this.dispose();
        });
        headerPanel.add(backButton, BorderLayout.WEST);
        
        JLabel headerLabel = new JLabel("Inventory");
        headerLabel.setFont(new Font("DM Sans", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create content panel to hold search and table
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        
        // Create search panel
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        });
        
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        contentPanel.add(searchPanel, BorderLayout.NORTH);

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
        
        // Set up the sorter
        sorter = new TableRowSorter<>(tableModel);
        inventoryTable.setRowSorter(sorter);
        
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);

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

    private void filter() {
        String searchText = searchField.getText().toLowerCase();
        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

    private void loadInventoryData() {
        tableModel.setRowCount(0);
        String query = "SELECT item_id, item_name, category, location_found, " +
                      "DATE_FORMAT(date_discovered, '%Y-%m-%d %H:%i:%s') as formatted_date, " +
                      "is_exhibited FROM item ORDER BY item_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getString("location_found"),
                    rs.getString("formatted_date") != null ? rs.getString("formatted_date") : "N/A",
                    rs.getBoolean("is_exhibited") ? "Exhibited" : "Stored"
                };
                tableModel.addRow(row);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error loading inventory data: " + e.getMessage());
            e.printStackTrace();
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

        int itemId = (int) tableModel.getValueAt(selectedRow, 0);
        EditItem editDialog = new EditItem(this, itemId);
        editDialog.setVisible(true);
        loadInventoryData(); // Refresh the table after editing
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