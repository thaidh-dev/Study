package demojlegu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class Ghe extends JPanel {
    int width, height;

    public Ghe(int width, int height) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel pnl = new JPanel();
        this.width = width;
        this.height = height;
        pnl.setPreferredSize(new Dimension(width, height));
        pnl.setBackground(Color.BLACK);
        add(pnl);
    }
}
