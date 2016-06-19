package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Haralick {

	// rgb
	int numberOfGreyValues = 255;
	// private double[][][] cooccurrenceMatrices = null;
	double[] features = new double[24];

	Haralick(BufferedImage image) {
		double[][][] cm = generateCooccurrenceMatrices(image);
		// TODO callculate features
		for (int matrix = 0; matrix < cm.length; matrix++)
			for (int i = 0; i < numberOfGreyValues; i++) {
				for (int j = 0; j < numberOfGreyValues; j++) {
					double p_ij = cm[matrix][i][j];
					features[0] += p_ij * p_ij;
				}
			}

	}

	public double[] getVector() {
		return features;
	}

	private double[][][] generateCooccurrenceMatrices(BufferedImage image) {

		double[][][] cooccurrenceMatrices = new double[4][255][255];
		final int imageWidth = image.getWidth();
		final int imageHeight = image.getHeight();
		int i, j, pos;
		for (int y = 0; y < imageHeight; y++) {
			for (int x = 0; x < imageWidth; x++) {
				pos = imageWidth * y + x;

				Color c = new Color(image.getRGB(x, y));
				int avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;

				// 0 degrees
				i = x - 1;
				if (i >= 0) {
					cooccurrenceMatrices[0][pos][pos - 1]++;
					cooccurrenceMatrices[0][pos - 1][pos]++;
				}
				// 90 degree
				j = y - imageWidth;
				if (j >= 0) {
					cooccurrenceMatrices[1][pos][pos - imageWidth]++;
					cooccurrenceMatrices[1][pos - imageWidth][pos]++;
				}
				// 45
				i = x + imageWidth;
				j = y - imageWidth;
				if (i < imageWidth && j >= 0) {
					cooccurrenceMatrices[2][pos][pos + 1 - imageWidth]++;
					cooccurrenceMatrices[2][pos + 1 - imageWidth][pos]++;

				}
				// 135
				i = x - imageWidth;
				j = y - imageWidth;
				if (i >= 0 && j >= 0) {
					cooccurrenceMatrices[3][pos][pos - 1 - imageWidth]++;
					cooccurrenceMatrices[3][pos - 1 - imageWidth][pos]++;

				}
			}
		}
		return cooccurrenceMatrices;
	}

}
