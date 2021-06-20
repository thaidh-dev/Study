package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bai3 {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Quản lý sinh viên");

        GridBagConstraints gbc = new GridBagConstraints();
        
        f.setLayout(new GridBagLayout());
        
        JLabel l = new JLabel("QUẢN LÝ SINH VIÊN", JLabel.CENTER);
        l.setFont(new Font("", Font.BOLD, 30));
        l.setForeground(Color.red);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        f.add(l, gbc);
        
        JLabel l2 = new JLabel("HỌ TÊN");
        l2.setFont(new Font("", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 5);
        f.add(l2, gbc);
        
        JTextField tf = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        f.add(tf, gbc);
        
        JLabel l3 = new JLabel("ĐIỂM");
        l3.setFont(new Font("", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        f.add(l3, gbc);
        
        JTextField tf2 = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        f.add(tf2, gbc);
        
        JLabel l4 = new JLabel("HỌC LỰC");
        l4.setFont(new Font("", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        f.add(l4, gbc);
        
        JTextField tf3 = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        tf3.setEditable(false);
        f.add(tf3, gbc);

        JCheckBox cb = new JCheckBox("Có phần thưởng?");
        cb.setFont(new Font("", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 5, 0, 0);
        f.add(cb, gbc);
        
        JButton b = new JButton("LƯU");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 5, 0);
        f.add(b, gbc);
        
        JButton b2 = new JButton("NHẬP MỚI");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 80, 5, 0);
        f.add(b2, gbc);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("");
                tf2.setText("");
                cb.setSelected(false);
                tf3.setText("");
                JOptionPane.showMessageDialog(f, "Đã xóa trắng tất cả các mục");
            }
        });
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student sv = new Student();
                sv.ten = tf.getText();
                sv.diem = Double.parseDouble(tf2.getText());
                tf3.setText(sv.hocLuc());
                cb.setSelected(sv.thuong());
                JOptionPane.showMessageDialog(f, "Lưu thành công");
            }
        });

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
