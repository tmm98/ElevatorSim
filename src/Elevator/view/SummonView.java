package Elevator.view;

import static Elevator.controller.ElevatorController.cfi;
import static Elevator.controller.ElevatorController.mfi;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// Creates a JPanel for the summon buttons
class SummonView extends JPanel implements ActionListener
{
   private static final int FRAME_WIDTH = 200;
   private static final int FRAME_HEIGHT = 700;
   private static final int BUTTON_SIZE = 20;
   
    JButton up1, down1, up2, down2, up3, down3, up4, down4, up5, down5;
    JButton up6, down6, up7, down7, up8, down8, up9, down9, up10, down10;
    
    private BufferedImage bg = null;
  
    // Constructs the content for the summon JPanel
    SummonView ()
    {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        FlowLayout pLayout = new FlowLayout(FlowLayout.CENTER, 40, 20);
        setLayout(pLayout);
        
        up10 = new RoundButton("", 0, "hidden", "", 10);
        
        down10 = new RoundButton("", BUTTON_SIZE, "summon", "down", 10);
        down10.setActionCommand("down10");
        
        up9 = new RoundButton("", BUTTON_SIZE, "summon", "up", 9);
        up9.setActionCommand("up9");
        
        down9 = new RoundButton("", BUTTON_SIZE, "summon", "down", 9);
        down9.setActionCommand("down9");
        
        up8 = new RoundButton("", BUTTON_SIZE, "summon", "up", 8);
        up8.setActionCommand("up8");
        
        down8 = new RoundButton("", BUTTON_SIZE, "summon", "down", 8);
        down8.setActionCommand("down8");
        
        up7 = new RoundButton("", BUTTON_SIZE, "summon", "up", 7);
        up7.setActionCommand("up7");
        
        down7 = new RoundButton("", BUTTON_SIZE, "summon", "down", 7);
        down7.setActionCommand("down7");
        
        up6 = new RoundButton("", BUTTON_SIZE, "summon", "up", 6);
        up6.setActionCommand("up6");
        
        down6 = new RoundButton("", BUTTON_SIZE, "summon", "down", 6);
        down6.setActionCommand("down6");
        
        up5 = new RoundButton("", BUTTON_SIZE, "summon", "up", 5);
        up5.setActionCommand("up5");
        
        down5 = new RoundButton("", BUTTON_SIZE, "summon", "down", 5);
        down5.setActionCommand("down5");
        
        up4 = new RoundButton("", BUTTON_SIZE, "summon", "up", 4);
        up4.setActionCommand("up4");
        
        down4 = new RoundButton("", BUTTON_SIZE, "summon", "down", 4);
        down4.setActionCommand("down4");
        
        up3 = new RoundButton("", BUTTON_SIZE, "summon", "up", 3);
        up3.setActionCommand("up3");
        
        down3 = new RoundButton("", BUTTON_SIZE, "summon", "down", 3);
        down3.setActionCommand("down3");
        
        up2 = new RoundButton("", BUTTON_SIZE, "summon", "up", 2);
        up2.setActionCommand("up2");
        
        down2 = new RoundButton("", BUTTON_SIZE, "summon", "down", 2);
        down2.setActionCommand("down2");
        
        up1 = new RoundButton("", BUTTON_SIZE, "summon", "up", 1);
        up1.setActionCommand("up1");
        
        down1 = new RoundButton("", 0, "hidden", "", 1);
 
        
        down10.addActionListener(this);
        up9.addActionListener(this);
        down9.addActionListener(this);
        up8.addActionListener(this);
        down8.addActionListener(this);
        up7.addActionListener(this);
        down7.addActionListener(this);
        up6.addActionListener(this);
        down6.addActionListener(this);
        up5.addActionListener(this);
        down5.addActionListener(this);
        up4.addActionListener(this);
        down4.addActionListener(this);
        up3.addActionListener(this);
        down3.addActionListener(this);
        up2.addActionListener(this);
        down2.addActionListener(this);
        up1.addActionListener(this);
 
        down10.setToolTipText("Go Down");
        up9.setToolTipText("Go Up");
        down9.setToolTipText("Go Down");
        up8.setToolTipText("Go Up");
        down8.setToolTipText("Go Down");
        up7.setToolTipText("Go Up");
        down7.setToolTipText("Go Down");
        up6.setToolTipText("Go Up");
        down6.setToolTipText("Go Down");
        up5.setToolTipText("Go Up");
        down5.setToolTipText("Go Down");
        up4.setToolTipText("Go Up");
        down4.setToolTipText("Go Down");
        up3.setToolTipText("Go Up");
        down3.setToolTipText("Go Down");
        up2.setToolTipText("Go Up");
        down2.setToolTipText("Go Down");
        up1.setToolTipText("Go Up");
        
        
        add(up10);     // Hidden button to fix layout
        add(down10);
        add(up9);
        add(down9);
        add(up8);
        add(down8);
        add(up7);
        add(down7);
        add(up6);
        add(down6);
        add(up5);
        add(down5);
        add(up4);
        add(down4);
        add(up3);
        add(down3);
        add(up2);
        add(down2);
        add(up1);
        add(down1); // Hidden button to fix layout
        
       
        Thread summonThread = new Thread() {
        @Override
        public void run() {
            while (true)
            {   
                updateLights();
           
                try
                {
                    Thread.sleep(500);  // Refresh rate in miliseconds
                } catch (InterruptedException ex) { }
            }
        }
   
    };
    summonThread.start();
    }
    
/**
 * Recalls paint to turn lights on and off
 */
    public void updateLights()
    {
        repaint();
    }
    
   @Override
    public void actionPerformed(ActionEvent e) {
        if (null != e.getActionCommand())switch (e.getActionCommand())
        {
            case "down10":
                cfi.distributeRideToArray(10, false);
                mfi.setDownLight(10, true);
                break;
            case "up9":
                cfi.distributeRideToArray(9, true);
                mfi.setUpLight(9, true);
                break;
            case "down9":
                cfi.distributeRideToArray(9, false);
                mfi.setDownLight(9, true);
                break;
            case "up8":
                cfi.distributeRideToArray(8, true);
                mfi.setUpLight(8, true);
                break;
            case "down8":
                cfi.distributeRideToArray(8, false);
                mfi.setDownLight(8, true);
                break;
            case "up7":
                cfi.distributeRideToArray(7, true);
                mfi.setUpLight(7, true);
                break;
            case "down7":
                cfi.distributeRideToArray(7, false);
                mfi.setDownLight(7, true);
                break;
            case "up6":
                cfi.distributeRideToArray(6, true);
                mfi.setUpLight(6, true);
                break;
            case "down6":
                cfi.distributeRideToArray(6, false);
                mfi.setDownLight(6, true);
                break;
            case "up5":
                cfi.distributeRideToArray(5, true);
                mfi.setUpLight(5, true);
                break;
            case "down5":
                cfi.distributeRideToArray(5, false);
                mfi.setDownLight(5, true);;
                break;
            case "up4":
                cfi.distributeRideToArray(4, true);
                mfi.setUpLight(4, true);
                break;
            case "down4":
                cfi.distributeRideToArray(4, false);
                mfi.setDownLight(4, true);
                break;
            case "up3":
                cfi.distributeRideToArray(3, true);
                mfi.setUpLight(3, true);
                break;
            case "down3":
                cfi.distributeRideToArray(3, false);
                mfi.setDownLight(3, true);
                break;
            case "up2":
                cfi.distributeRideToArray(2, true);
                mfi.setUpLight(2, true);
                break;
            case "down2":
                cfi.distributeRideToArray(2, false);
                mfi.setDownLight(2, true);
                break;
            case "up1":
                cfi.distributeRideToArray(1, true);
                mfi.setUpLight(1, true);
                break;
       }
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        try
        {
            bg = ImageIO.read(getClass().getClassLoader().getResource(
                "Elevator/util/summonBG.png"));
        } catch (IOException ex) {}
          
        Graphics2D g2 = (Graphics2D)g;
        TexturePaint bgTex =
            new TexturePaint(bg, new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        g2.setPaint(bgTex);
        g2.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
      }
}
