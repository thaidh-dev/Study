package lab.pkg6;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Bai4 extends javax.swing.JFrame {

    public Bai4() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTieuDe = new javax.swing.JLabel();
        lblTram = new javax.swing.JLabel();
        lblChuc = new javax.swing.JLabel();
        lblDonVi = new javax.swing.JLabel();
        txtTram = new javax.swing.JTextField();
        txtDonVi = new javax.swing.JTextField();
        txtChuc = new javax.swing.JTextField();
        btnTram = new javax.swing.JButton();
        btnChuc = new javax.swing.JButton();
        btnDonVi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTieuDe.setFont(new java.awt.Font("Dialog", 0, 40)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(204, 0, 0));
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("XỔ SỐ KIẾN THIẾT");

        lblTram.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblTram.setForeground(new java.awt.Color(0, 0, 0));
        lblTram.setText("Trăm");

        lblChuc.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblChuc.setForeground(new java.awt.Color(0, 0, 0));
        lblChuc.setText("Chục");

        lblDonVi.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblDonVi.setForeground(new java.awt.Color(0, 0, 0));
        lblDonVi.setText("Đơn vị");

        txtTram.setEditable(false);
        txtTram.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtDonVi.setEditable(false);
        txtDonVi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtChuc.setEditable(false);
        txtChuc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnTram.setText("Start");
        btnTram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTramActionPerformed(evt);
            }
        });

        btnChuc.setText("Start");
        btnChuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChucActionPerformed(evt);
            }
        });

        btnDonVi.setText("Start");
        btnDonVi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonViActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtTram, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTram)
                    .addComponent(btnTram))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblChuc)
                    .addComponent(txtChuc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChuc))
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDonVi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDonVi))
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTram)
                            .addComponent(lblDonVi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonVi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTram)
                            .addComponent(btnChuc)
                            .addComponent(btnDonVi))
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblChuc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTramActionPerformed
        quay(txtTram, btnTram);
    }//GEN-LAST:event_btnTramActionPerformed

    private void btnChucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChucActionPerformed
        quay(txtChuc, btnChuc);
    }//GEN-LAST:event_btnChucActionPerformed

    private void btnDonViActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonViActionPerformed
        quay(txtDonVi, btnDonVi);
    }//GEN-LAST:event_btnDonViActionPerformed

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
            java.util.logging.Logger.getLogger(Bai4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bai4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bai4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bai4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bai4().setVisible(true);
            }
        });
    }
    
    public void quay(JTextField txt, JButton btn) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    /* Math.round(double x); nó nhận tham số là double và nó cũng trả về double
                        nên là phải (int) để đặt kiểu int cho nó thì mới gắn cho int so đc */
                    int so = (int) Math.round(Math.random()*9);
                    txt.setText(String.valueOf(so));
                    try {
                        Thread.sleep(10);
                    }
                    catch (Exception e) {
                        break;
                    }
                }
            }
        }.start();
        btn.setEnabled(false);        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChuc;
    private javax.swing.JButton btnDonVi;
    private javax.swing.JButton btnTram;
    private javax.swing.JLabel lblChuc;
    private javax.swing.JLabel lblDonVi;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTram;
    private javax.swing.JTextField txtChuc;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtTram;
    // End of variables declaration//GEN-END:variables
}
