package Elevator.controller;

abstract class BrakeController
{
    private boolean brakeOn;

    void setBrakeOn(boolean value)
    {
        this.brakeOn = value;
    }

    boolean isBrakeOn()
    {
        return this.brakeOn;
    }
}
