package lab.pkg2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Bai2 {
    JFrame f;
    JScrollPane spKetQua, spPhepToan;
    JTextField txtKetQua;
    JTextArea txtPhepToan;
    JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAm, btnC, btnCong, btnTru, btnNhan, btnChia, btnCan, btnBang, btnDu, btnMotTrenX;
    JPanel pPhim;
    double so1, so2;
    int x, y, z;
    List list = new ArrayList();
    DecimalFormat df = new DecimalFormat("#.#######");
    
    public static void main(String[] args) {
        new Bai2();
    }
    
    Bai2() {
        form();
        event();
    }
    
    public void form() {
        f = new JFrame("Bài 2");
        f.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        txtPhepToan = new JTextArea(2, 19);
        txtPhepToan.setFont(new Font("", Font.PLAIN, 20));
        txtPhepToan.setEditable(false);
        spPhepToan = new JScrollPane(txtPhepToan);
        spPhepToan.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        f.add(spPhepToan, gbc);
        
        txtKetQua = new JTextField();
        txtKetQua.setPreferredSize(new Dimension(300, 50));
        txtKetQua.setFont(new Font("", Font.PLAIN, 40));
        txtKetQua.setText("0");
        txtKetQua.setHorizontalAlignment(JTextField.RIGHT);
        txtKetQua.setEditable(false);
        spKetQua = new JScrollPane(txtKetQua);
        gbc.gridy = 1;
        f.add(spKetQua, gbc);
        
        pPhim = new JPanel();
        pPhim.setLayout(new GridLayout(4, 5, 5, 5));
        gbc.gridy = 2;
        f.add(pPhim, gbc);
        
        btn1 = new JButton("1");
        pPhim.add(btn1);
        btn2 = new JButton("2");
        pPhim.add(btn2);
        btn3 = new JButton("3");
        pPhim.add(btn3);
        btnChia = new JButton("/");
        pPhim.add(btnChia);
        btnCan = new JButton("sqrt");
        pPhim.add(btnCan);
        btn4 = new JButton("4");
        pPhim.add(btn4);
        btn5 = new JButton("5");
        pPhim.add(btn5);
        btn6 = new JButton("6");
        pPhim.add(btn6);
        btnNhan = new JButton("*");
        pPhim.add(btnNhan);
        btnDu = new JButton("%");
        pPhim.add(btnDu);
        btn7 = new JButton("7");
        pPhim.add(btn7);
        btn8 = new JButton("8");
        pPhim.add(btn8);
        btn9 = new JButton("9");
        pPhim.add(btn9);
        btnTru = new JButton("-");
        pPhim.add(btnTru);
        btnMotTrenX = new JButton("1/x");
        pPhim.add(btnMotTrenX);
        btn0 = new JButton("0");
        pPhim.add(btn0);
        btnAm = new JButton("+/-");
        pPhim.add(btnAm);
        btnC = new JButton("C");
        pPhim.add(btnC);
        btnCong = new JButton("+");
        pPhim.add(btnCong);
        btnBang = new JButton("=");
        pPhim.add(btnBang);
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void event() {
        phimSo(btn1, "1");
        phimSo(btn2, "2");
        phimSo(btn3, "3");
        phimSo(btn4, "4");
        phimSo(btn5, "5");
        phimSo(btn6, "6");
        phimSo(btn7, "7");
        phimSo(btn8, "8");
        phimSo(btn9, "9");
        phimSo(btn0, "0");
        
        btnAm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x == 0) {
                    if (so1 != 0) {
                        if (z == 1) {
                            txtKetQua.setText("-");
                            so1 = 0;
                            z = 0;
                        }
                        else {
                            so1 = so1/-1;
                            txtKetQua.setText(df.format(so1));
                        }
                    }
                    else {
                        txtKetQua.setText("-");
                    }
                }
                else {
                    if (so2 != 0) {
                        so2  = so2/-1;
                        txtKetQua.setText(df.format(so2));
                    }
                    else {
                        txtKetQua.setText("-");
                    }
                }

// bấm = rồi thì muốn bấm số nữa thì các ô kết quả phải hiện ra số đấy
// chỉ để bấm đc nút âm
// bấm 8 rồi bấm - liên tục không ra j, bấm mỗi nút âm liên tục cũng không ra j
            }
        });
        
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPhepToan.setText("");
                txtKetQua.setText("0");
                x = y = z = 0;
                so1 = so2 = 0;
                list.clear();
            }
        });
        
        phimTinh(btnCong, "+");
        phimTinh(btnTru, "-");
        phimTinh(btnNhan, "*");
        phimTinh(btnChia, "/");
        
        conLai(btnCan, "sqrt(x)");
        conLai(btnMotTrenX, "1/x");
        conLai(btnDu, "%(x)");
        
        btnBang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // bấm 5 rồi bấm = luôn nó chưa đưa đc cái j vào list nên là có exception
                /* bấm 5+5=, ra kết quả bấm tiếp 6 =, trong calculator bấm = liên tục nó ra 11, 16, ...
                    còn mình bấm = liên tục không ra j
                */
                try {
                    phepTinh((String) list.get(x-1));
                    txtPhepToan.setText(txtPhepToan.getText() + " " + df.format(so2));
                    txtKetQua.setText(df.format(so1));
                }
                catch (Exception ex) {
                }
                // bấm = sạc toàn bộ về ban đầu trừ so1 để còn lấy đấy mà cộng tiếp
                so1 = Double.parseDouble(txtKetQua.getText());
                so2 = 0;
                x = y = 0;
                list.clear();
                z = 1; // bấm 5 + 5 = , bấm tiếp số nữa
            }
        });
    }
    
    public void phimSo(JButton btn, String nhap) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y = 0;
                if (x == 0) {
                    if (txtKetQua.getText().equals("0")) {
                        txtKetQua.setText(nhap);
                        so1 = Double.parseDouble(txtKetQua.getText());
                        z = 0; // bấm = rồi bấm 5 rồi bấm 6
                    }
                    else { // bắt đc lỗi bấm 5 + = của calculator(bấm = liên tục là nó cứ tự tính)
                        if (z == 1) {
                            txtKetQua.setText(nhap);
                            so1 = Double.parseDouble(txtKetQua.getText());
                            z = 0;
                        }
                        else {
                            txtKetQua.setText(txtKetQua.getText() + nhap);
                            so1 = Double.parseDouble(txtKetQua.getText());
                        }
                    }
                }
                else {
                    if (so2 == 0 && !txtKetQua.getText().equals("-")) {
                        txtKetQua.setText(nhap);
                        so2 = Double.parseDouble(txtKetQua.getText());
                    }
                    else {
                        txtKetQua.setText(txtKetQua.getText() + nhap);
                        so2 = Double.parseDouble(txtKetQua.getText());
                    }
                }
            }
        });
    }

    public void phimTinh(JButton btn, String phepToan) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (y == 0) { // bấm phím số rồi bấm phím + liên tục sẽ không add phím + vào list
                    list.add(phepToan);
                    if (x > 0) {
                        phepTinh((String) list.get(x-1));
                        txtPhepToan.setText(txtPhepToan.getText() + " " + df.format(so2) + " " + phepToan);
                        so2 = 0;
                        txtKetQua.setText(df.format(so1));
                    }
                    else {
                        txtPhepToan.setText(df.format(so1) + " " + phepToan);
                    }
                    x++;
                }
                else {
                    list.set(x-1, phepToan); // đang bấm + liên tục chuyển sang bấm trừ thì sẽ thay thế + thành - trong list
                    int a = txtPhepToan.getText().length();
                    try {
                        txtPhepToan.setText(txtPhepToan.getText(0, a-1) + phepToan);
                    }
                    catch (Exception ex) {
                    }
                }
                y = 1;
            }
        });
    }
    
    public void phepTinh(String phepTinh) {
        switch (phepTinh) {
            case "+": 
                so1 += so2;
                so1 = Math.ceil(so1) - so1 >= 0.5 ? Math.ceil(so1*10000000) / 10000000 : Math.floor(so1*10000000) / 10000000;
                break;
            case "-": 
                so1 -= so2;
                so1 = Math.ceil(so1) - so1 >= 0.5 ? Math.ceil(so1*10000000) / 10000000 : Math.floor(so1*10000000) / 10000000;
                break;
            case "*": 
                so1 *= so2;
                so1 = Math.ceil(so1) - so1 >= 0.5 ? Math.ceil(so1*10000000) / 10000000 : Math.floor(so1*10000000) / 10000000;
                break;
            case "/": 
                so1 /= so2;
                so1 = Math.ceil(so1) - so1 >= 0.5 ? Math.ceil(so1*10000000) / 10000000 : Math.floor(so1*10000000) / 10000000;
                break;
            case "sqrt(x)": 
                so1 = Math.sqrt(so1);
                so1 = Math.ceil(so1) - so1 >= 0.5 ? Math.ceil(so1*10000000) / 10000000 : Math.floor(so1*10000000) / 10000000;
                break;
            case "%(x)": 
                so1 /= 100;
                so1 = Math.ceil(so1) - so1 >= 0.5 ? Math.ceil(so1*10000000) / 10000000 : Math.floor(so1*10000000) / 10000000;
                break;
            case "1/x": 
                so1 = 1/so1;
                so1 = Math.ceil(so1) - so1 >= 0.5 ? Math.ceil(so1*10000000) / 10000000 : Math.floor(so1*10000000) / 10000000;
                break;
        }
    }
    
    public void conLai(JButton btn, String conLai) {
        btn.addActionListener(new ActionListener() {
            @Override
            // bấm 2 + 2 sqrt 5 calculator có lỗi
            // bấm 2 + 2 + sqrt vẫn ngon(ra 4)
            // bấm 1 + sqrt 4 ra lỗi
            // bấm 4 sqrt 2 ra lỗi
            public void actionPerformed(ActionEvent e) {
                if (x == 0) {
                    txtPhepToan.setText(conLai.replaceAll("x", df.format(so1)));
                    phepTinh(conLai);
                    txtKetQua.setText(df.format(so1));
                    z = 1;
                }
            }
        });
    }
}
