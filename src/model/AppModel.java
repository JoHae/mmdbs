/**
 * OptionsModel.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import model.Statics.ExtractionMethod;
import model.Statics.SimilarityMeasure;

public class AppModel {

   private static AppModel INSTANCE = new AppModel();
   
   public static AppModel getInstance() {
      return INSTANCE;
   }
   
   private ExtractionMethod selectedExtractionMethod;
   private SimilarityMeasure selectedSimilarityMeasure;
   private BufferedImage queryImage;
   private File queryImageFile;
   private String queryCategory;
   private List<ResultImage> resultImages;
   
   public ExtractionMethod getSelectedExtractionMethod() {
      return this.selectedExtractionMethod;
   }
   
   public void setSelectedExtractionMethod(ExtractionMethod selectedExtractionMethod) {
      this.selectedExtractionMethod = selectedExtractionMethod;
   }
   
   public SimilarityMeasure getSelectedSimilarityMeasure() {
      return this.selectedSimilarityMeasure;
   }
   
   public void setSelectedSimilarityMeasure(SimilarityMeasure selectedSimilarityMeasure) {
      this.selectedSimilarityMeasure = selectedSimilarityMeasure;
   }
   
   public BufferedImage getQueryImage() {
      return this.queryImage;
   }
   
   public void setQueryImage(BufferedImage queryImage) {
      System.out.println("setQueryImage");
      this.queryImage = queryImage;
   }
   
   public List<ResultImage> getResultImages() {
      return this.resultImages;
   }
   
   public void setResultImages(List<ResultImage> resultImages) {
      System.out.println("setResultImages: " + resultImages.size());
      this.resultImages = resultImages;
   }

   
   public String getQueryCategory() {
      return this.queryCategory;
   }

   
   public void setQueryCategory(String queryCategory) {
      System.out.println("setQueryCategory: " + queryCategory);
      this.queryCategory = queryCategory;
   }

   
   public File getQueryImageFile() {
      return this.queryImageFile;
   }

   
   public void setQueryImageFile(File queryImageFile) {
      this.queryImageFile = queryImageFile;
   }
   
}
