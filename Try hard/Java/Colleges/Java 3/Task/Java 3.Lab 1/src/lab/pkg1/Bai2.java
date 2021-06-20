package lab.pkg1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bai2 {
    JFrame f;
    JPanel pTren, pDuoi;
    JLabel lblSo1, lblSo2, lblKetQua;
    JTextField txtSo1, txtSo2, txtKetQua;
    JButton btnCong, btnTru, btnNhan, btnChia;
    
    public static void main(String[] args) {
        Bai2 bai2 = new Bai2();
    }
    
    Bai2() {
        form();
        event();
    }
    
    public void toan(int chon) {
        try {
            double tinh = 0;
            double so1 = Double.parseDouble(txtSo1.getText());
            double so2 = Double.parseDouble(txtSo2.getText());
            switch (chon) {
                case 1: tinh = so1 + so2;
                    break;
                case 2: tinh = so1 - so2;
                    break;
                case 3: tinh = so1 * so2;
                    break;
                case 4: 
                    if (so2 == 0) {
                        JOptionPane.showMessageDialog(f, "Số bị chia không thể là số 0");
                    }
                    else {
                        tinh = so1 / so2;
                    }
                    break;
            }
            txtKetQua.setText(""+tinh);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Nhập sai, hãy nhập số vào các ô");
        }
    }
    
    public void form() {
        f = new JFrame("Bài 2");
        f.setLayout(new GridLayout(2,1));
        
        pTren = new JPanel();
        pTren.setLayout(new GridLayout(3, 2, 0, 10));
        
        Font font = new Font("", Font.PLAIN, 20);
        lblSo1 = new JLabel("Số 1");
        lblSo1.setFont(font);
        lblSo1.setHorizontalAlignment(JLabel.CENTER);
        lblSo2 = new JLabel("Số 2");
        lblSo2.setFont(font);
        lblSo2.setHorizontalAlignment(JLabel.CENTER);
        lblKetQua = new JLabel("Kết quả");
        lblKetQua.setFont(font);
        lblKetQua.setHorizontalAlignment(JLabel.CENTER);

        txtSo1 = new JTextField(15);
        txtSo1.setHorizontalAlignment(JTextField.CENTER);
        txtSo2 = new JTextField(15);
        txtSo2.setHorizontalAlignment(JTextField.CENTER);
        txtKetQua = new JTextField(15);
        txtKetQua.setHorizontalAlignment(JTextField.CENTER);
        txtKetQua.setEditable(false);
        
        pTren.add(lblSo1);
        pTren.add(txtSo1);
        pTren.add(lblSo2);
        pTren.add(txtSo2);
        pTren.add(lblKetQua);
        pTren.add(txtKetQua);

        FlowLayout fl = new FlowLayout();
        fl.setAlignment(2);
        fl.setHgap(20);
        fl.setVgap(20);
        pDuoi = new JPanel();
        pDuoi.setLayout(fl);
        
        btnCong = new JButton("+");
        btnTru = new JButton("-");
        btnNhan = new JButton("*");
        btnChia = new JButton("/");
        
        pDuoi.add(btnCong);
        pDuoi.add(btnTru);
        pDuoi.add(btnNhan);
        pDuoi.add(btnChia);
        
        f.add(pTren);
        f.add(pDuoi);
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void event() {
        
        btnCong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toan(1);
            }
        });

        btnTru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toan(2);
            }
        });
        
        btnNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toan(3);
            }
        });

        btnChia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toan(4);
            }
        });
    }
}
