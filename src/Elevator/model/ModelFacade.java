package Elevator.model;

import javax.swing.JTextField;

/**
 *
 * @author travis
 */
public class ModelFacade
{
    ElevatorModel emi = new ElevatorModel();
    SummonButton sbi = new SummonButton();
    FloorButton fbi = new FloorButton();
    EmergencyButton ebi = new EmergencyButton();
    CallButton cbi = new CallButton();
    
    public void moveToFloor()
    {
        emi.moveToFloor();
    }
    
    public int getCurrentFloor()
    {
        return emi.getCurrentFloor();
    }
    
    public void setFloorLight(int floor, boolean choice)
    {
       fbi.setFloorLight(floor, choice);
    }
    
    public boolean isStopped()
    {
        return emi.isStopped();
    }
    
    public boolean isDirectionUp()
    {
        return emi.isDirectionUp();
    }
    
    public void updateFloorList(int floor)
    {
        emi.updateFloorList(floor);
    }
    
    public void setSummonOff()
    {
        sbi.setAllOff();
    }
    
    public void setFloorOff()
    {
        fbi.setAllOff();
    }
    
    public void setUpLight(int floor, boolean choice)
    {
        sbi.setUpLight(floor, choice);
    }
    
    public boolean getUpLight(int floor)
    {
        return sbi.getUpLight(floor);
    }
 
    public void setDownLight(int floor, boolean choice)
    {
        sbi.setDownLight(floor, choice);
    }
    
    public boolean getDownLight(int floor)
    {
        return sbi.getDownLight(floor);
    }
    
    public boolean getFloorLight(int floor)
    {
        return fbi.getFloorLight(floor);
    }
    
    public boolean isMxOpsMode()
    {
        return ebi.isMxOpsMode();
    }
    
    public void setMxOpsMode(boolean choice, JTextField display)
    {
        ebi.setMxOpsMode(choice, display);
    }
    
    public boolean isFireOpsMode()
    {
        return ebi.isFireOpsMode();
    }
    
    public void setFireOpsMode(boolean choice, JTextField display)
    {
        ebi.setFireOpsMode(choice, display);
    }
    
    public double getPos()
    {
        return emi.getPos();
    }
    
    public void setSpeedBrakeTest(boolean choice, JTextField display)
    {
        ebi.setSpeedBrakeTest(choice, null);
    }
    
    public void setEmergencyBrakeTest(boolean choice, JTextField display)
    {
        ebi.setEmergencyBrakeTest(choice, display);
    }
    
    public void setPowerDegradedMode(boolean choice, JTextField display)
    {
        ebi.setPowerDegradedMode(choice, display);
    }
 
    public void setPowerOutMode(boolean choice, JTextField display)
    {
        ebi.setPowerOutMode(choice, display);
    }
    
    public void setReset(JTextField display)
    {
        ebi.setReset(display);
    }
    
    public void makeCall()
    {
        cbi.makeCall();
    }
}
