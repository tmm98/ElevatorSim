package Elevator.controller;

class EmergencyBrakeController extends BrakeController
{
    private boolean emergencyBrakeApplied;

    boolean getEmergencyBrakeApplied()
    {
        return this.emergencyBrakeApplied;
    }

    void setEmergencyBrakeApplied(boolean value)
    {
        this.emergencyBrakeApplied = value;
    }
}
