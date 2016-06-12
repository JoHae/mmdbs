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
import java.awt.event.MouseAdapter;
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

import controller.ImageController;
import model.AppModel;


public class QuerySelectorPanel extends JPanel {

   /**
    * Create the panel.
    */
   public QuerySelectorPanel() {
      setBorder(new TitledBorder(null, "Select Query Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.columnWidths = new int[]{150, 0};
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
      
      final OverlayImageViewer viewer = new OverlayImageViewer();
      lblPreview.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseEntered(java.awt.event.MouseEvent evt) {
            BufferedImage img = AppModel.getInstance().getQueryImage();
            if(img != null) {
               JPanel p = new JPanel();
               JLabel  l = new JLabel();
               l.setIcon(new ImageIcon(img));
               p.add(l);
               viewer.setContent(p);
               viewer.setVisible(true);
            }
         }
         
         @Override
         public void mouseExited(java.awt.event.MouseEvent evt) {
            if(viewer.isVisible() && !viewer.mouseIsOver()) {
               viewer.setVisible(false); 
            }
         }
     });
      
      // Events
      btnBrowse.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            
            ImagePreviewPanel preview = new ImagePreviewPanel();
            chooser.setAccessory(preview);
            chooser.addPropertyChangeListener(preview);
            
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            chooser.setCurrentDirectory(workingDirectory);
            int returnVal = chooser.showOpenDialog(QuerySelectorPanel.this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               File f = chooser.getSelectedFile();
               System.out.println("You chose to open this file: " +
                    f.getName());
               
               try {
                  BufferedImage img = ImageIO.read(f);
                  AppModel model = AppModel.getInstance();
                  model.setQueryImage(img);
                  model.setQueryCategory(f.getParentFile().getName());
                  model.setQueryImageFile(f);
                  ImageIcon icon = new ImageIcon(ImageController.getInstance().scaleImage(img, 100));
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
