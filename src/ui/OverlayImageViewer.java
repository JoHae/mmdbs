/**
 * OverlayImageViewer.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OverlayImageViewer extends JFrame {
   public OverlayImageViewer() {
      this.setUndecorated(true);
      this.setBackground(new Color(0, 0, 0, 0));
      this.setAlwaysOnTop(true);
      // Without this, the window is draggable from any non transparent
      // point, including points  inside textboxes.
      this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
   }

   public void setContent(Component content) {
      this.getContentPane().removeAll();
      this.getContentPane().add(content);
      this.pack();
   }
}
