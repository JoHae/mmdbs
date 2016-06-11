/**
 * ResultsPanel.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class ResultsPanel extends JPanel {

   /**
    * Create the panel.
    */
   public ResultsPanel() {
      setBorder(new TitledBorder(null, "Query Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));

   }
}
