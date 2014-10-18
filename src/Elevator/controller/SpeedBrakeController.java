package Elevator.controller;

class SpeedBrakeController extends BrakeController
{
    private boolean speedBrake;

    boolean isSpeedBrakeSet()
    {
        return this.speedBrake;
    }

    void setSpeedBrake(boolean speedBrake)
    {
        this.speedBrake = speedBrake;
    }
}
