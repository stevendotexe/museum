package Components.Cards;

import Pages.User.HomePage;
import Pages.User.ItemDisplay;
import Pages.User.MuseumCollection;
import Pages.User.SuggestionForm;
import javax.swing.SwingUtilities;

public class NavigationBar extends javax.swing.JPanel {
    private javax.swing.JButton displayedButton;

    /**
     * Creates new form NavigationBar
     */
    public NavigationBar() {
        initComponents();
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        homeButton = new javax.swing.JButton();
        suggestionButton = new javax.swing.JButton();
        collectionsButton = new javax.swing.JButton();
        displayedButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(146, 20, 12));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(146, 20, 12));
        jTextField1.setFont(new java.awt.Font("DM Sans", 1, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("Museum Name");
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(146, 20, 12)); // Match background color to hide cursor
        jTextField1.setFocusable(false); // Prevent focus
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Show hand cursor
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButton(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeButton(null);
            }
        });

        homeButton.setBackground(new java.awt.Color(232, 235, 242));
        homeButton.setFont(new java.awt.Font("DM Sans", 0, 12));
        homeButton.setText("Home");
        homeButton.setBorder(null);
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButton(evt);
            }
        });

        suggestionButton.setBackground(new java.awt.Color(232, 235, 242));
        suggestionButton.setFont(new java.awt.Font("DM Sans", 0, 12)); // NOI18N
        suggestionButton.setText("Suggestion");
        suggestionButton.setBorder(null);
        suggestionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suggestionButtonActionPerformed(evt);
            }
        });

        collectionsButton.setBackground(new java.awt.Color(232, 235, 242));
        collectionsButton.setFont(new java.awt.Font("DM Sans", 0, 12));
        collectionsButton.setText("Collections");
        collectionsButton.setBorder(null);
        collectionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectionsButtonActionPerformed(evt);
            }
        });

        displayedButton.setBackground(new java.awt.Color(232, 235, 242));
        displayedButton.setFont(new java.awt.Font("DM Sans", 0, 12));
        displayedButton.setText("Displayed");
        displayedButton.setBorder(null);
        displayedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(30, 30)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                    .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(collectionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(displayedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(suggestionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(suggestionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(collectionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(homeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
    }

    public void addDisplayedButton() {
        displayedButton = new javax.swing.JButton();
        displayedButton.setBackground(new java.awt.Color(232, 235, 242));
        displayedButton.setFont(new java.awt.Font("DM Sans", 0, 12));
        displayedButton.setText("Displayed");
        displayedButton.setBorder(null);
        displayedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayedButtonActionPerformed(evt);
            }
        });

        // Remove the old layout
        this.removeAll();
        
        // Create new layout with the displayed button
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(30, 30)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                    .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(collectionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(displayedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(suggestionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(suggestionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(collectionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(homeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
    }

    private void displayedButtonActionPerformed(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(() -> {
            try {
                java.awt.Window currentWindow = SwingUtilities.getWindowAncestor(this);
                
                // If we're already on the ItemDisplay page, do nothing
                if (currentWindow instanceof ItemDisplay) {
                    return;
                }
                
                // Close current window and open ItemDisplay
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                new ItemDisplay().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error opening Items Display page: " + e.getMessage(), 
                    "Navigation Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void suggestionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(() -> {
            try {
                java.awt.Window currentWindow = SwingUtilities.getWindowAncestor(this);
                
                // If we're already on the SuggestionForm page, do nothing
                if (currentWindow instanceof SuggestionForm) {
                    return;
                }
                
                // Close current window and open SuggestionForm
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                new SuggestionForm().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error opening Suggestion page: " + e.getMessage(), 
                    "Navigation Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void homeButton(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(() -> {
            try {
                java.awt.Window currentWindow = SwingUtilities.getWindowAncestor(this);
                
                // If we're already on the HomePage, do nothing
                if (currentWindow instanceof HomePage) {
                    return;
                }
                
                // Close current window and open HomePage
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                new HomePage().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error opening Home page: " + e.getMessage(), 
                    "Navigation Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void collectionsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(() -> {
            try {
                java.awt.Window currentWindow = SwingUtilities.getWindowAncestor(this);
                
                // If we're already on the MuseumCollection page, do nothing
                if (currentWindow instanceof MuseumCollection) {
                    return;
                }
                
                // Close current window and open MuseumCollection
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                new MuseumCollection().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error opening Collections page: " + e.getMessage(), 
                    "Navigation Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton homeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton suggestionButton;
    private javax.swing.JButton collectionsButton;
    // End of variables declaration
}
