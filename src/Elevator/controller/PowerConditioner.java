package Elevator.controller;

public class PowerConditioner
{
    private boolean powerOut;
    private boolean powerDegraded;
    private float powerSensor;

    boolean getPowerOut()
    {
        return this.powerOut;
    }

    void setPowerOut(boolean powerOutMode)
    {
        powerOut = powerOutMode;
    }

    boolean getPowerDegraded()
    {
        return this.powerDegraded;
    }

    void setPowerDegraded(boolean powerDegradedMode)
    {
        powerDegraded = powerDegradedMode;
    }

    void smoothPower(float volts)
    {
        float powerValue = getPowerSensor();
        if (powerValue > 250 || powerValue < 210)
        {
            throw new UnsupportedOperationException("Hardware interface missing");
        }
    }

    float getPowerSensor()
    {
        return powerSensor;
    }

    void setPowerSensor(float powerSensor)
    {
        this.powerSensor = powerSensor;
    }
}
