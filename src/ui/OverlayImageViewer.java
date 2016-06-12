/**
 * OverlayImageViewer.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;

public class OverlayImageViewer extends JFrame {

   private boolean mouseIsOver = false;

   public OverlayImageViewer() {
      this.setUndecorated(true);
      this.setBackground(new Color(0, 0, 0, 0));
      this.setAlwaysOnTop(true);
      // Without this, the window is draggable from any non transparent
      // point, including points inside textboxes.
      this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

      this.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseExited(java.awt.event.MouseEvent evt) {
               setVisible(false);
         }
      });
   }

   public void setContent(Component content) {
      this.getContentPane().removeAll();
      this.getContentPane().add(content);
      this.pack();
   }

   public boolean mouseIsOver() {
      Point mousePos = MouseInfo.getPointerInfo().getLocation();
      Rectangle bounds = this.getBounds();
      bounds.setLocation(this.getLocationOnScreen());
      return bounds.contains(mousePos);
   }
}
