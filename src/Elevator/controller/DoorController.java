package Elevator.controller;

class DoorController
{
    private boolean open;
    private boolean opening;
    public int i = 0;

    DoorController()
    {
        DoorControl();
    }

    // Sets the door to open or close    
    void setOpen(boolean choice)
    {
        this.open = choice;
    }

    // Returns if the door is open    
    boolean isOpen()
    {
        return open;
    }

    // Returns if the door is opening or closing
    boolean isOpening()
    {
        return this.opening;
    }

    // Sets door opening to true or false    
    void openDoor(boolean value)
    {
        this.opening = value;
    }

    // Creates a thread that controls the door position    
    void DoorControl()
    {
        Thread doorThread = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    // if statement for saying the door should open
                    if (isOpening())
                    {
                        if (i < 20)//increment door opening
                        {
                            i++;
                        }

                    }
                    else
                    {
                        if (i > 0)//increment door opening
                        {
                            i--;
                        }
                        if (i <= 0)
                        {
                            setOpen(false);
                        }
                    }
                    try
                    {
                        Thread.sleep(100);  // Refresh rate in miliseconds
                    }
                    catch (InterruptedException ex)
                    {
                    }
                }
            }
        };
        doorThread.start();
    }

    // Returns the position of the doors    
    int getDoorState()
    {
        return this.i;
    }

    // Creates a thread that cycles the doors once in a timed manner    
    void doorCycle()
    {
        Thread doorThread = new Thread()
        {
            @Override
            public void run()
            {
                setOpen(true);
                openDoor(true);
                try
                {
                    Thread.sleep(2000); // Refresh rate in miliseconds
                }
                catch (InterruptedException ex)
                {
                }
                openDoor(false);
            }
        };
        doorThread.start();
    }

    // Cycles the doors if the door is already open    
    void openDoorB()
    {
        if (isOpen())
        {
            doorCycle();
        }
    }

    // Closes the doors prematurely if they are already open    
    void closeDoorB()
    {
        if (isOpen())
        {
            opening = false;
        }
    }
}
