/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Utility class for various common functionalities.
 *
 * @author risha
 */
public class BDUtility {

    // Method to set a background image for a JFrame
    public static void setImage(JFrame frame, String imagePath, int newWidth, int newHeight) {
        try {
            // Load the image from the resources
            BufferedImage originalImage = ImageIO.read(BDUtility.class.getResourceAsStream(imagePath));
            
            if (originalImage == null) {
                throw new IllegalArgumentException("Image not found: " + imagePath);
            }

            // Resize the image to fit the given dimensions
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon backgroundImage = new ImageIcon(scaledImage);

            // Set the background image on a JLabel
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setBounds(0, 0, newWidth, newHeight);

            // Add the JLabel to the frame
            frame.getContentPane().add(backgroundLabel);
            frame.getContentPane().setLayout(null); // Ensure absolute positioning

            frame.validate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static HashMap<String, JFrame> formsMap = new HashMap<>();

    // Method to open a form (JFrame) and manage its visibility
    public static void openForm(String formName, JFrame formInstance) {
        JFrame existingForm = formsMap.get(formName);
        if (existingForm == null || !existingForm.isVisible()) {
            formsMap.put(formName, formInstance);
            formInstance.setVisible(true);
        } else {
            existingForm.toFront();
        }
    }

    // Method to get the absolute path of a file in the project
    public static String getPath(String finalPath) {
        String projectPath = System.getProperty("user.dir");
        return projectPath + File.separator + "src" + finalPath;  // Use File.separator for portability
    }

    // Method to get the file extension from a file name
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    // Method to get the file extension from a File object
    public static String getFileExtension(File selectedFile) {
        if (selectedFile != null) {
            return getFileExtension(selectedFile.getName());  // Reuse the first method for extracting the extension
        }
        return "";
    }

    // Method to scale an image to match the size of another image
    public static BufferedImage scaleImage(BufferedImage originalImage, BufferedImage selectedImage) {
        int width = selectedImage.getWidth();
        int height = selectedImage.getHeight();
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();
        return scaledImage;
    }
}
