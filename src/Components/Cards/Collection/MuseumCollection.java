package Components.Cards.Collection;

import Components.Utilities.CustomScrollBar;
import Components.Utilities.DatabaseConnection;
import Components.Utilities.ImageResizer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class MuseumCollection extends javax.swing.JPanel {
    private static final int MAX_TITLE_LENGTH = 15;
    private static final String NO_IMAGE_URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVREE8a8Z-siiy_X-r0tbjb9aDvOH7CEPcuA&s";
    private static final int ITEMS_PER_PAGE = 9; // 3x3 grid
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private JPanel contentPanel;
    private JPanel paginationPanel;
    private int currentPage = 1;
    private int totalPages = 1;
    private List<ItemData> allItems = new ArrayList<>();
    
    /**
     * Creates new form MuseumCollection
     */
    public MuseumCollection() {
        initComponents();
        customizeScrollBar();
        loadItems();
    }

    private void customizeScrollBar() {
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        CustomScrollBar.customizeScrollBar(verticalScrollBar);
        
        // Update scroll pane policies
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void loadItems() {
        contentPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        contentPanel.setBackground(new Color(232, 235, 242));
        
        // Create pagination panel
        paginationPanel = new JPanel();
        paginationPanel.setBackground(new Color(232, 235, 242));
        paginationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        // Create main container with BorderLayout
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(new Color(232, 235, 242));
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        mainContainer.add(paginationPanel, BorderLayout.SOUTH);
        
        jScrollPane1.setViewportView(mainContainer);

        executorService.submit(() -> {
            allItems = fetchItemsFromDB();
            SwingUtilities.invokeLater(() -> {
                totalPages = (int) Math.ceil((double) allItems.size() / ITEMS_PER_PAGE);
                displayItems(allItems);
                updatePagination();
            });
        });
    }

    private List<ItemData> fetchItemsFromDB() {
        List<ItemData> items = new ArrayList<>();
        String query = "SELECT item_id, item_name, category, date_discovered, image_url FROM item WHERE is_exhibited = 1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                String itemName = rs.getString("item_name");
                String category = rs.getString("category");
                Timestamp dateDiscovered = rs.getTimestamp("date_discovered");
                String imageUrl = rs.getString("image_url");
                items.add(new ItemData(itemId, itemName, category, dateDiscovered, imageUrl));
            }
        } catch (SQLException | ClassNotFoundException e) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this,
                    "Error loading items: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            });
        }
        return items;
    }

    private void displayItems(List<ItemData> items) {
        contentPanel.removeAll();
        contentPanel.setLayout(new GridLayout(0, 3, 10, 10));
        
        // Sort items: prioritize items with valid images
        items.sort((a, b) -> {
            boolean aHasValidImage = a.imageUrl != null && !a.imageUrl.isEmpty() && !a.imageUrl.equals(NO_IMAGE_URL);
            boolean bHasValidImage = b.imageUrl != null && !b.imageUrl.isEmpty() && !b.imageUrl.equals(NO_IMAGE_URL);
            return Boolean.compare(bHasValidImage, aHasValidImage);
        });
        
        if (items.isEmpty()) {
            JLabel noDataLabel = new JLabel("No items available for display.");
            noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(noDataLabel, BorderLayout.CENTER);
        } else {
            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, items.size());
            
            for (int i = startIndex; i < endIndex; i++) {
                contentPanel.add(createItemCard(items.get(i)));
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void updatePagination() {
        paginationPanel.removeAll();
        
        // Previous button
        JButton prevButton = new JButton("Previous");
        prevButton.setEnabled(currentPage > 1);
        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                displayItems(allItems);
                updatePagination();
            }
        });
        
        // Page numbers
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(totalPages, startPage + 4);
        
        if (endPage - startPage < 4) {
            startPage = Math.max(1, endPage - 4);
        }
        
        for (int i = startPage; i <= endPage; i++) {
            final int pageNum = i;
            JButton pageButton = new JButton(String.valueOf(i));
            pageButton.setPreferredSize(new Dimension(40, 30));
            pageButton.setBackground(pageNum == currentPage ? new Color(0, 123, 255) : Color.WHITE);
            pageButton.setForeground(pageNum == currentPage ? Color.WHITE : Color.BLACK);
            pageButton.addActionListener(e -> {
                currentPage = pageNum;
                displayItems(allItems);
                updatePagination();
            });
            paginationPanel.add(pageButton);
        }
        
        // Next button
        JButton nextButton = new JButton("Next");
        nextButton.setEnabled(currentPage < totalPages);
        nextButton.addActionListener(e -> {
            if (currentPage < totalPages) {
                currentPage++;
                displayItems(allItems);
                updatePagination();
            }
        });
        
        // Add buttons to pagination panel
        paginationPanel.add(prevButton);
        paginationPanel.add(nextButton);
        
        // Add page info
        JLabel pageInfo = new JLabel(String.format("Page %d of %d", currentPage, totalPages));
        paginationPanel.add(pageInfo);
        
        paginationPanel.revalidate();
        paginationPanel.repaint();
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

        JLabel nameLabel = new JLabel("<html><b>Name:</b> " + truncateText(itemData.itemName, MAX_TITLE_LENGTH) + "</html>");
        JLabel categoryLabel = new JLabel("<html><b>Category:</b> " + itemData.category + "</html>");

        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Font defaultFont = new Font("DM Sans", Font.PLAIN, 14);
        nameLabel.setFont(defaultFont);
        categoryLabel.setFont(defaultFont);

        card.add(nameLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(categoryLabel);

        // Load image in background if available
        if (itemData.imageUrl != null && !itemData.imageUrl.isEmpty()) {
            executorService.submit(() -> loadItemImage(card, itemData.imageUrl));
        }

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new Pages.User.ItemProfile(itemData.itemId).setVisible(true);
                });
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

    private void loadItemImage(JPanel card, String imageUrl) {
        try {
            // Skip loading if it's the no-image URL
            if (imageUrl == null || imageUrl.isEmpty() || imageUrl.equals(NO_IMAGE_URL)) {
                return;
            }
            
            Image image = ImageIO.read(new java.net.URL(imageUrl));
            if (image != null) {
                Image resizedImage = ImageResizer.resizeImage(image, 100, 100);
                JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
                imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                
                SwingUtilities.invokeLater(() -> {
                    card.add(Box.createRigidArea(new Dimension(0, 5)));
                    card.add(imageLabel);
                    card.revalidate();
                    card.repaint();
                });
            }
        } catch (Exception e) {
            // Image loading failed, continue without image
        }
    }

    private String truncateText(String text, int maxLength) {
        if (text == null || text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        
        setBackground(new java.awt.Color(232, 235, 242));
        
        jScrollPane1.setBorder(null);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration

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