/**
 * QuerySelectorPanel.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import algorithms.ImageController;


public class QuerySelectorPanel extends JPanel {

   /**
    * Create the panel.
    */
   public QuerySelectorPanel() {
      setBorder(new TitledBorder(null, "Select Query Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[]{438, 0};
      gridBagLayout.rowHeights = new int[]{23, 44, 0};
      gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
      gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
      setLayout(gridBagLayout);
      
      JButton btnBrowse = new JButton("Browse...");
      btnBrowse.setAlignmentX(Component.CENTER_ALIGNMENT);
      GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
      gbc_btnBrowse.anchor = GridBagConstraints.WEST;
      gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
      gbc_btnBrowse.gridx = 0;
      gbc_btnBrowse.gridy = 0;
      add(btnBrowse, gbc_btnBrowse);
      
      JPanel queryPreviewPanel = new JPanel();
      GridBagConstraints gbc_queryPreviewPanel = new GridBagConstraints();
      gbc_queryPreviewPanel.fill = GridBagConstraints.BOTH;
      gbc_queryPreviewPanel.gridx = 0;
      gbc_queryPreviewPanel.gridy = 1;
      add(queryPreviewPanel, gbc_queryPreviewPanel);
      
      final JLabel lblPreview = new JLabel("Preview");
      queryPreviewPanel.add(lblPreview);
      
      // Events
      btnBrowse.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            chooser.setCurrentDirectory(workingDirectory);
            int returnVal = chooser.showOpenDialog(QuerySelectorPanel.this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
               
               try {
                  BufferedImage img = ImageController.getInstance().scaleImage(ImageIO.read(chooser.getSelectedFile()), 100);
                  ImageIcon icon = new ImageIcon(img);
                  lblPreview.setIcon(icon);
                  lblPreview.setText("");
               } catch (IOException ex) {
                  ex.printStackTrace();
               }
               
            }
         }
      });
   }

}
