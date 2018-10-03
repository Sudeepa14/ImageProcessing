package algorithm;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Scaling {
	
	public static BufferedImage bilinearInterpolation(double size ,BufferedImage image) {
		int h=image.getHeight();
		int w=image.getWidth();
		int nW= (int) (image.getWidth()*size);
		int nH= (int) (h*size);
        BufferedImage temp = new BufferedImage(nW, nH, BufferedImage.TYPE_INT_RGB); // creating the output_image
        int A, B, C, D, x, y, blue, green, red, p;
        float x_ratio = ((float) (image.getWidth() - 1)) / nW;
        float y_ratio = ((float) (image.getHeight() - 1)) / nH;
        float x_diff, y_diff;
        for (int i = 0; i < nH; i++) {
            for (int j = 0; j < nW; j++) {
                x = (int) (x_ratio * j);
                y = (int) (y_ratio * i);
                x_diff = (x_ratio * j) - x;
                y_diff = (y_ratio * i) - y;

                // range is 0 to 255 thus bitwise AND with 0xff
                A = image.getRGB(x, y);
                B = image.getRGB(x + 1, y);
                C = image.getRGB(x, y + 1);
                D = image.getRGB(x + 1, y + 1);

                // Y = A(1-w)(1-h) + B(w)(1-h) + C(h)(1-w) + Dwh
                blue = (int) ((A & 0xff) * (1 - x_diff) * (1 - y_diff) + (B & 0xff) * (x_diff) * (1 - y_diff)
                        + (C & 0xff) * (y_diff) * (1 - x_diff) + (D & 0xff) * (x_diff * y_diff));

                green = (int) ((A >> 8 & 0xff) * (1 - x_diff) * (1 - y_diff)+ (B >> 8 & 0xff) * (x_diff) * (1 - y_diff)
                        + (C >> 8 & 0xff) * (y_diff) * (1 - x_diff) + (D >> 8 & 0xff) * (x_diff * y_diff));

                red = (int) (((A >> 16) & 0xff) * (1 - x_diff) * (1 - y_diff) + ((B >> 16) & 0xff) * (x_diff) * (1 - y_diff)
                        + ((C >> 16) & 0xff) * (y_diff) * (1 - x_diff) + ((D >> 16) & 0xff) * (x_diff * y_diff));

                int a = (A >> 24) & 0xff;
                p = (a << 24) | (red << 16) | (green << 8 | blue);

                temp.setRGB(j, i, p);
            }
        }

        return temp;

    }

	public static void linearIntepolate(int size,BufferedImage image) {
		//setting up the image
		int nWidth=image.getWidth()*size;
		int nHeight=image.getHeight()*size;
		BufferedImage createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
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
				createdImage.setRGB(i, j, image.getRGB(i/size,j/size));		
			}	
		}
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
		System.out.println("Successfully created the new image using 'Linear Inteporation' ");
	}
	public static BufferedImage nearestNeighbour(double size,BufferedImage image) throws IOException {
		//setting up the image
		int nWidth=(int) (image.getWidth()*size);
		int nHeight=(int) (image.getHeight()*size);
		BufferedImage createdImage=new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
		// n,m variables to keep track of original pixel values relevant to the new image
		int n;
		int m;
		for(int i=0;i<createdImage.getHeight();i++) {
			for (int j=0;j<createdImage.getWidth();j++) {
				//relation between the original and the new image
				n=(int) ((i-1)/size);
				m=(int) ((j-1)/size);
				//(0,y) / (x,0) of the new image is relavent to the (0,Y) / (X,0) of the original image
				if(i==0) {
					n=0;
				}
				if(j==0) {
					m=0;
				}
				createdImage.setRGB(i, j, image.getRGB(n,m));		
			}	
		}
		return createdImage;
		
	}
	public static int inteporation(int c1,int c2,int c3,int c4,int lftLen,int rhtLen,int upLen,int dwnLen ) {
		//inteporating colour (i1,j1) and (i1,j2)
		int  c5=(c1*rhtLen+c2*lftLen)/2	;
		//inteporating colour (i2,j3) and (i,j4)
		int  c6=(c3*rhtLen+c4*lftLen)/2	;
		//inteporating colour c5 and c6
		int  cNew=(c5*dwnLen+c6*upLen)/2	;
		return cNew;
	}
}

