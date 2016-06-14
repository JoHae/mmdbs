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
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

import model.AppModel;
import model.Feature;
import model.FeatureExtractor;
import model.ResultImage;

public class QueryProcessor extends Observable {
  private static QueryProcessor INSTANCE = new QueryProcessor();
   
   public static QueryProcessor getInstance() {
      return INSTANCE;
   }
   
   public void executeQuery() {
      AppModel model = AppModel.getInstance();
           
      ISimilarityMeasure measure = null;
      switch(model.getSelectedSimilarityMeasure()) {
         case EUCLIDEAN_DISTANCE:
            measure = new EuclideanMeasure();
            break;
         case QUADRATIC_FORM_DISTANCE:
            // TODO
            break;
         default: System.err.println("Unknown Similiarity Measure");
      }
     
      FeatureExtractor extractor = new FeatureExtractor(model.getSelectedExtractionMethod());  
      Feature featureQueryImage =   extractor.process(model.getQueryImage());
    
      
      // For testing
      List<ResultImage> results = new ArrayList<ResultImage> ();
      File[] resL = model.getQueryImageFile().getParentFile().listFiles();
      for(int i = 0; i < resL.length; i++) {
         File f = resL[i];
         try {
            ResultImage res = new ResultImage();
            BufferedImage resI = ImageIO.read(f);
            
            Feature featureCandidate  = extractor.process(resI);
           
            
            res.setImage(resI);
            res.setThumbnail(ImageController.getInstance().scaleImage(resI, 100));
            res.setRank(i);
            res.setCategory(f.getParentFile().getName());
            res.setSimilarity( measure.calculateSimilarity(featureQueryImage, featureCandidate));
            results.add(res);
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      Collections.sort(results, (a, b) ->  b.compareTo(a) );
      
      model.setResultImages(results);
      
      // Notify, captain notify what?
      setChanged();
      notifyObservers();
   }
}
