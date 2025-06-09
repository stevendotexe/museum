package Components.Cards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchBar extends javax.swing.JPanel {
    // Variables declaration - do not modify                     
    private javax.swing.JTextField searchItem;
    private javax.swing.JLabel searchLabel;
    private List<ActionListener> searchListeners;
    // End of variables declaration                   

    private final ActionListener searchAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String searchText = searchItem.getText();
            System.out.println("Search text: " + searchText);
            // Notify all listeners
            for (ActionListener listener : searchListeners) {
                listener.actionPerformed(evt);
            }
        }
    };

    public SearchBar() {
        searchListeners = new ArrayList<>();
        initComponents();
    }

    public void addSearchListener(ActionListener listener) {
        searchListeners.add(listener);
    }

    public String getSearchText() {
        return searchItem.getText();
    }

    private void initComponents() {
        searchItem = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0, 0)); // Transparent background
        setOpaque(false);

        searchItem.setFont(new java.awt.Font("DM Sans", 0, 12)); // NOI18N
        searchItem.setText("");
        searchItem.addActionListener(searchAction);

        searchLabel.setFont(new java.awt.Font("DM Sans", 1, 12)); // NOI18N
        searchLabel.setText("Search:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, 10)
                .addComponent(searchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchItem, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap(10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel))
                .addContainerGap(20, 20))
        );
    }
}
