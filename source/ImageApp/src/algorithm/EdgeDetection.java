package algorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;

public class EdgeDetection {
	public static BufferedImage robert(  BufferedImage image) {
		
        double[][] horizontal = new double[][]{{0,0,0}, {0,1,0}, {0,0,-1}};
        double[][] vertical = new double[][]{{0,0,0}, {0,0,1}, {0,-1,0}};   
        int length = image.getWidth();
        int height = image.getHeight();
        
        BufferedImage nImage = new BufferedImage(length , height , image.getType());
            for (int i = 0; i < length - 2; i++) {
                for (int j = 0; j < height - 2; j++) {
                    int R1=0 ;
                    int R2 = 0;
                    int G1=0 ;
                    int G2 = 0;
                    int B1=0 ;
                    int B2 = 0;
                    //looping kernal
                    for (int n = 0; n < 3; n++) {
                        for (int m = 0; m < 3; m++) {
                            Color color = new Color(image.getRGB(i + n, j + m));
                            R1 += horizontal[m][n] * color.getRed();
                            G1 += horizontal[m][n] * color.getGreen();
                            B1 += horizontal[m][n] * color.getBlue();
                        }
                    }
                    R1 = checkRange(R1);
                    G1 = checkRange(G1);
                    B1 = checkRange(B1);
                    
                    for (int n = 0; n < 3; n++) {
                        for (int m = 0; m < 3; m++) {
                            Color color = new Color(image.getRGB(i + n, j + m));
                            R2 += vertical[m][n] * color.getRed();
                            G2 += vertical[m][n] * color.getGreen();
                            B2 += vertical[m][n] * color.getBlue();
                        }
                    }
                    R2 = checkRange(R2);
                    G2 = checkRange(G2);
                    B2 = checkRange(B2);
                    
                    int nR = (int) Math.pow(Math.pow(R1, 2.0)+ Math.pow(R2, 2.0),0.5);
                    int nG = (int) Math.pow(Math.pow(G1, 2.0)+ Math.pow(G2, 2.0),0.5);
                    int nB = (int) Math.pow(Math.pow(B1, 2.0)+ Math.pow(B2, 2.0),0.5);
                    
                    nR = checkRange(nR);
                    nG = checkRange(nG);
                    nB = checkRange(nB);
                            
                    Color color = new Color(nR, nG, nB);
                    nImage.setRGB(i + 1, j + 1, color.getRGB());
                }
            }
            return nImage;
        }
	 public static BufferedImage sobel( BufferedImage image) {
		    //kernals
	        double[][] horizontal = new double[][]{{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
	        double[][] vertical = new double[][]{{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};
	        
	        int length = image.getWidth();
	        int height = image.getHeight();
	        BufferedImage nImage = new BufferedImage(length , height , image.getType());
	            for (int x = 0; x < length - 2; x++) {
	                for (int y = 0; y < height - 2; y++) {

	                    int R1=0 ,R2 = 0;
	                    int G1=0 ,G2 = 0;
	                    int B1=0 ,B2 = 0;
	                    for (int n = 0; n < 3; n++) {
	                        for (int m = 0; m < 3; m++) {
	                            Color color = new Color(image.getRGB(x + n, y + m));
	                            R1 += horizontal[m][n] * color.getRed();
	                            G1 += horizontal[m][n] * color.getGreen();
	                            B1 += horizontal[m][n] * color.getBlue();
	                        }
	                    }
	                    R1 = checkRange(R1);
	                    G1 = checkRange(G1);
	                    B1 = checkRange(B1);
	                    
	                    for (int n = 0; n < 3; n++) {
	                        for (int m = 0; m < 3; m++) {
	                            Color color = new Color(image.getRGB(x + n, y + m));
	                            R2 += vertical[m][n] * color.getRed();
	                            G2 += vertical[m][n] * color.getGreen();
	                            B2 += vertical[m][n] * color.getBlue();
	                        }
	                    }
	                    R2 = checkRange(R2);
	                    G2 = checkRange(G2);
	                    B2 = checkRange(B2);
	                    
	                    int nR = (int) Math.pow(Math.pow(R1, 2.0)+ Math.pow(R2, 2.0),0.5);
	                    int nG = (int) Math.pow(Math.pow(G1, 2.0)+ Math.pow(G2, 2.0),0.5);
	                    int nB = (int) Math.pow(Math.pow(B1, 2.0)+ Math.pow(B2, 2.0),0.5);
	                    
	                    nR = checkRange(nR);
	                    nG = checkRange(nG);
	                    nB = checkRange(nB);
	                            
	                    Color color = new Color(nR, nG, nB);
	                    nImage.setRGB(x + 1, y + 1, color.getRGB());
	                }
	            }
	            return nImage;
	        }
	 
	private static int checkRange(int newColor) {
        if (newColor > 255) {
            newColor = 255;
        } else if (newColor < 0) {
            newColor = 0;
        }
        return newColor;
    }
}
