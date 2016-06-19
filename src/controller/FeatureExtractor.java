package controller;

import java.awt.image.BufferedImage;

import model.AppModel;
import model.ColorHistogram;
import model.ConcolutionColorHistogram;
import model.IFeature;
import model.Statics.ExtractionMethod;

public class FeatureExtractor {

   ExtractionMethod m;

   public IFeature process(BufferedImage image) {
      AppModel model = AppModel.getInstance();
      
      int numBins = model.getNumBins();
      int numCells = model.getNumCells();
      switch (AppModel.getInstance().getSelectedExtractionMethod()) {
         case GLOBAL_COLOR_HISTOGRAM:
            // TODO: read a bin value from the ui
            return new ColorHistogram(image, numBins, 1);
         case GLOBAL_EDGE_HISTOGRAM:
            // TODO: read a bin value from the ui
            return new ConcolutionColorHistogram(image, numBins, 1);
         case LOCAL_EDGE_HISTOGRAM:
            // TODO: read a bin value from the ui
            return new ConcolutionColorHistogram(image, numBins, numCells);
         case LOCAL_COLOR_HISTOGRAM:
            // TODO: read a bin and a cell value from the ui
            return new ColorHistogram(image, numBins, numCells);
         case TEXTURE_HARALICK_FEATURES:
            return null;
         default:
            return null;
      }

   }

}
