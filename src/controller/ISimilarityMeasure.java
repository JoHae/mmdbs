/**
 * ISimilarityMeasure.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package controller;

import model.ColorHistogram;
import model.Feature;

public interface ISimilarityMeasure {

   //double calculateSimilarity(ColorHistogram histo1, ColorHistogram histo2);

   double calculateSimilarity(Feature featureSearch, Feature featureCompare);
}