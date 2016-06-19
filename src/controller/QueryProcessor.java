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
import model.IFeature;
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
     
      FeatureExtractor extractor = new FeatureExtractor();  
      IFeature featureQueryImage =   extractor.process(model.getQueryImage()).setMeasure(measure);
      
      // For testing
      List<ResultImage> results = new ArrayList<ResultImage> ();
      File[] resL = model.getQueryImageFile().getParentFile().listFiles();
      for(int i = 0; i < resL.length; i++) {
         File f = resL[i];
         try {
            ResultImage res = new ResultImage();
            BufferedImage resI = ImageIO.read(f);
            
            
            
            //calculateSimilarity(featureQueryImage);
            //res.setSimilarity( measure.calculateSimilarity(featureQueryImage, featureCandidate));
            
            res.setSimilarity(featureQueryImage.calculateSimilarity(extractor.process(resI)));
            
            res.setImage(resI);
            res.setThumbnail(ImageController.getInstance().scaleImage(resI, 100));
            res.setRank(0);
            res.setCategory(f.getParentFile().getName());
            results.add(res);
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      // sort the results according to the distance
      Collections.sort(results, (a, b) ->  b.compareTo(a) );
      
      // update rank label
      
      model.setResultImages(results);
      for (int i = 0; i < results.size(); i++) {
    	  ((ResultImage) results.get(i)).setRank(i+1);
      }
      
      
      // Notify, captain notify what?
      // Houston, we have to notify, Houston
      setChanged();
      notifyObservers();
   }
}
