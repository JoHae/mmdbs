import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ui.OptionsPanel;
import ui.QuerySelectorPanel;
import ui.ResultsPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


/**
 * Main.java  - 11.06.2016
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
               Main window = new Main();
               window.frame.setVisible(true);
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
      frame.setBounds(100, 100, 450, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[]{434, 0};
      gridBagLayout.rowHeights = new int[]{105, 121, 33, 0};
      gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
      gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
      frame.getContentPane().setLayout(gridBagLayout);
      
      JPanel queryPreviewPanel = new QuerySelectorPanel();
      GridBagConstraints gbc_queryPreviewPanel = new GridBagConstraints();
      gbc_queryPreviewPanel.fill = GridBagConstraints.HORIZONTAL;
      gbc_queryPreviewPanel.insets = new Insets(0, 0, 5, 0);
      gbc_queryPreviewPanel.gridx = 0;
      gbc_queryPreviewPanel.gridy = 0;
      frame.getContentPane().add(queryPreviewPanel, gbc_queryPreviewPanel);
      
      JPanel optionsPanel = new OptionsPanel();
      GridBagConstraints gbc_optionsPanel = new GridBagConstraints();
      gbc_optionsPanel.fill = GridBagConstraints.HORIZONTAL;
      gbc_optionsPanel.insets = new Insets(0, 0, 5, 0);
      gbc_optionsPanel.gridx = 0;
      gbc_optionsPanel.gridy = 1;
      frame.getContentPane().add(optionsPanel, gbc_optionsPanel);
      
      JPanel resultsPanel = new ResultsPanel();
      GridBagConstraints gbc_resultsPanel = new GridBagConstraints();
      gbc_resultsPanel.fill = GridBagConstraints.BOTH;
      gbc_resultsPanel.gridx = 0;
      gbc_resultsPanel.gridy = 2;
      frame.getContentPane().add(resultsPanel, gbc_resultsPanel);
   }

}
