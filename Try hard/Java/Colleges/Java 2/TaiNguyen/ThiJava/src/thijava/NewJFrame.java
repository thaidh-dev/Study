package thijava;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NewJFrame extends javax.swing.JFrame {

    public NewJFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMa = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblDiem = new javax.swing.JLabel();
        lblLop = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtDiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnDoc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblMa.setText("Mã sinh viên");

        lblTen.setText("Tên sinh viên");

        lblNgaySinh.setText("Ngày sinh");

        lblDiem.setText("Điểm tổng kết");

        lblLop.setText("Lớp");

        btnThem.setText("Thêm vào file");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnDoc.setText("Đọc");
        btnDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Ngày sinh", "Điểm", "Lớp"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMa)
                            .addComponent(lblTen)
                            .addComponent(lblNgaySinh)
                            .addComponent(lblDiem)
                            .addComponent(lblLop))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                .addComponent(txtDiem)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(35, 35, 35)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(btnXoa)
                        .addGap(36, 36, 36)
                        .addComponent(btnDoc)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMa)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTen)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgaySinh)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiem)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLop)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnDoc))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        jTable1.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    private void btnDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocActionPerformed
        jComboBox1.addItem("lop 1");
        jComboBox1.addItem("lop 2");
        jComboBox1.addItem("lop 3");
        jComboBox1.addItem("lop 4");
        jComboBox1.addItem("lop 5");
    }//GEN-LAST:event_btnDocActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them();
        save();
        fillToTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
        save();
        fillToTable();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        sua();
        save();
        fillToTable();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        open();
        fillToTable();
    }//GEN-LAST:event_formWindowOpened

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        showDetail();
    }//GEN-LAST:event_jTable1MouseClicked

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
        
    }
    
    String lop[] = {"pt14201-ud", "pt14202-ud", "pt14203-ud", "pt14204-ud", "pt14205-ud"};
    List<ThongTin> list = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    
    public void them() {
        ThongTin info = new ThongTin();
        int sai = 0;
        int trong = 0;

        info.ma = txtMa.getText();
        if (info.ma.isEmpty()) {
            trong++;
        }
        
        info.ten = txtTen.getText();
        if (info.ten.isEmpty()) {
            trong++;
        }
        
        info.ngaySinh = txtNgaySinh.getText();
        if (info.ngaySinh.isEmpty()) {
            trong++;
        }
        else {
            try {
                sdf.setLenient(false);
                sdf.parse(info.ngaySinh);
            } 
            catch (Exception ex) {
                sai++;
                JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng");
            }
        }
        
        info.diem = Double.parseDouble(txtDiem.getText());
        if (txtDiem.getText().isEmpty()) {
            trong++;
        }
        else {
            if (info.diem < 0 || info.diem > 10) {
                sai++;
                JOptionPane.showMessageDialog(this, "Điểm không đúng định dạng");
            }
        }
        
        info.lop = (String) jComboBox1.getSelectedItem();
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(this, "Không để trông ô");
        }

        if (trong == 0 && sai == 0) {
            list.add(info);
        }
    }
    
    public void sua() {
        int index = jTable1.getSelectedRow();
        ThongTin info = new ThongTin();
        int sai = 0;
        int trong = 0;

        info.ma = txtMa.getText();
        if (info.ma.isEmpty()) {
            trong++;
        }
        
        info.ten = txtTen.getText();
        if (info.ten.isEmpty()) {
            trong++;
        }
        
        info.ngaySinh = txtNgaySinh.getText();
        if (info.ngaySinh.isEmpty()) {
            trong++;
        }
        else {
            try {
                sdf.setLenient(false);
                sdf.parse(info.ngaySinh);
            } 
            catch (Exception ex) {
                sai++;
                JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng");
            }
        }
        
        info.diem = Double.parseDouble(txtDiem.getText());
        if (txtDiem.getText().isEmpty()) {
            trong++;
        }
        else {
            if (info.diem < 0 || info.diem > 10) {
                sai++;
                JOptionPane.showMessageDialog(this, "Điểm không đúng định dạng");
            }
        }
        
        info.lop = (String) jComboBox1.getSelectedItem();
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(this, "Không để trông ô");
        }

        if (trong == 0 && sai == 0) {
            list.set(index, info);
        }
    }
    
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dht.txt"));
            oos.writeObject(list);
            oos.close();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi ghi file");
        }
    }
    
    public void open() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dht.txt"));
            list = (List<ThongTin>) ois.readObject();
            ois.close();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu để mở");
        }
    }
    
    public void showDetail() {
        try {
            int index = jTable1.getSelectedRow();
            ThongTin info = list.get(index);
            txtMa.setText(info.ma);
            txtTen.setText(info.ten);
            txtNgaySinh.setText(info.ngaySinh);
            txtDiem.setText(String.valueOf(info.diem));
            jComboBox1.setSelectedItem(info.lop);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng trong bảng");
        }
    }
    
    public void delete() {
        try {
            int index = jTable1.getSelectedRow();
            list.remove(index);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng trong bảng trước");
        }
    }
    
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        for (ThongTin a : list) {
            Object row[] = {a.ma, a.ten, a.ngaySinh, a.diem, a.lop};
            model.addRow(row);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoc;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDiem;
    private javax.swing.JLabel lblLop;
    private javax.swing.JLabel lblMa;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblTen;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
