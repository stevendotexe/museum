package Components.Utilities;

import Components.Cards.Collection.RecentlyAddedCard;
import Components.Cards.Collection.RecentlyAddedContainer;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ContainerPopulator {
    
    private static String getRelativeTime(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
            LocalDateTime now = LocalDateTime.now();
            
            long days = ChronoUnit.DAYS.between(date, now);
            long months = ChronoUnit.MONTHS.between(date, now);
            long years = ChronoUnit.YEARS.between(date, now);
            
            if (years > 0) {
                return years == 1 ? "1 year ago" : years + " years ago";
            } else if (months > 0) {
                return months == 1 ? "1 month ago" : months + " months ago";
            } else if (days > 0) {
                return days == 1 ? "1 day ago" : days + " days ago";
            } else {
                long hours = ChronoUnit.HOURS.between(date, now);
                if (hours > 0) {
                    return hours == 1 ? "1 hour ago" : hours + " hours ago";
                } else {
                    long minutes = ChronoUnit.MINUTES.between(date, now);
                    return minutes <= 1 ? "just now" : minutes + " minutes ago";
                }
            }
        } catch (Exception e) {
            return dateStr; // Return original date string if parsing fails
        }
    }
    
    public static void populateRecentlyAddedContainer(RecentlyAddedContainer container, ResultSet rs) {
        try {
            List<Item> items = new ArrayList<>();
            boolean hasItems = false;
            int itemCount = 0;
            
            while (rs.next()) {
                hasItems = true;
                itemCount++;
                System.out.println("Processing item " + itemCount);
                
                int id = rs.getInt("item_id");
                String name = rs.getString("item_name");
                String imageUrl = rs.getString("image_url");
                String description = rs.getString("item_description");
                String locationFound = rs.getString("location_found");
                String category = rs.getString("category");
                String dateDiscovered = rs.getString("date_discovered");
                String dateAdded = rs.getString("created_at");
                String modifiedAt = rs.getString("modified_at");
                boolean isExhibited = rs.getBoolean("is_exhibited");
                
                items.add(new Item(id, name, imageUrl, description, locationFound, 
                                 category, dateDiscovered, dateAdded, modifiedAt, isExhibited));
            }
            System.out.println("Total items processed: " + itemCount);
            
            JPanel cardsPanel = createCardsPanel(items, hasItems);
            container.getScrollPane().setViewportView(cardsPanel);
            container.getScrollPane().setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            container.getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            
        } catch (SQLException e) {
            System.err.println("Error populating recently added container");
            e.printStackTrace();
        }
    }
    
    private static JPanel createCardsPanel(List<Item> items, boolean hasItems) {
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        cardsPanel.setPreferredSize(new Dimension(1710, 450));
        
        if (!hasItems) {
            JLabel noItemsLabel = new JLabel("No collections added yet");
            noItemsLabel.setFont(new Font("DM Sans", Font.PLAIN, 14));
            noItemsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardsPanel.add(noItemsLabel);
        } else {
            for (Item item : items) {
                String relativeTime = getRelativeTime(item.getDateAdded());
                RecentlyAddedCard card = new RecentlyAddedCard(
                    item.getName(),
                    item.getImageUrl(),
                    item.getDescription(),
                    relativeTime
                );
                card.setMaximumSize(new Dimension(200, 200));
                cardsPanel.add(card);
            }
        }
        
        return cardsPanel;
    }
} 