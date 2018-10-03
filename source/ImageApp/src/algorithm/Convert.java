package algorithm;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convert {

public static BufferedImage negative(BufferedImage image) {
		BufferedImage createdImage= image;
        int length = image.getWidth();
        int height = image.getHeight();
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                int R;
                int G;
                int B;
                R = checkRange(255 - color.getRed());
                G = checkRange(255 - color.getGreen());
                B = checkRange(255 - color.getBlue());
                color = new Color(R, G, B);
                image.setRGB(i, j, color.getRGB());
            }
        }
        return createdImage;
    }
public static BufferedImage grayscaling(BufferedImage image) {
    BufferedImage createdImage = image;
    int length = image.getWidth();
    int height = image.getHeight();

    for (int i = 0; i < length; i++) {
        for (int j = 0; j < height; j++) {
            int rgb = image.getRGB(i, j);
            
            int A = (rgb >> 24) & 0xFF;
            int R = (rgb >> 16) & 0xFF;
            int G = (rgb >> 8) & 0xFF;
            int B = (rgb & 0xFF);
            int gray = (R + G + B) / 3;
            rgb = (A << 24) | (gray << 16) | (gray << 8) | gray;
            image.setRGB(i, j, rgb);
        }
    }
   return createdImage;
}

private static int checkRange(int newColor) {
    if (newColor < 0) {
        newColor = 0;
    } else if (newColor > 255) {
        newColor = 255;
    }
    return newColor;
}
}