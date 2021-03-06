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
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import model.AppModel;
import model.IFeature;
import model.ResultImage;

public class QueryProcessor extends Observable {
  private static QueryProcessor INSTANCE = new QueryProcessor();
   
   public static QueryProcessor getInstance() {
      return INSTANCE;
   }
   
   public void listf(File directory , ArrayList<File> files) {
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile() && !file.isHidden()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file, files);
	        }
	    }
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
      final List<ResultImage> results = new ArrayList<ResultImage> ();
      //String[] resL = model.getQueryImageFile().getParentFile().list();
      
      File rootImage = model.getQueryImageFile().getParentFile().getParentFile();
      ArrayList<File> images = new ArrayList<>();
      listf(rootImage , images);
      
      ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
      
      for(int i = 0; i < images.size(); i++) {
         File f =  images.get(i);// resL[i];
         executor.submit(() -> {
            try {
               ResultImage res = new ResultImage();
               BufferedImage resI = ImageIO.read(f);
               //System.out.println(f.getAbsolutePath());
               
               
               //calculateSimilarity(featureQueryImage);
               //res.setSimilarity( measure.calculateSimilarity(featureQueryImage, featureCandidate));
               
               res.setSimilarity(featureQueryImage.calculateSimilarity(extractor.process(resI)));
              // res.setSimilarity(0);
               res.setFile(f);
               //res.setImage(resI);
               res.setThumbnail(ImageController.getInstance().scaleImage(resI, 100));
               res.setRank(0);
               res.setCategory(f.getParentFile().getName());
               results.add(res);
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
        });
      }
      
      try {
         System.out.println("attempt to shutdown executor");
         executor.shutdown();
         executor.awaitTermination(5, TimeUnit.MINUTES);
     }
     catch (InterruptedException e) {
         System.err.println("tasks interrupted");
     }
     finally {
         if (!executor.isTerminated()) {
             System.err.println("cancel non-finished tasks");
         }
         executor.shutdownNow();
         System.out.println("shutdown finished");
     }
      
      // sort the results according to the distance
      Collections.sort(results, (a, b) ->  b.compareTo(a) );
      
      List<ResultImage> topResults = results.subList(0, 499);
      
      // update rank label
      model.setResultImages(topResults);
      for (int i = 0; i < topResults.size(); i++) {
    	  ((ResultImage) topResults.get(i)).setRank(i+1);
      }
      
      
      // Notify, captain notify what?
      // Houston, we have to notify, Houston
      setChanged();
      notifyObservers();
   }
}
