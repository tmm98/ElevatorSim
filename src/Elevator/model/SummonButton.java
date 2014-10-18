package Elevator.model;

import static Elevator.controller.ElevatorController.cfi;

class SummonButton
{

    boolean up1Light, up2Light, down2Light, up3Light, down3Light, up4Light;
    boolean down4Light, up5Light, down5Light, up6Light, down6Light, up7Light;
    boolean down7Light, up8Light, down8Light, up9Light, down9Light, down10Light;
    
    // Turn all summon lights off
    public void setAllOff()
    {
        up1Light = false;
        up2Light = false;
        up3Light = false;
        up4Light = false;
        up5Light = false;
        up6Light = false;
        up7Light = false;
        up8Light = false;
        up9Light = false;
        down2Light = false;
        down3Light = false;
        down4Light = false;
        down5Light = false;
        down6Light = false;
        down7Light = false;
        down8Light = false;
        down9Light = false;
        down10Light = false;
    }

    void setUpLight(int floor, boolean value)
    {
        switch (floor)
        {
            case 1:
                up1Light = value;
                break;
            case 2:
                up2Light = value;
                break;
            case 3:
                up3Light = value;
                break;
            case 4:
                up4Light = value;
                break;
            case 5:
                up5Light = value;
                break;
            case 6:
                up6Light = value;
                break;
            case 7:
                up7Light = value;
                break;
            case 8:
                up8Light = value;
                break;
            case 9:
                up9Light = value;
                break;
        }
    }

    void setDownLight(int floor, boolean value)
    {
        switch (floor)
        {
            case 2:
                down2Light = value;
                break;
            case 3:
                down3Light = value;
                break;
            case 4:
                down4Light = value;
                break;
            case 5:
                down5Light = value;
                break;
            case 6:
                down6Light = value;
                break;
            case 7:
                down7Light = value;
                break;
            case 8:
                down8Light = value;
                break;
            case 9:
                down9Light = value;
                break;
            case 10:
                down10Light = value;
                break;
        }
    }

    boolean getUpLight(int floor)
    {
        switch (floor)
        {
            case 1:
                return this.up1Light;
            case 2:
                return this.up2Light;
            case 3:
                return this.up3Light;
            case 4:
                return this.up4Light;
            case 5:
                return this.up5Light;
            case 6:
                return this.up6Light;
            case 7:
                return this.up7Light;
            case 8:
                return this.up8Light;
            case 9:
                return this.up9Light;
            default:
                return false;
        }
    }

    boolean getDownLight(int floor)
    {
        switch (floor)
        {
            case 2:
                return this.down2Light;

            case 3:
                return this.down3Light;

            case 4:
                return this.down4Light;

            case 5:
                return this.down5Light;

            case 6:
                return this.down6Light;

            case 7:
                return this.down7Light;

            case 8:
                return this.down8Light;

            case 9:
                return this.down9Light;

            case 10:
                return this.down10Light;
            default:
                return false;
        }
    }
}
