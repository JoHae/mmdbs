/**
 * QueryProcessor.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

import model.AppModel;
import model.ResultImage;

public class QueryProcessor extends Observable {
  private static QueryProcessor INSTANCE = new QueryProcessor();
   
   public static QueryProcessor getInstance() {
      return INSTANCE;
   }
   
   public void executeQuery() {
      AppModel model = AppModel.getInstance();
      ISimilarityMeasure measure;
      switch(model.getSelectedSimilarityMeasure()) {
         case EUCLIDEAN_DISTANCE:
            measure = new EuclideanMeasure();
         case QUADRATIC_FORM_DISTANCE:
            // TODO
         default: System.err.println("Unknown Similiarity Measure");
      }
      
      // For testing
      List<ResultImage> results = new ArrayList();
      for (File f : model.getQueryImageFile().getParentFile().listFiles()) {
         try {
            ResultImage res = new ResultImage();
            res.setImage(ImageIO.read(f));
            results.add(res);
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      model.setResultImages(results);
      
      // Notify
      setChanged();
      notifyObservers();
   }
}
