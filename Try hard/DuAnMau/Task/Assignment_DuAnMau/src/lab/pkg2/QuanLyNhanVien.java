package lab.pkg2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static lab.pkg2.Khung.pst;
import static lab.pkg2.Khung.rs1;
import java.util.List;
import static lab.pkg2.Khung.truongPhong;

public class QuanLyNhanVien extends Khung {
    JPanel pnlRdo, pnlBtn, pnlInfo, pnlJFrame, pnlTim, pnlTop, pnlInfoBtn;
    JLabel lblTitle, lblTaiKhoan, lblMatKhau, lblHoTen, lblXacNhanMK, lblVaiTro;
    JTextField txtTaiKhoan, txtHoTen, txtTim;
    ButtonGroup bgrVaiTro;
    JRadioButton rdoNhanVien, rdoTruongPhong;
    JButton btnInsert, btnUpdate, btnDelete, btnClear, btnSave, btnCancel, btnTim;
    JTable tblGridView;
    GridBagConstraints gbc;
    String tenCot[] = {"STT", "Họ tên", "Tài khoản", "Vai trò"};
    DefaultTableModel model;
    JScrollPane scr;
    boolean status;
    List<Object> list;

    public QuanLyNhanVien() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        gbc = new GridBagConstraints();
        
        pnlJFrame = new JPanel(new BorderLayout(0, 10));
        pnlRdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnlInfo = new JPanel(new GridBagLayout());
        pnlTim = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnlInfoBtn = new JPanel(new GridBagLayout());
        pnlTop = new JPanel(new BorderLayout(0, 10));
        
        txtHoTen = new JTextField(25);
        txtTaiKhoan = new JTextField(25);
        txtTim = new JTextField(20);
        
        bgrVaiTro = new ButtonGroup();
        rdoNhanVien = new JRadioButton("Nhân viên");
        rdoTruongPhong = new JRadioButton("Trưởng phòng");
        
        lblTitle = new JLabel("Quản lý nhân viên quản trị");
        lblTaiKhoan = new JLabel("Tài khoản");
        lblMatKhau = new JLabel("Mật khẩu");
        lblXacNhanMK = new JLabel("Xác nhận mật khẩu");
        lblHoTen = new JLabel("Họ tên");
        lblVaiTro = new JLabel("Vai trò");
        
        btnInsert = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnClear = new JButton("Làm mới");
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Bỏ qua");
        btnTim = new JButton("Tìm theo mã");
        
        model = new DefaultTableModel(tenCot, 0);
        tblGridView = new JTable(model);
        scr = new JScrollPane(tblGridView);
        
        list = new ArrayList();
    }

    @Override
    public void taoDang() {
        lblTitle.setFont(new Font("tahoma", Font.BOLD, 14));
        lblTitle.setForeground(Color.white);
        lblTitle.setOpaque(true);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBackground(Color.darkGray);
        lblTitle.setPreferredSize(new Dimension(580, 30));
        
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        tblGridView.setFillsViewportHeight(true);
        tblGridView.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblGridView.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblGridView.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblGridView.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        Set<KeyStroke> set = new HashSet();
        set.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
        JComponent txt[] = {txtTaiKhoan,txtHoTen,rdoNhanVien,rdoTruongPhong,btnInsert,
        btnUpdate,btnDelete,btnClear,btnSave,btnCancel,txtTim,btnTim};
        for(int i=0;i<txt.length;i++){
            txt[i].setFocusTraversalKeys(0, set);
        }
        tblGridView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void add() {
        bgrVaiTro.add(rdoNhanVien);
        bgrVaiTro.add(rdoTruongPhong);
        pnlRdo.add(rdoNhanVien);
        pnlRdo.add(rdoTruongPhong);
        
        JComponent a[] = {lblTaiKhoan, lblHoTen, lblVaiTro, txtTaiKhoan, txtHoTen, pnlRdo};
        addChung(pnlInfo, gbc, a, 3, 2, new Insets(5, 5, 5, 5), 17);
        
        JComponent b[] = {btnInsert, btnUpdate, btnDelete, btnClear, btnSave, btnCancel};
        addChung(pnlBtn, null, b, 1, 6, null, 0);
        
        pnlTim.add(txtTim);
        pnlTim.add(btnTim);
        
        JComponent c[] = {pnlInfo, pnlBtn, pnlTim};
        addChung(pnlInfoBtn, gbc, c, 3, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);

        pnlTop.add(lblTitle, BorderLayout.NORTH);
        pnlTop.add(pnlInfoBtn, BorderLayout.CENTER);
        
        pnlJFrame.add(pnlTop, BorderLayout.NORTH);
        pnlJFrame.add(scr, BorderLayout.CENTER);
    }

    public String role1(boolean a){
        String vt=null;
        if(a==true){
            vt="Trưởng phòng";
        }else{
            vt="Nhân viên";
        }
        return vt;
    }
    
    public boolean role2(){
        boolean bit=true;
        if(rdoNhanVien.isSelected()==true){
            bit=false;
        }else{
            for(int i=0;i<tblGridView.getRowCount();i++){
                if(tblGridView.getValueAt(i,3).toString().equalsIgnoreCase("Trưởng phòng")){
                    int r = JOptionPane.showConfirmDialog(pnlTop, "Đặt nhân viên này làm trưởng phòng sẽ "
                                + "khiến trưởng phòng hiện tại trở thành nhân viên. Tiếp tục?",
                                "Đặt trưởng phòng mới?", JOptionPane.YES_NO_OPTION);
                    if(r==JOptionPane.YES_OPTION){
                        try{
                            pst2 = con.prepareStatement("update NhanVien set VaiTro=? where MaNV=?");
                            pst2.setString(1, "0");
                            pst2.setString(2, (String) list.get(i));
                            pst2.executeUpdate();
                            pst2.close();
                        }catch(Exception e){
                            System.out.println(e);
                        }
                        bit=true;
                    }else{
                        bit = false;
                    }
                    break;
                }
            }
        }
        return bit;
    }
    
    public void loadData(){
        try {
            pst = con.prepareStatement("select * from NhanVien");
            rs1 = pst.executeQuery();
            
            int i = 1;
            model.setRowCount(0);
            list.clear();
            while(rs1.next()){
                list.add(rs1.getString(1));
                
                Object row[] = {i,rs1.getString(4),rs1.getString(2),role1(rs1.getBoolean(5))};               
                model.addRow(row);
                i++;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pnlTop, ex);
            System.out.println(ex);
        }
    }
    
    public void showDetail(int i){
        try{
            pst = con.prepareStatement("select * from NhanVien where MaNV=?");
            pst.setString(1, (String) list.get(i));
            rs2 = pst.executeQuery();
            while(rs2.next()){
                txtTaiKhoan.setText(rs2.getString(2));
                txtHoTen.setText(rs2.getString(4));
                if(rs2.getBoolean(5)){
                    rdoTruongPhong.setSelected(true);
                }else{
                    rdoNhanVien.setSelected(true);
                }
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public boolean validatInsert(){
        boolean res = true;
        if(txtTaiKhoan.getText().length()==0){
            JOptionPane.showMessageDialog(pnlTop,"Chưa nhập tên tài khoản");
            return res=false;
        }
        for(int i=0;i<tblGridView.getRowCount();i++){
            if(txtTaiKhoan.getText().trim().equalsIgnoreCase((String) tblGridView.getValueAt(i,2))){
                JOptionPane.showMessageDialog(pnlTop,"Tài khoản đã tồn tại");
                return res = false;
            }
        }
        if(txtHoTen.getText().length()==0){
            JOptionPane.showMessageDialog(pnlTop,"Chưa nhập tên nhân viên");
            return res=false;
        }
        if(rdoNhanVien.isSelected()==false&rdoTruongPhong.isSelected()==false){
            JOptionPane.showMessageDialog(pnlTop,"Chưa chọn vai trò");
            return res=false;
        } 
        return res;
    }
    
    public boolean validatUpdate(){
        boolean res = true;
        if(txtTaiKhoan.getText().length()==0){
            JOptionPane.showMessageDialog(pnlTop,"Chưa nhập tên tài khoản");
            return res=false;
        }
        if(txtHoTen.getText().length()==0){
            JOptionPane.showMessageDialog(pnlTop,"Chưa nhập tên nhân viên");
            return res=false;
        }
        if(rdoNhanVien.isSelected()==false&rdoTruongPhong.isSelected()==false){
            JOptionPane.showMessageDialog(pnlTop,"Chưa chọn vai trò");
            return res=false;
        } 
        return res;
    }
    
    
    @Override
    public void event() {
        loadData();
        tblGridView.setRowSelectionInterval(0, 0);
        showDetail(0);
        
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        
        txtHoTen.setEditable(false);
        txtTaiKhoan.setEditable(false);
        rdoNhanVien.setEnabled(false);
        rdoTruongPhong.setEnabled(false);
        if (truongPhong == false) {
            btnDelete.setEnabled(false);
        }
        
        tblGridView.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e){
                int i = tblGridView.getSelectedRow();
                showDetail(i);
            }
        });
        
        tblGridView.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){
                int i = tblGridView.getSelectedRow();
                if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB){
                    showDetail(i);
                    
                }
            }
});
        
        btnSave.addActionListener(new ActionListener() {
            @Override           
            public void actionPerformed(ActionEvent e) {
                if(status==true/*them*/){
                    if(validatInsert()){
                        try {
                            pst = con.prepareStatement("insert into NhanVien values(?,123,?,?)");
                            pst.setString(1,txtTaiKhoan.getText());
                            pst.setString(2,txtHoTen.getText());
                            pst.setBoolean(3,role2());
                            pst.executeUpdate();
                            loadData();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(pnlTop, ex);
                            System.out.println(ex);
                        }
                    }
                }
                if(status==false/*update*/){
                    if(validatUpdate()){
                        int i = tblGridView.getSelectedRow();
                        try{
                            pst = con.prepareStatement("update NhanVien set TaiKhoan=?,HoTen=?,VaiTro=? where MaNV=?");
                            pst.setString(1,txtTaiKhoan.getText());
                            pst.setString(2,txtHoTen.getText());
                            pst.setBoolean(3,role2());
                            pst.setString(4,(String) list.get(i));
                            pst.executeUpdate();
                            loadData();
                            pst.close();
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(pnlTop, ex);
                            System.out.println(ex);
                        }
                    }
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loggingAcc!=null){
                    for(int i=0;i<tblGridView.getRowCount();i++){
                        if(loggingAcc.equalsIgnoreCase((String) tblGridView.getValueAt(i,2))){
                            if(tblGridView.getValueAt(i, 3).toString().equalsIgnoreCase("Nhân viên")){
                                truongPhong=false;
                                break;
                            }
                        }
                    }
                }
                if(truongPhong==true){
                    btnDelete.setEnabled(true);
                }
                btnInsert.setEnabled(true);
                btnUpdate.setEnabled(true);
                btnSave.setEnabled(false);
                btnCancel.setEnabled(false);
                
                txtHoTen.setEditable(false);
                txtTaiKhoan.setEditable(false);
                rdoNhanVien.setEnabled(false);
                rdoTruongPhong.setEnabled(false);
                tblGridView.setEnabled(true);
                txtTim.setEditable(true);
                btnTim.setEnabled(true);
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtHoTen.setText("");
                txtTaiKhoan.setText("");
                bgrVaiTro.clearSelection();
                tblGridView.clearSelection();
            }
        });
        
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status=true;
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
                btnCancel.setEnabled(true);
                btnSave.setEnabled(true);
                
                txtHoTen.setEditable(true);
                txtTaiKhoan.setEditable(true);
                
                txtHoTen.setText("");
                txtTaiKhoan.setText("");
                txtTaiKhoan.requestFocus();
                bgrVaiTro.clearSelection();
                tblGridView.clearSelection();
                
                tblGridView.setEnabled(false);
                txtTim.setEditable(false);
                btnTim.setEnabled(false);
                
                if(truongPhong==false){
                    rdoNhanVien.setEnabled(true);
                    rdoNhanVien.setSelected(true);
                }else{
                    rdoNhanVien.setEnabled(true);
                    rdoTruongPhong.setEnabled(true);
                }
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status=false;
                btnInsert.setEnabled(false);
                btnDelete.setEnabled(false);
                btnCancel.setEnabled(true);
                btnSave.setEnabled(true);
                
                txtHoTen.setEditable(true);
                txtTaiKhoan.setEditable(true);
                if(truongPhong==true){
                    rdoNhanVien.setEnabled(true);
                    rdoTruongPhong.setEnabled(true);
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tblGridView.getSelectedRow();
                int delcon = JOptionPane.showConfirmDialog(pnlTop,"Bạn có chắc chắn muốn xóa không?", "Xóa", JOptionPane.YES_NO_OPTION);
                if(delcon==JOptionPane.YES_OPTION){
                }else{
                    return;
                }
                
                if(loggingAcc!=null && loggingAcc.equalsIgnoreCase((String) tblGridView.getValueAt(i,2))){
                    JOptionPane.showMessageDialog(pnlTop,"Không thể xóa do tài khoản đang trong phiên làm việc");
                    return;
                }
                try{
                    pst = con.prepareStatement("delete from NhanVien where MaNV=?");
                    pst.setString(1,(String) list.get(i));
                    pst.executeUpdate();
                    loadData();
                    if(i==tblGridView.getRowCount()){
                        if(tblGridView.getRowCount()==0){
                            txtHoTen.setText("");
                            txtTaiKhoan.setText("");
                            bgrVaiTro.clearSelection();
                        }
                        else {
                            tblGridView.setRowSelectionInterval(i-1, i-1);
                            showDetail(i-1);
                        }
                    }
                    else {
                        tblGridView.setRowSelectionInterval(i, i);
                        showDetail(i);
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(pnlTop, ex);
                }
            }
        });
        
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtTim.getText().length()==0){
                    loadData();
                    return;
                }
                try {
                    pst = con.prepareStatement("select * from NhanVien where MaNV=?");
                    pst.setString(1,txtTim.getText());
                    rs3 = pst.executeQuery();
                    model.setRowCount(0);
                    while(rs3.next()){
                        Object row[] = {"1",rs3.getString(4),rs3.getString(2),role1(rs3.getBoolean(5))};               
                        model.addRow(row);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pnlTop, ex);
                    System.out.println(ex);
                }
                
            }
        });
    }
}
