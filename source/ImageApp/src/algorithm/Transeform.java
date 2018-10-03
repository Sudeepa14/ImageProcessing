package algorithm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Transeform {
	
	private static File importedFile;
	private static File createdFile;
	private static BufferedImage importedImage;
	private static BufferedImage createdImage;
	private int width;
	private int height;
	private static int nWidth;
	private static int nHeight;
	//nerarest neighour algorithm
	
	public static BufferedImage nearestNeighbour(int size,BufferedImage image) throws IOException {
			//setting up the image
			nWidth=image.getWidth()*size;
			nHeight=image.getHeight()*size;
			createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
			// n,m variables to keep track of original pixel values relevant to the new image
			int n;
			int m;
			for(int i=0;i<createdImage.getHeight();i++) {
				for (int j=0;j<createdImage.getWidth();j++) {
					//relation between the original and the new image
					n=(i-1)/size;
					m=(j-1)/size;
					//(0,y) / (x,0) of the new image is relavent to the (0,Y) / (X,0) of the original image
					if(i==0) {
						n=0;
					}
					if(j==0) {
						m=0;
					}
					createdImage.setRGB(i, j, importedImage.getRGB(n,m));		
				}	
			}
			return createdImage;	
		}

		public static BufferedImage rotate(int degree,BufferedImage image) {
			nWidth=image.getWidth();
			nHeight=image.getHeight();
			createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
			//by 270
			if (degree ==3) {
			for(int i=0;i<createdImage.getHeight();i++) {
				for (int j=0;j<createdImage.getWidth();j++) {			
					createdImage.setRGB(j, i, image.getRGB(i,j));		
				}	
			}
			}
			if(degree==1) {
				nWidth=image.getHeight();
				nHeight=image.getWidth();
				//System.out.println(importedImage.getRGB(0,1999));
				createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);		
				for(int j=image.getHeight()-1;j>-1;j--) {
					for (int i=0;i<image.getWidth();i++) {	
				       createdImage.setRGB(j,i, image.getRGB(i,(image.getHeight()-1)-j));
					}
				}
			}
			return createdImage;
		}
		//for hi >
		public static  BufferedImage flip(BufferedImage image) {
			nWidth=image.getWidth();
			nHeight=image.getHeight();
			createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);		
			for(int i=0;i<createdImage.getWidth();i++) {
				for (int j=0;j<createdImage.getHeight()-1;j++) {
					createdImage.setRGB(i,j, image.getRGB(image.getWidth()-1-i,j));		
				}	
			}
			return createdImage;
		}
}
