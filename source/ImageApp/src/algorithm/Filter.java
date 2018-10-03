package algorithm;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Filter {
	static int[] r=new int[9];
	static int[] g=new int[9];
	static int[] b=new int[9];
	static Color[] rgb=new Color[9];
	static int rSum;
	static int gSum;
	static int bSum;
	
	private static BufferedImage createdImage;
	
	public static BufferedImage meanFilter(BufferedImage image) {
		createdImage=image;
		for(int i=1;i<image.getHeight()-1;i+=2) {
			for (int j=1;j<image.getWidth()-1;j+=2) {				
				rSum=0;gSum=0;bSum=0;
				rgb[0]= new Color(image.getRGB(i-1, j-1));
				rgb[1]=new Color(image.getRGB(i-1, j));
				rgb[2]=new Color(image.getRGB(i-1, j+1));
				rgb[3]=new Color(image.getRGB(i, j-1));
				rgb[4]=new Color(image.getRGB(i, j));
				rgb[5]=new Color(image.getRGB(i, j+1));
				rgb[6]=new Color(image.getRGB(i+1, j-1));
				rgb[7]=new Color(image.getRGB(i+1, j));
				rgb[8]=new Color(image.getRGB(i+1, j+1));
				//System.out.println(i+" "+j);
				//getting the r g b vavlues seperatly of the selected frame
				for(int l=0;l<9;l++) {
					r[l]=rgb[l].getRed();
					g[l]=rgb[l].getGreen();
					b[l]=rgb[l].getBlue();
					//System.out.println(r[l]+" "+g[l]+" "+b[l]+" "+rgb[l].getRGB());
				}
				//summing up the RGB values seperatly
				for(int m=0;m<9;m++) {
					rSum+=r[m];
					gSum+=g[m];
					bSum+=b[m];
				}
				createdImage.setRGB(i, j,(new Color((rSum/9),(gSum/9),(bSum)/9)).getRGB());
			}
		}
		return createdImage;
	}
	public static BufferedImage medianFilter(BufferedImage image) {
		createdImage =image;
		for(int i=1;i<image.getHeight()-1;i++) {
			for (int j=1;j<image.getWidth()-1;j++) {	
				rSum=0;gSum=0;bSum=0;
				rgb[0]= new Color(image.getRGB(i-1, j-1));
				rgb[1]=new Color(image.getRGB(i-1, j));
				rgb[2]=new Color(image.getRGB(i-1, j+1));
				rgb[3]=new Color(image.getRGB(i, j-1));
				rgb[4]=new Color(image.getRGB(i, j));
				rgb[5]=new Color(image.getRGB(i, j+1));
				rgb[6]=new Color(image.getRGB(i+1, j-1));
				rgb[7]=new Color(image.getRGB(i+1, j));
				rgb[8]=new Color(image.getRGB(i+1, j+1));
				//System.out.println(i+" "+j);
				//getting the r g b vavlues seperatly of the selected frame
				for(int l=0;l<9;l++) {
					r[l]=rgb[l].getRed();
					g[l]=rgb[l].getGreen();
					b[l]=rgb[l].getBlue();
				}
				//sorting the arrays
				Arrays.sort(r);
				Arrays.sort(g);
				Arrays.sort(b);
				//reconstucting image with the applied mask.
				createdImage.setRGB(i, j,(new Color(r[4],g[4],b[4]).getRGB()));
			}
		}
	
		return createdImage;
				
	}
	public static BufferedImage midPoint(BufferedImage image) {
        BufferedImage createdImage=image;
        int length = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < length - 2; x++) {
            for (int y = 0; y < height - 2; y++) {
                int[] R = new int[9];
                int[] G = new int[9];
                int[] B = new int[9];
                int counter = 0;
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        Color color = new Color(image.getRGB(x + m, y + n));
                        R[counter] = color.getRed();
                        G[counter] = color.getGreen();
                        B[counter] = color.getBlue();
                        counter++;
                    }
                }

                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                int nR = checkRange((R[0]+R[8])/2);
                int nG = checkRange((G[0]+G[8])/2);
                int nB = checkRange((B[0]+B[8])/2);
                Color nColor = new Color(nR, nG, nB);
                createdImage.setRGB(x + 1, y + 1, nColor.getRGB());

            }
        }
       return createdImage;
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

