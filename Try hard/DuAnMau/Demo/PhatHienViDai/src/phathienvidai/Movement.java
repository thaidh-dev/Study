package phathienvidai;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;

public class Movement implements MouseListener, MouseMotionListener {
    private int x, y;
    
    public Movement(JButton btn) {
        btn.addMouseListener(this);
        btn.addMouseMotionListener(this);
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        e.getComponent().setLocation((e.getX() + e.getComponent().getX()) - x, (e.getY() + e.getComponent().getY()) - y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
