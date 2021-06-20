package lab.pkg6;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bai2 extends javax.swing.JFrame implements Runnable{

    public Bai2() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDayChooserBeanInfo1 = new com.toedter.calendar.JDayChooserBeanInfo();
        btnClock = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bài 2");

        btnClock.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        btnClock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btnClock, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btnClock, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClockActionPerformed
        Thread a = new Thread(this);
        a.start();
        btnClock.setEnabled(false);
    }//GEN-LAST:event_btnClockActionPerformed

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
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        /* Nếu bạn thấy chữ ký của phương thức invokeLater, 
        bạn sẽ thấy invokeLater lấy một đối tượng Runnable và 
        xếp hàng nó để được xử lý bởi luồng EventDispatcher. 
        Chủ đề EDT sẽ xử lý yêu cầu này chỉ sau khi sắp xếp tất cả các sự kiện hoặc 
        yêu cầu đang chờ xử lý AWT. 
        Ngay cả khi invokeLater được gọi trực tiếp dưới dạng Event, 
        việc xử lý luồng của tác vụ Runnable vẫn chỉ được thực hiện sau khi xử lý tất cả các Sự kiện AWT đang chờ xử lý. */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bai2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClock;
    private com.toedter.calendar.JDayChooserBeanInfo jDayChooserBeanInfo1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (true) {
            Date now = new Date();
            SimpleDateFormat formater = new SimpleDateFormat();
            formater.applyPattern("hh:mm:ss aa");
            String time = formater.format(now);
            btnClock.setText(time);
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {
                break;
            }
        }
    }
}

// thread không dùng cũng đc, nó giúp nhanh hơn, chạy đa tiến trình sẽ tận dụng sức mạnh của cpu