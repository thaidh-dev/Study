package demojlegu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class DemoJlegu extends JPanel {

    public DemoJlegu() {
        setLayout(new BorderLayout());
        setBorder(new EtchedBorder(EtchedBorder.RAISED));
        setPreferredSize(new Dimension(100, 100));
        
        add(new Ghe(50, 10), BorderLayout.NORTH);
        add(new Ghe(50, 10), BorderLayout.SOUTH);
        add(new Ghe(10, 50), BorderLayout.WEST);
        add(new Ghe(10, 50), BorderLayout.EAST);
        add(new Ban(), BorderLayout.CENTER);
        
        new Movement(this);
        
        setLocation(100, 100);
    }
    
}
