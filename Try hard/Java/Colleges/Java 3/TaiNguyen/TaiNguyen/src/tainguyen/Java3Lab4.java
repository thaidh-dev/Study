package lab.pkg4;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;




// mouse wheel là sự kiện lăn chuột

class UndecoratedFrame {
    private static Point point = new Point();

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        

        // Disables or enables decorations for this frame. By setting undecorated
        // to true will remove the frame's title bar including the maximize,
        // minimize and the close icon.
        frame.setUndecorated(true);

        // As the the frame's title bar removed we need to close out frame for
        // instance using our own button.
        JButton button = new JButton("Close Me");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // The mouse listener and mouse motion listener we add here is to simply
        // make our frame dragable.
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = frame.getLocation();
                frame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
            }
        });

        frame.setSize(300, 300);
        frame.setLocation(200, 200);
        frame.setLayout(new BorderLayout());

        frame.getContentPane().add(button, BorderLayout.NORTH);
        frame.getContentPane().add(new JLabel("Drag Me", JLabel.CENTER), BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
}

class BackgroundComponentDragger implements MouseMotionListener {

    private Component controlledComponent;

    /*
     * Point where cursor was last clicked.
     */
    private Point originPoint;

    public BackgroundComponentDragger(Component component) {
        this.controlledComponent = component;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currentFramePosition = controlledComponent.getLocation();
        Point newFramePosition = new Point(currentFramePosition.x + e.getX()
                - originPoint.x, currentFramePosition.y + e.getY() - originPoint.y);
        controlledComponent.setLocation(newFramePosition);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        originPoint = e.getPoint();
    }
}




class ComponentBorderDragger implements MouseMotionListener {

    private Component controlledComponent;

    private byte direction;
    protected static final byte NORTH = 1;
    protected static final byte WEST = 2;
    protected static final byte SOUTH = 4;
    protected static final byte EAST = 8;

    private Cursor sourceCursor;

    private static Map<Byte, Integer> cursors = new HashMap<Byte, Integer>();
    {
        cursors.put((byte) 1, Cursor.N_RESIZE_CURSOR);
        cursors.put((byte) 2, Cursor.W_RESIZE_CURSOR);
        cursors.put((byte) 4, Cursor.S_RESIZE_CURSOR);
        cursors.put((byte) 8, Cursor.E_RESIZE_CURSOR);
        cursors.put((byte) 3, Cursor.NW_RESIZE_CURSOR);
        cursors.put((byte) 9, Cursor.NE_RESIZE_CURSOR);
        cursors.put((byte) 6, Cursor.SW_RESIZE_CURSOR);
        cursors.put((byte) 12, Cursor.SE_RESIZE_CURSOR);
    }

    private Insets dragInsets;
    private Dimension minSize;

    private Point basePoint;

    public ComponentBorderDragger(Component controlledComponent, Insets dragInsets,
            Dimension minSize) {
        super();
        this.controlledComponent = controlledComponent;
        this.dragInsets = dragInsets;
        this.minSize = minSize;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (direction == 0) {
            return;
        }

        Point newPoint = e.getPoint();
        int x, y, width, height, newBasePointX, newBasePointY;
        x = controlledComponent.getX();
        y = controlledComponent.getY();
        width = controlledComponent.getWidth();
        height = controlledComponent.getHeight();
        newBasePointX = newPoint.x;
        newBasePointY = newPoint.y;

        if ((direction & EAST) == EAST) {
            int newWidth;
            newWidth = Math.max(minSize.width, width + newPoint.x
                    - basePoint.x);
            width = newWidth;
        }
        if ((direction & SOUTH) == SOUTH) {
            int novoAlto;
            novoAlto = Math.max(minSize.height, height + newPoint.y
                    - basePoint.y);
            height = novoAlto;
        }
        if ((direction & WEST) == WEST) {
            int newWidth, newX;
            newWidth = Math.max(minSize.width, width - newPoint.x
                    + basePoint.x);
            newX = Math.min(x + width - minSize.width, x + newPoint.x
                    - basePoint.x);

            // Changing coordenates of new base point to refer to the new component position
            newBasePointX -= newX - x;
            x = newX;
            width = newWidth;
        }
        if ((direction & NORTH) == NORTH) {
            int newHeigth, newY;
            newHeigth = Math.max(minSize.height, height - newPoint.y
                    + basePoint.y);
            newY = Math.min(y + height - minSize.height, y + newPoint.y
                    - basePoint.y);
            // Changing coordenates of new base point to refer to the new component position
            newBasePointY -= newY - y;
            y = newY;
            height = newHeigth;
        }
        controlledComponent.setBounds(x, y, width, height);
        basePoint = new Point(newBasePointX, newBasePointY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component originator = e.getComponent();
        if (direction == 0) {
            sourceCursor = originator.getCursor();
        }
        calculateDirection(e.getPoint(), e.getComponent().getSize());
        setCursor(e.getComponent());
        basePoint = e.getPoint();
    }

    private void setCursor(Component component) {
        if (direction == 0) {
            component.setCursor(sourceCursor);
        } else {
            int cursorType = cursors.get(direction);
            Cursor cursor = Cursor.getPredefinedCursor(cursorType);
            component.setCursor(cursor);
        }
    }

    private void calculateDirection(Point point, Dimension componentSize) {
        direction = 0;
        if (point.x < dragInsets.left) {
            direction |= WEST;
        }
        if (point.y < dragInsets.top) {
            direction |= NORTH;
        }
        if (point.x > componentSize.width - dragInsets.right) {
            direction |= EAST;
        }
        if (point.y > componentSize.height - dragInsets.bottom) {
            direction |= SOUTH;
        }
    }
}

class FrameComponent extends JComponent {

    private static final long serialVersionUID = 3383070502274306213L;

    private Insets insets;

    @Override
    public boolean contains(int x, int y) {
        return x < insets.left || y < insets.top || getHeight() - y < insets.bottom || getWidth() - x < insets.right;
    }

    public FrameComponent(Insets insets) {
        this.insets = insets;
    }
}

class GUI {

    private JFrame compoundFrame;
    private JPanel backgroundPanel;

    Dimension gUISize = new Dimension(400, 400);

    public GUI() {
        buildResizeableFrame();
    }

    public void activate() {
        compoundFrame.setVisible(true);
    }

    private void buildResizeableFrame() {
        compoundFrame = new JFrame();
        FrameComponent frame = new FrameComponent(new Insets(5, 5, 5, 5));
        backgroundPanel = new JPanel();
        compoundFrame.setLayout(null);
        compoundFrame.add(frame);
        compoundFrame.add(backgroundPanel);
        setFrameSizeController(frame, backgroundPanel);
        setFrameController(frame);
        setBackgroundPanelController(backgroundPanel);
        Dimension dimPant = Toolkit.getDefaultToolkit().getScreenSize();
        compoundFrame.setBounds(dimPant.width / 4, dimPant.height / 4, dimPant.width / 2, dimPant.height / 2);
        compoundFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        compoundFrame.setUndecorated(true);
    }

    private void setFrameSizeController(FrameComponent frame, JPanel panel) {
        compoundFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension sizeIn = ((JFrame) e.getComponent()).getContentPane().getSize();
                frame.setSize(sizeIn);
                panel.setSize(sizeIn);
            }
        });
    }

    private void setFrameController(FrameComponent frame) {
        ComponentBorderDragger controller = new ComponentBorderDragger(compoundFrame,
                new Insets(5, 5, 5, 5), new Dimension(10, 10));
        frame.addMouseMotionListener(controller);
    }

    private void setBackgroundPanelController(JPanel panel) {
        panel.addMouseMotionListener(new BackgroundComponentDragger(compoundFrame));
    }

    public static void main(String[] args) {
        new GUI().activate();
    }
}

class MouseListenerExample1 extends Frame implements MouseListener {
    private Label label;

    public MouseListenerExample1() {
        addMouseListener(this);

        label = new Label();
        label.setBounds(20, 50, 100, 20);
        add(label);
        setSize(300, 300);
        setLayout(null);
        setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        label.setText("Mouse Clicked");
    }

    public void mouseEntered(MouseEvent e) {
        label.setText("Mouse Entered");
    }

    public void mouseExited(MouseEvent e) {
        label.setText("Mouse Exited");
    }

    public void mousePressed(MouseEvent e) {
        label.setText("Mouse Pressed");
    }

    public void mouseReleased(MouseEvent e) {
        label.setText("Mouse Released");
    }

    public static void main(String[] args) {
        new MouseListenerExample1();
    }
}


    
   