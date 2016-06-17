package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import util.Convolution2D;

public class ConcolutionColorHistogram extends ColorHistogram {

	public ConcolutionColorHistogram(BufferedImage image, int totalBins, int numberOfCells) {
		super(image, totalBins, numberOfCells);
		// TODO Auto-generated constructor stub
	}

	int[][][] createHistogram(BufferedImage image, int cellNumber, int totalCells, int totalBins, int xLenght, int yLenght){
		
		// kernel for x and y direction
		double sobelx[] = { 1, 0, -1, 2, 0, -2, 1, 0, -1 };
		double sobely[] = { 1, 2, 1, 0, 0, 0, -1, -2, -1 };
		
		double[][] resultX = Convolution2D.convolve(image, sobelx, 3, 3);
		double[][] resultY = Convolution2D.convolve(image, sobely, 3, 3);

		BufferedImage edgeImg = new BufferedImage(resultY.length, resultY[0].length, BufferedImage.TYPE_BYTE_BINARY);
		for (int x = 0; x < resultY.length; x++) {
			for (int y = 0; y < resultY[0].length; y++) {

				// calculate the magnitude
				double val = Math.hypot(resultX[x][y], resultY[x][y]);

				// don't forget the thresholding (the threshold is arbitrary).
				if (val > 200) {
					edgeImg.setRGB(x, y, Color.white.getRGB());
				}
			}
		}

		return super.createHistogram(edgeImg, cellNumber, totalCells, totalBins, xLenght, yLenght);
	}
}
