package Elevator.controller;

/**
 * A facade to interact with the controller package
 * @author Travis McCoy
 */
public class ControllerFacade
{
    ElevatorController eci = new ElevatorController();
    EmergencyBrakeController ebc = new EmergencyBrakeController();
    SpeedBrakeController sbc = new SpeedBrakeController();
    PowerConditioner pci = new PowerConditioner();
    DoorController dci = new DoorController();
    
    /**
     * Adds or removes a floor to be visited in the summonUp list.
     *
     * @param floor the floor to be visited
     * @param choice the boolean value to set the floor to
    */
    public void setSummonUp(int floor, boolean choice)
    {
        eci.setSummonUp(floor, choice);
    }
    
    /**
     * Returns a boolean array indicating what summonUp floors need to be visited.
     *
     * @return summonUp an array containing which floors to be visited
    */
    public boolean[] getSummonUp()
    {
        return eci.getSummonUp();
    }
    
    /**
     * Adds or removes a floor to be visited in the summonDown list.
     *
     * @param floor the floor to be visited
     * @param choice the boolean value to set the floor to
    */
    public void setSummonDown(int floor, boolean choice)
    {
        eci.setSummonDown(floor, choice);
    }
    
    /**
     * Returns a boolean array indicating what summonDown floors need to be visited.
     *
     * @return summonDown an array containing which floors to be visited
    */
    public boolean[] getSummonDown()
    {
        return eci.getSummonDown();
    }
    
    /**
     * Returns a list of up floors that need to be stopped at.
     * @return a boolean array of all up stops needed to be visited.
     */    
    public boolean[] getUpArray()
    {
        return eci.getUpArray();
    }
    
    /**
     * Returns a list of down floors that need to be stopped at.
     * @return a boolean array of all down stops needed to be visited.
     */
    public boolean[] getDownArray()
    {
        return eci.getDownArray();
    }  
   
    /**
     * Adds a floor to be visited when a summon button is pressed.
     * @param floor the floor to be stopped at.
     * @param choice true = up; false = down;
     */
    public void distributeRideToArray(int floor, boolean choice)
    {
        eci.distributeRideToArray(floor, choice);
    }
    
    /**
     * Adds a floor to be visited when a panel button is pressed.
     * @param floor the floor to be stopped at.
     */
    public void distributeStopToArray(int floor)
    {
        eci.distributeStopToArray(floor);
    }

    /**
     * Checks to see if the emergency brake is engaged.
     * @return boolean whether the emergency brake is engaged.
     */
    public boolean getEmergencyBrakeApplied()
    {
        return ebc.getEmergencyBrakeApplied();
    }
    
    /**
     * Checks to see if the speed brake is engaged.
     * @return boolean whether the speed brake is engaged.
     */
    public boolean isSpeedBrakeSet()
    {
        return sbc.isSpeedBrakeSet();
    }
    
    /**
     * Clears the up list to be visited
     */
    public void clearUpArray()
    {
        eci.clearUpArray();
    }    
     
    /**
     * Clears the down list to be visited.
     */
    public void clearDownArray()
    {
        eci.clearDownArray();
    }   
        
    /**
     * Clears the summonUp list to be visited.
     */
    public void clearSummonUp()
    {
        eci.clearSummonUp();
    }   
     
    /**
     * Clears the summonDown list to be visited.
     */
    public void clearSummonDown()
    {
        eci.clearSummonDown();
    }
    
    /**
     * Returns a boolean indicating if the door is currently open.
     * @return a boolean true = open; false = closed;
     */
    public boolean isOpen()
    {
        return dci.isOpen();
    }
    
    /**
     * Cycle the doors open and then close.
     */
    public void doorCycle()
    {
        dci.doorCycle();
    }
    
    /**
     * Sets the doors open or closed.
     * @param choice true = open; false = close;
     */
    public void setOpen(boolean choice)
    {
        dci.setOpen(choice);
    }
    
    /**
     * Animates the door open or close.
     * @param choice true = open; false = close;
     */
    public void openDoor(boolean choice)
    {
        dci.openDoor(choice);
    }
    
    /**
     * Getter for if the power is out.
     *
     * @return a boolean indicating whether the power is out
     */
    public boolean getPowerOut()
    {
        return pci.getPowerOut();
    }
    
    /**
     * Sets the power to on or off for the simulation.
     * @param choice a boolean to switch the power to
     */
    public void setPowerOut(boolean choice)
    {
        pci.setPowerOut(choice);
    }
    
    /**
     * Getter for if the power is degraded.
     * @return a boolean indicating whether the power is degraded
     */
    public boolean getPowerDegraded()
    {
        return pci.getPowerDegraded();
    }
    
    /**
     * Setter for power degrade.
     * @param choice a boolean to set power degrade to
     */
    public void setPowerDegraded(boolean choice)
    {
        pci.setPowerDegraded(choice);
    }
    
    /**
     * Setter to apply the emergency brakes.
     * @param choice a boolean to choose if the brakes are applied or not
     */
    public void setEmergencyBrakeApplied(boolean choice)
    {
        ebc.setEmergencyBrakeApplied(choice);
    }
    
    /**
     * Setter to apply the speed brakes.
     * @param choice a boolean to choose if the speed brake is engaged or not
     */
    public void setSpeedBrake(boolean choice)
    {
        sbc.setSpeedBrake(choice);
    }
    
    /**
     * Getter for what position the door is in.
     * @return an int between 0 and 10 with 0 being closed and 10 being open.
     */
    public int getDoorState()
    {
        return dci.getDoorState();
    }
    
    /**
     * Called when the open door button is pressed.
     */
    public void openDoorB()
    {
        dci.openDoorB();
    }
    
    /**
     * Called when the close door button is pressed.
     */ 
    public void closeDoorB()
    {
        dci.closeDoorB();
    }
}
