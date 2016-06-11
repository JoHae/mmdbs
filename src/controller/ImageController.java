/**
 * ImageController.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package controller;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageController {

   private static ImageController INSTANCE = new ImageController();
   
   public static ImageController getInstance() {
      return INSTANCE;
   }
   
   public BufferedImage scaleImage(BufferedImage img, int maxPixels) {
      int w = img.getWidth();
      int h = img.getHeight();
      
      double scale = (double) maxPixels / Math.max(w, h);
      int scaledW = (int) (w * scale);
      int scaledH = (int) (h * scale);
      
      BufferedImage after = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_ARGB);
      AffineTransform at = new AffineTransform();
      at.scale(scale, scale);
      AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
      after = scaleOp.filter(img, after);
      
      return after;
   }
   
   public int getNumRelevantImages(File queryImage) {
      return queryImage.getParentFile().list().length;
   }
}
