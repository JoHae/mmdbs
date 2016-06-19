/**
 * EuclideanMeasure.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package controller;

import java.util.List;

import model.ColorHistogram;
import model.Haralick;
import model.IFeature;

public class EuclideanMeasure implements ISimilarityMeasure {

	@Override
	public double calculateSimilarity(ColorHistogram histo1, ColorHistogram histo2) {
		double distance = 0;
		
		// assumption both ColorHistogram have been created with the same parameter
		for (int i = 0; i < histo1.getHistograms().size(); i++) {
			distance += calculateDistance( histo1.getHistograms().get(i),  histo2.getHistograms().get(i));
		}
		// im not sure about the devision, it makes global and local distance comparable
		return distance/histo1.getHistograms().size();
	}
	
	@Override
	public double calculateSimilarity(Haralick haralick1, Haralick haralick2) {
		return distance(haralick1.getVector(),haralick2.getVector());
	}
	
	/**
	 * Calculate the distance between two rgb color histograms
	 * each rgb bucket is take as one point and compared with the same bucket of the other array
	 * @param hist1
	 * @param hist2
	 * @return
	 */
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
	
	/**
	 * euclidean-distance between two vectors
	 */
	private double distance(double[] a, double[] b) {
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow((a[i] - b[i]),2);
        }
        return Math.sqrt(sum);
    }

	

	
	
}
