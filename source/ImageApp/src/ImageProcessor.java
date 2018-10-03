import gui.App;
import gui.ImportImage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.text.rtf.RTFEditorKit;

import org.w3c.dom.css.RGBColor;

import algorithm.Convert;
import algorithm.EdgeDetection;
import algorithm.Filter;
public class ImageProcessor {
	private static File importedFile;
	private static File createdFile;
	private static BufferedImage importedImage;
	private static BufferedImage createdImage;
	private int width;
	private int height;
	private static int nWidth;
	private static int nHeight;
	
	
	public static void main(String arg[]){

		//starting the gui
		App app=new App();
		app.setVisible(true);
	}
	//importing
	public static void importImage() {
		
		importedFile= new File("C:/Users/Nadeeshan/Pictures/Lenna.jpg");
		//validating the result
		System.out.println( " this is the file path" + importedFile.getAbsolutePath() );
		//getting the image from the imported file
				try {
					importedImage=ImageIO.read(importedFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("ERROR couldn't convert to an image");
					e.printStackTrace();
				}
		System.out.println( " pixel height" + importedImage.getHeight() );
		System.out.println( " pixel width " + importedImage.getWidth() );
	
		
		
	}
	//exporting
	public static void exportImage(String name) {
		createdFile= new File(name);
		//System.out.println("Successfully exported '"+name+".jpg' ");
		try {
			ImageIO.write(createdImage, "jpg", createdFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//check the colour range
	private static int checkRange(int newColor) {
        if (newColor > 255) {
            newColor = 255;
        } else if (newColor < 0) {
            newColor = 0;
        }
        return newColor;
    }
	
	////////////////////////////////////////////////////////////////////////////////Algorithms////////////////////////////////////////////////////////////////////////////////////////////
	
	//nerarest neighour algorithm
	public static void nearestNeighbour(int size,BufferedImage image) throws IOException {
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
		exportImage("nearestNeighbour");
		System.out.println("Successfully created the new image using 'Nearest Neighbour' ");	
	}

	public static BufferedImage rotate(int degree,BufferedImage image) {
		nWidth=image.getWidth();
		nHeight=image.getHeight();
		createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
		//by 270
		if (degree ==3) {
		for(int i=0;i<createdImage.getHeight();i++) {
			for (int j=0;j<createdImage.getWidth();j++) {			
				createdImage.setRGB(j, i, importedImage.getRGB(i,j));		
			}	
		}
		}
		if(degree==5) {
			nWidth=image.getHeight();
			nHeight=image.getWidth();
			createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
//			createdImage.setRGB(0, 511, 4096000);		
			for(int i=0;i<createdImage.getWidth()+1;i++) {
				for (int j=0;j<createdImage.getHeight()-1;j++) {
					System.out.println(importedImage.getHeight()-i+" "+j);
//					System.out.println(i);
					createdImage.setRGB(i,j, importedImage.getRGB(importedImage.getHeight()-i,j));
					
				}	
			}
		}
		if(degree==1) {
			nWidth=image.getHeight();
			nHeight=image.getWidth();
			//System.out.println(importedImage.getRGB(0,1999));
			createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);		
			for(int j=importedImage.getHeight()-1;j>-1;j--) {
				for (int i=0;i<importedImage.getWidth();i++) {	
					//System.out.println( i+" "+j+" "+i+"    "+((importedImage.getHeight()-1)-j));
					//System.out.println(importedImage.getRGB(i,(importedImage.getHeight()-1)	-j));
					createdImage.setRGB(j,i, image.getRGB(i,(importedImage.getHeight()-1)-j));
				}
			}
		}
		if(degree==2) {
			
		}
		
		return createdImage;
	}
	//for hi >
	public static  BufferedImage flip() {
		nWidth=importedImage.getWidth();
		nHeight=importedImage.getHeight();
		createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
//		createdImage.setRGB(0, 511, 4096000);		
		for(int i=0;i<createdImage.getWidth();i++) {
			for (int j=0;j<createdImage.getHeight()-1;j++) {
				
				System.out.println(importedImage.getWidth()-1-i+" "+j);
				createdImage.setRGB(i,j, importedImage.getRGB(importedImage.getWidth()-1-i,j));		
			}	
		}
		return createdImage;
	}
	
	//interporation way one
	public static void linearIntepolate(int size,BufferedImage image) {
		//setting up the image
		nWidth=image.getWidth()*size;
		nHeight=image.getHeight()*size;
		createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
		// n,m variables to keep track of original pixel values relevant to the new image
		int n;
		int m;
		int[] r=new int[4];
		int[] g=new int[4];
		int[] b=new int[4];
		Color[] rgb=new Color[4];
		
		////getting the image to inteporate linearly
		for(int i=0;i<createdImage.getHeight();i+=size) {
			for (int j=0;j<createdImage.getWidth();j+=size) {			
				createdImage.setRGB(i, j, importedImage.getRGB(i/size,j/size));		
			}	
		}
		exportImage("tLineraInterporation");
		System.out.println("Successfully created the new image using 'Linear Inteporation' ");
		int iRmdr;
		int jRmdr;
		int iQutn;
		int jQutn;
		//linear ineteporating the image
	
		for(int i=0;i<createdImage.getHeight()-1;i++) {
			iRmdr=i%size;
			iQutn=i/size;
			for (int j=0;j<createdImage.getWidth()-1;j++) {
				int lftLen=0;			
				int rhtLen=size;
				int upLen=0;
				int dwnLen=size;
				jRmdr=j%size;
				jQutn=j/size;
				
				int cR;
				int cG;
				int cB;
				System.out.println(i+" "+j+" "+createdImage.getRGB(i, j));
				if (iRmdr!=0 & jRmdr!=0) {
					//System.out.println(i+" "+j+" "+createdImage.getRGB(i, j));
					lftLen=iRmdr;
					rhtLen=size-iRmdr;
					upLen=jRmdr;
					dwnLen=size-jRmdr;	
					rgb[0]=new Color(createdImage.getRGB(iQutn, jQutn));
					rgb[1]=new Color(createdImage.getRGB(iQutn+size, jQutn));
					rgb[2]=new Color(createdImage.getRGB(iQutn, jQutn+size));
					rgb[3]=new Color(createdImage.getRGB(iQutn+size, jQutn+size));
					for(int p=0;p<4;p++) {
						r[p]=rgb[p].getRed();
						g[p]=rgb[p].getGreen();
						b[p]=rgb[p].getBlue();
					}
					cR=inteporation(r[0], r[1], r[2],r[3], lftLen, rhtLen, upLen, dwnLen);
					cG=inteporation(g[0], g[1], g[2],g[3], lftLen, rhtLen, upLen, dwnLen);
					cB=inteporation(b[0], b[1], b[2],b[3], lftLen, rhtLen, upLen, dwnLen);
					//System.out.println(cR+ " "+cG+" "+cB);
					createdImage.setRGB(i, j, new Color(cR,cG,cB).getRGB());
				}
				if (iRmdr!=0 & jRmdr==0) {
					lftLen=iRmdr;
					rhtLen=size-iRmdr;
					rgb[0]=new Color(createdImage.getRGB(iQutn, jQutn));
					rgb[1]=new Color(createdImage.getRGB(iQutn+size, jQutn));
					
					for(int p=0;p<2;p++) {
						r[p]=(rgb[p].getRed())/2;
						g[p]=(rgb[p].getGreen())/2;
						b[p]=(rgb[p].getBlue())/2;
					}
					cR=r[0]*rhtLen+g[1]*lftLen;
					cG=g[0]*rhtLen+g[1]*lftLen;
					cB=g[0]*rhtLen+g[1]*lftLen;
					createdImage.setRGB(i, j, new Color(cR,cG,cB).getRGB());
				}
				if (iRmdr==0 & jRmdr!=0) {
					//System.out.println(i+" "+j+" "+createdImage.getRGB(i, j));
					upLen=jRmdr;
					dwnLen=size-jRmdr;
					rgb[0]=new Color(createdImage.getRGB(iQutn, jQutn));
					//System.out.println(jQutn+size);
					rgb[1]=new Color(createdImage.getRGB(iQutn, jQutn+size));
					
					for(int p=0;p<2;p++) {
						r[p]=rgb[p].getRed();
						g[p]=rgb[p].getGreen();
						b[p]=rgb[p].getBlue();
					}
					cR=(r[0]*dwnLen+g[1]*upLen)/2;
					cG=(g[0]*dwnLen+g[1]*upLen)/2;
					cB=(g[0]*dwnLen+g[1]*upLen)/2	;
					//System.out.println(cR+ " "+cG+" "+cB);
					createdImage.setRGB(i, j, new Color(cR,cG,cB).getRGB());
				}
			}	
		}
		exportImage("LineraInterporation");
		System.out.println("Successfully created the new image using 'Linear Inteporation' ");
	}	
		
	//inteporation function
	public static int inteporation(int c1,int c2,int c3,int c4,int lftLen,int rhtLen,int upLen,int dwnLen ) {
		//inteporating colour (i1,j1) and (i1,j2)
		int  c5=(c1*rhtLen+c2*lftLen)/2	;
		//inteporating colour (i2,j3) and (i,j4)
		int  c6=(c3*rhtLen+c4*lftLen)/2	;
		//inteporating colour c5 and c6
		int  cNew=(c5*dwnLen+c6*upLen)/2	;
		return cNew;
	}
//	public static int checkColorRang(){
//		
//	}
	
	
}
