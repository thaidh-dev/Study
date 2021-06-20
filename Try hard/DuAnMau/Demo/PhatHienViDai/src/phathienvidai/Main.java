package phathienvidai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setDefaultCloseOperation(3);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=EMPDB", "thaidhph06986", "dht24111997");
            PreparedStatement pst = con.prepareStatement("select * from ban");
            ResultSet rs = pst.executeQuery();
            rs.next();
            
            JButton btn = new JButton("alo");
            btn.setLocation(rs.getInt(1), rs.getInt(2));
            btn.setSize(150, 100);
            add(btn);
            
            Movement mv = new Movement(btn);

            rs.next();
            JButton btn2 = new JButton("button");
            btn2.setSize(150, 100);
            btn2.setLocation(rs.getInt(1), rs.getInt(2));
            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btn.removeMouseMotionListener(mv);
                    try {
                        PreparedStatement pst = con.prepareStatement("update ban set x = ?, y = ? where ma = 1");
                        pst.setInt(1, btn.getX());
                        pst.setInt(2, btn.getY());
                        pst.executeUpdate();
                        
                        System.out.println(btn.getVisibleRect());
                        

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    
                }
            });
            add(btn2);
            
            
            JButton btn3 = new JButton("xóa");
            btn3.setSize(100, 50);
            btn3.setLocation(300, 300);
            btn3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(btn2);
                    repaint();
                    System.out.println("â");
                    
                }
            });
            add(btn3);

            JButton btn4 = new JButton("thêm");
            btn4.setSize(100, 50);
            btn4.setLocation(200, 200);
            btn4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn5 = new JButton("thêmed");
                    btn5.setSize(100, 50);
                    btn5.setLocation(20, 20);
                    add(btn5);

                    repaint();
                }
            });
            
            add(btn4);
            
            JButton btn5 = new JButton("sửa");
            btn5.setSize(100, 50);
            btn5.setLocation(400, 400);
            btn5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(btn4.isValid());
                    System.out.println(isValid());
                    btn4.setFont(new Font("tahoma", Font.BOLD, 100));
                    System.out.println(btn4.isValid());
                    System.out.println(isValid());
                }
            });
            
            add(btn5);
            

            
            
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        
        
        
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Main();

    }
    
}
