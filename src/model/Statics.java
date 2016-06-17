/**
 * Statics.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package model;


public class Statics {

   public enum ExtractionMethod {
      GLOBAL_COLOR_HISTOGRAM, LOCAL_COLOR_HISTOGRAM, GLOBAL_EDGE_HISTOGRAM, LOCAL_EDGE_HISTOGRAM, TEXTURE_HARALICK_FEATURES
  }
   
   public enum SimilarityMeasure {
      EUCLIDEAN_DISTANCE, QUADRATIC_FORM_DISTANCE
   }
}
