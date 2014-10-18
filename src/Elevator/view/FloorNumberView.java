package Elevator.view;

import static Elevator.controller.ElevatorController.mfi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;

// Creates a JPanel for the floor numbers to be displayed
class FloorNumberView extends JPanel {
   private static final int FRAME_WIDTH = 300;
   private static final int FRAME_HEIGHT = 325;
   private String floor = "Up";
  
    // Constructs the content inside the floor number JPanel  
    FloorNumberView()
    {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        Thread floorThread = new Thread()
        {
            public void run()
            {
                while(true)
                {
                    repaint();
                    try
                    {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) { }
                }
            }
        };
        floorThread.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
       
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        
        g2.setColor(Color.RED);
        
        if(mfi.isDirectionUp()) floor = "Up";
        else floor = "Down";
        g2.drawString(floor, 20, 20);
        
        Font font = new Font("Arial", Font.BOLD, 220);
        g2.setFont(font);
        g2.setColor(Color.RED);
        floor = String.valueOf(mfi.getCurrentFloor());
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(floor, (int)((FRAME_WIDTH)/2)-(fm.stringWidth(floor)/2),
            (int)((FRAME_HEIGHT)/2)+(fm.getAscent()/2));
   }
    
}

