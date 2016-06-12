import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.AppModel;
import ui.GCHOptions;
import ui.LCHOptions;
import ui.OptionsPanel;
import ui.QuerySelectorPanel;
import ui.ResultsPanel;

/**
 * Main.java - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */

public class Main {

   private JFrame frame;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {

         public void run() {
            try {
               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               Main window = new Main();
               window.frame.setVisible(true);
               window.frame.setSize(1000, 600);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public Main() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frame = new JFrame();
      frame.setBounds(100, 100, 1066, 499);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[] { 277 };
      gridBagLayout.rowHeights = new int[] { 96, 121, 0 };
      gridBagLayout.columnWeights = new double[] { 1.0 };
      gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
      frame.getContentPane().setLayout(gridBagLayout);

      JPanel panel = new JPanel();
      GridBagConstraints gbc_panel = new GridBagConstraints();
      gbc_panel.insets = new Insets(0, 0, 5, 0);
      gbc_panel.fill = GridBagConstraints.BOTH;
      gbc_panel.gridx = 0;
      gbc_panel.gridy = 0;
      frame.getContentPane().add(panel, gbc_panel);
      GridBagLayout gbl_panel = new GridBagLayout();
      gbl_panel.columnWidths = new int[] { 165, 310, 403, 0 };
      gbl_panel.rowHeights = new int[] { 81, 59, 0 };
      gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
      gbl_panel.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
      panel.setLayout(gbl_panel);

      JPanel queryPreviewPanel = new QuerySelectorPanel();
      GridBagConstraints gbc_queryPreviewPanel = new GridBagConstraints();
      gbc_queryPreviewPanel.gridheight = 2;
      gbc_queryPreviewPanel.fill = GridBagConstraints.BOTH;
      gbc_queryPreviewPanel.insets = new Insets(0, 0, 0, 5);
      gbc_queryPreviewPanel.gridx = 0;
      gbc_queryPreviewPanel.gridy = 0;
      panel.add(queryPreviewPanel, gbc_queryPreviewPanel);

      JPanel optionsPanel = new OptionsPanel();
      GridBagConstraints gbc_optionsPanel = new GridBagConstraints();
      gbc_optionsPanel.gridheight = 2;
      gbc_optionsPanel.fill = GridBagConstraints.BOTH;
      gbc_optionsPanel.insets = new Insets(0, 0, 0, 5);
      gbc_optionsPanel.gridx = 1;
      gbc_optionsPanel.gridy = 0;
      panel.add(optionsPanel, gbc_optionsPanel);

      JPanel featureOptionsPanel = new JPanel();
      GridBagConstraints gbc_featureOptionsPanel = new GridBagConstraints();
      gbc_featureOptionsPanel.insets = new Insets(0, 0, 5, 0);
      gbc_featureOptionsPanel.fill = GridBagConstraints.BOTH;
      gbc_featureOptionsPanel.gridx = 2;
      gbc_featureOptionsPanel.gridy = 0;
      panel.add(featureOptionsPanel, gbc_featureOptionsPanel);

      JPanel similarityOptionsPanel = new JPanel();
      GridBagConstraints gbc_similarityOptionsPanel = new GridBagConstraints();
      gbc_similarityOptionsPanel.fill = GridBagConstraints.BOTH;
      gbc_similarityOptionsPanel.gridx = 2;
      gbc_similarityOptionsPanel.gridy = 1;
      panel.add(similarityOptionsPanel, gbc_similarityOptionsPanel);

      JPanel resultsPanel = new ResultsPanel();
      GridBagConstraints gbc_resultsPanel = new GridBagConstraints();
      gbc_resultsPanel.fill = GridBagConstraints.BOTH;
      gbc_resultsPanel.gridx = 0;
      gbc_resultsPanel.gridy = 1;
      frame.getContentPane().add(resultsPanel, gbc_resultsPanel);

      setSpecificOptionsPanels();
   }

   private void setSpecificOptionsPanels() {
      AppModel model = AppModel.getInstance();
      switch (model.getSelectedExtractionMethod()) {
         case GLOBAL_COLOR_HISTOGRAM:
            // featurePanel = new GCHOptions();
            break;
         case LOCAL_COLOR_HISTOGRAM:
            // featurePanel = new LCHOptions();
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

   }
}
