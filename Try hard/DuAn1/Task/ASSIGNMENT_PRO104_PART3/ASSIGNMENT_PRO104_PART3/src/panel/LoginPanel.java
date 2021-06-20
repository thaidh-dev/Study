package panel;

import dao.NhanVienDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import object.NhanVien;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel box1, box2, pn1, pn2, pn3, pnMain;
	public  JLabel lblTitle, lblLogo, lblTaiKhoan, lblMatKhau, lblQen;
	public JTextField txtTaiKhoan;
	public JPasswordField txtMatKhau;
	public JButton btnDangNhap, btnKetThuc;
	private ImageIcon icLogo, icDangNhap, icKetThuc;

	private NhanVienDAO nhanVienDAO = new NhanVienDAO();
        private List<NhanVien> listNV;
	// TODO remove init value later
	public static boolean haveTruongPhong;
	public static boolean existedAccount;
	public static boolean isNhanVien;
	public static String whoIsLogin;
	public static String loggingAcc;
        public static String idIsLogin;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {

		this.setLayout(new BorderLayout());
		/// Tạo Title
		lblTitle = new JLabel("Đăng Nhập");
		lblTitle.setPreferredSize(new Dimension(230, 70));
		lblTitle.setFont(new Font("Segoe UI", 1, 38));
		lblTitle.setForeground(new java.awt.Color(0, 102, 51));
		pn1 = new JPanel(new FlowLayout());
		pn1.setPreferredSize(new Dimension(600, 70));
		pn1.setBackground(Color.white);
		pn1.add(lblTitle);

		///// Tạo Logo
		icLogo = new ImageIcon("src/Icon/security.png");
		lblLogo = new JLabel(icLogo);
		lblLogo.setPreferredSize(new Dimension(175, 200));

		box1 = new JPanel(new FlowLayout(0, 0, 30));
		box1.setPreferredSize(new Dimension(175, 260));
		box1.setBackground(Color.white);
		box1.add(lblLogo);

		//// Form Đăng Nhập
		lblTaiKhoan = new JLabel("Tài Khoản");
		lblTaiKhoan.setForeground(new java.awt.Color(0, 102, 51));
		lblMatKhau = new JLabel("Mật Khẩu");
		lblMatKhau.setForeground(new java.awt.Color(0, 102, 51));
		lblTaiKhoan.setPreferredSize(new Dimension(330, 50));
		lblTaiKhoan.setFont(new Font("Tahoma", 2, 20));
		lblMatKhau.setPreferredSize(lblTaiKhoan.getPreferredSize());
		lblMatKhau.setFont(lblTaiKhoan.getFont());

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setPreferredSize(lblTaiKhoan.getPreferredSize());
		txtTaiKhoan.setFont(new Font("Segoe UI", 1, 20));
		txtMatKhau = new JPasswordField();
		txtMatKhau.setPreferredSize(lblTaiKhoan.getPreferredSize());
		txtMatKhau.setFont(txtTaiKhoan.getFont());

		box2 = new JPanel(new FlowLayout(10, 10, 8));
		box2.setPreferredSize(new Dimension(350, 260));
		box2.setBackground(Color.white);
		box2.add(lblTaiKhoan);
		box2.add(txtTaiKhoan);
		box2.add(lblMatKhau);
		box2.add(txtMatKhau);

		pn2 = new JPanel(new FlowLayout(0, 25, 0));
		pn2.setPreferredSize(new Dimension(600, 260));
		pn2.setBackground(Color.white);
		pn2.add(box1);
		pn2.add(box2);

		icDangNhap = new ImageIcon("src/Icon/Unlock.png");
		btnDangNhap = new JButton("  Đăng Nhập", icDangNhap);
		btnDangNhap.setPreferredSize(new Dimension(190, 50));
		btnDangNhap.setFont(new Font("tahoma", 1, 20));
		btnDangNhap.setForeground(new java.awt.Color(30, 92, 102));
		btnDangNhap.setBackground(new java.awt.Color(0, 157, 110));

		icKetThuc = new ImageIcon("src/Icon/Stop.png");
		btnKetThuc = new JButton("  Kết Thúc", icKetThuc);
		btnKetThuc.setPreferredSize(btnDangNhap.getPreferredSize());
		btnKetThuc.setFont(btnDangNhap.getFont());
		btnKetThuc.setForeground(new java.awt.Color(153, 0, 0));
		btnKetThuc.setBackground(new java.awt.Color(255, 153, 153));
                
                lblQen = new JLabel("Quên Mật Khẩu ?");
                lblQen.setForeground(Color.GRAY);
                lblQen.setFont(new Font("tahoma", 1, 17));
                
		pn3 = new JPanel(new FlowLayout(1, 60, 15));
		pn3.setPreferredSize(new Dimension(600, 117));
		pn3.setBackground(Color.white);
		pn3.add(btnDangNhap);
		pn3.add(btnKetThuc);
                pn3.add(lblQen);
		pnMain = new JPanel(new BorderLayout());
		pnMain.setPreferredSize(new Dimension(600, 480));
		pnMain.add(pn1, BorderLayout.NORTH);
		pnMain.add(pn2, BorderLayout.CENTER);
		pnMain.add(pn3, BorderLayout.SOUTH);

		this.add(pnMain);

	}

	public boolean authentication() {
		listNV = nhanVienDAO.select();
		for (NhanVien nv : listNV) {
			if (nv.isChucVu()) {
				haveTruongPhong = true;
			} else {
				haveTruongPhong = false;
			}
		}
		for (NhanVien nv : listNV) {
			if (nv.getTaiKhoan().equals(txtTaiKhoan.getText())) {
				existedAccount = true;
				break;
			} else {
				existedAccount = false;
			}
		}
		for (NhanVien nv : listNV) {
                    String result = DigestUtils.md5Hex(txtMatKhau.getText());
			if (nv.getTaiKhoan().equals(txtTaiKhoan.getText())) {
				if (nv.getMatKhau().equals(String.valueOf(result))) {
                                    System.out.println(nv.getMatKhau());
					whoIsLogin = nv.getTenNV();
                                        idIsLogin = nv.getMaNV();
                                        loggingAcc = nv.getTaiKhoan();
					if (nv.isChucVu()) {
						isNhanVien = false;
						System.out.println("Login as 'Trưởng Phòng'");
						System.out.println("Login as '" + whoIsLogin + "' with id: " + idIsLogin);
					} else {
						isNhanVien = true;
						System.out.println("Login as 'Nhan vien'");
						System.out.println("Login as '" + whoIsLogin + "' with id: " + idIsLogin);
					}
					System.out.println(isNhanVien);
					return true;
				}
			}
		}
		return false;
	}

}