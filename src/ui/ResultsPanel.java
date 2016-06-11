/**
 * ResultsPanel.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.ImageController;
import controller.QueryProcessor;
import model.AppModel;
import model.ResultImage;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;


public class ResultsPanel extends JPanel implements Observer {

   /**
    * Create the panel.
    */
   List<JLabel> resultLabels = new ArrayList();
   JPanel imagePanel = new JPanel();
   
   public ResultsPanel() {
      imagePanel.setLayout(new ModifiedFlowLayout());
      
      setBorder(new TitledBorder(null, "Query Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      
      QueryProcessor.getInstance().addObserver(this);
      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      add(scrollPane);
      scrollPane.setViewportView(imagePanel);
   }

   public void update(Observable o, Object arg) {
      for (JLabel jLabel : resultLabels) {
         this.remove(jLabel);
      }
      List<ResultImage> results = AppModel.getInstance().getResultImages();
      for (ResultImage resultImage : results) {
         ImageIcon icon = new ImageIcon(ImageController.getInstance().scaleImage(resultImage.getImage(), 100));
         JLabel lbl = new JLabel(icon);
         resultLabels.add(lbl);
         imagePanel.add(lbl);
      }
      validate();
      repaint();
   }
}
