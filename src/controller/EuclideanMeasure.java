/**
 * EuclideanMeasure.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package controller;

import java.util.List;

import model.ColorHistogram;
import model.IFeature;

public class EuclideanMeasure implements ISimilarityMeasure {


	public double calculateSimilarity(ColorHistogram histo1, ColorHistogram histo2) {
		double distance = 0;
		
		// assumption both ColorHistogram have been created with the same parameter
		for (int i = 0; i < histo1.getHistograms().size(); i++) {
			distance += calculateDistance( histo1.getHistograms().get(i),  histo2.getHistograms().get(i));
		}
		// im not sure about the devision, it makes global and local distance comparable
		return distance/histo1.getHistograms().size();
	}
	
	private double calculateDistance(int[][][] hist1,int[][][] hist2) {
		double distance = 0;
		// assumption arrays are symmetric
		for (int r = 0; r < hist1.length; r++) {
			for (int g = 0; g < hist1.length; g++) {
				for (int b = 0; b < hist1.length; b++) {
					distance += Math.pow((double)(hist1[r][g][b]-hist2[r][g][b]),2); 
				}
			}
		}
		return Math.sqrt(distance);
	}

	
	
}
