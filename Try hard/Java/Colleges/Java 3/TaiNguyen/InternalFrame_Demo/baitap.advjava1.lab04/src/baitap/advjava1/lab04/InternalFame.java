/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap.advjava1.lab04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.*;
import javax.swing.JButton;

import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InternalFame {

 private JFrame frame;

 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     InternalFame window = new InternalFame();
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
 public InternalFame() {
  initialize();
 }

 /**
  * Initialize the contents of the frame.
  */
 private void initialize() {
  frame = new JFrame();
  frame.setBounds(100, 100, 554, 300);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
  frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
  
  JPanel panel = new JPanel();
  tabbedPane.addTab("Quan ly sinh vien", null, panel, null);
  tabbedPane.setForegroundAt(0, Color.black);
  tabbedPane.setMnemonicAt(0, 555);
  tabbedPane.setDisabledIconAt(0, new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\InternalFrame_Demo\\baitap.advjava1.lab04\\image\\save.png"));
  tabbedPane.setBackgroundAt(0, SystemColor.menu);
  panel.setLayout(null);
  
  JDesktopPane desktopPane_1 = new JDesktopPane();
  desktopPane_1.setDragMode(1);
  desktopPane_1.setBackground(Color.LIGHT_GRAY);
  desktopPane_1.setBounds(0, 0, 532, 234);
  desktopPane_1.setToolTipText("");
  desktopPane_1.setForeground(Color.GREEN);
  panel.add(desktopPane_1);
  
  JInternalFrame internalFrame = new JInternalFrame("Dang ky");
  internalFrame.setClosable(true);
  internalFrame.setIconifiable(true);
  internalFrame.setMaximizable(true);
  internalFrame.setEnabled(false);
  internalFrame.setBounds(10, 35, 243, 171);
  
  JInternalFrame internalFrame_1 = new JInternalFrame("Lich hoc");
  internalFrame_1.setClosable(true);
  internalFrame_1.setMaximizable(true);
  internalFrame_1.setIconifiable(true);
  internalFrame_1.setBounds(276, 35, 246, 171);
  internalFrame_1.setVisible(true);
  internalFrame.setVisible(true);

  desktopPane_1.add(internalFrame);
  desktopPane_1.add(internalFrame_1);
  
  JPanel panel_1 = new JPanel();
  tabbedPane.addTab("Quan ly diem", null, panel_1, null);
  
  JPanel panel_2 = new JPanel();
  tabbedPane.addTab("Quan ly mon hoc", null, panel_2, null);
  
  JPanel panel_3 = new JPanel();
  tabbedPane.addTab("Thong ke", null, panel_3, null);
 }
}

