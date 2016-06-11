/**
 * OptionsPanel.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import model.Statics;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;


public class OptionsPanel extends JPanel {

   /**
    * Create the panel.
    */
   public OptionsPanel() {
      setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[]{219, 219, 0};
      gridBagLayout.rowHeights = new int[]{23, 23, 23, 0};
      gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
      gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gridBagLayout);
      
      JLabel lblFeatureExtractionMethod = new JLabel("Feature Extraction Method:");
      GridBagConstraints gbc_lblFeatureExtractionMethod = new GridBagConstraints();
      gbc_lblFeatureExtractionMethod.anchor = GridBagConstraints.EAST;
      gbc_lblFeatureExtractionMethod.insets = new Insets(0, 0, 5, 5);
      gbc_lblFeatureExtractionMethod.gridx = 0;
      gbc_lblFeatureExtractionMethod.gridy = 0;
      add(lblFeatureExtractionMethod, gbc_lblFeatureExtractionMethod);
      
      JComboBox extractionBox = new JComboBox(Statics.ExtractionMethod.values());
      GridBagConstraints gbc_extractionBox = new GridBagConstraints();
      gbc_extractionBox.anchor = GridBagConstraints.NORTHWEST;
      gbc_extractionBox.insets = new Insets(0, 0, 5, 0);
      gbc_extractionBox.gridx = 1;
      gbc_extractionBox.gridy = 0;
      add(extractionBox, gbc_extractionBox);
      
      JLabel lblSimilarityMeasure = new JLabel("Similarity Measure:");
      GridBagConstraints gbc_lblSimilarityMeasure = new GridBagConstraints();
      gbc_lblSimilarityMeasure.anchor = GridBagConstraints.EAST;
      gbc_lblSimilarityMeasure.insets = new Insets(0, 0, 5, 5);
      gbc_lblSimilarityMeasure.gridx = 0;
      gbc_lblSimilarityMeasure.gridy = 1;
      add(lblSimilarityMeasure, gbc_lblSimilarityMeasure);
      
      JComboBox similarityBox = new JComboBox(Statics.SimilarityMeasure.values());
      GridBagConstraints gbc_similarityBox = new GridBagConstraints();
      gbc_similarityBox.anchor = GridBagConstraints.NORTHWEST;
      gbc_similarityBox.insets = new Insets(0, 0, 5, 0);
      gbc_similarityBox.gridx = 1;
      gbc_similarityBox.gridy = 1;
      add(similarityBox, gbc_similarityBox);
      
      JButton btnExecuteQuery = new JButton("Execute Query");
      GridBagConstraints gbc_btnExecuteQuery = new GridBagConstraints();
      gbc_btnExecuteQuery.anchor = GridBagConstraints.WEST;
      gbc_btnExecuteQuery.gridx = 1;
      gbc_btnExecuteQuery.gridy = 2;
      add(btnExecuteQuery, gbc_btnExecuteQuery);

   }

}