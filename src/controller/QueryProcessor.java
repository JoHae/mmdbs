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
            break;
         case QUADRATIC_FORM_DISTANCE:
            // TODO
            break;
         default: System.err.println("Unknown Similiarity Measure");
      }
      
      // For testing
      List<ResultImage> results = new ArrayList();
      File[] resL = model.getQueryImageFile().getParentFile().listFiles();
      for(int i = 0; i < resL.length; i++) {
         File f = resL[i];
         try {
            ResultImage res = new ResultImage();
            BufferedImage resI = ImageIO.read(f);
            res.setImage(resI);
            res.setThumbnail(ImageController.getInstance().scaleImage(resI, 100));
            res.setRank(i);
            res.setCategory(f.getParentFile().getName());
            res.setSimilarity(0.0);
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
