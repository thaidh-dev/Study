package baithijava2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
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

        lblTieuDe = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

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

        lblTieuDe.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("CUSTOMER INFOR");

        lblID.setText("Customer ID");

        lblName.setText("Customer name");

        lblYear.setText("Year of birth");

        lblEmail.setText("Email");

        lblPhone.setText("Phone number");

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Customer name", "Year of birth", "Email", "Phone number"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID)
                            .addComponent(lblName)
                            .addComponent(lblYear)
                            .addComponent(lblEmail)
                            .addComponent(lblPhone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtEmail)
                            .addComponent(txtYear)
                            .addComponent(txtName)
                            .addComponent(txtID)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addGap(49, 49, 49)
                        .addComponent(btnUpdate)
                        .addGap(42, 42, 42)
                        .addComponent(btnDelete)
                        .addGap(41, 41, 41)
                        .addComponent(btnExit)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYear)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhone)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnExit))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        JTextField mangTxt[] = {txtID, txtName, txtYear, txtEmail, txtPhone};
        for (int i = 0; i < mangTxt.length; i++) {
            mangTxt[i].setText("");
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        them();
        save();
        fillToTable();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
        save();
        fillToTable();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
        save();
        fillToTable();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        open();
        fillToTable();
    }//GEN-LAST:event_formWindowOpened

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        showDetail();
    }//GEN-LAST:event_jTable2MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        jTable2.clearSelection();
    }//GEN-LAST:event_formMouseClicked

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
    
    public void them() {
        Customer_info info = new Customer_info();
        int sai = 0;
        int trong = 0;

        info.ID = txtID.getText();
        if (info.ID.isEmpty()) {
            trong++;
        }
        
        info.name = txtName.getText();
        if (info.name.isEmpty()) {
            trong++;
        }
        
        info.year = txtYear.getText();
        if (info.year.isEmpty()) {
            trong++;
        }
        else {
            try {
                sdf.parse(info.year);
            } 
            catch (Exception ex) {
                sai++;
                JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng");
            }
        }
        
        info.email = txtEmail.getText();
        if (info.email.isEmpty()) {
            trong++;
        }
        else {
            if (!info.email.matches(regexEmail)) {
                sai++;
                JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            }
        }
        
        info.phone = txtPhone.getText();
        if (info.phone.isEmpty()) {
            trong++;
        }
        else {
            if (!info.phone.matches(regexPhone)) {
                sai++;
                JOptionPane.showMessageDialog(this, "Hãy nhập số điện thoại, số có 10 đến 11 chữ số");
            }
        }
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(this, "Không để trông ô");
        }

        if (trong == 0 && sai == 0) {
            list.add(info);
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
    
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        
        for (Customer_info a : list) {
            Object row[] = {a.ID, a.name, a.year, a.email, a.phone};
            model.addRow(row);
        }
    }
    
    public void showDetail() {
        try {
            int index = jTable2.getSelectedRow();
            Customer_info info = list.get(index);
            txtID.setText(info.ID);
            txtName.setText(info.name);
            txtYear.setText(info.year);
            txtEmail.setText(info.email);
            txtPhone.setText(info.phone);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng trong bảng");
        }
    }
    
    public void update() {
        int index = jTable2.getSelectedRow();
        Customer_info info = new Customer_info();
        int sai = 0;
        int trong = 0;

        info.ID = txtID.getText();
        if (info.ID.isEmpty()) {
            trong++;
        }
        
        info.name = txtName.getText();
        if (info.name.isEmpty()) {
            trong++;
        }
        
        info.year = txtYear.getText();
        if (info.year.isEmpty()) {
            trong++;
        }
        else {
            try {
                sdf.parse(info.year);
            } 
            catch (Exception ex) {
                sai++;
                JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng");
            }
        }
        
        info.email = txtEmail.getText();
        if (info.email.isEmpty()) {
            trong++;
        }
        else {
            if (!info.email.matches(regexEmail)) {
                sai++;
                JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            }
        }
        
        info.phone = txtPhone.getText();
        if (info.phone.isEmpty()) {
            trong++;
        }
        else {
            if (!info.phone.matches(regexPhone)) {
                sai++;
                JOptionPane.showMessageDialog(this, "Hãy nhập số điện thoại, số có 10 đến 11 chữ số");
            }
        }
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(this, "Không để trông ô");
        }

        if (trong == 0 && sai == 0) {
            list.set(index, info);
        }
    }
    
    public void delete() {
        try {
            int index = jTable2.getSelectedRow();
            list.remove(index);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng trong bảng trước");
        }
    }
    
    public void open() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dht.txt"));
            list = (List<Customer_info>) ois.readObject();
            ois.close();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu để mở");
        }
    }
    
    List<Customer_info> list = new ArrayList<>();
    String regexEmail = "\\w+@\\w+\\.\\w+";
    String regexPhone = "0\\d{9,10}";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
