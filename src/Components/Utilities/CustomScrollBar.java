package Components.Utilities;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;

public class CustomScrollBar {
    public static void customizeScrollBar(JScrollBar scrollBar) {
        // Set scroll bar properties
        scrollBar.setPreferredSize(new Dimension(0, 12));
        scrollBar.setBackground(new Color(232, 235, 242));
        scrollBar.setForeground(new Color(146, 20, 12));
        
        // Create custom UI
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(146, 20, 12);
                this.trackColor = new Color(232, 235, 242);
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
    }
} 