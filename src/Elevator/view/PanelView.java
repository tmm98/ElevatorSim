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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class PanelView extends JPanel implements ActionListener
{
   private static final int FRAME_WIDTH = 300;
   private static final int FRAME_HEIGHT = 325;
   private static final int BUTTON_SIZE = 40;
   
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    JButton open, close, call, emergency;
    BufferedImage bg = null;
  
    PanelView()
    {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        FlowLayout pLayout = new FlowLayout(FlowLayout.CENTER, 40, 20);
        setLayout(pLayout);
        
        b1 = new RoundButton("", BUTTON_SIZE, "floor", "", 1);
        b1.setMnemonic(KeyEvent.VK_1);
        b1.setActionCommand("enable1");
 
        b2 = new RoundButton("", BUTTON_SIZE, "floor", "", 2);
        b2.setMnemonic(KeyEvent.VK_2);
        b2.setActionCommand("enable2");
 
        b3 = new RoundButton("", BUTTON_SIZE, "floor", "", 3);
        b3.setMnemonic(KeyEvent.VK_3);
        b3.setActionCommand("enable3");
        
        b4 = new RoundButton("", BUTTON_SIZE, "floor", "", 4);
        b4.setMnemonic(KeyEvent.VK_4);
        b4.setActionCommand("enable4");
     
        b5 = new RoundButton("", BUTTON_SIZE, "floor", "", 5);
        b5.setMnemonic(KeyEvent.VK_5);
        b5.setActionCommand("enable5");
        
        b6 = new RoundButton("", BUTTON_SIZE, "floor", "", 6);
        b6.setMnemonic(KeyEvent.VK_6);
        b6.setActionCommand("enable6");
 
        b7 = new RoundButton("", BUTTON_SIZE, "floor", "", 7);
        b7.setMnemonic(KeyEvent.VK_7);
        b7.setActionCommand("enable7");
        
        b8 = new RoundButton("", BUTTON_SIZE, "floor", "", 8);
        b8.setMnemonic(KeyEvent.VK_8);
        b8.setActionCommand("enable8");
        
        b9 = new RoundButton("", BUTTON_SIZE, "floor", "", 9);
        b9.setMnemonic(KeyEvent.VK_9);
        b9.setActionCommand("enable9");
        
        b10 = new RoundButton("", BUTTON_SIZE, "floor", "", 10);
        b10.setMnemonic(KeyEvent.VK_0);
        b10.setActionCommand("enable10");
        
        open = new RoundButton("", BUTTON_SIZE, "open", "", 0);
        open.setActionCommand("open");
        
        close = new RoundButton("", BUTTON_SIZE, "close", "", 0);
        close.setActionCommand("close");
        
        call = new JButton("Call");
        call.setActionCommand("call");
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
        b3.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b10.addActionListener(this);
        open.addActionListener(this);
        close.addActionListener(this);
        call.addActionListener(this);
 
        b1.setToolTipText("Move to 1st floor");
        b2.setToolTipText("Move to 2nd floor");
        b3.setToolTipText("Move to 3rd floor");
        b4.setToolTipText("Move to 4th floor");
        b5.setToolTipText("Move to 5th floor");
        b6.setToolTipText("Move to 6th floor");
        b7.setToolTipText("Move to 7th floor");
        b8.setToolTipText("Move to 8th floor");
        b9.setToolTipText("Move to 9th floor");
        b10.setToolTipText("Move to 10th floor");
        open.setToolTipText("Open the doors");
        close.setToolTipText("Close the doors");
        call.setToolTipText("Call the operator");
        
        b1.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b2.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b3.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b4.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b5.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b6.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b7.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b8.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b9.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        b10.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        open.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        close.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(open);
        add(b10);
        add(close);
        add(call);
        
        Thread panelThread = new Thread() {
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
        panelThread.start();
    }
    
    void updateLights()
    {
        repaint();
    }
 
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (null != e.getActionCommand()) switch (e.getActionCommand())
        {
            case "enable1":
                cfi.distributeStopToArray(1);
                mfi.setFloorLight(1, true);
                break;
            case "enable2":
                cfi.distributeStopToArray(2);
                mfi.setFloorLight(2, true);
                break;
            case "enable3":
                cfi.distributeStopToArray(3);
                mfi.setFloorLight(3, true);
                break;
            case "enable4":
                cfi.distributeStopToArray(4);
                mfi.setFloorLight(4, true);
                break;
            case "enable5":
                cfi.distributeStopToArray(5);
                mfi.setFloorLight(5, true);
                break;
            case "enable6":
                cfi.distributeStopToArray(6);
                mfi.setFloorLight(6, true);
                break;
            case "enable7":
                cfi.distributeStopToArray(7);
                mfi.setFloorLight(7, true);
                break;
            case "enable8":
               cfi.distributeStopToArray(8);
               mfi.setFloorLight(8, true);
               break;
            case "enable9":
                cfi.distributeStopToArray(9);
                mfi.setFloorLight(9, true);
                break;
            case "enable10":
                cfi.distributeStopToArray(10);
                mfi.setFloorLight(10, true);
                break;
            case "open":
                cfi.openDoorB();
                break;
            case "close":
                cfi.closeDoorB();
                break;
            case "call":
                mfi.makeCall();
                break;
       }
    }
    
    
    @Override
    protected void paintComponent(Graphics g)
    {
        try
        {
            bg = ImageIO.read(getClass().getClassLoader().getResource(
                "Elevator/util/panelBG.png"));
        } catch (IOException ex) {}
          
          
        Graphics2D g2 = (Graphics2D)g;
        TexturePaint bgTex =
            new TexturePaint(bg, new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        g2.setPaint(bgTex);
        g2.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
      }
    
}

