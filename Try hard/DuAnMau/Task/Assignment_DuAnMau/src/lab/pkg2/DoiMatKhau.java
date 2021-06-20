package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class DoiMatKhau extends Khung {
    JDialog winDoiMatKhau;
    JLabel lblTaiKhoan, lblTenTaiKhoan, lblMatKhauCu, lblMatKhauMoi, lblXacNhan;
    JPasswordField txtMatKhauCu, txtMatKhauMoi, txtXacNhan;
    JButton btnXacNhan;
    GridBagConstraints gbc;
    String regexMatKhau = "\\w+";

    public DoiMatKhau() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
//    public static void main(String[] args) {
//        new DoiMatKhau();
//    }
    
    @Override
    public void taoDoiTuong() {
        winDoiMatKhau = new JDialog();
        gbc = new GridBagConstraints();
        
        lblTaiKhoan = new JLabel("Tài khoản");
        try {
            pst = con.prepareStatement("select * from nhanvien where taikhoan = ?");
            pst.setString(1, loggingAcc);
            rs1 = pst.executeQuery();
            rs1.next();
            lblTenTaiKhoan = new JLabel(loggingAcc + " - " + rs1.getString(4));
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        lblMatKhauCu = new JLabel("Mật khẩu cũ");
        lblMatKhauMoi = new JLabel("Mật khẩu mới");
        lblXacNhan = new JLabel("Xác nhận mật khẩu");
        
        txtMatKhauCu = new JPasswordField(20);
        txtMatKhauMoi = new JPasswordField(20);
        txtXacNhan = new JPasswordField(20);
        
        btnXacNhan = new JButton("Xác nhận");
    }

    @Override
    public void taoDang() {
        winDoiMatKhau.setLayout(new GridBagLayout());
        winDoiMatKhau.setModal(true);
        winDoiMatKhau.setTitle("Đổi mật khẩu");
        
        lblTenTaiKhoan.setFont(new Font("tahoma", Font.BOLD, 15));
    }

    @Override
    public void add() {
        JComponent a[] = {lblTaiKhoan, lblMatKhauCu, lblMatKhauMoi, lblXacNhan, lblTenTaiKhoan, txtMatKhauCu, txtMatKhauMoi, txtXacNhan};
        addChung(winDoiMatKhau, gbc, a, 4, 2, new Insets(5, 5, 5, 5), 17);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        winDoiMatKhau.add(btnXacNhan, gbc);
        
        winDoiMatKhau.pack();
        winDoiMatKhau.setLocationRelativeTo(null);
        winDoiMatKhau.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        winDoiMatKhau.setVisible(true);
    }

    @Override
    public void event() {
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField txt[] = {txtMatKhauCu, txtMatKhauMoi, txtXacNhan};
                trong  = 0;
                
                for (int i = 0; i < txt.length; i++) {
                    if (txt[i].getPassword().length == 0) {
                        trong++;
                        txt[i].requestFocus();
                        JOptionPane.showMessageDialog(winDoiMatKhau, "Bạn hãy nhập đủ dữ liệu vào tất cả các ô");
                        break;
                    }
                }
                
                if (trong == 0) {
                    try {
                        pst = con.prepareStatement("select * from nhanvien where taikhoan = ?");
                        pst.setString(1, loggingAcc);
                        rs1 = pst.executeQuery();
                        
                        while (rs1.next()) {
                            if (Arrays.equals(rs1.getString(3).toCharArray(), txtMatKhauCu.getPassword())) {
                                if (txtMatKhauMoi.getPassword().length <= 20) {
                                    char matKhauChar[] = txtMatKhauMoi.getPassword();
                                    String matKhauString = "";
                                    for (int i = 0; i < matKhauChar.length; i++) {
                                        matKhauString = "" + matKhauString + matKhauChar[i];
                                    }
                                    if (matKhauString.matches(regexMatKhau)) {
                                        if (Arrays.equals(txtMatKhauMoi.getPassword(), txtXacNhan.getPassword())) {
                                            pst = con.prepareStatement("update nhanvien set matkhau = ? where manv = ?");
                                            pst.setString(1, matKhauString);
                                            pst.setString(2, rs1.getString(1));
                                            pst.executeUpdate();
                                            JOptionPane.showMessageDialog(winDoiMatKhau, "Đổi thành công");
                                            winDoiMatKhau.dispose();
                                        }
                                        else {
                                            txtXacNhan.requestFocus();
                                            JOptionPane.showMessageDialog(winDoiMatKhau, "Mật khẩu xác nhận không đúng");
                                        }
                                    }
                                    else {
                                        txtMatKhauMoi.requestFocus();
                                        JOptionPane.showMessageDialog(winDoiMatKhau, "Mật khẩu chỉ được phép chứa số, chữ, chữ hoa và '_'");
                                    }
                                }
                                else {
                                    txtMatKhauMoi.requestFocus();
                                    JOptionPane.showMessageDialog(winDoiMatKhau, "Mật khẩu chỉ được phép chứa 20 kí tự");
                                }
                            }
                            else {
                                txtMatKhauCu.requestFocus();
                                JOptionPane.showMessageDialog(winDoiMatKhau, "Mật khẩu cũ không đúng");
                            }
                        }
                    } 
                    catch (Exception ex) {
                    }
                }
            }
        });
        
        txtMatKhauCu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnXacNhan.doClick();
                }
            }
        });

        txtMatKhauMoi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnXacNhan.doClick();
                }
            }
        });

        txtXacNhan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnXacNhan.doClick();
                }
            }
        });
    }
}
