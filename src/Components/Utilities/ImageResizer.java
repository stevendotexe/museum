package Components.Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageResizer {
    public ImageIcon toCover(URL imageUrl, int targetWidth, int targetHeight) {
        try {
            BufferedImage originalImage = ImageIO.read(imageUrl);

            if (originalImage == null) {
                System.err.println("Failed to load image from URL: " + imageUrl);
                return null;
            }

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            // 2. Calculate scaling factors for width and height
            double widthRatio = (double) targetWidth / originalWidth;
            double heightRatio = (double) targetHeight / originalHeight;

            // 3. For 'cover' behavior, we need to take the maximum ratio.
            // This ensures the image fully covers the target area,
            // even if it means parts of the image are cropped.
            double scale = Math.max(widthRatio, heightRatio);

            // 4. Calculate the dimensions of the image after scaling,
            // based on the 'cover' scale factor.
            int scaledWidth = (int) (originalWidth * scale);
            int scaledHeight = (int) (originalHeight * scale);

            // 5. Create a new BufferedImage with the target dimensions.
            // This will be our "canvas" for the resized image.
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

            // 6. Get a Graphics2D object to draw onto the new image.
            Graphics2D g2d = resizedImage.createGraphics();

            // Improve rendering quality for smoother image scaling
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 7. Calculate the offset to center the scaled image within the target area.
            // If scaledWidth > targetWidth, offsetX will be negative, causing a left crop.
            // If scaledHeight > targetHeight, offsetY will be negative, causing a top crop.
            int offsetX = (targetWidth - scaledWidth) / 2;
            int offsetY = 0;

            // 8. Draw the original image onto the new BufferedImage.
            // The image will be drawn starting at (offsetX, offsetY) with (scaledWidth, scaledHeight)
            // dimensions. Since 'resizedImage' is only 'targetWidth' x 'targetHeight',
            // any parts of the drawn image outside these bounds will be automatically clipped.
            g2d.drawImage(originalImage, offsetX, offsetY, scaledWidth, scaledHeight, null);

            // 9. Dispose of the Graphics2D object to release system resources.
            g2d.dispose();

            // 10. Return the new resized and cropped image as an ImageIcon.
            return new ImageIcon(resizedImage);

        } catch (IOException e) {
            System.err.println("Error loading or processing image from URL: " + imageUrl);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during image processing: " + imageUrl);
            e.printStackTrace();
            return null;
        }
    }

    public static Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
        if (originalImage == null) return null;

        // Calculate the new dimensions while maintaining aspect ratio
        double aspectRatio = (double) originalImage.getWidth(null) / originalImage.getHeight(null);
        int newWidth = targetWidth;
        int newHeight = targetHeight;

        if (aspectRatio > 1) {
            // Image is wider than tall
            newHeight = (int) (targetWidth / aspectRatio);
        } else {
            // Image is taller than wide
            newWidth = (int) (targetHeight * aspectRatio);
        }

        // Create a new buffered image with the calculated dimensions
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        
        // Draw the original image onto the new buffered image
        java.awt.Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, 
                            java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return resizedImage;
    }
}
