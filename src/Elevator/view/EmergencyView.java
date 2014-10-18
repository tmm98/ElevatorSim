package Elevator.view;

import static Elevator.controller.ElevatorController.mfi;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

// Creates a JPanel for the emergency buttons
class EmergencyView extends JPanel implements ActionListener
{
     private static final int FRAME_WIDTH = 300;
     private static final int FRAME_HEIGHT = 325;
     
     JTextField display;
     
     BufferedImage bg = null;
     
    // Creates the contents inside the emergency JPanel
    EmergencyView()
    {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        GridLayout experimentLayout = new GridLayout(0, 1, 10, 10);
        setLayout(experimentLayout);
        
        display = new JTextField("Normal operations");
        display.setHorizontalAlignment(JTextField.CENTER);
        display.setEditable(false);
        display.addActionListener(this);
        
        JButton speedBrakeTest = new JButton("Speed Brake Test");
        speedBrakeTest.setActionCommand("SBT");
        
        JButton emergencyBrakeTest = new JButton("Emergency Brake Test");
        emergencyBrakeTest.setActionCommand("EBT");
        
        JButton fireOps = new JButton("Fire Test");
        fireOps.setActionCommand("FOT");
        
        JButton mxOps = new JButton("Maintanence Mode Test");
        mxOps.setActionCommand("MOT");
        
        JButton powerDeg = new JButton("Power Degrade Test");
        powerDeg.setActionCommand("PDT");
            
        JButton powerOut = new JButton("Power Out Test");
        powerOut.setActionCommand("POT");
            
        JButton reset = new JButton("Reset");
        reset.setActionCommand("reset");
        
        display.addActionListener(this);
        speedBrakeTest.addActionListener(this);
        emergencyBrakeTest.addActionListener(this);
        fireOps.addActionListener(this);
        mxOps.addActionListener(this);
        powerDeg.addActionListener(this);
        powerOut.addActionListener(this);
        reset.addActionListener(this);
        
        add(display);
        add(speedBrakeTest);
        add(emergencyBrakeTest);
        add(fireOps);
        add(mxOps);
        add(powerDeg);
        add(powerOut);
        add(reset);
    }
       
    @Override
    public void actionPerformed(ActionEvent e) {
        if (null != e.getActionCommand()) switch (e.getActionCommand())
       {
           case "SBT":
               mfi.setSpeedBrakeTest(true, display);
               break;
           case "EBT":
               mfi.setEmergencyBrakeTest(true, display);
               break;
           case "FOT":
               mfi.setFireOpsMode(true, display);
               break;
           case "MOT":
               mfi.setMxOpsMode(true, display);
               break;
           case "PDT":
               mfi.setPowerDegradedMode(true, display);
               break;
           case "POT":
               mfi.setPowerOutMode(true, display);
               break;
           case "reset":
               mfi.setReset(display);
               break;
        }   
    }    
}
