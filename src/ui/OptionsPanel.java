/**
 * OptionsPanel.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.QueryProcessor;
import model.AppModel;
import model.Statics;
import model.Statics.ExtractionMethod;
import model.Statics.SimilarityMeasure;

public class OptionsPanel extends JPanel {

   /**
    * Create the panel.
    */
   public OptionsPanel() {
      setBorder(new TitledBorder(null, "General Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[] { 156, 141, 0 };
      gridBagLayout.rowHeights = new int[] { 23, 23, 23, 0 };
      gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
      gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
      setLayout(gridBagLayout);

      JLabel lblFeatureExtractionMethod = new JLabel("IFeature Extraction Method:");
      GridBagConstraints gbc_lblFeatureExtractionMethod = new GridBagConstraints();
      gbc_lblFeatureExtractionMethod.anchor = GridBagConstraints.EAST;
      gbc_lblFeatureExtractionMethod.insets = new Insets(0, 0, 5, 5);
      gbc_lblFeatureExtractionMethod.gridx = 0;
      gbc_lblFeatureExtractionMethod.gridy = 0;
      add(lblFeatureExtractionMethod, gbc_lblFeatureExtractionMethod);

      final JComboBox extractionBox = new JComboBox(Statics.ExtractionMethod.values());
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

      final JComboBox similarityBox = new JComboBox(Statics.SimilarityMeasure.values());
      GridBagConstraints gbc_similarityBox = new GridBagConstraints();
      gbc_similarityBox.fill = GridBagConstraints.HORIZONTAL;
      gbc_similarityBox.anchor = GridBagConstraints.NORTH;
      gbc_similarityBox.insets = new Insets(0, 0, 5, 0);
      gbc_similarityBox.gridx = 1;
      gbc_similarityBox.gridy = 1;
      add(similarityBox, gbc_similarityBox);

      JButton btnExecuteQuery = new JButton("Execute Query");
      GridBagConstraints gbc_btnExecuteQuery = new GridBagConstraints();
      gbc_btnExecuteQuery.anchor = GridBagConstraints.SOUTHWEST;
      gbc_btnExecuteQuery.gridx = 1;
      gbc_btnExecuteQuery.gridy = 2;
      add(btnExecuteQuery, gbc_btnExecuteQuery);

      // Init model values
      final AppModel model = AppModel.getInstance();
      model.setSelectedExtractionMethod((ExtractionMethod) extractionBox.getSelectedItem());
      model.setSelectedSimilarityMeasure((SimilarityMeasure) similarityBox.getSelectedItem());
            
      extractionBox.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            model.setSelectedExtractionMethod((ExtractionMethod) extractionBox.getSelectedItem());
         }
      });
      similarityBox.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            model.setSelectedSimilarityMeasure((SimilarityMeasure) similarityBox.getSelectedItem());
         }
      });
      btnExecuteQuery.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            QueryProcessor.getInstance().executeQuery();
         }
      });
   }

}
