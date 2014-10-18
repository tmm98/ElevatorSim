package Elevator.view;

/**
 * Facade to initialize GUI
 * @author Travis McCoy
 */
public class ViewFacade
{
    /**
     * Creates the GUI interface for the elevator simulation
     */
    public void createGUI()
    {
        MenuView mvi = new MenuView();
        
        mvi.createGUI();
    }
}
