package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import controller.ISimilarityMeasure;

public class Haralick implements IFeature {
	
	// as recomended in the paper, because some probabilities can be 0
	// and ln(o) is undefined, page 619 sidenote 2
	double entrophy = 0.000000000000001;

	public class statistics {
		// row variance
		public double var_x = 0;
		// column variance
		public double var_y = 0;
		// row mean value
		public double mu_x = 0;
		// column mean value
		public double mu_y = 0;
		// HXY1
		public double hx = 0;
		// HXY2
		public double hy = 0;
		// HXY1
		public double hxy1 = 0;
		// HXY2
		public double hxy2 = 0;
		// p_x
		public final double[] p_x = new double[255];
		// p_y
		public final double[] p_y = new double[255];
		// p_(x+y)
		public final double[] p_x_plus_y = new double[2 * 255 - 1];
		// p_(x-y)
		public final double[] p_x_minus_y = new double[255];
	}

	private ISimilarityMeasure measure;
	// rgb
	int numberOfGreyValues = 255;
	// private double[][][] cooccurrenceMatrices = null;
	double[] features = new double[24];

	public Haralick(BufferedImage image) {
		double[][][] cm = generateCooccurrenceMatrices(image);
		statistics[] stats = generateStats(cm);

		// TODO callculate features
		for (int matrix = 0; matrix < cm.length; matrix++){
			
			double[][] coMatrix = cm[matrix];
			statistics stat = stats[matrix];
			
			for (int i = 0; i < numberOfGreyValues; i++) {
				double sum_j_p_x_minus_y = 0;
				for (int j = 0; j < numberOfGreyValues; j++) {
					double p_ij = coMatrix[i][j];
					features[0] += p_ij * p_ij;
					features[2] += i * j * p_ij - stat.mu_x * stat.mu_y;
					
					// TODO: feature 3 is missing here
					features[4] += p_ij / (1 + (i - j) * (i - j));
					features[8] += p_ij * Math.log(p_ij+entrophy);
				}

	            features[1] += i * i * stat.p_x_minus_y[i];
	            features[9] += (i - sum_j_p_x_minus_y) * (i - sum_j_p_x_minus_y) * stat.p_x_minus_y[i];
	            features[10] += stat.p_x_minus_y[i] * Math.log(stat.p_x_minus_y[i]+entrophy);
			}
			// finish the calculation
			features[2] /= Math.sqrt(stat.var_x * stat.var_y);
	        features[8] *= -1;
	        features[10] *= -1;
	        
	        // this part isn`t  finished
	        double maxhxhy = Math.max(stat.hx, stat.hy);
	        if (Math.signum(maxhxhy) == 0) {
	        	features[11] = 0;
	        } else {
	        	features[11] = (features[8] - stat.hxy1) / maxhxhy;
	        }
	        features[12] = Math.sqrt(1 - Math.exp(-2 * (stat.hxy2 - features[8])));

	        for (int i = 0; i < 2 * 255 - 1; i++) {
	            features[5] += i * stat.p_x_plus_y[i];
	            features[7] += stat.p_x_plus_y[i] * Math.log(stat.p_x_plus_y[i]+entrophy);

	            double sum_j_p_x_plus_y = 0;
	            for (int j = 0; j < 2 * 255 - 1; j++) {
	                sum_j_p_x_plus_y += j * stat.p_x_plus_y[j];
	            }
	            features[6] += (i - sum_j_p_x_plus_y) * (i - sum_j_p_x_plus_y) * stat.p_x_plus_y[i];
	        }

	        features[7] *= -1;
		}
	}

	private statistics[] generateStats(double[][][] cm) {

		statistics[] stats = new statistics[cm.length];

		for (int cmId = 0; cmId < cm.length; cmId++) {
			double[][] cooccurrenceMatrix = cm[cmId];
			statistics stat = stats[cmId];
			for (int i = 0; i < 255; i++) {
				for (int j = 0; j < 255; j++) {
					double p_ij = cooccurrenceMatrix[i][j];

					stat.p_x[i] += p_ij;
					stat.p_y[j] += p_ij;

					stat.p_x_plus_y[i + j] += p_ij;
					stat.p_x_minus_y[Math.abs(i - j)] += p_ij;
				}
			}
			// mean and variance values
			double[] meanVar;
			// X relatet stats
			meanVar = meanVar(stat.p_x);
			stat.mu_x = meanVar[0];
			stat.var_x = meanVar[1];
			// Y related stats
			meanVar = meanVar(stat.p_y);
			stat.mu_y = meanVar[0];
			stat.var_y = meanVar[1];

			
			for (int i = 0; i < 255; i++) {
				// hx and hy
				stat.hx += stat.p_x[i] * Math.log(stat.p_x[i] + entrophy);
				stat.hy += stat.p_y[i] * Math.log(stat.p_y[i] + entrophy);

				// hxy1 and hxy2
				// that could be wrong...
				for (int j = 0; j < 255; j++) {
					double p_ij = cooccurrenceMatrix[i][j];
					stat.hxy1 += p_ij * Math.log((stat.p_x[i] * stat.p_y[j]) * +entrophy);
					stat.hxy2 += stat.p_x[i] * stat.p_y[j] * Math.log(stat.p_x[i] * stat.p_y[j]);
				}
			}
			stat.hx *= -1;
			stat.hy *= -1;
			stat.hxy1 *= -1;
			stat.hxy2 *= -1;
		}

		return null;
	}

	public double[] getVector() {
		return features;
	}

	private double[][][] generateCooccurrenceMatrices(BufferedImage image) {
		
		double[][][] cooccurrenceMatrices = new double[4][256][256];
		//double[][][] cooccurrenceMatrices = new double[4][55600][55600];
		final int imageWidth = image.getWidth();
		final int imageHeight = image.getHeight();
		int i, j, pos;
		for (int y = 0; y < imageHeight; y++) {
			for (int x = 0; x < imageWidth; x++) {
				pos = imageWidth * y + x;

				Color c = new Color(image.getRGB(x, y));
				int avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
/*
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
				*/
			}
		}
		return cooccurrenceMatrices;
	}

	/**
	 * Compute mean and variance of the given array
	 */
	private double[] meanVar(double[] a) {

		double ex = 0;
		for (int i = 0; i < 255; i++) {
			ex += a[i];
		}
		ex /= a.length;
		double var = 0;
		for (int i = 0; i < 255; i++) {
			var += (a[i] - ex) * (a[i] - ex);
		}
		var /= (a.length - 1);

		return new double[] { ex, var };
	}

	@Override
	public double calculateSimilarity(IFeature f) {
		if (measure != null) {
			return this.measure.calculateSimilarity(this, (Haralick) f);
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
