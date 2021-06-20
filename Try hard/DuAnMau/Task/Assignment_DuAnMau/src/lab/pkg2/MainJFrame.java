package lab.pkg2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class MainJFrame extends Khung {
    JFrame mainJFrame;
    JList lstLeft;
    JPanel pnlRight;
    JSplitPane spl;
    CardLayout cl;
    QuanLyNhanVien nv;
    QuanLyChuyenDe cd;
    QuanLyNguoiHoc nh;
    QuanLyKhoaHoc kh;
    ThongKe tk;
    Chao c;

    public MainJFrame() {
//        lienKetSQL();
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
//    public static void main(String[] args) {
//        new MainJFrame();
//    }
    
    @Override
    public void taoDoiTuong() {
        Object tenCot[] = {"Quản lý nhân viên", "Quản lý chuyên đề", "Quản lý khóa học", "Quản lý học viên", "Thống kê", "Đổi mật khẩu", "Đăng xuất"};
        mainJFrame = new JFrame("Hệ thống quản lý đào tạo");
        lstLeft = new JList(tenCot);
        cl = new CardLayout();
        pnlRight = new JPanel(cl);
        spl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lstLeft, pnlRight);

        nv = new QuanLyNhanVien();
        cd = new QuanLyChuyenDe();
        nh = new QuanLyNguoiHoc();
        kh = new QuanLyKhoaHoc();
        tk = new ThongKe();
    }

    @Override
    public void taoDang() {
        spl.setOneTouchExpandable(true);

        pnlRight.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        pnlRight.setPreferredSize(new Dimension(600, 650));
        
        lstLeft.setSelectedIndex(0);
        lstLeft.setFixedCellHeight(30);
        lstLeft.setFixedCellWidth(150);
        lstLeft.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        lstLeft.setFont(new Font("tahoma", Font.PLAIN, 14));
        lstLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void add() {
        mainJFrame.add(spl);

        pnlRight.add(nv.pnlJFrame, "1");
        pnlRight.add(cd.pnlJFrame, "2");
        pnlRight.add(kh.pnlJFrame, "3");
        pnlRight.add(nh.pnlJFrame, "4");
        pnlRight.add(tk.pnlJFrame, "5");

        mainJFrame.pack();
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.setDefaultCloseOperation(3);
        mainJFrame.setVisible(true);
    }
    
    @Override
    public void event() {
        lstLeft.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    switch (lstLeft.getSelectedIndex()) {
                        case 0:
                            cl.show(pnlRight, "1");
                            break;
                        case 1:
                            cl.show(pnlRight, "2");
                            break;
                        case 2:
                            cl.show(pnlRight, "3");
                            break;
                        case 3:
                            cl.show(pnlRight, "4");
                            break;
                        case 4:
                            cl.show(pnlRight, "5");
                            break;
                        case 5:
                            new DoiMatKhau();
                            break;
                        case 6:
                            try {
                                con.close();
                                mainJFrame.dispose();
                                new DangNhap();
                            } 
                            catch (Exception ex) {
                            }
                            break;
                    }
                }
            }
        });
    }
}
