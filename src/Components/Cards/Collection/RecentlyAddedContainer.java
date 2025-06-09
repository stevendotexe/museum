package Components.Cards.Collection;

import Components.Utilities.CustomScrollBar;
import Components.Utilities.ImageResizer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class RecentlyAddedContainer extends JPanel {
    private static final int MAX_TITLE_LENGTH = 15;
    private static final String NO_IMAGE_URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVREE8a8Z-siiy_X-r0tbjb9aDvOH7CEPcuA&s";
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private JPanel contentPanel;
    
    public RecentlyAddedContainer() {
        initComponents();
        customizeScrollBar();
    }

    private void customizeScrollBar() {
        JScrollBar horizontalScrollBar = jScrollPane1.getHorizontalScrollBar();
        CustomScrollBar.customizeScrollBar(horizontalScrollBar);
        horizontalScrollBar.setUnitIncrement(16);
        horizontalScrollBar.setBlockIncrement(100);
    }

    public void populateContainer(ResultSet rs) throws SQLException {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(new Color(232, 235, 242));
        
        List<ItemData> items = new ArrayList<>();
        
        while (rs.next()) {
            int itemId = rs.getInt("item_id");
            String itemName = rs.getString("item_name");
            String category = rs.getString("category");
            String imageUrl = rs.getString("image_url");
            items.add(new ItemData(itemId, itemName, category, imageUrl));
        }
        
        // Sort items: prioritize items with valid images
        items.sort((a, b) -> {
            boolean aHasValidImage = a.imageUrl != null && !a.imageUrl.isEmpty() && !a.imageUrl.equals(NO_IMAGE_URL);
            boolean bHasValidImage = b.imageUrl != null && !b.imageUrl.isEmpty() && !b.imageUrl.equals(NO_IMAGE_URL);
            return Boolean.compare(bHasValidImage, aHasValidImage);
        });
        
        for (ItemData item : items) {
            contentPanel.add(createItemCard(item));
            contentPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        
        jScrollPane1.setViewportView(contentPanel);
    }

    private JPanel createItemCard(ItemData itemData) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.GRAY, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(200, 250));

        // Truncate title to 15 characters
        String truncatedName = truncateText(itemData.itemName, MAX_TITLE_LENGTH);
        JLabel nameLabel = new JLabel("<html><b>Name:</b> " + truncatedName + "</html>");
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
        if (itemData.imageUrl != null && !itemData.imageUrl.isEmpty() && !itemData.imageUrl.equals(NO_IMAGE_URL)) {
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
            Image image = ImageIO.read(new URL(imageUrl));
            if (image != null) {
                Image resizedImage = ImageResizer.resizeImage(image, 150, 150);
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

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setBorder(null);
        
        setLayout(new BorderLayout());
        add(jScrollPane1, BorderLayout.CENTER);
    }

    public JScrollPane getScrollPane() {
        return jScrollPane1;
    }

    private JScrollPane jScrollPane1;

    private static class ItemData {
        int itemId;
        String itemName;
        String category;
        String imageUrl;

        public ItemData(int itemId, String itemName, String category, String imageUrl) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.category = category;
            this.imageUrl = imageUrl;
        }
    }
}