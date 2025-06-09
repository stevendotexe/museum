package Pages.User;

import Components.Utilities.DatabaseConnection;
import Components.Utilities.ImageResizer;
import Components.Cards.NavigationBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ItemDisplay extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JPanel cardsPanel;
    private NavigationBar navigationBar;
    private static final String NO_IMAGE_URL = "https://placehold.co/400x300/CCCCCC/000000?text=No+Image";
    private static final int ITEM_CARD_TITLE_LENGTH = 45;
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private JScrollPane scrollPane;
    private int currentPage = 1;
    private static final int ITEMS_PER_PAGE = 21;
    private JPanel paginationPanel;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel pageLabel;
    private int totalItems = 0;

    public ItemDisplay() {
        setTitle("Items on Display");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add navigation bar
        navigationBar = new NavigationBar();
        mainPanel.add(navigationBar, BorderLayout.NORTH);

        // Create title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Items on Display");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // Create cards panel
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(0, 3, 15, 15));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create scroll pane
        scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(null);
        
        // Set scroll speed
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setBlockIncrement(64);
        
        // Apply custom scrollbar styling but keep it visible
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(12, 0)); // Set proper width
        verticalScrollBar.setBackground(new Color(232, 235, 242));
        verticalScrollBar.setForeground(new Color(146, 20, 12));

        // Create pagination panel
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paginationPanel.setBackground(Color.WHITE);
        paginationPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        pageLabel = new JLabel("Page 1");

        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                loadAndDisplayItems();
            }
        });

        nextButton.addActionListener(e -> {
            if (currentPage * ITEMS_PER_PAGE < totalItems) {
                currentPage++;
                loadAndDisplayItems();
            }
        });

        paginationPanel.add(prevButton);
        paginationPanel.add(pageLabel);
        paginationPanel.add(nextButton);

        // Create content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(paginationPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Set the main panel as the content pane
        setContentPane(mainPanel);

        // Load items
        loadAndDisplayItems();
    }

    private List<ItemData> fetchItemsFromDB() {
        List<ItemData> items = new ArrayList<>();
        String countQuery = "SELECT COUNT(*) FROM item WHERE is_exhibited = 1";
        String query = "SELECT item_id, item_name, category, date_discovered, image_url FROM item WHERE is_exhibited = 1 ORDER BY date_discovered DESC LIMIT ? OFFSET ?";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement countStmt = conn.createStatement();
             ResultSet countRs = countStmt.executeQuery(countQuery)) {
            
            if (countRs.next()) {
                totalItems = countRs.getInt(1);
            }

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, ITEMS_PER_PAGE);
                pstmt.setInt(2, (currentPage - 1) * ITEMS_PER_PAGE);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int itemId = rs.getInt("item_id");
                        String itemName = rs.getString("item_name");
                        String category = rs.getString("category");
                        Timestamp dateDiscovered = rs.getTimestamp("date_discovered");
                        String imageUrl = rs.getString("image_url");
                        items.add(new ItemData(itemId, itemName, category, dateDiscovered, imageUrl));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this,
                    "Error loading items: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            });
        }
        return items;
    }

    private void createItemCard(ItemData item) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xE0E0E0)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(350, 450));
        card.setMinimumSize(new Dimension(350, 450));
        card.setMaximumSize(new Dimension(350, 450));

        // Add image panel with loading placeholder
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(250, 250));
        imagePanel.setMinimumSize(new Dimension(250, 250));
        imagePanel.setMaximumSize(new Dimension(250, 250));
        imagePanel.setBackground(new Color(0xF5F5F5));
        imagePanel.setLayout(new BorderLayout());
        
        JLabel loadingLabel = new JLabel("Loading image...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        imagePanel.add(loadingLabel, BorderLayout.CENTER);
        
        card.add(imagePanel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add item details
        JLabel nameLabel = new JLabel("<html><div style='text-align: center;'>" + truncateText(item.itemName, ITEM_CARD_TITLE_LENGTH) + "</div></html>");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel categoryLabel = new JLabel(item.category);
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        categoryLabel.setForeground(new Color(0x666666));
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(categoryLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));

        // Format date
        String formattedDate = "";
        if (item.dateDiscovered != null) {
            formattedDate = String.valueOf(item.dateDiscovered.getYear() + 1900);
        }
        JLabel dateLabel = new JLabel(formattedDate);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(0x666666));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(dateLabel);

        // Add mouse listener for hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(0xF8F8F8));
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
                card.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                openItemProfile(item.itemId);
            }
        });

        // Load image asynchronously
        executorService.submit(() -> {
            try {
                if (item.imageUrl != null && !item.imageUrl.isEmpty() && !item.imageUrl.equals(NO_IMAGE_URL)) {
                    URL url = new URL(item.imageUrl);
                    BufferedImage originalImage = ImageIO.read(url);
                    if (originalImage != null) {
                        Image resizedImage = ImageResizer.resizeImage(originalImage, 250, 250);
                        ImageIcon imageIcon = new ImageIcon(resizedImage);
                        SwingUtilities.invokeLater(() -> {
                            imagePanel.removeAll();
                            JLabel imageLabel = new JLabel(imageIcon);
                            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            imagePanel.add(imageLabel, BorderLayout.CENTER);
                            imagePanel.revalidate();
                            imagePanel.repaint();
                        });
                    } else {
                        showNoImage(imagePanel);
                    }
                } else {
                    showNoImage(imagePanel);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showNoImage(imagePanel);
            }
        });

        cardsPanel.add(card);
    }

    private void showNoImage(JPanel imagePanel) {
        SwingUtilities.invokeLater(() -> {
            imagePanel.removeAll();
            JLabel noImageLabel = new JLabel("No image available", SwingConstants.CENTER);
            noImageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            imagePanel.add(noImageLabel, BorderLayout.CENTER);
            imagePanel.revalidate();
            imagePanel.repaint();
        });
    }

    private void loadAndDisplayItems() {
        // Clear existing cards
        cardsPanel.removeAll();
        cardsPanel.setLayout(new GridLayout(0, 3, 15, 15));

        // Fetch items from database
        List<ItemData> items = fetchItemsFromDB();

        // Create and add cards
        for (ItemData item : items) {
            createItemCard(item);
        }

        // Update pagination
        pageLabel.setText("Page " + currentPage);
        prevButton.setEnabled(currentPage > 1);
        nextButton.setEnabled(currentPage * ITEMS_PER_PAGE < totalItems);

        // Refresh the layout
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void openItemProfile(int itemId) {
        SwingUtilities.invokeLater(() -> {
            ItemProfile profileFrame = new ItemProfile(itemId);
            profileFrame.setVisible(true);
        });
    }

    private String truncateText(String text, int maxLength) {
        if (text == null || text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
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
        String imageUrl;

        public ItemData(int itemId, String itemName, String category, Timestamp dateDiscovered, String imageUrl) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.category = category;
            this.dateDiscovered = dateDiscovered;
            this.imageUrl = imageUrl;
        }
    }
} 