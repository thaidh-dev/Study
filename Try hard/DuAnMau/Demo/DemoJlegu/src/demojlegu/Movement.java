package demojlegu;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.peer.MouseInfoPeer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Movement implements MouseListener, MouseMotionListener {
    private int x, y;
    private Robot robot;
    
    public Movement(JPanel btn) {
        try {
            robot = new Robot();
        } 
        catch (AWTException ex) {
            System.out.println(ex);
        }
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
        int a = e.getX() + e.getComponent().getX() - x; // tọa độ x của bàn vs khu
        int b = e.getY() + e.getComponent().getY() - y; // tọa độ y của bàn vs khu
//        int i = e.getComponent().getParent().getWidth() - e.getComponent().getWidth(); // tọa độ x tối đa của bàn
//        int j = e.getComponent().getParent().getHeight() - e.getComponent().getHeight(); // tọa độ y tối đa của bàn
        
        e.getComponent().setLocation(a, b);

        double u = e.getComponent().getX(); // tọa độ x của bàn vs khu
        double v = e.getComponent().getY(); // tọa độ y của bàn vs khu
        
        if (u <= 0) {
            if (v <= 0) {
                e.getComponent().setLocation(0, 0);
            }
            else if (v >= 407) {
                e.getComponent().setLocation(0, 407);
            }
            else {
                e.getComponent().setLocation(0, b);
            }
            locationMouse(e);
        }
        else if (u >= 656 /*tọa độ x tối đa mà chuột di được trên khu*/) {
            if (v <= 0) {
                e.getComponent().setLocation(656, 0);
            }
            else if (v >= 407) {
                e.getComponent().setLocation(656, 407);
            }
            else {
                e.getComponent().setLocation(656, b);
            }
            locationMouse(e);
        }
        else {
            if (v <= 0) {
                e.getComponent().setLocation(a, 0);
                locationMouse(e);
            }
            else if (v >= 407 /*tọa độ y tối đa mà chuột di được trên khu*/) {
                e.getComponent().setLocation(a, 407);
                locationMouse(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    public void locationMouse(MouseEvent e) {
        Point p = e.getComponent().getLocationOnScreen();
        try {
            robot.mouseMove((int) p.getX() + x, (int) p.getY() + y);
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}