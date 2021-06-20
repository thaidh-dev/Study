package lab.pkg7;

import static java.lang.Math.*;
import javax.swing.JOptionPane;

public class Bai3 extends javax.swing.JFrame {

    public Bai3() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSo1 = new javax.swing.JLabel();
        lblMax = new javax.swing.JLabel();
        lblMu = new javax.swing.JLabel();
        lblMin = new javax.swing.JLabel();
        lblSo2 = new javax.swing.JLabel();
        lblCan = new javax.swing.JLabel();
        lblKetQua = new javax.swing.JLabel();
        txtSo1 = new javax.swing.JTextField();
        txtSo2 = new javax.swing.JTextField();
        txtMin = new javax.swing.JTextField();
        txtMax = new javax.swing.JTextField();
        txtMu = new javax.swing.JTextField();
        txtCan = new javax.swing.JTextField();
        btnThucHien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblSo1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblSo1.setText("Số 1");

        lblMax.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblMax.setText("Max");

        lblMu.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblMu.setText("Số 1 mũ số 2");

        lblMin.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblMin.setText("Min");

        lblSo2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblSo2.setText("Số 2");

        lblCan.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblCan.setText("Căn số 1");

        lblKetQua.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblKetQua.setText("Kết quả");

        txtSo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtSo2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtMin.setEditable(false);
        txtMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtMax.setEditable(false);
        txtMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtMu.setEditable(false);
        txtMu.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtCan.setEditable(false);
        txtCan.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnThucHien.setText("Thực hiện");
        btnThucHien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThucHienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKetQua)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnThucHien)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSo1)
                                .addComponent(lblSo2)
                                .addComponent(lblCan)
                                .addComponent(lblMu)
                                .addComponent(lblMin)
                                .addComponent(lblMax))
                            .addGap(72, 72, 72)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSo1)
                                .addComponent(txtSo2)
                                .addComponent(txtCan)
                                .addComponent(txtMu)
                                .addComponent(txtMin)
                                .addComponent(txtMax, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSo1)
                    .addComponent(txtSo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSo2)
                    .addComponent(txtSo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblKetQua)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCan)
                    .addComponent(txtCan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMu)
                    .addComponent(txtMu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMin)
                    .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMax)
                    .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThucHien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThucHienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThucHienActionPerformed
        double so1, so2;
        try {
            so1 = Double.parseDouble(txtSo1.getText());
            so2 = Double.parseDouble(txtSo2.getText());
            txtCan.setText(String.valueOf(sqrt(so1)));
            txtMu.setText(String.valueOf(pow(so1, so2)));
            txtMin.setText(String.valueOf(min(so1, so2)));
            txtMax.setText(String.valueOf(max(so1, so2)));
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(txtSo1, "Bạn hãy nhập số vào 2 ô số 1 và số 2");
        }
    }//GEN-LAST:event_btnThucHienActionPerformed

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
            java.util.logging.Logger.getLogger(Bai3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bai3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bai3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bai3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bai3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThucHien;
    private javax.swing.JLabel lblCan;
    private javax.swing.JLabel lblKetQua;
    private javax.swing.JLabel lblMax;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblMu;
    private javax.swing.JLabel lblSo1;
    private javax.swing.JLabel lblSo2;
    private javax.swing.JTextField txtCan;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    private javax.swing.JTextField txtMu;
    private javax.swing.JTextField txtSo1;
    private javax.swing.JTextField txtSo2;
    // End of variables declaration//GEN-END:variables
}
