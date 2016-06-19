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
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class OptionsPanel extends JPanel {

   AppModel model = AppModel.getInstance();
   JPanel specificOptionsPanel = new JPanel();

   /**
    * Create the panel.
    */
   public OptionsPanel() {
      setBorder(null);
      setLayout(new BorderLayout(0, 0));

      JPanel generalOptionsPanel = new JPanel();
      generalOptionsPanel.setBorder(
            new TitledBorder(null, "General Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      add(generalOptionsPanel, BorderLayout.WEST);
      GridBagLayout gbl_generalOptionsPanel = new GridBagLayout();
      gbl_generalOptionsPanel.columnWidths = new int[] { 137, 182, 0 };
      gbl_generalOptionsPanel.rowHeights = new int[] { 23, 0, 0, 0 };
      gbl_generalOptionsPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
      gbl_generalOptionsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
      generalOptionsPanel.setLayout(gbl_generalOptionsPanel);

      JLabel lblFeatureExtractionMethod = new JLabel("IFeature Extraction Method:");
      GridBagConstraints gbc_lblFeatureExtractionMethod = new GridBagConstraints();
      gbc_lblFeatureExtractionMethod.anchor = GridBagConstraints.WEST;
      gbc_lblFeatureExtractionMethod.insets = new Insets(0, 0, 5, 5);
      gbc_lblFeatureExtractionMethod.gridx = 0;
      gbc_lblFeatureExtractionMethod.gridy = 0;
      generalOptionsPanel.add(lblFeatureExtractionMethod, gbc_lblFeatureExtractionMethod);

      final JComboBox extractionBox = new JComboBox(Statics.ExtractionMethod.values());
      GridBagConstraints gbc_extractionBox = new GridBagConstraints();
      gbc_extractionBox.fill = GridBagConstraints.HORIZONTAL;
      gbc_extractionBox.insets = new Insets(0, 0, 5, 0);
      gbc_extractionBox.gridx = 1;
      gbc_extractionBox.gridy = 0;
      generalOptionsPanel.add(extractionBox, gbc_extractionBox);
      model.setSelectedExtractionMethod((ExtractionMethod) extractionBox.getSelectedItem());

      extractionBox.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            model.setSelectedExtractionMethod((ExtractionMethod) extractionBox.getSelectedItem());
            setSpecificOptionsPanels();
         }
      });

      JLabel lblSimilarityMeasure = new JLabel("Similarity Measure:");
      GridBagConstraints gbc_lblSimilarityMeasure = new GridBagConstraints();
      gbc_lblSimilarityMeasure.anchor = GridBagConstraints.WEST;
      gbc_lblSimilarityMeasure.insets = new Insets(0, 0, 5, 5);
      gbc_lblSimilarityMeasure.gridx = 0;
      gbc_lblSimilarityMeasure.gridy = 1;
      generalOptionsPanel.add(lblSimilarityMeasure, gbc_lblSimilarityMeasure);

      final JComboBox similarityBox = new JComboBox(Statics.SimilarityMeasure.values());
      GridBagConstraints gbc_similarityBox = new GridBagConstraints();
      gbc_similarityBox.fill = GridBagConstraints.HORIZONTAL;
      gbc_similarityBox.insets = new Insets(0, 0, 5, 0);
      gbc_similarityBox.gridx = 1;
      gbc_similarityBox.gridy = 1;
      generalOptionsPanel.add(similarityBox, gbc_similarityBox);

      JButton btnExecuteQuery = new JButton("Execute Query");
      GridBagConstraints gbc_btnExecuteQuery = new GridBagConstraints();
      gbc_btnExecuteQuery.anchor = GridBagConstraints.NORTHWEST;
      gbc_btnExecuteQuery.gridx = 1;
      gbc_btnExecuteQuery.gridy = 2;
      generalOptionsPanel.add(btnExecuteQuery, gbc_btnExecuteQuery);
      btnExecuteQuery.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            QueryProcessor.getInstance().executeQuery();
         }
      });
      model.setSelectedSimilarityMeasure((SimilarityMeasure) similarityBox.getSelectedItem());
      similarityBox.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            model.setSelectedSimilarityMeasure((SimilarityMeasure) similarityBox.getSelectedItem());
            setSpecificOptionsPanels();
         }
      });
      add(specificOptionsPanel);
      specificOptionsPanel.setLayout(new BorderLayout(0, 0));

      setSpecificOptionsPanels();
   }

   private void setSpecificOptionsPanels() {
      specificOptionsPanel.removeAll();
      switch (model.getSelectedExtractionMethod()) {
         case GLOBAL_COLOR_HISTOGRAM:
            specificOptionsPanel.add(new GCHOptions());
            break;
         case LOCAL_COLOR_HISTOGRAM:
            specificOptionsPanel.add(new LCHOptions());
            break;
         case GLOBAL_EDGE_HISTOGRAM:
            break;
         case TEXTURE_HARALICK_FEATURES:
            break;
      }

      switch (model.getSelectedSimilarityMeasure()) {
         case EUCLIDEAN_DISTANCE:
            break;
         case QUADRATIC_FORM_DISTANCE:
            break;
      }
      
      specificOptionsPanel.invalidate();
      specificOptionsPanel.validate();
      specificOptionsPanel.repaint();

   }

}
