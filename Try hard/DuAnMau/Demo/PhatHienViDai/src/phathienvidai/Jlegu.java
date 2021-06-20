package phathienvidai;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Jlegu {
    public static void main(String[] args) {
        JFrame win = new JFrame("Jlegu");
        win.setLayout(new GridLayout());
        
        JPanel pnlA = pnl(new JPanel());
        JPanel pnlB = pnl(new JPanel());
        JPanel pnlC = pnl(new JPanel());
        
        win.add(pnlA);
        win.add(pnlB);
        win.add(pnlC);
        
        win.setSize(1000, 700);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(3);
        win.setVisible(true);
    }
    
    public static JPanel pnl(JPanel pnl) {
        pnl = new JPanel();
        pnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        return pnl;
    }
}
