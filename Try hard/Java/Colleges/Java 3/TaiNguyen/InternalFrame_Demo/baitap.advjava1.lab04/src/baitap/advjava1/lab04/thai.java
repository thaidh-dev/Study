package baitap.advjava1.lab04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class thai {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1000, 1000);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setDragMode(1);
        tabbedPane.addTab("Một", desktopPane);
        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.setClosable(true);
        internalFrame.setSize(300, 300);
        internalFrame.setLocation(60, 60);
        
                internalFrame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                System.out.println("sdsdsdsdsdsds");
            }

            
        });

        
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        
        
        JPanel panel2 = new JPanel();
        tabbedPane.addTab("Hai", panel2);
        
        int index = tabbedPane.indexOfTab("Hai");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setOpaque(false);
        JLabel lblThai = new JLabel("thái");
        
        JButton btn = new JButton("x");
        btn.setBorder(null);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        panel.add(lblThai);
        panel.add(btn);
        tabbedPane.setTabComponentAt(index, panel);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        
        f.add(tabbedPane);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("mở frame");
            }
        });
        f.setVisible(true);
        
        
    }
}

class DemoCustomJTabbedPane extends JFrame {
    JTabbedPane tabbedPane;
    int numTabs;
 
    public DemoCustomJTabbedPane() {
        createGUI();
        setDisplay();
    }
 
    /** set diplay for JFrame */
    private void setDisplay() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    /** set title and add JTabbedPane into JFrame */
    private void createGUI() {
        setTitle("Demo custum JTabbedPane");
        createJTabbedPane();
        add(tabbedPane);
    }
 
    /** create JTabbedPane contain 2 tab */
    private void createJTabbedPane() {
        /* create JTabbedPane */
        tabbedPane = new JTabbedPane(JTabbedPane.TOP,
                JTabbedPane.SCROLL_TAB_LAYOUT);
 
        /* add first tab */
        tabbedPane.add(createJPanel(), "Tab " + String.valueOf(numTabs),
                numTabs++);
        tabbedPane.setTabComponentAt(0, new DemoCustomTab(this));
 
        /* add tab to add new tab when click */
        tabbedPane.add(new JPanel(), "+", numTabs++);
 
        tabbedPane.addChangeListener(changeListener);
    }
 
    /** create JPanel contain a JLabel */
    private JPanel createJPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.add(new JScrollPane(createTextArea(10, 40)));
        return panel;
    }
 
    private JTextArea createTextArea(int row, int col) {
        JTextArea ta = new JTextArea(row, col);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setForeground(Color.BLUE);
        return ta;
    }
 
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            addNewTab();
        }
    };
 
    private void addNewTab() {
        int index = numTabs - 1;
        if (tabbedPane.getSelectedIndex() == index) { /* if click new tab */
            /* add new tab */
            tabbedPane.add(createJPanel(), "Tab " + String.valueOf(index),
                    index);
            /* set tab is custom tab */
            tabbedPane.setTabComponentAt(index, new DemoCustomTab(this));
            tabbedPane.removeChangeListener(changeListener);
            tabbedPane.setSelectedIndex(index);
            tabbedPane.addChangeListener(changeListener);
            numTabs++;
        }
    }
 
    public void removeTab(int index) {
        tabbedPane.remove(index);
        numTabs--;
 
        if (index == numTabs - 1 && index > 0) {
            tabbedPane.setSelectedIndex(numTabs - 2);
        } else {
            tabbedPane.setSelectedIndex(index);
        }
 
        if (numTabs == 1) {
            addNewTab();
        }
    }
 
    public static void main(String[] args) {
        new DemoCustomJTabbedPane();
    }
}

class DemoCustomTab extends JPanel {
 
    DemoCustomJTabbedPane customJTabbedPane;
 
    /** JPanel contain a JLabel and a JButton to close */
    public DemoCustomTab(DemoCustomJTabbedPane customJTabbedPane) {
        this.customJTabbedPane = customJTabbedPane;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBorder(new EmptyBorder(5, 2, 2, 2));
        setOpaque(false);
        addLabel();
        add(new CustomButton("x"));
    }
 
    private void addLabel() {
        JLabel label = new JLabel() {
            /** set text for JLabel, it will title of tab */
            public String getText() {
                int index = customJTabbedPane.tabbedPane
                        .indexOfTabComponent(DemoCustomTab.this);
                if (index != -1) {
                    return customJTabbedPane.tabbedPane.getTitleAt(index);
                }
                return null;
            }
        };
        /** add more space between the label and the button */
        label.setBorder(new EmptyBorder(0, 0, 0, 10));
        add(label);
    }
 
    class CustomButton extends JButton implements MouseListener {
        public CustomButton(String text) {
            int size = 15;
            setText(text);
            /** set size for button close */
            setPreferredSize(new Dimension(size, size));
 
            setToolTipText("close the Tab");
 
            /** set transparent */
            setContentAreaFilled(false);
 
            /** set border for button */
            setBorder(new EtchedBorder());
            /** don't show border */
            setBorderPainted(false);
 
            setFocusable(false);
 
            /** add event with mouse */
            addMouseListener(this);
 
        }
 
        /** when click button, tab will close */
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = customJTabbedPane.tabbedPane
                    .indexOfTabComponent(DemoCustomTab.this);
            if (index != -1) {
                customJTabbedPane.removeTab(index);
            }
        }
 
        @Override
        public void mousePressed(MouseEvent e) {
        }
 
        @Override
        public void mouseReleased(MouseEvent e) {
        }
 
        /** show border button when mouse hover */
        @Override
        public void mouseEntered(MouseEvent e) {
            setBorderPainted(true);
            setForeground(Color.RED);
        }
 
        /** hide border when mouse not hover */
        @Override
        public void mouseExited(MouseEvent e) {
            setBorderPainted(false);
            setForeground(Color.BLACK);
        }
    }
}