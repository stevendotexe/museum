package Pages.User;

import Components.Utilities.DatabaseConnection;
import Components.Cards.NavigationBar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ItemDisplay extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JPanel cardsPanel;
    private JButton refreshButton;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

    public ItemDisplay() {
        setTitle("Museum Collection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add NavigationBar at the top
        NavigationBar navigationBar = new NavigationBar();
        mainPanel.add(navigationBar, BorderLayout.NORTH);

        // Create cards panel
        cardsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create refresh button
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadAndDisplayItems());

        // Create top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Museum Collection");
        titleLabel.setFont(new Font("DM Sans", Font.BOLD, 24));
        topPanel.add(titleLabel);
        topPanel.add(refreshButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        setContentPane(mainPanel);
        loadAndDisplayItems();
    }

    private List<ItemData> fetchItemsFromDB() {
        List<ItemData> items = new ArrayList<>();
        String query = "SELECT item_id, item_name, category, date_discovered FROM item WHERE is_exhibited = 1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                String itemName = rs.getString("item_name");
                String category = rs.getString("category");
                Timestamp dateDiscovered = rs.getTimestamp("date_discovered");
                items.add(new ItemData(itemId, itemName, category, dateDiscovered));
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading items: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
        return items;
    }

    private JPanel createItemCard(ItemData itemData) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.GRAY, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(300, 150));

        String formattedDate = itemData.dateDiscovered != null ? 
            dateFormat.format(itemData.dateDiscovered) : "N/A";

        JLabel nameLabel = new JLabel("<html><b>Name:</b> " + itemData.itemName + "</html>");
        JLabel categoryLabel = new JLabel("<html><b>Category:</b> " + itemData.category + "</html>");
        JLabel dateLabel = new JLabel("<html><b>Discovered:</b> " + formattedDate + "</html>");

        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Font defaultFont = new Font("DM Sans", Font.PLAIN, 14);
        nameLabel.setFont(defaultFont);
        categoryLabel.setFont(defaultFont);
        dateLabel.setFont(defaultFont);

        card.add(nameLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(categoryLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(dateLabel);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ItemProfile profileFrame = new ItemProfile(itemData.itemId);
                profileFrame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(240, 240, 240));
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
                card.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return card;
    }

    private void loadAndDisplayItems() {
        cardsPanel.removeAll();
        List<ItemData> items = fetchItemsFromDB();

        if (items.isEmpty()) {
            JLabel noDataLabel = new JLabel("No items available for display.");
            noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardsPanel.setLayout(new BorderLayout());
            cardsPanel.add(noDataLabel, BorderLayout.CENTER);
        } else {
            cardsPanel.setLayout(new GridLayout(0, 3, 10, 10));
            for (ItemData item : items) {
                cardsPanel.add(createItemCard(item));
            }
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
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
            new ItemDisplay().setVisible(true);
        });
    }

    // Item data class
    private static class ItemData {
        int itemId;
        String itemName;
        String category;
        Timestamp dateDiscovered;

        public ItemData(int itemId, String itemName, String category, Timestamp dateDiscovered) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.category = category;
            this.dateDiscovered = dateDiscovered;
        }
    }
} 