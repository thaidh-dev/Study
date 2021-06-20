/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phathienvidai;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class NonOverlappingLayout implements LayoutManager
{
    @Override
    public void addLayoutComponent (String name, Component comp)
    {
        // Do nothing
    }

    @Override
    public void removeLayoutComponent (Component comp)
    {
        // Do nothing
    }

    @Override
    public Dimension preferredLayoutSize (Container parent)
    {
        int count = parent.getComponentCount ();
        Rectangle [] r = new Rectangle [count];

        for (int i = 0; i < count; i++)
        {
            Component c = parent.getComponent (i);
            if (c.isVisible ())
                r [i] = c.getBounds ();
            else r [i] = new Rectangle (0, 0, 0, 0);
        }

        r = doLayout (r);

        Rectangle result = new Rectangle (0, 0, 0, 0);
        for (int i = 0; i < count; i++)
        {
            result = result.union (r [i]);
        }

        return result.getSize ();
    }

    @Override
    public Dimension minimumLayoutSize (Container parent)
    {
        return preferredLayoutSize (parent);
    }

    @Override
    public void layoutContainer (Container parent)
    {
        int count = parent.getComponentCount ();
        Rectangle [] r = new Rectangle [count];

        for (int i = 0; i < count; i++)
        {
            Component c = parent.getComponent (i);
            if (c.isVisible ())
                r [i] = c.getBounds ();
            else r [i] = new Rectangle (0, 0, 0, 0);
        }

        r = doLayout (r);

        int minX = 0;
        int minY = 0;
        for (int i = 0; i < count; i++)
        {
            minX = Math.min (minX, r [i].x);
            minY = Math.min (minY, r [i].y);
        }

        for (int i = 0; i < count; i++)
        {
            r [i].translate (-minX, -minY);
            Component c = parent.getComponent (i);
            if (c.isVisible ())
                c.setBounds (r [i]);
        }
    }

    public Rectangle [] doLayout (Rectangle [] initialPositions)
    {
        int count = initialPositions.length;
        Rectangle [] result = new Rectangle [count];
        for (int i = 0; i < count; i++)
            result [i] = new Rectangle (initialPositions [i]);

        boolean intersection;

        do
        {
            intersection = false;

            for (int i = 0; i < count; i++)
            {
                int xf = 0;
                int yf = 0;

                Rectangle r1 = result [i];

                for (int j = 0; j < count; j++)
                {
                    Rectangle r2 = result [j];

                    if (i != j && r1.intersects (r2))
                    {
                        if (r1.x * 2 + r1.width < r2.x * 2 + r2.width)
                            xf -= 1;
                        else
                            xf += 1;

                        if (r1.y * 2 + r1.height < r2.y * 2 + r2.height)
                            yf -= 1;
                        else
                            yf += 1;

                        intersection = true;
                    }
                }

                if (xf > 0) result [i].x += 1;
                else if (xf < 0) result [i].x -= 1;

                if (yf > 0) result [i].y += 1;
                else if (yf < 0) result [i].y -= 1;
            }
        } while (intersection);

        return result;
    }

    public static void main (String [] args)
    {
        JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane ().setLayout (new NonOverlappingLayout ());

        Random r = new Random ();
        for (int i = 0; i < 100; i++)
        {
            JButton b = new JButton ();
            b.setBounds (r.nextInt (780), r.nextInt (580), 20, 20);
            frame.getContentPane ().add (b);
        }
        frame.getContentPane ();

        frame.pack ();
        frame.setVisible (true);
    }
}