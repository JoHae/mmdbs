/**
 * ResultImagePanel.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver;

import model.AppModel;
import model.ResultImage;


public class ResultImagePanel extends JPanel {

   /**
    * Create the panel.
    */
   public ResultImagePanel(final ResultImage resImg) {
      setBorder(new LineBorder(new Color(0, 0, 0)));
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[]{60, 36, 0};
      gridBagLayout.rowHeights = new int[]{1, 0, 0, 0, 0};
      gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
      gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gridBagLayout);
      
      JLabel lblPreview = new JLabel("");
      lblPreview.setIcon(new ImageIcon(resImg.getThumbnail()));
      GridBagConstraints gbc_lblPreview = new GridBagConstraints();
      gbc_lblPreview.gridwidth = 2;
      gbc_lblPreview.insets = new Insets(0, 0, 5, 5);
      gbc_lblPreview.gridx = 0;
      gbc_lblPreview.gridy = 0;
      add(lblPreview, gbc_lblPreview);
      
      JLabel lblRank = new JLabel("Rank:");
      GridBagConstraints gbc_lblRank = new GridBagConstraints();
      gbc_lblRank.anchor = GridBagConstraints.EAST;
      gbc_lblRank.insets = new Insets(0, 0, 5, 5);
      gbc_lblRank.gridx = 0;
      gbc_lblRank.gridy = 1;
      add(lblRank, gbc_lblRank);
      
      JLabel lblRankVal = new JLabel(String.valueOf(resImg.getRank()));
      GridBagConstraints gbc_lblRankVal = new GridBagConstraints();
      gbc_lblRankVal.anchor = GridBagConstraints.WEST;
      gbc_lblRankVal.insets = new Insets(0, 0, 5, 0);
      gbc_lblRankVal.gridx = 1;
      gbc_lblRankVal.gridy = 1;
      add(lblRankVal, gbc_lblRankVal);
      
      JLabel lblSimilarity = new JLabel("Similarity:");
      GridBagConstraints gbc_lblSimilarity = new GridBagConstraints();
      gbc_lblSimilarity.anchor = GridBagConstraints.EAST;
      gbc_lblSimilarity.insets = new Insets(0, 0, 5, 5);
      gbc_lblSimilarity.gridx = 0;
      gbc_lblSimilarity.gridy = 2;
      add(lblSimilarity, gbc_lblSimilarity);
      
      JLabel lblSimiliarityVal = new JLabel(String.valueOf(resImg.getSimilarity()));
      GridBagConstraints gbc_lblSimiliarityVal = new GridBagConstraints();
      gbc_lblSimiliarityVal.anchor = GridBagConstraints.WEST;
      gbc_lblSimiliarityVal.insets = new Insets(0, 0, 5, 0);
      gbc_lblSimiliarityVal.gridx = 1;
      gbc_lblSimiliarityVal.gridy = 2;
      add(lblSimiliarityVal, gbc_lblSimiliarityVal);
      
      JLabel lblCategory = new JLabel("Category:");
      GridBagConstraints gbc_lblCategory = new GridBagConstraints();
      gbc_lblCategory.anchor = GridBagConstraints.EAST;
      gbc_lblCategory.insets = new Insets(0, 0, 0, 5);
      gbc_lblCategory.gridx = 0;
      gbc_lblCategory.gridy = 3;
      add(lblCategory, gbc_lblCategory);
      
      JLabel lblCategoryVal = new JLabel(resImg.getCategory());
      GridBagConstraints gbc_lblCategoryVal = new GridBagConstraints();
      gbc_lblCategoryVal.anchor = GridBagConstraints.WEST;
      gbc_lblCategoryVal.gridx = 1;
      gbc_lblCategoryVal.gridy = 3;
      add(lblCategoryVal, gbc_lblCategoryVal);
      
      final OverlayImageViewer viewer = new OverlayImageViewer();
      this.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseEntered(java.awt.event.MouseEvent evt) {
               JPanel p = new JPanel();
               JLabel  l = new JLabel();
               l.setIcon(new ImageIcon(resImg.getImage()));
               p.add(l);
               viewer.setContent(p);
               viewer.setVisible(true);
         }
         
         @Override
         public void mouseExited(java.awt.event.MouseEvent evt) {
            if(viewer.isVisible() && !viewer.mouseIsOver()) {
               viewer.setVisible(false); 
            }
         }
     });

   }

}
