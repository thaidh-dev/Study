package panel;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ManHinhThongKe implements Khung {
    public JPanel pnlThan;
    JTabbedPane tab;
    ThongKeSach tk;
    ThongKeGiaTienTungSach tkgt;
    ThongKeSoLuongDauSach tkslds;
    ThongKeSoPhieuMuonTheoThang tkspmtt;
    LietKePMQuaHan lkpmqh;
    ThongKeLuotMuonTungSach tklmts;

    public ManHinhThongKe() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        pnlThan = new JPanel(new BorderLayout());
        tab = new JTabbedPane();
        tk = new ThongKeSach();
        tkgt = new ThongKeGiaTienTungSach();
        tkslds = new ThongKeSoLuongDauSach();
        tkspmtt = new ThongKeSoPhieuMuonTheoThang();
        lkpmqh = new LietKePMQuaHan();
        tklmts = new ThongKeLuotMuonTungSach();
    }

    @Override
    public void taoDang() {
    }

    @Override
    public void event() {
        pnlThan.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                switch (tab.getSelectedIndex()) {
                    case 0:
                        tk.fillToTable();
                        break;
                    case 1:
                        tkgt.fillToTable();
                        break;
                    case 2:
                        tkslds.fillToTable();
                        break;
                    case 3:
                        tkspmtt.fillToComboBox();
                        break;
                    case 4:
                        lkpmqh.fillToTable();
                        break;
                    case 5:
                        tklmts.fillToComboBox();
                        break;
                }
            }
        });
        
        tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tab.getSelectedIndex()) {
                    case 0:
                        tk.fillToTable();
                        break;
                    case 1:
                        tkgt.fillToTable();
                        break;
                    case 2:
                        tkslds.fillToTable();
                        break;
                    case 3:
                        tkspmtt.fillToComboBox();
                        break;
                    case 4:
                        lkpmqh.fillToTable();
                        break;
                    case 5:
                        tklmts.fillToComboBox();
                        break;
                }
            }
        });
    }

    @Override
    public void add() {
        tab.addTab("Th???ng k?? s??ch", tk.pnlThan);
        tab.addTab("Th???ng k?? gi?? ti???n theo t???ng s??ch", tkgt.pnlThan);
        tab.addTab("Th???ng k?? s??? l?????ng m???i ?????u s??ch", tkslds.pnlThan);
        tab.addTab("Th???ng k?? s??? phi???u m?????n theo th??ng", tkspmtt.pnlThan);
        tab.addTab("Li???t k?? phi???u m?????n qu?? h???n", lkpmqh.pnlThan);
        tab.addTab("Th???ng k?? s??? l?????t m?????n t???ng ?????u s??ch", tklmts.pnlThan);
        
        pnlThan.add(tab, BorderLayout.CENTER);
    }

    @Override
    public void fillToTable() {
        
    }

    @Override
    public void showItem(int a) {
        
    }

    @Override
    public void search() {
        
    }

    @Override
    public void insert() {
        
    }

    @Override
    public void update() {
        
    }

    @Override
    public void delete() {
        
    }

    @Override
    public void clear() {
        
    }

    @Override
    public void editOn() {
        
    }

    @Override
    public void editOff() {
        
    }
}
