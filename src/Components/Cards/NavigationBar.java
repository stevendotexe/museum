package Components.Cards;

public class NavigationBar extends javax.swing.JPanel {

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
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButton(evt);
            }
        });

        homeButton.setBackground(new java.awt.Color(232, 235, 242));
        homeButton.setFont(new java.awt.Font("DM Sans", 0, 12)); // NOI18N
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
                                        .addComponent(homeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
    }// </editor-fold>

    private void suggestionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Suggestion button pressed");
    }

    private void homeButton(java.awt.event.ActionEvent evt) {
        System.out.println("Home button pressed");
    }


    // Variables declaration - do not modify
    private javax.swing.JButton homeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton suggestionButton;
    // End of variables declaration
}
