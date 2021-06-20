package lab.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Bai3 {
    JFrame f;
    JLabel lblTen, lblMatKhau, lblNhapLai;
    JButton btnDangKy, btnThoat;
    JTextField txtTen;
    JPasswordField passwordFieldMatKhau, passwordFieldNhapLai;
    JPanel pTren, pDuoi;
    
    public static void main(String[] args) {
        Bai3 bai3 = new Bai3();
    }
    
    Bai3() {
        form();
        event();
    }
    
    public void form() {
        f = new JFrame("Bài 3");
        f.setLayout(new GridLayout(2,1));
        
        Font font = new Font("", Font.PLAIN, 20);
        lblTen = new JLabel("Tên");
        lblTen.setFont(font);
        lblTen.setHorizontalAlignment(JLabel.CENTER);
        lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setFont(font);
        lblMatKhau.setHorizontalAlignment(JLabel.CENTER);
        lblNhapLai = new JLabel("Nhập lại");
        lblNhapLai.setFont(font);
        lblNhapLai.setHorizontalAlignment(JLabel.CENTER);
        
        txtTen = new JTextField(15);
        txtTen.setHorizontalAlignment(JTextField.CENTER);
        passwordFieldMatKhau = new JPasswordField(15);
        passwordFieldMatKhau.setHorizontalAlignment(JTextField.CENTER);
        passwordFieldNhapLai = new JPasswordField(15);
        passwordFieldNhapLai.setHorizontalAlignment(JTextField.CENTER);
        
        pTren = new JPanel();
        pTren.setLayout(new GridLayout(3, 2, 0, 10));
        pTren.add(lblTen);
        pTren.add(txtTen);
        pTren.add(lblMatKhau);
        pTren.add(passwordFieldMatKhau);
        pTren.add(lblNhapLai);
        pTren.add(passwordFieldNhapLai);
        
        btnDangKy = new JButton("Đăng ký");
        btnThoat = new JButton("Thoát");
        
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.RIGHT);
        fl.setHgap(20);
        fl.setVgap(20);
        pDuoi = new JPanel();
        pDuoi.setLayout(fl);
        pDuoi.add(btnDangKy);
        pDuoi.add(btnThoat);
        
        f.add(pTren);
        f.add(pDuoi);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void event() {
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnDangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordFieldMatKhau.getText().equals(passwordFieldNhapLai.getText())) {
                    JOptionPane.showMessageDialog(f, "Đăng ký thành công");
                }
                else {
                    JOptionPane.showMessageDialog(f, "Mời bạn kiểm tra lại mật khẩu");
                }
            }
        });
    }
}
