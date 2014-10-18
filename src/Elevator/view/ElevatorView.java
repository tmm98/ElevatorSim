package Elevator.view;

import static Elevator.controller.ElevatorController.cfi;
import static Elevator.controller.ElevatorController.mfi;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class ElevatorView extends JPanel
{    
    private static final int FRAME_WIDTH = 150;
    private static final int FRAME_HEIGHT = 700;
    private static final double BOX_WIDTH = 40;
    private static final double BOX_HEIGHT = 30;
    private static final int UPDATE_RATE = 60;
    private static final double elevX = 75;
   
    private double elevY = 670;
    
    TexturePaint elevator1;
    TexturePaint door1;
    TexturePaint door2;
    Graphics2D g2;
  
    ElevatorView() 
    {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
      
        Thread simThread = new Thread() {
        @Override
        public void run() {
            while(true)
            {
                elevY = mfi.getPos();
                
                repaint(new Rectangle((int)(elevX - BOX_WIDTH - 10),
                    (int)(elevY - BOX_HEIGHT - 10), (int)((2 * BOX_WIDTH)+20),
                    (int)((2 * BOX_HEIGHT))+20));
    
                try
                {
                    Thread.sleep(1000 / UPDATE_RATE);
                } catch (InterruptedException ex) { }
            }
        }
        };
    simThread.start();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        BufferedImage elevatorTex = null, bgTex = null, lDoorTex = null, rDoorTex = null;;
        TexturePaint bg;
       
        super.paintComponent(g);
        g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      
        try {
            elevatorTex = ImageIO.read(getClass().getClassLoader().getResource(
                "Elevator/util/elevatorDoor.png"));
            bgTex = ImageIO.read(getClass().getClassLoader().getResource(
                "Elevator/util/simBG.png"));
            lDoorTex = ImageIO.read(getClass().getClassLoader().getResource(
                "Elevator/util/leftDoor.png"));
            rDoorTex = ImageIO.read(getClass().getClassLoader().getResource(
                "Elevator/util/rightDoor.png"));
        } catch (IOException ex) {}
            
        bg = new TexturePaint(bgTex, new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        g2.setPaint(bg);
        g2.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
      
        elevator1 = new TexturePaint(elevatorTex,
            new Rectangle((int) (elevX - BOX_WIDTH), (int) (elevY - BOX_HEIGHT),
            (int)(2 * BOX_WIDTH), (int)(2 * BOX_HEIGHT)));
        g2.setPaint(elevator1);
        g2.fillRect((int) (elevX - BOX_WIDTH), (int) (elevY - BOX_HEIGHT),
            (int)(2 * BOX_WIDTH), (int)(2 * BOX_HEIGHT));
      
        int rightDoorC = cfi.getDoorState();
        door1 = new TexturePaint(rDoorTex,
            new Rectangle((int) (elevX + rightDoorC), (int) (elevY - BOX_HEIGHT),
            (int) (BOX_WIDTH / 2), (int) (2 * BOX_HEIGHT)));
        g2.setPaint(door1);
        g2.fillRect((int) (elevX + rightDoorC), (int) (elevY - BOX_HEIGHT),
            (int) (BOX_WIDTH / 2), (int) (2 * BOX_HEIGHT));

        int leftDoorC = cfi.getDoorState();
        door2 = new TexturePaint(lDoorTex,
            new Rectangle((int) (elevX - 20 - leftDoorC), (int) (elevY - BOX_HEIGHT),
            (int) (BOX_WIDTH / 2), (int) (2 * BOX_HEIGHT)));
        g2.setPaint(door2);
        g2.fillRect((int) (elevX - 20 - leftDoorC), (int) (elevY - BOX_HEIGHT),
            (int) ((BOX_WIDTH / 2)), (int) (2 * BOX_HEIGHT));
   }         
}
