package panel;

import dao.NhanVienDAO;
import helper.MailHelper;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import object.NhanVien;
import org.apache.commons.codec.digest.DigestUtils;

public class DoiMatKhau implements Khung {

    JDialog winDoiMatKhau;
    JLabel lblTaiKhoan, lblTenTaiKhoan, lblMatKhauCu, lblMatKhauMoi, lblXacNhan;
    JPasswordField txtMatKhauCu, txtMatKhauMoi, txtXacNhan;
    JButton btnXacNhan;
    GridBagConstraints gbc;
    String regexMatKhau = "\\w+";
    NhanVienDAO nvDAO;
    int trong;
    MailHelper mailHelper = new MailHelper();
    ManHinhChinh MHC = new ManHinhChinh();
    LoginPanel lg = new LoginPanel();
    
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
        nvDAO = new NhanVienDAO();

        lblTaiKhoan = new JLabel("Tài khoản");
        try {
            lblTenTaiKhoan = new JLabel(LoginPanel.loggingAcc + " - " + LoginPanel.whoIsLogin);
        } catch (Exception e) {
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
        Khung.addChung(winDoiMatKhau, gbc, a, 4, 2, new Insets(5, 5, 5, 5), 17);

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
                JPasswordField txt[] = {txtMatKhauMoi, txtXacNhan};
                trong = 0;

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
                        String matKhauCu = DigestUtils.md5Hex(txtMatKhauCu.getText());
                        NhanVien nv = nvDAO.findByTaiKhoan(LoginPanel.loggingAcc);
                        System.out.println("Mới Nhập : " + matKhauCu);
                        System.out.println("Cũ Trong : " + nv.getMatKhau());
                        if (nv.getMatKhau().equals(matKhauCu)) {
                            if (txtMatKhauMoi.getPassword().length <= 32) {
                                char matKhauChar[] = txtMatKhauMoi.getPassword();
                                String matKhauString = "";
                                for (int i = 0; i < matKhauChar.length; i++) {
                                    matKhauString = "" + matKhauString + matKhauChar[i];
                                }
                                if (matKhauString.matches(regexMatKhau)) {
                                    if (Arrays.equals(txtMatKhauMoi.getPassword(), txtXacNhan.getPassword())) {
                                        nvDAO.updateMatKhau(matKhauString, String.valueOf(nv.getMaNV()));
                                        //send mail
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String to = nv.getEmail();
                                                String sub = "Thông Báo Cập Nhật Mật Khẩu Thành Công !";
                                                String content = "Xin Chào " + nv.getTenNV() + "! \n"
                                                        + "\n Tài khoản của bạn đã thay đổi mật khẩu. Chúng tôi gửi email xác nhận thông tin của bạn:"
                                                        + "\n  "
                                                        + "\n 1,  Mã Số Nhân Viên: " + nv.getMaNV()
                                                        + "\n 2,  Họ và Tên: " + nv.getTenNV()
                                                        + "\n 3,  Chức Vụ: " + (nv.isChucVu() == true ? "Trưởng Kho" : "Nhân Viên")
                                                        + "\n 4,  Tài Khoản Đăng Nhập: " + nv.getTaiKhoan()
                                                        + "\n 5,  Mật Khẩu Mới Cập Nhật: " + txtXacNhan.getText()
                                                        + "\n 6,  Email Bạn Đã Đăng Ký: " + nv.getEmail()
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
                                        JOptionPane.showMessageDialog(winDoiMatKhau, "Đổi Mật Khẩu Thành Công");
                                        winDoiMatKhau.dispose();
                                    } else {
                                        txtXacNhan.requestFocus();
                                        JOptionPane.showMessageDialog(winDoiMatKhau, "Mật khẩu xác nhận không đúng");
                                    }
                                } else {
                                    txtMatKhauMoi.requestFocus();
                                    JOptionPane.showMessageDialog(winDoiMatKhau, "Mật khẩu chỉ được phép chứa số, chữ, chữ hoa và '_'");
                                }
                            } else {
                                txtMatKhauMoi.requestFocus();
                                JOptionPane.showMessageDialog(winDoiMatKhau, "Mật khẩu chỉ được phép chứa 32 kí tự");
                            }
                        } else {
                            txtMatKhauCu.requestFocus();
                            JOptionPane.showMessageDialog(winDoiMatKhau, "Mật khẩu cũ không đúng");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
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
