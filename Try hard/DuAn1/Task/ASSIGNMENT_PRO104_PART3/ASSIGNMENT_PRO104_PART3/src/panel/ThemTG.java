/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import com.toedter.calendar.JDateChooser;
import dao.TacGiaDAO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import panel.ManHinhChinh;
import object.TacGia;

/**
 *
 * @author Thanh
 */
public class ThemTG extends JPanel {

    private JLabel lblTenTG, lblSex, lblNgaySinh;
    public JTextField txtTen;
    public JRadioButton rdoNam, rdoNu;
    private ButtonGroup btnSex;
    public JDateChooser txtNgaySinh;
    public JButton btnThem, btnHuy;
    private JPanel pnlSex, box1, box2, pnlMain;
    private TacGia TG = new TacGia();
    private TacGiaDAO tgDao = new TacGiaDAO();

    public ThemTG() {
        this.setLayout(new BorderLayout());

        lblTenTG = new JLabel("Tên tác giả");
        txtTen = new JTextField();
        txtTen.setPreferredSize(new Dimension(200, 28));
        lblSex = new JLabel("Giới tính");
        pnlSex = new JPanel(new FlowLayout());
        pnlSex.setPreferredSize(new Dimension(200, 28));
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");

        btnSex = new ButtonGroup();
        btnSex.add(rdoNam);
        btnSex.add(rdoNu);
        pnlSex.add(rdoNam);
        pnlSex.add(rdoNu);
        lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setPreferredSize(new Dimension(63, 20));
        lblSex.setPreferredSize(lblNgaySinh.getPreferredSize());
        lblTenTG.setPreferredSize(lblNgaySinh.getPreferredSize());
        txtNgaySinh = new JDateChooser();
        txtNgaySinh.setPreferredSize(new Dimension(200, 28));

        box1 = new JPanel(new FlowLayout(10, 38, 22));
        box1.setPreferredSize(new Dimension(390, 100));
        box1.add(lblTenTG);
        box1.add(txtTen);
        box1.add(lblSex);
        box1.add(pnlSex);
        box1.add(lblNgaySinh);
        box1.add(txtNgaySinh);

        btnHuy = new JButton("Huỷ");
        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(120, 28));
        btnHuy.setPreferredSize(new Dimension(120, 28));

        box2 = new JPanel(new FlowLayout(10, 46, 0));
        box2.setPreferredSize(new Dimension(390, 90));
        box2.add(btnThem);
        box2.add(btnHuy);

        pnlMain = new JPanel(new BorderLayout());
        pnlMain.setPreferredSize(new Dimension(390, 270));
        pnlMain.add(box1, BorderLayout.CENTER);
        pnlMain.add(box2, BorderLayout.SOUTH);

        this.add(pnlMain);

    }
}
