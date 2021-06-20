package lab.pkg2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static lab.pkg2.Khung.pst;
import java.util.List;

public class QuanLyChuyenDe extends Khung {
    JLabel lblTitle, lblTenCD, lblThoiLuong, lblHocPhi, lblMota;
    JTextField txtTenCD, txtThoiLuong, txtHocPhi;
    JTextArea txtMoTa;
    JScrollPane scrMoTa, scrGridView;
    JButton btnInsert, btnUpdate, btnDelete, btnClear, btnSave, btnCancel;
    GridBagConstraints gbc;
    JPanel pnlBtn, pnlInfo, pnlJFrame, pnlCenter, pnlRdo, pnlScr, pnlTop;
    JTable tblGridView;
    DefaultTableModel model;
    JRadioButton rdoTang, rdoGiam;
    ButtonGroup bgrSapXep;
    boolean edit;
    String tenCot[] = {"STT", "Tên CD", "Học phí", "Thời lượng"};
    boolean status;
    List<Object> list;

    public QuanLyChuyenDe() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        gbc = new GridBagConstraints();
        
        lblTitle = new JLabel("Quản lý chuyên đề");
        lblTenCD = new JLabel("Tên chuyên đề");
        lblThoiLuong = new JLabel("Thời lượng (giờ)");
        lblHocPhi = new JLabel("Học phí");
        lblMota = new JLabel("Mô tả chyên đề");
        
        txtTenCD = new JTextField(25);
        txtThoiLuong = new JTextField(25);
        txtHocPhi = new JTextField(25);
        
        txtMoTa = new JTextArea();
        scrMoTa = new JScrollPane(txtMoTa);
        
        btnInsert = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnClear = new JButton("Làm mới");
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Bỏ qua");
        
        pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnlRdo = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pnlInfo = new JPanel(new GridBagLayout());
        pnlJFrame = new JPanel(new BorderLayout(0, 10));
        pnlCenter = new JPanel(new GridBagLayout());
        pnlScr = new JPanel(new BorderLayout(0, 0));
        pnlTop = new JPanel(new BorderLayout(0, 10));
        
        model = new DefaultTableModel(tenCot, 0);
        tblGridView = new JTable(model);
        scrGridView = new JScrollPane(tblGridView);
        
        bgrSapXep = new ButtonGroup();
        rdoGiam = new JRadioButton("Học phí giảm dần");
        rdoTang = new JRadioButton("Học phí tăng dần");
        
        list = new ArrayList();
    }

    @Override
    public void taoDang() {
        scrMoTa.setPreferredSize(new Dimension(280, 70));
        scrMoTa.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        scrGridView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        tblGridView.setFillsViewportHeight(true);
        tblGridView.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblGridView.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblGridView.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblGridView.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        lblTitle.setOpaque(true);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("tahoma", Font.BOLD, 14));
        lblTitle.setForeground(Color.white);
        lblTitle.setPreferredSize(new Dimension(580, 30));
        lblTitle.setBackground(Color.darkGray);
        
        if (truongPhong == false) {
            btnDelete.setEnabled(false);
        }
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        
        Set <KeyStroke> set = new HashSet();
        set.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
        JComponent txt[] = {txtTenCD,txtThoiLuong,txtHocPhi,txtMoTa,btnInsert,btnUpdate,btnDelete,btnClear,btnSave,btnCancel};
        for(int i = 0; i<txt.length;i++){
            txt[i].setFocusTraversalKeys(0, set);
        }
        
        txtHocPhi.setEditable(false);
        txtMoTa.setEditable(false);
        txtTenCD.setEditable(false);
        txtThoiLuong.setEditable(false);
        
        tblGridView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void add() {
        pnlBtn.add(btnInsert);
 
        pnlBtn.add(btnUpdate);
      
        pnlBtn.add(btnDelete);
     
        pnlBtn.add(btnClear);
        
        pnlBtn.add(btnSave);
        
        pnlBtn.add(btnCancel);


        
        bgrSapXep.add(rdoTang);
        bgrSapXep.add(rdoGiam);
        pnlRdo.add(rdoGiam);
        pnlRdo.add(rdoTang);

        JComponent a[] = {lblTenCD, lblThoiLuong, lblHocPhi, lblMota, txtTenCD, txtThoiLuong, txtHocPhi, scrMoTa};
        addChung(pnlInfo, gbc, a, 4, 2, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST);
        
        JComponent b[] = {pnlInfo, pnlBtn};
        addChung(pnlCenter, gbc, b, 2, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
        pnlTop.add(lblTitle, BorderLayout.NORTH);
        pnlTop.add(pnlCenter, BorderLayout.CENTER);
        
        pnlScr.add(pnlRdo, BorderLayout.NORTH);
        pnlScr.add(scrGridView, BorderLayout.CENTER);
        
        pnlJFrame.add(pnlTop, BorderLayout.NORTH);
        pnlJFrame.add(pnlScr, BorderLayout.CENTER);
    }

    public void loadData(){
        try{
            pst = con.prepareStatement("select * from ChuyenDe");
            rs1 = pst.executeQuery();
            
            model.setRowCount(0);
            int i = 1;
            list.clear();
            while(rs1.next()){
                list.add(rs1.getString(1));
                Object row[] = {i,rs1.getString(2),rs1.getString(3),rs1.getString(4)};
                model.addRow(row);
                i++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void showDetail(int i){
        try {
            pst = con.prepareStatement("select * from ChuyenDe where MaCD=?");
            pst.setString(1, (String) list.get(i));
            rs1 = pst.executeQuery();
            while(rs1.next()){
                txtTenCD.setText(rs1.getString(2));
                txtHocPhi.setText(rs1.getString(3));
                txtThoiLuong.setText(rs1.getString(4));
                txtMoTa.setText(rs1.getString(5));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public boolean validat(){
        boolean res = true;
        if(txtTenCD.getText().length()==0){
            JOptionPane.showMessageDialog(pnlCenter,"Chưa nhập tên chuyên đề");
            return res = false;
        }
        for(int i = 0;i<tblGridView.getRowCount();i++){
            if(txtTenCD.getText().trim().equalsIgnoreCase((String) tblGridView.getValueAt(i, 1))){
                JOptionPane.showMessageDialog(pnlCenter,"Chuyên đề này đã tồn tại");
                return res = false;
            }
        }
        if(txtThoiLuong.getText().length()==0){
            JOptionPane.showMessageDialog(pnlCenter,"Chưa nhập thời lượng của chuyên đề");
            return res = false;
        }
        try{
            if(Double.parseDouble(txtThoiLuong.getText())%1==0){      
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(pnlCenter, "Thời lượng phải được nhập là số");
            return res = false;
        }
        if(txtHocPhi.getText().length()==0){
            JOptionPane.showMessageDialog(pnlCenter,"Chưa nhập học phí");
            return res = false;
        } 
        try{
            if(Double.parseDouble(txtHocPhi.getText())%1==0){      
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(pnlCenter, "Học phí phải được nhập là số");
            return res = false;
        }
        
        return res;
    }
    
    public boolean valiDelete(){
        boolean res;
        int i = tblGridView.getSelectedRow();
        if(i<0){
            JOptionPane.showMessageDialog(pnlCenter,"Hãy chọn một dòng để xóa");
            return res = false;
        }
        try{
            pst = con.prepareStatement("select MaCD from KhoaHoc");
            rs1 = pst.executeQuery();
            while(rs1.next()){
                if(rs1.getString(1).equalsIgnoreCase((String) list.get(i))){
                    JOptionPane.showMessageDialog(pnlCenter,"Không thể xóa chuyên đề này do đang có khóa học sử dụng");
                    return res = false;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return res = true;
    }
    
    //event--------------------------------------------------------------------------------------||
    //event--------------------------------------------------------------------------------------||
    @Override
    public void event() {
        loadData();
        //tblGridView.setRowSelectionInterval(0,0);
        showDetail(0);
        
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtHocPhi.setText("");
                txtMoTa.setText("");
                txtTenCD.setText("");
                txtThoiLuong.setText("");
                tblGridView.clearSelection();
            }
        });
        
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = true;
                btnDelete.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnCancel.setEnabled(true);
                btnSave.setEnabled(true);
                
                txtHocPhi.setEditable(true);
                txtMoTa.setEditable(true);
                txtTenCD.setEditable(true);
                txtThoiLuong.setEditable(true);
                
                txtHocPhi.setText("");
                txtMoTa.setText("");
                txtThoiLuong.setText("");
                txtTenCD.setText("");
                tblGridView.clearSelection();
                tblGridView.setEnabled(false);
                rdoGiam.setEnabled(false);
                rdoTang.setEnabled(false);
                txtTenCD.requestFocus();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(valiDelete()==false){
                    return;
                }
                int delcon = JOptionPane.showConfirmDialog(pnlCenter, "Bạn có chắc chắn muốn xóa không?","Xóa",JOptionPane.YES_NO_OPTION);
                if(delcon==JOptionPane.YES_OPTION){
                }else{
                    return;
                }
                try {
                    pst = con.prepareStatement("delete from ChuyenDe where MaCD=?");
                    int i = tblGridView.getSelectedRow();
                    pst.setString(1, (String) list.get(i));
                    pst.executeUpdate();
                    loadData();
                    if(i==tblGridView.getRowCount()){
                        if(tblGridView.getRowCount()==0){
                            txtHocPhi.setText("");
                            txtMoTa.setText("");
                            txtTenCD.setText("");
                            txtThoiLuong.setText("");
                        }
                        else {
                            tblGridView.setRowSelectionInterval(i-1, i-1);
                            showDetail(i-1);
                        }
                    }else {
                        tblGridView.setRowSelectionInterval(i, i);
                        showDetail(i);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = false;
                btnInsert.setEnabled(false);
                btnDelete.setEnabled(false);
                btnSave.setEnabled(true);
                btnCancel.setEnabled(true);
                
                txtHocPhi.setEditable(true);
                txtMoTa.setEditable(true);
                txtTenCD.setEditable(true);
                txtThoiLuong.setEditable(true);
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(status==true/*them*/){
                    if(validat()){
                        try {
                            pst = con.prepareStatement("insert into ChuyenDe values(?,?,?,?)");
                            pst.setString(1,txtTenCD.getText());
                            pst.setString(2,txtHocPhi.getText());
                            pst.setInt(3, Integer.parseInt(txtThoiLuong.getText()));
                            pst.setString(4,txtMoTa.getText());
                            pst.executeUpdate();
                            loadData();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(pnlCenter,ex);
                            System.out.println(ex);
                        }
                    }
                }
                if(status==false/*sua*/){
                    if(validat()){
                        try{
                            int i = tblGridView.getSelectedRow();
                            pst = con.prepareStatement("update ChuyenDe set TenCD=?, HocPhi=?, ThoiLuong=?, MoTa=? where MaCD=?");
                            pst.setString(1, txtTenCD.getText());
                            pst.setString(2,txtHocPhi.getText());
                            pst.setString(3,txtThoiLuong.getText());
                            pst.setString(4,txtMoTa.getText());
                            pst.setString(5, (String) list.get(i));
                            pst.executeUpdate();
                            loadData();
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(pnlBtn, ex);
                            System.out.println(ex);
                        }
                    }
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(truongPhong==true){
                    btnDelete.setEnabled(true);
                }
                btnUpdate.setEnabled(true);
                btnInsert.setEnabled(true);
                btnSave.setEnabled(false);
                btnCancel.setEnabled(false);
                
                txtHocPhi.setEditable(false);
                txtMoTa.setEditable(false);
                txtThoiLuong.setEditable(false);
                txtTenCD.setEditable(false);     
                tblGridView.setEnabled(true);
                rdoGiam.setEnabled(true);
                rdoTang.setEnabled(true);
            }
        });
        
        tblGridView.addMouseListener(new MouseAdapter() {
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
        
        rdoTang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    try{
                        pst = con.prepareStatement("select * from chuyende order by HocPhi asc");
                        rs2 = pst.executeQuery();            
                        model.setRowCount(0);
                        int i = 1;
                        while(rs2.next()){
                            Object row[] = {i,rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5)};
                            model.addRow(row);
                            i++;
                        }
                    }
                    catch(Exception ex){
                        System.out.println(ex);
                    }
                }
            }
        });
        
        rdoGiam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    try{
                        pst = con.prepareStatement("select * from ChuyenDe order by HocPhi desc");
                        rs3 = pst.executeQuery();            
                        model.setRowCount(0);
                        int i = 1;
                        while(rs3.next()){
                            Object row[] = {i,rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5)};
                            model.addRow(row);
                            i++;
                        }
                    }
                    catch(Exception ex){
                        System.out.println(ex);
                    }
                }
            }
        });
    }
}
