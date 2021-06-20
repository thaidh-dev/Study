package Frame.Panel.Manager;

import DAO.DAOImpl.ThongKe1DaoImpl;
import DAO.DAOImpl.ThongKe2DaoImpl;
import DAO.DAOImpl.ThongKe3DaoImpl;
import Frame.Panel.Report.ThongKeTaiSanHetKhauHao;
import Frame.Panel.Report.ThongKeTheoPhanLoai;
import Frame.Panel.Report.ThongKeTheoPhongBan;
import Utils.DateUtil;
import Utils.MoneyUtil;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaoCaoThongKe extends JPanel {
    ThongKeTheoPhanLoai thongKeTheoPhanLoai = new ThongKeTheoPhanLoai();
    ThongKeTheoPhongBan thongKeTheoPhongBan = new ThongKeTheoPhongBan();
    ThongKeTaiSanHetKhauHao thongKeTaiSanHetKhauHao = new ThongKeTaiSanHetKhauHao();


    public BaoCaoThongKe() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaoCaoThongKe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BaoCaoThongKe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaoCaoThongKe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BaoCaoThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        open();
        doneLoad = true;
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {
        cbxChoose = new JComboBox(new Object[] {
            " - Thống kê số lượng và tổng giá trị tài sản theo phân loại",
                " - Thống kê số lượng và tổng giá trị tài sản phòng ban",
                " - Liệt kê danh sách tài sản hết khấu hao"
        });

        cbxChoose.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        cbxChoose.setPreferredSize(new Dimension(800, 30));
    }

    public void addControls() {
        JPanel pnHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pnHeader.add(cbxChoose);

        cardLayout = new CardLayout();
        pnContent = new JPanel(cardLayout);

        pnContent.add(thongKeTheoPhanLoai,"ThongKe1");
        pnContent.add(thongKeTheoPhongBan,"ThongKe2");
        pnContent.add(thongKeTaiSanHetKhauHao,"ThongKe3");

        JPanel pnTemp1 = new JPanel();
        JPanel pnTemp2 = new JPanel();

        this.setLayout(new BorderLayout(10, 10));
        this.add(pnTemp1, BorderLayout.WEST);
        this.add(pnTemp2, BorderLayout.EAST);
        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnContent, BorderLayout.CENTER);
    }

    public void addEvents() {
        cbxChoose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                int i = cbxChoose.getSelectedIndex() + 1;
                switch (i) {
                    case 1:
                        cardLayout.show(pnContent, "ThongKe1");
                        break;
                    case 2:
                        cardLayout.show(pnContent, "ThongKe2");
                        break;
                    case 3:
                        cardLayout.show(pnContent, "ThongKe3");
                        break;
                    default: break;
                }
            }
        });
    }

    //<editor-fold desc="COMPONENT">
    public boolean doneLoad = false;

    JComboBox cbxChoose;
    CardLayout cardLayout;
    JPanel pnContent;
    //</editor-fold>
}
