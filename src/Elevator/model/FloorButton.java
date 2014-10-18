package Elevator.model;

public class FloorButton {
    
    boolean light1, light2, light3, light4, light5, light6, light7;
    boolean light8, light9, light10;
   
    void setFloorLight(int floor, boolean value)
    {
        switch(floor)
        {
            case 1:
                light1 = value;
                break;
                
            case 2:
                light2 = value;
                break;
                
            case 3:
                light3 = value;
                break;
                
            case 4:
                light4 = value;
                break;
                
            case 5:
                light5 = value;
                break;
                
            case 6:
                light6 = value;
                break;
                
            case 7:
                light7 = value;
                break;
                
            case 8:
                light8 = value;
                break;
                
            case 9:
                light9 = value;
                break;
                
            case 10:
                light10 = value;
                break;
        }
    }
    
    boolean getFloorLight(int floor)
    {
        switch(floor)
        {
            case 1:
                return(light1);
                
            case 2:
                return(light2);
                
            case 3:
                return(light3);
                
            case 4:
                return(light4);
                
            case 5:
                return(light5);
                
            case 6:
                return(light6);
                
            case 7:
                return(light7);
                
            case 8:
                return(light8);
                
            case 9:
                return(light9);
                
            case 10:
                return(light10);
        }
        return false;
    }
    
    // Turns off all panel lights
    void setAllOff()
    {
        light1 = false;
        light2 = false;
        light3 = false;
        light4 = false;
        light5 = false;
        light6 = false;
        light7 = false;
        light8 = false;
        light9 = false;
        light10 = false;
    }
}

