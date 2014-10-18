package Elevator.view;

import static Elevator.controller.ElevatorController.mfi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

// Class for the round buttons
class RoundButton extends JButton
{

    int radius, floor;
    String type, orient;
    Shape shape;
    BufferedImage on = null;
    BufferedImage off = null;

    RoundButton(String label, int radius, String type, String orient,
        int floor)
    {
        super(label);

        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
    
        this.radius = radius;
        this.type = type;
        this.orient = orient;
        this.floor = floor;

        setContentAreaFilled(false);
    
        try
        {
            if(type.equals("summon") && orient.equals("up"))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/upButtonOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/upButtonOff.png"));
            }
            if(type.equals("summon") && orient.equals("down"))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/downButtonOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/downButtonOff.png"));
            }
            if(type.equals("floor") && (floor == 1))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/oneOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/oneOff.png"));
            }
            if(type.equals("floor") && (floor == 2))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/twoOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/twoOff.png"));
            }
            if(type.equals("floor") && (floor == 3))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/threeOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/threeOff.png"));
            }
            if(type.equals("floor") && (floor == 4))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/fourOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/fourOff.png"));
            }
            if(type.equals("floor") && (floor == 5))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/fiveOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/fiveOff.png"));
            }
            if(type.equals("floor") && (floor == 6))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/sixOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/sixOff.png"));
            }
            if(type.equals("floor") && (floor == 7))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/sevenOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/sevenOff.png"));
            }
            if(type.equals("floor") && (floor == 8))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/eightOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/eightOff.png"));
            }
            if(type.equals("floor") && (floor == 9))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/nineOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/nineOff.png"));
            }
            if(type.equals("floor") && (floor == 10))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/tenOn.png"));
                off = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/tenOff.png"));
            }
            if(type.equals("open"))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/open.png"));
            }
            if(type.equals("close"))
            {
                on = ImageIO.read(getClass().getClassLoader().getResource(
                    "Elevator/util/close.png"));
            }
        } catch (IOException ex) {}
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;

        if(type.equals("summon"))
        {
            if(orient.equals("up"))
            {
                if(mfi.getUpLight(floor))
                {
                    TexturePaint upOnTex = new TexturePaint(on,
                        new Rectangle(0, 0, radius, radius));
                    g2.setPaint(upOnTex);
                }
                else
                {
                    TexturePaint upOffTex = new TexturePaint(off,
                        new Rectangle(0, 0, radius, radius));
                    g2.setPaint(upOffTex);
                }
            }
            else
            {
                if(mfi.getDownLight(floor))
                {
                    TexturePaint upOnTex = new TexturePaint(on,
                        new Rectangle(0, 0, radius, radius));
                    g2.setPaint(upOnTex);
                }
                else
                {
                    TexturePaint upOffTex = new TexturePaint(off,
                        new Rectangle(0, 0, radius, radius));
                    g2.setPaint(upOffTex);
                }  
            }
        }
        if(type.equals("floor"))
        {
            if(mfi.getFloorLight(floor))
            {
                TexturePaint OnTex = new TexturePaint(on,
                    new Rectangle(0, 0, radius, radius));
                g2.setPaint(OnTex);
            }
            else
            {
                TexturePaint OffTex = new TexturePaint(off,
                    new Rectangle(0, 0, radius, radius));
                g2.setPaint(OffTex);
            }
        }
        if(type.equals("open"))
        {
            TexturePaint OnTex = new TexturePaint(on, new Rectangle(0, 0, radius, radius));
            g2.setPaint(OnTex);
        }
        if(type.equals("close"))
        {
            TexturePaint OnTex = new TexturePaint(on, new Rectangle(0, 0, radius, radius));
            g2.setPaint(OnTex);
        }

        g2.fillOval(0, 0, radius, radius);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g)
    {
        g.setColor(new Color(1f,0f,0f,0f ));
        g.drawOval(0, 0, 0, 0);
    }

    @Override
    public boolean contains(int x, int y)
    {
        if (shape == null || 
            !shape.getBounds().equals(getBounds()))
        {
            shape = new Ellipse2D.Float(0, 0, radius, radius);
        }
        return shape.contains(x, y);
    }
}
