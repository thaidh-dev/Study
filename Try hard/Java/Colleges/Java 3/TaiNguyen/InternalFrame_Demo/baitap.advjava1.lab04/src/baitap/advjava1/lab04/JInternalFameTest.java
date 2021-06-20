/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap.advjava1.lab04;

import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author HongAnh
 */
public class JInternalFameTest {
    public static void main(String[] a) {
      JFrame myFrame = new JFrame("Internal Frames");
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.setSize(300,300);
      JDesktopPane myDesktop = new JDesktopPane();
      myFrame.add(myDesktop);
      JInternalFrame f = createFrame("Frame 1");
      f.setLocation(10,10);
      myDesktop.add(f);
      f = createFrame("Frame 2");
      f.setLocation(60,60);
      myDesktop.add(f);
      myFrame.setVisible(true);
   }
   private static JInternalFrame createFrame(String t) {
      JInternalFrame f = new JInternalFrame(t);
      f.setResizable(true);
      f.setClosable(true);
      f.setMaximizable(true);
      f.setIconifiable(true);
      f.setSize(200,200);
      f.setVisible(true);
      return f;
   }
}
