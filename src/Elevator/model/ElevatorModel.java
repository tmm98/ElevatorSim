package Elevator.model;

import static Elevator.controller.ElevatorController.cfi;
import static Elevator.controller.ElevatorController.mfi;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Manages the functionality of the elevator.
 * Each instance of this class is responsible for controlling one elevator.
 *
 * @author Travis McCoy
 * @author Paul Mitchell
 */
class ElevatorModel
{
    private static final double ACC_CONST = 0.01;
    private static final double MAX_SPEED = 0.5;
    private static final double BOX_WIDTH = 40;
    private static final double BOX_HEIGHT = 30;
    private static final int NUM_FLOORS = 10;
    private static final double TOP_OF_SHAFT = 40;
    private static final double BOTTOM_OF_SHAFT = 670;
    private static final int UPDATE_RATE = 60;
    private static final double elevX = 75;
    
    private double elevY = BOTTOM_OF_SHAFT;
    private double elevSpeedY = 0.0;
    private double floorDest = BOTTOM_OF_SHAFT;
    private double tempSpeed = 0.0;
    private double stopDist = 0.0;
    
    private boolean floorStop = false;
    private boolean outOfService = false;
    private boolean directionUp = true;
    private boolean stopped = true;
    private int currentFloor = 1;
    private boolean[] floorList = new boolean[10];
    private boolean[] summonUpList = cfi.getSummonUp();
    private boolean[] summonDownList = cfi.getSummonDown();
    

    // Creates a thread that controls the elevators behavior.
    ElevatorModel()
    {
        while (tempSpeed < MAX_SPEED)
        {
            tempSpeed = tempSpeed + ACC_CONST;
            stopDist = stopDist + tempSpeed;
        }

        Thread elevatorThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);  // Wait for program to load
                } catch (InterruptedException ex){}

                while (true)
                {
                    if (!cfi.getEmergencyBrakeApplied() &&
                        !cfi.isSpeedBrakeSet())
                    {
                        if (directionUp)
                        {
                            if (elevY - stopDist > floorDest)
                            {
                                if (elevSpeedY < MAX_SPEED)
                                {
                                    accElev();
                                    updateElev();
                                } else
                                {
                                    updateElev();
                                }
                            }
                            else
                            {
                                if (elevY <= floorDest)
                                {
                                    stopElev();
                                    elevArrive();
                                }
                                else
                                {
                                    if (elevSpeedY > .1)
                                    {
                                        decElev();
                                        updateElev();
                                    }
                                    else
                                    {
                                        updateElev();
                                    }
                                }
                            }
                        }
                        else
                        {
                            if (elevY + stopDist < floorDest)
                            {
                                if (elevSpeedY < MAX_SPEED)
                                {
                                    accElev();
                                    updateElev();
                                }
                                else
                                {
                                    updateElev();
                                }
                            }
                            else
                            {
                                if (elevY >= floorDest)
                                {
                                    stopElev();
                                    elevArrive();
                                }
                                else
                                {
                                    if (elevSpeedY > .1)
                                    {
                                        decElev();
                                        updateElev();
                                    }
                                    else
                                    {
                                        updateElev();
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        elevSpeedY = 0.0;
                    }
                    if (cfi.getPowerOut() || cfi.getPowerDegraded() ||
                        mfi.isFireOpsMode() || mfi.isMxOpsMode())
                    {
                        if(!outOfService)
                        {
                            cfi.clearUpArray();
                            cfi.clearDownArray();
                            cfi.clearSummonUp();
                            cfi.clearSummonDown();
                            mfi.setSummonOff();
                            mfi.setFloorOff();
                            Arrays.fill(floorList, false);
                            if(isDirectionUp())
                            {
                                if(mfi.isMxOpsMode() &&
                                    (cfi.getEmergencyBrakeApplied() ||
                                    cfi.isSpeedBrakeSet()))
                                {
                                    setDirectionUp(false);
                                    updateFloorList(1);
                                    cfi.setEmergencyBrakeApplied(false);
                                    cfi.setSpeedBrake(false);
                                }
                                else if(stopped)
                                {
                                    updateFloorList(getCurrentFloor());
                                }
                                else
                                {
                                    updateFloorList(getCurrentFloor()+1);
                                }
                            }
                            else
                            {
                                updateFloorList(1);
                            }
                            moveToFloor();
                            updateFloorList(1);
                            outOfService = true;
                        }
                        else
                        {
                            if(getCurrentFloor() == 1 && stopped)
                            {
                                cfi.setOpen(true);
                                cfi.openDoor(true);
                            }
                        }
                    }
                    try
                    {
                        Thread.sleep(1000 / UPDATE_RATE);
                    }
                    catch (InterruptedException ex){}
                }
            }
        };
        elevatorThread.start();
    }

    double getPos()
    {
        return elevY;
    }

    // Increases the speed of the elevator by a fixed amount
    void accElev()
    {
        elevSpeedY = elevSpeedY + ACC_CONST;
    }

    // Decreases the speed of the elevator by a set amount    
    void decElev()
    {
        elevSpeedY = elevSpeedY - ACC_CONST;
    }

    // Changes the position of the elevator depending on direction and speed    
    void updateElev()
    {
        if (this.directionUp)
        {
            elevY = elevY - elevSpeedY;
        }
        else
        {
            elevY = elevY + elevSpeedY;
        }
    }

    // Stops the elevator at the desired floor
    void stopElev()
    {
        elevSpeedY = 0.0;
        elevY = floorDest;
    }
 
    boolean isStopped()
    {
        return stopped;
    }

    void setDirectionUp(boolean value)
    {
        this.directionUp = value;
    }

    boolean isDirectionUp()
    {
        return this.directionUp;
    }

    void setFloorList(boolean[] value)
    {
        this.floorList = value;
    }

    void updateFloorList(int floor)
    {
        this.floorList[floor - 1] = true;
    }

    int getCurrentFloor()
    {
        return (int) Math.floor(10.0 - (((int) (elevY - TOP_OF_SHAFT)
            / (((BOTTOM_OF_SHAFT - TOP_OF_SHAFT) / (NUM_FLOORS - 1))))));
    }

    void setOutOfService(boolean value)
    {
        this.outOfService = value;
    }

    boolean isOutOfService()
    {
        return this.outOfService;
    }

    
    // Creates a thread that plays a ding noise
    void playDing()
    {
        Thread dingThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    AudioInputStream inputStream
                        = AudioSystem.getAudioInputStream(getClass().
                        getClassLoader().getResource(
                        "Elevator/util/ElevatorBell.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    clip.loop(1);
                    Thread.sleep(1500);
                    clip.stop();
                }
                catch (FileNotFoundException e){}
                catch (IOException error){}
                catch (UnsupportedAudioFileException ex){}
                catch (LineUnavailableException ex){}
                catch (InterruptedException ex){}
            }
        };
        dingThread.start();
    }
        
    // Makes the necessary changes to the arrays and lights when the floor is reached.
    // Plays a ding noise and cycles the doors.
    void elevArrive()
    {
        if(!stopped)
        {
            currentFloor = 10 - (int)(elevY
                / ((BOTTOM_OF_SHAFT - TOP_OF_SHAFT)/(NUM_FLOORS-1)));
            floorList[currentFloor-1] = false;
            cfi.setSummonUp(currentFloor, false);
            cfi.setSummonDown(currentFloor, false);
            mfi.setFloorLight(currentFloor, false);
            mfi.setUpLight(currentFloor, false);
            mfi.setDownLight(currentFloor, false);
            stopped = true;
            playDing();
            cfi.doorCycle();
        }
        else
        {
            if (!cfi.isOpen()) {moveToFloor();}
        }
    }

    // Finds the next floor the elevator needs to travel to
    void moveToFloor()
    {        
        // Get both the false arrays for later
        int i = 0;
        boolean floorEmpty = true;
        boolean summonEmpty = true;
        double tempFloor = 0;
        // Loop through the floor list and look for stops
        
        while(i < 10)
        {
            if((isDirectionUp()) && summonUpList[i])
            {
                if(getCurrentFloor() < (i+1))
                {
                    updateFloorList(i+1);
                }
            }
            if((!isDirectionUp()) && summonDownList[i])
            {
                if(getCurrentFloor() > (i+1))
                {
                    updateFloorList(i+1);
                }
            }
            i++;
        }
        
        i = 0;
        while(i<10)
        {
            if(floorList[i])
            {
                // If a floor is found, change floorDest to that location
                floorEmpty = false;
                tempFloor = BOTTOM_OF_SHAFT -
                    (i * ((BOTTOM_OF_SHAFT - TOP_OF_SHAFT)/(NUM_FLOORS-1)));
                if(directionUp) i = 10;
            }
            i++;
        }
        
        
        if(!floorEmpty)
        {
            if(isDirectionUp() && (tempFloor > elevY))
            {
                setDirectionUp(false);
                floorList = cfi.getDownArray();
            }
            else if(!isDirectionUp() && (tempFloor < elevY))
            {
                setDirectionUp(true);
                floorList = cfi.getUpArray();
            }
            floorDest = tempFloor;
            stopped = false;
        }
        
        
        
        if(floorEmpty)
        {
            // Get the summon array
            i = 0;
            summonEmpty = true;
            tempFloor = 0;
            // Loop through the floor list and look for stops
            while(i<10)
            {
                if(isDirectionUp() && summonDownList[i])
                {
                    // If a floor is found, change floorDest to that location
                    summonEmpty = false;
                    tempFloor = BOTTOM_OF_SHAFT -
                        (i * ((BOTTOM_OF_SHAFT - TOP_OF_SHAFT)/(NUM_FLOORS-1)));
                }
                if(!isDirectionUp() && summonUpList[i])
                {
                    // If a floor is found, change floorDest to that location
                    summonEmpty = false;
                    tempFloor = BOTTOM_OF_SHAFT -
                        (i * ((BOTTOM_OF_SHAFT - TOP_OF_SHAFT)/(NUM_FLOORS-1)));
                    i = 10;
                }
                i++;
            }
        
            if(summonEmpty)
            {
                i = 0;
                while(i<10)
                {
                    if(isDirectionUp() && summonUpList[i])
                    {
                        // If a floor is found, change floorDest to that location
                        summonEmpty = false;
                        tempFloor = BOTTOM_OF_SHAFT -
                        (i * ((BOTTOM_OF_SHAFT - TOP_OF_SHAFT)/(NUM_FLOORS-1)));
                    }
                    if(!isDirectionUp() && summonDownList[i])
                    {
                        // If a floor is found, change floorDest to that location
                        summonEmpty = false;
                        tempFloor = BOTTOM_OF_SHAFT -
                            (i * ((BOTTOM_OF_SHAFT - TOP_OF_SHAFT)/(NUM_FLOORS-1)));
                        i = 10;
                    }
                    i++;
                }
            }    
        }
        
        if(floorEmpty && !summonEmpty)
        {
            if(isDirectionUp() && tempFloor > elevY) setDirectionUp(false);
            else if(!isDirectionUp() && tempFloor < elevY) setDirectionUp(true);
                
            floorDest = tempFloor;
            stopped = false;
        }
        if (floorEmpty && summonEmpty)
        {
            if(cfi.getUpArray() == floorList)
            {
                floorList = cfi.getDownArray();
            }
            else
            {
                floorList = cfi.getUpArray();
            }
        }
    }
    
    /**********************************************************************
     * These functions are used for testing purposes only
     */
     public boolean[] getFloorList()
     {
         return this.floorList;
     }
      
     public boolean positionInFloorList(int floor)
     {
         boolean[] floors = getFloorList();
         floorStop = floors[floor];
         return this.floorStop;
     }
     
     public void setElevY(int floor)
     {
        elevY = BOTTOM_OF_SHAFT -
            ((floor-1) * ((BOTTOM_OF_SHAFT - TOP_OF_SHAFT)/(NUM_FLOORS-1)));
     }
     
     public void clearFloorList()
     {
         Arrays.fill(floorList, false);
     }
}
