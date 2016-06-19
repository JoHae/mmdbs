/**
 * GCHOptions.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AppModel;


public class GCHOptions extends JPanel {
   
   AppModel model = AppModel.getInstance();

   /**
    * Create the panel.
    */
   public GCHOptions() {
      setBorder(new TitledBorder(null, "Global Color Histogram Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[]{0, 66, 0};
      gridBagLayout.rowHeights = new int[]{0, 0};
      gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
      gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
      setLayout(gridBagLayout);
      
      JLabel lblNumberOfBins = new JLabel("Number of Bins:");
      GridBagConstraints gbc_lblNumberOfBins = new GridBagConstraints();
      gbc_lblNumberOfBins.insets = new Insets(0, 0, 0, 5);
      gbc_lblNumberOfBins.gridx = 0;
      gbc_lblNumberOfBins.gridy = 0;
      add(lblNumberOfBins, gbc_lblNumberOfBins);
      
      JSpinner spinner = new JSpinner();
      spinner.setValue(model.getNumBins());
      GridBagConstraints gbc_spinner = new GridBagConstraints();
      gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
      gbc_spinner.gridx = 1;
      gbc_spinner.gridy = 0;
      add(spinner, gbc_spinner);
      
      spinner.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent e) {
             model.setNumBins((int) spinner.getValue());
         }
     });

   }

}
