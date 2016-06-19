/**
 * ISimilarityMeasure.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package controller;

import model.ColorHistogram;
import model.Haralick;
import model.IFeature;

public interface ISimilarityMeasure {

   double calculateSimilarity(ColorHistogram histo1, ColorHistogram histo2);
   double calculateSimilarity(Haralick haralick1, Haralick haralick2);

   //double calculateSimilarity(IFeature featureSearch, IFeature featureCompare);
}
