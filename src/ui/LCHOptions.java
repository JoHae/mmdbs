/**
 * LCHOptions.java  - 11.06.2016
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

public class LCHOptions extends JPanel {

   AppModel model = AppModel.getInstance();

   /**
    * Create the panel.
    */
   public LCHOptions() {
      setBorder(new TitledBorder(null, "Local Color Histogram Options", TitledBorder.LEADING,
            TitledBorder.TOP, null, null));
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[] { 96, 100, 0 };
      gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
      gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
      gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
      setLayout(gridBagLayout);

      JLabel lblNumberOfBins = new JLabel("Number of Bins:");
      GridBagConstraints gbc_lblNumberOfBins = new GridBagConstraints();
      gbc_lblNumberOfBins.anchor = GridBagConstraints.EAST;
      gbc_lblNumberOfBins.insets = new Insets(0, 0, 5, 5);
      gbc_lblNumberOfBins.gridx = 0;
      gbc_lblNumberOfBins.gridy = 0;
      add(lblNumberOfBins, gbc_lblNumberOfBins);

      JSpinner binsSpinner = new JSpinner();
      binsSpinner.setValue(model.getNumBins());
      GridBagConstraints gbc_binsSpinner = new GridBagConstraints();
      gbc_binsSpinner.insets = new Insets(0, 0, 5, 0);
      gbc_binsSpinner.fill = GridBagConstraints.HORIZONTAL;
      gbc_binsSpinner.gridx = 1;
      gbc_binsSpinner.gridy = 0;
      add(binsSpinner, gbc_binsSpinner);

      JLabel lblNumberOfCells = new JLabel("Number of Cells");
      GridBagConstraints gbc_lblNumberOfCells = new GridBagConstraints();
      gbc_lblNumberOfCells.anchor = GridBagConstraints.EAST;
      gbc_lblNumberOfCells.insets = new Insets(0, 0, 0, 5);
      gbc_lblNumberOfCells.gridx = 0;
      gbc_lblNumberOfCells.gridy = 1;
      add(lblNumberOfCells, gbc_lblNumberOfCells);

      JSpinner cellSpinner = new JSpinner();
      cellSpinner.setValue(model.getNumCells());
      GridBagConstraints gbc_cellSpinner = new GridBagConstraints();
      gbc_cellSpinner.fill = GridBagConstraints.HORIZONTAL;
      gbc_cellSpinner.gridx = 1;
      gbc_cellSpinner.gridy = 1;
      add(cellSpinner, gbc_cellSpinner);

      binsSpinner.addChangeListener(new ChangeListener() {

         @Override
         public void stateChanged(ChangeEvent e) {
            model.setNumBins((int) binsSpinner.getValue());
         }
      });
      
      cellSpinner.addChangeListener(new ChangeListener() {

         @Override
         public void stateChanged(ChangeEvent e) {
            model.setNumCells((int) binsSpinner.getValue());
         }
      });
   }

}
