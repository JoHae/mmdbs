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

import controller.ISimilarityMeasure;

public class ColorHistogram implements IFeature {

	List<int[][][]> histograms = new ArrayList<int[][][]>();
	private ISimilarityMeasure measure;

	public ColorHistogram(BufferedImage image, int totalBins, int numberOfCells) {

		int cellHeight, cellWidth;
		if (numberOfCells == 1) {
			cellHeight = image.getHeight();
			cellWidth = image.getWidth();
		} else {
			// if the image has an uneven height/width one pixel row/cell is
			// ignored
			cellHeight = (int) Math.ceil(image.getHeight() / (numberOfCells / 2));
			cellWidth = (int) Math.ceil(image.getWidth() / (numberOfCells / 2));
		}

		// create histograms, one per cell
		for (int i = 0; i < numberOfCells; i++) {
			histograms.add(createHistogram(image,i, numberOfCells, totalBins, cellWidth, cellHeight));
		}

	}
	
	int[][][]  createHistogram(BufferedImage image, int cellNumber, int totalCells, int totalBins, int xLenght, int yLenght){
		
		// cell layout 
		// [0][1]
		// [2][3]
		// Calculate the starting points for this cell
		int numberOfRowsColumns;
		if(totalCells == 1){
			numberOfRowsColumns = 1;
		}else{
			numberOfRowsColumns = (totalCells/2);
		}
		
		int row = (cellNumber / numberOfRowsColumns);
		int col = cellNumber - (row*numberOfRowsColumns);
		//System.out.println("Row: "+ row + " col:" + col);
		int startX = row*xLenght;
		int startY = col*yLenght;
		
		int range = (int) Math.ceil((double)256/(double)totalBins);
		System.out.println(range);
		// totalBins is actually to big, but it has no negative effect
		int[][][] bin = new  int[totalBins][totalBins][totalBins];
		//int binrange = totalBins*totalBins*totalBins;
		for (int x = startX; x < startX+xLenght; x++) {
			for (int y = startY; y < startY+yLenght; y++) {
				int color = image.getRGB(x, y);

				// int alpha = (color & 0xff000000) >> 24;
				int red = (color & 0x00ff0000) >> 16;
				int green = (color & 0x0000ff00) >> 8;
				int blue = color & 0x000000ff;
			 
				bin[red / range][green / range][blue / range]++;
			}

		}
		return bin;
	}
	

	public List<int[][][]> getHistograms() {
		return histograms;
	}

	@Override
	public double calculateSimilarity(IFeature f) {
		if (measure != null) {
			return this.measure.calculateSimilarity(this, (ColorHistogram) f);
		} else {
			System.err.println("Call setMeasure first");
			return 0;
		}

	}

	@Override
	public IFeature setMeasure(ISimilarityMeasure measure) {
		this.measure = measure;
		return this;
	}
}
