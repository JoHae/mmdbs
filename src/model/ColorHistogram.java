/**
 * ColorHistogram.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ColorHistogram implements Feature {

	
	List<int[][][]> histograms = new ArrayList<int[][][]>();
	public ColorHistogram(BufferedImage image,int bins ,Boolean local) {
		// TODO Auto-generated constructor stub
		if(local == false){
			 int[][][] ch = new int[bins/3][bins/3][bins/3];
			 for(int x = 0; x < image.getWidth(); x++){
		            for(int y = 0; y < image.getHeight(); y++) {
		                int color = image.getRGB(x, y);
		               // int alpha = (color & 0xff000000) >> 24;
		                int red = (color & 0x00ff0000) >> 16;
		                int green = (color & 0x0000ff00) >> 8;
		                int blue = color & 0x000000ff;
		                ch[red / bins][green / bins][blue / bins]++;
		            }
			 }
			 histograms.add(ch);
		}
	}
	
	public List<int[][][]> getHistograms(){
		return histograms;
	}
}
