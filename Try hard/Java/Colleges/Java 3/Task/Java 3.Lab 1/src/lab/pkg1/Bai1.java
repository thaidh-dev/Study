package lab.pkg1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bai1 {
    JFrame f;
    JLabel lblHeader, lblFooterApple, lblFooterMango, lblFooterPeer;
    JCheckBox chkApple, chkMango, chkPeer;
    JPanel p;
    
    public static void main(String[] args) {
        Bai1 a = new Bai1();
    }
    
    Bai1() {
        form();
        event();
    }
    
    public void form() {
        f = new JFrame("Bài 1");
        f.setLayout(new GridLayout(5, 1));
        f.setSize(400, 400);
        
        Font font = new Font("", Font.PLAIN, 20);
        lblHeader = new JLabel("Control in action: CheckBox", JLabel.CENTER);
        lblHeader.setFont(font);
        lblFooterApple = new JLabel("", JLabel.CENTER);
        lblFooterApple.setFont(font);
        lblFooterMango = new JLabel("", JLabel.CENTER);
        lblFooterMango.setFont(font);
        lblFooterPeer = new JLabel("", JLabel.CENTER);
        lblFooterPeer.setFont(font);
        
        chkApple = new JCheckBox("Apple");
        chkMango = new JCheckBox("Mango");
        chkPeer = new JCheckBox("Peer");
        
        p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(chkApple);
        p.add(chkMango);
        p.add(chkPeer);

        f.add(lblHeader);
        f.add(p);
        f.add(lblFooterApple);
        f.add(lblFooterMango);
        f.add(lblFooterPeer);


        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void event() {
        chkApple.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                lblFooterApple.setText("Apple CheckBox: " + (e.getStateChange()==1 ? "Checked" : "Unckecked"));
            }
        });
        
        chkMango.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                lblFooterMango.setText("Mango CheckBox: " + (e.getStateChange()==1 ? "Checked" : "Unchecked"));
            }
        });

        chkPeer.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                lblFooterPeer.setText("Peer CheckBox: " + (e.getStateChange()==1 ? "Checked" : "Unchecked"));
            }
        });
    }
}
