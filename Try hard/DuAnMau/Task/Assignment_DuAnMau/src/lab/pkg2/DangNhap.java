package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class DangNhap extends Khung {
    JLabel lblTitle, lblImage, lblMaNV, lblMatKhau;
    JTextField txtMaNV;
    JPasswordField txtMatKhau;
    JButton btnDangNhap, btnKetThuc;
    GridBagConstraints gbc;
    JPanel pnlForm;
    JDialog dialogDangNhap;

    public DangNhap() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }

    @Override
    public void taoDoiTuong() {
        dialogDangNhap = new JDialog();
        lblImage = new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\Hinh\\security.png"));
        lblTitle = new JLabel("Đăng nhập");
        lblMaNV = new JLabel("Tên đăng nhập");
        lblMatKhau = new JLabel("Mật khẩu");
        txtMaNV = new JTextField(30);
        txtMatKhau = new JPasswordField(30);
        btnDangNhap = new JButton("Đăng nhập");
        btnKetThuc = new JButton("Kết thúc");
        pnlForm = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
    }

    @Override
    public void taoDang() {
        dialogDangNhap.setLayout(new GridBagLayout());
        dialogDangNhap.setUndecorated(true);
        dialogDangNhap.setTitle("Đăng nhập");

        lblTitle.setFont(new Font("tahoma", Font.BOLD, 18));
        lblTitle.setForeground(new Color(0, 102, 51));
    }

    @Override
    public void add() {
        JComponent a[] = {lblTitle, lblMaNV, txtMaNV, lblMatKhau, txtMatKhau, btnDangNhap};
        addChung(pnlForm, gbc, a, 6, 1, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);

        gbc.insets = new Insets(5, 120, 5, 5);
        pnlForm.add(btnKetThuc, gbc);

        JComponent b[] = {lblImage, pnlForm};
        addChung(dialogDangNhap, gbc, b, 1, 2, new Insets(5, 5, 5, 5), 17);

        dialogDangNhap.pack();
        dialogDangNhap.setLocationRelativeTo(null);
        dialogDangNhap.setVisible(true);
    }

    @Override
    public void event() {
        lienKetSQL();

        txtMaNV.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnDangNhap.doClick();
                }
            }
        });

        txtMatKhau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnDangNhap.doClick();
                }
            }
        });

        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtMaNV.getText().isEmpty() || txtMatKhau.getPassword().length == 0) {
                        if (txtMaNV.getText().isEmpty()) {
                            txtMaNV.requestFocus();
                        }
                        else if (txtMatKhau.getPassword().length == 0) {
                            txtMatKhau.requestFocus();
                        }
                        JOptionPane.showMessageDialog(dialogDangNhap, "Bạn hãy điền đủ các ô tài khoản và mật khẩu");
                    }
                    else {
                        pst = con.prepareStatement("select * from nhanvien where taikhoan = ?");
                        pst.setString(1, txtMaNV.getText());
                        rs1 = pst.executeQuery();
                        boolean dungTaiKhoan = rs1.next();
                        
                        if (dungTaiKhoan == false) {
                            JOptionPane.showMessageDialog(dialogDangNhap, "Tên đăng nhập không tồn tại");
                        }
                        else {
                            if (Arrays.equals(rs1.getString(3).toCharArray(), txtMatKhau.getPassword())) {
                                if (rs1.getBoolean(5) == false) {
                                    truongPhong = false;
                                } 
                                else {
                                    truongPhong = true;
                                }
                                dungTaiKhoan = true;
                                dialogDangNhap.dispose();
                                loggingAcc = txtMaNV.getText();
                                new MainJFrame();
                            }
                            else {
                                JOptionPane.showMessageDialog(dialogDangNhap, "Sai mật khẩu");
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        });

        btnKetThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
