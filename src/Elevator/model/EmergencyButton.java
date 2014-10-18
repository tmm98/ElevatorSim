package Elevator.model;

import static Elevator.controller.ElevatorController.cfi;
import static Elevator.controller.ElevatorController.mfi;
import javax.swing.JTextField;

class EmergencyButton
{

    private boolean reset;
    private boolean mxOpsMode;
    private boolean fireOpsMode;
    private boolean emergencyBrakeTest;
    private boolean speedBrakeTest;
    private boolean powerDegradedMode;
    private boolean powerOutMode;

    boolean isReset()
    {
        return reset;
    }
    
    void setReset(JTextField display)
    {
        fireOpsMode = false;
        emergencyBrakeTest = false;
        speedBrakeTest = false;
        mxOpsMode = false;
        powerDegradedMode = false;
        powerOutMode = false;
        cfi.setPowerDegraded(false);
        cfi.setPowerOut(false);
        cfi.setEmergencyBrakeApplied(false);
        cfi.setSpeedBrake(false);
        mfi.emi.setOutOfService(false);
        cfi.openDoor(false);
        display.setText("Normal operations");
    }

    boolean isMxOpsMode()
    {
        return mxOpsMode;
    }

    void setMxOpsMode(boolean value, JTextField display)
    {
        this.mxOpsMode = value;
        display.setText("Maintenance mode enabled");
    }

    boolean isFireOpsMode()
    {
        return fireOpsMode;
    }

    void setFireOpsMode(boolean value, JTextField display)
    {
        if(!emergencyBrakeTest && !speedBrakeTest)
        {
            this.fireOpsMode = value;
            display.setText("Fire threat detected");
        }
    }

    boolean isEmergencyBrakeTest()
    {
        return emergencyBrakeTest;
    }

    void setEmergencyBrakeTest(boolean value, JTextField display)
    {
        if(!speedBrakeTest)
        {
            display.setText("Emergency brakes engaged");
            cfi.setEmergencyBrakeApplied(value);
            this.emergencyBrakeTest = value;
        }
        
    }

    boolean isSpeedBrakeTest()
    {
        return speedBrakeTest;
    }

    void setSpeedBrakeTest(boolean value, JTextField display)
    {
        if(!emergencyBrakeTest)
        {
            display.setText("Speed brakes engaged");
            cfi.setSpeedBrake(value);
            this.speedBrakeTest = value;
        }
    }

    boolean isPowerDegradedMode()
    {
        return powerDegradedMode;
    }

    void setPowerDegradedMode(boolean value, JTextField display)
    {
        if(!emergencyBrakeTest && !speedBrakeTest)
        {
            display.setText("Power degrade detected");
            cfi.setPowerDegraded(value);
        }
    }

    boolean isPowerOutMode()
    {
        return powerOutMode;
    }

    void setPowerOut(boolean value)
    {
        powerOutMode = value;
    }

    void setPowerOutMode(boolean value, final JTextField display)
    {
        if(!emergencyBrakeTest && !speedBrakeTest)
        {
            Thread powerThread = new Thread()
            {
                @Override
                public void run()
                {
                    display.setText("Power outage detected");
                    setEmergencyBrakeTest(true, display);
                    try
                    {
                        Thread.sleep(2000);
                    } catch (InterruptedException e){}
                    setEmergencyBrakeTest(false, display);
                    display.setText("Generator has started");
                    cfi.setPowerOut(true);
                }
            };
            powerThread.start();
        }
    }
}
