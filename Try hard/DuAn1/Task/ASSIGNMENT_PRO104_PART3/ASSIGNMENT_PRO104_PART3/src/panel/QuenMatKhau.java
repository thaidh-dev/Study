package panel;

import dao.NhanVienDAO;
import helper.MailHelper;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import object.NhanVien;

public class QuenMatKhau implements Khung {

    JDialog winQenMatKhau;
    JLabel lblTaiKhoan, lblHoTen, lblEmail;
    JTextField txtHoTen, txtTaiKhoan, txtEmail;
    JButton btnXacNhan, btnHuy;
    GridBagConstraints gbc;
    NhanVienDAO nvDAO;
    NhanVien nv;
    MailHelper mailHelper = new MailHelper();

    public QuenMatKhau() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }

//    public static void main(String[] args) {
//        new QuenMatKhau();
//    }
    @Override
    public void taoDoiTuong() {
        winQenMatKhau = new JDialog();
        gbc = new GridBagConstraints();
        nvDAO = new NhanVienDAO();
//        lblTit = new JLabel("Thông Tin Tài Khoản");
        lblHoTen = new JLabel("Họ Tên");
        lblTaiKhoan = new JLabel("Tài khoản");
        lblEmail = new JLabel("Email");

        txtHoTen = new JTextField(20);
        txtTaiKhoan = new JTextField(20);
        txtEmail = new JTextField(20);

        btnXacNhan = new JButton("Xác nhận");
        btnHuy = new JButton("Huỷ Bỏ");
    }

    @Override
    public void taoDang() {
        winQenMatKhau.setLayout(new GridBagLayout());
        winQenMatKhau.setModal(true);
        winQenMatKhau.setTitle("Qên Mật Khẩu");
        btnHuy.setPreferredSize(btnXacNhan.getPreferredSize());
//        lblTit.setFont(new Font("tahoma", 1, 16));
//        lblTit.setForeground(Color.blue);
    }

    @Override
    public void add() {
        JComponent a[] = {lblHoTen, lblTaiKhoan, lblEmail, txtHoTen, txtTaiKhoan, txtEmail};
        Khung.addChung(winQenMatKhau, gbc, a, 3, 2, new Insets(5, 5, 5, 5), 17);

        gbc.gridx = 1;
        gbc.gridy = 3;

        winQenMatKhau.add(btnXacNhan, gbc);
        gbc.insets = new Insets(5, 100, 5, 5);
        winQenMatKhau.add(btnHuy, gbc);

        winQenMatKhau.pack();
        winQenMatKhau.setLocationRelativeTo(null);
        winQenMatKhau.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        winQenMatKhau.setVisible(true);
    }

    @Override
    public void event() {
        txtHoTen.setText("");
        txtTaiKhoan.setText("");
        txtEmail.setText("");

        // <editor-fold defaultstate="collapsed" desc="Gợi ý email ">
        txtEmail.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Set<String> s = new TreeSet<>();
                s.add("gmail.com");
                s.add("fpt.edu.vn");

                String text = txtEmail.getText();
                int lengthText = text.length();
                int lengthCheck = text.indexOf("@");
                int n = lengthText - lengthCheck - 1;

                for (String data : s) {
                    String str = "";

                    if (lengthCheck >= 0) {
                        str = text.substring(0, lengthCheck + 1);

                        for (int j = 0; j < n; j++) {
                            if (n < data.length()) {
                                str += data.charAt(j);
                            }
                        }
                    }

                    if (str.equals(text) && str.length() > 0 && e.getKeyCode() != 8) {
                        txtEmail.setText(text + data.substring(n));
                        txtEmail.setSelectionStart(lengthText);
                        txtEmail.setSelectionEnd(data.length() + lengthText);
                    }
                }
            }
        });
        // </editor-fold>

        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String tk = txtTaiKhoan.getText();
                    String email = txtEmail.getText();

                    nv = nvDAO.findByTaiKhoan(tk);

                    if (checkNull()) {
                        //send mail
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String to = email;
                                String sub = "Thông Báo Khôi Phục Mật Khẩu Thành Công !";
                                String content = "Xin Chào " + txtHoTen.getText() + "! \n"
                                        + "\n Tài khoản của bạn đã được khôi phục về mật khẩu mặc định ban đầu. Chúng tôi gửi email xác nhận thông tin của bạn:"
                                        + "\n  "
                                        + "\n 1,  Mã Số Nhân viên: " + nv.getMaNV()
                                        + "\n 2,  Họ và tên: " + nv.getTenNV()
                                        + "\n 3,  Chức Vụ: " + (nv.isChucVu() == true ? "Trưởng Kho" : "Nhân Viên")
                                        + "\n 4,  Tài Khoản Đăng Nhập: " + txtTaiKhoan.getText()
                                        + "\n 5,  Mật Khẩu Đăng Nhập Mặc Định: " + "123456"
                                        + "\n 6,  Email Bạn Đã Đăng Ký: " + txtEmail.getText()
                                        + "\n  "
                                        + "\n  Lưu ý: Bạn nhớ thay đổi mật khẩu của mình ở lần đầu tiên đăng nhập vào phần mềm quản lý thư viện."
                                        + "\n  "
                                        + "\n  Chúc bạn có một ngày làm việc thật nhiều hứng khởi và thành công\n"
                                        + "\n"
                                        + " Mọi thắc mắc vui lòng liên hệ lại cho chúng tôi qua email: mrrthanhbeo@gmail.com or Điện thoại số: 0976566686\n"
                                        + "\n"
                                        + "\n  "
                                        + "\n  Trân Trọng";
                                try {
                                    mailHelper.sendMail(to, sub, content);
                                } catch (MessagingException ex) {
                                    System.out.println(ex);
                                }
                            }
                        }).start();
                        nvDAO.updateAutoMK(nv.getMaNV());
                        JOptionPane.showMessageDialog(null, "Khôi Phục Mật Khẩu Tài Khoản " + txtTaiKhoan.getText() + " Thành Công ! Mời Bạn Kiểm Tra Email Để Lấy Mật Khẩu !", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                        winQenMatKhau.dispose();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });

    }

    private boolean checkNull() throws SQLException {
        if (!nvDAO.checkTrungTK(txtTaiKhoan.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Không Có Tài Khoản Này Trong Danh Sách Nhân Viên Mời Bạn Nhập Lại!", "Xác Thực Tài Khoản", JOptionPane.WARNING_MESSAGE);
            txtTaiKhoan.requestFocus();
            return false;
        } else if (txtHoTen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Họ Tên Nhân Viên !", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtHoTen.requestFocus();
            return false;
        } else if (!txtHoTen.getText().equals(nv.getTenNV())) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Đúng Họ Tên Đã Đăng Ký Tài Khoản " + nv.getTaiKhoan() + " !", "Xác Thực Tên Nhân Viên", JOptionPane.WARNING_MESSAGE);
            txtHoTen.requestFocus();
            return false;
        } else if (txtTaiKhoan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Tài Khoản Nhân Viên !", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtTaiKhoan.requestFocus();
            return false;
        } else if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Email Nhân Viên !", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        } else if (!txtEmail.getText().equals(nv.getEmail())) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Đúng Email Đã Đăng Ký Của Tài Khoản " + nv.getTaiKhoan() + " ! ", "Xác Thực Email", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void fillToTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showItem(int a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editOn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editOff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
