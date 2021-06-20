package lab.pkg2;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;

public class ThongKe extends Khung {
    JTabbedPane tab;
    JPanel pnlJFrame;
    ThongKeDoanhThu dt;
    ThongKeDoanhThu2 dt2;
    TongHopDiem d;
    TongHopSoLuong sl;

    public ThongKe() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        tab = new JTabbedPane();
        pnlJFrame = new JPanel(new BorderLayout());
        
        dt = new ThongKeDoanhThu();
        dt2 = new ThongKeDoanhThu2();
        d = new TongHopDiem();
        sl = new TongHopSoLuong();
    }

    @Override
    public void taoDang() {
    }

    @Override
    public void add() {
        tab.addTab("Một", d.pnlJFrame);
        if (truongPhong == true) {
            tab.addTab("Hai", dt.pnlJFrame);
        }
        else {
            tab.addTab("Hai", dt2.pnlJFrame);
        }
        tab.addTab("Ba", sl.pnlJFrame);
        
        pnlJFrame.add(tab, BorderLayout.CENTER);
    }

    @Override
    public void event() {
        pnlJFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                d.eventPnlJFrame();
                dt.eventPnlJFrame();
                sl.eventPnlJFrame();
            }
        });
    }
}
