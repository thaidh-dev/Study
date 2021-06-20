package lab.pkg3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Bai1 {
    JFrame f;
    JPanel p1, p2, p3, p4;
    JButton btnNorth, btnCenter, btnEast, btnWest, btnSouth, btnRed, btnGreen, btnYellow, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;
    JTextField txt;
    
    public static void main(String[] args) {
        new Bai1();
    }
    
    Bai1() {
        form();
        event();
    }
    
    public void form() {
        f = new JFrame("Bài 1");
        f.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        p1.setPreferredSize(new Dimension(500, 50));
        btnRed = new JButton("Red");
        btnGreen = new JButton("Green");
        btnYellow = new JButton("Yellow");
        p1.add(btnRed);
        p1.add(btnGreen);
        p1.add(btnYellow);
        
        p2 = new JPanel(new BorderLayout());
        p2.setPreferredSize(new Dimension(500, 150));
        btnNorth = new JButton("North");
        btnWest = new JButton("West");
        btnSouth = new JButton("South");
        btnEast = new JButton("East");
        btnCenter = new JButton("Center");
        p2.add(btnNorth, BorderLayout.NORTH);
        p2.add(btnWest, BorderLayout.WEST);
        p2.add(btnSouth, BorderLayout.SOUTH);
        p2.add(btnEast, BorderLayout.EAST);
        p2.add(btnCenter, BorderLayout.CENTER);
        
        p3 = new JPanel();
        txt = new JTextField();
        txt.setPreferredSize(new Dimension(460, 30));
        p3.add(txt);
        
        p4 = new JPanel(new GridLayout(2, 5, 10, 10));
        p4.setPreferredSize(new Dimension(500, 100));
        btn1 = new JButton("");
        btn2 = new JButton("");
        btn3 = new JButton("");
        btn4 = new JButton("");
        btn5 = new JButton("");
        btn6 = new JButton("");
        btn7 = new JButton("");
        btn8 = new JButton("");
        btn9 = new JButton("");
        btn10 = new JButton("");
        p4.add(btn1);
        p4.add(btn2);
        p4.add(btn3);
        p4.add(btn4);
        p4.add(btn5);
        p4.add(btn6);
        p4.add(btn7);
        p4.add(btn8);
        p4.add(btn9);
        p4.add(btn10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        f.add(p1, gbc);
        gbc.gridy = 1;
        f.add(p2, gbc);
        gbc.gridy = 2;
        f.add(p3, gbc);
        gbc.gridy = 3;
        f.add(p4, gbc);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    public void event() {
        chuyenMau(btnRed, Color.red);
        chuyenMau(btnGreen, Color.GREEN);
        chuyenMau(btnYellow, Color.yellow);
        
        phuongHuong(btnNorth, "North");
        phuongHuong(btnWest, "West");
        phuongHuong(btnEast, "East");
        phuongHuong(btnCenter, "Center");
        phuongHuong(btnSouth, "South");
    }
    
    public void chuyenMau(JButton btn, Color color) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setBackground(color);
            }
        });
    }
    
    public void phuongHuong(JButton btn, String string) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt.setText(string);
            }
        });
    }
}
