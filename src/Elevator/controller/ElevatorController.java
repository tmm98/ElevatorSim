package Elevator.controller;

import Elevator.model.ModelFacade;
import Elevator.view.ViewFacade;
import java.util.Arrays;

public class ElevatorController
{

    private boolean[] upArray = new boolean[10];
    private boolean[] summonUp = new boolean[10];
    private boolean[] downArray = new boolean[10];
    private boolean[] summonDown = new boolean[10];
    private boolean emergencyOps;

    public static ControllerFacade cfi = new ControllerFacade();
    public static ModelFacade mfi = new ModelFacade();
    public static ViewFacade vfi = new ViewFacade();
    static EmergencyBrakeController ecb = new EmergencyBrakeController();
    static SpeedBrakeController sbc = new SpeedBrakeController();
    static PowerConditioner pci = new PowerConditioner();
    static DoorController dci = new DoorController();

    /**
     * The main function that creates the GUI
     *
     * @param args[] does nothing
     */
    public static void main(String args[])
    {
        vfi.createGUI();
    }
    
    boolean[] getSummonUp()
    {
        return this.summonUp;
    }

    boolean[] getSummonDown()
    {
        return this.summonDown;
    }

    void setSummonUp(int floor, boolean status)
    {
        summonUp[floor - 1] = status;
    }

    void setSummonDown(int floor, boolean status)
    {
        summonDown[floor - 1] = status;
    }

    void clearSummonUp()
    {
        Arrays.fill(summonUp, false);
    }

    void clearSummonDown()
    {
        Arrays.fill(summonDown, false);
    }

    void setUpArray(int value)
    {
        upArray[value - 1] = true;
    }

    boolean[] getUpArray()
    {
        return this.upArray;
    }

    void setDownArray(int value)
    {
        downArray[value - 1] = true;
    }

    boolean[] getDownArray()
    {
        return this.downArray;
    }

    void clearUpArray()
    {
        Arrays.fill(upArray, false);
    }

    void clearDownArray()
    {
        Arrays.fill(downArray, false);
    }

    void setEmergencyOps(boolean value)
    {
        this.emergencyOps = value;
    }

    boolean isEmergencyOps()
    {
        return this.emergencyOps;
    }

    void distributeRideToArray(int floor, boolean orient)
    //this one is for summon buttons
    {
        if (orient)
        {
            if (mfi.isDirectionUp() && mfi.getCurrentFloor() < floor)
            {
                mfi.updateFloorList(floor);
            }
            else
            {
                summonUp[floor - 1] = true;
            }
        }
        else
        {
            if (!mfi.isDirectionUp() && mfi.getCurrentFloor() > floor)
            {
                mfi.updateFloorList(floor);
            }
            else
            {
                summonDown[floor - 1] = true;
            }
        }
        if (!dci.isOpen())
        {
            mfi.moveToFloor();
        }
    }

    void distributeStopToArray(int floor)
    //this one is for floor buttons
    {
        if (mfi.isDirectionUp() && mfi.getCurrentFloor() < floor)
        // if direction is up and current floor is lower than request
        {
            mfi.updateFloorList(floor);
        }
        else if  (mfi.isDirectionUp() && mfi.getCurrentFloor() >= floor)
        {
            setDownArray(floor);
        }
        else if (!mfi.isDirectionUp() && mfi.getCurrentFloor() > floor)
        {
            mfi.updateFloorList(floor);
        }
        else if (!mfi.isDirectionUp() && mfi.getCurrentFloor() <= floor)
        {
            setUpArray(floor);
        }
        // If the elevator is on the requested floor, turn the light off
        else if ((mfi.getCurrentFloor() == floor) && (mfi.isStopped()))
        {
            Thread buttonThread = new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(200);  // Refresh rate in miliseconds
                    }
                    catch (InterruptedException ex){}
                    mfi.setFloorLight(mfi.getCurrentFloor(), false);
                }
            };
            buttonThread.start();
        }
        
        if (!dci.isOpen())
        {
            mfi.moveToFloor();
        }
    }
}
