package com.app;

import Pages.Login.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.util.Stack;

public class Main {
    private static Stack<JFrame> windowStack = new Stack<>();
    private static JFrame currentWindow;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Start with the login window
            showWindow(new LoginFrame());
        });
    }

    public static void showWindow(JFrame newWindow) {
        if (currentWindow != null) {
            // Hide the current window instead of disposing it
            currentWindow.setVisible(false);
        }
        
        // Set up the new window
        newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newWindow.setVisible(true);
        
        // Add to stack and update current window
        windowStack.push(newWindow);
        currentWindow = newWindow;
    }

    public static void goBack() {
        if (!windowStack.isEmpty()) {
            // Hide current window
            currentWindow.setVisible(false);
            windowStack.pop(); // Remove current window from stack
            
            if (!windowStack.isEmpty()) {
                // Show previous window
                currentWindow = windowStack.peek();
                currentWindow.setVisible(true);
            }
        }
    }

    public static void clearStack() {
        while (!windowStack.isEmpty()) {
            JFrame window = windowStack.pop();
            window.dispose();
        }
        currentWindow = null;
    }
}
