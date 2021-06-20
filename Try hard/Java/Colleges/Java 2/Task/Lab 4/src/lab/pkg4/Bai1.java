package lab.pkg4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bai1 {
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Quản lý nhân viên");
        f.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTieuDe.setFont(new Font("", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.red);
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        f.add(lblTieuDe, gbc);
        
        Font font = new Font("", Font.BOLD, 20);
        
        JLabel lblTen = new JLabel("HỌ TÊN");
        lblTen.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        f.add(lblTen, gbc);
        
        JLabel lblNgaySinh = new JLabel("NGÀY SINH");
        lblNgaySinh.setFont(font);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 5, 5);
        f.add(lblNgaySinh, gbc);
        
        JLabel lblLuong = new JLabel("LƯƠNG");
        lblLuong.setFont(font);
        gbc.gridy = 3;
        f.add(lblLuong, gbc);
        
        JTextField txtTen = new JTextField(35);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 20, 5, 5);
        f.add(txtTen, gbc);
        
        JTextField txtNgaySinh = new JTextField(35);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 20, 5, 5);
        f.add(txtNgaySinh, gbc);
        
        JTextField txtLuong = new JTextField(35);
        gbc.gridy = 3;
        f.add(txtLuong, gbc);
        
        JButton btnKiemTra = new JButton("Kiểm tra");
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 20, 5, 5);
        f.add(btnKiemTra, gbc);
        
        btnKiemTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dem = 0;
                int trong = 0;
                String regexTen = "[a-zA-z ]+";
                String regexLuong = "\\d+";
                
                if (txtTen.getText().matches(regexTen)) {
                    dem++;
                }
                else {
                    JOptionPane.showMessageDialog(f, "Tên không đúng định dạng");
                    txtTen.setBackground(Color.yellow);
                }
                if (txtTen.getText().length() == 0) {
                    trong++;
                    txtTen.setBackground(Color.yellow);
                }
                
                try {
                    double luong = Double.parseDouble(txtLuong.getText());
                    if (luong < 0) {
                        JOptionPane.showMessageDialog(f, "Lương không thể âm");
                        txtLuong.setBackground(Color.yellow);
                    }
                    else {
                        txtLuong.setBackground(Color.white);
                        dem++;
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Lương không đúng định dạng");
                    txtLuong.setBackground(Color.yellow);
                }
                if (txtLuong.getText().length() == 0) {
                    trong++;
                    txtLuong.setBackground(Color.yellow);
                }
                
                try {
                    XDate.parse(txtNgaySinh.getText(), "dd-MM-yyyy");
                    txtNgaySinh.setBackground(Color.white);
                    dem++;
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Ngày sinh không đúng định dạng");
                    txtNgaySinh.setBackground(Color.yellow);
                }
                if (txtNgaySinh.getText().length() == 0) {
                    trong++;
                    txtNgaySinh.setBackground(Color.yellow);
                }
                
                if (dem == 3) {
                    JOptionPane.showMessageDialog(f, "Bạn đã nhập đúng");
                }
                if (trong >= 1) {
                    JOptionPane.showMessageDialog(f, "Không để trống ô");
                }
            }
        });
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
