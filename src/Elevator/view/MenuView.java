package Elevator.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Creates a JPanel to create other JPanels
class MenuView extends JPanel implements ActionListener
{

    JFrame mainMenu, elevatorSim, floorInd;
    JFrame elevatorPanel, summonButt, emergView;
    int floorIndWidth, floorIndHeight, elevatorPanelWidth, summonButtWidth;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 325;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Point middle = new Point(screenSize.width / 2, screenSize.height / 2);

    // Constructs the content to be displayed in the JPanel
    MenuView()
    {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        GridLayout experimentLayout = new GridLayout(0, 1, 10, 10);
        setLayout(experimentLayout);

        JButton makeSim = new JButton("Elevator Simulation");
        makeSim.setActionCommand("makeSim");

        JButton makeFloor = new JButton("Floor Indicator");
        makeFloor.setActionCommand("makeFloor");

        JButton makePanel = new JButton("Elevator Panel");
        makePanel.setActionCommand("makePanel");

        JButton makeSum = new JButton("Summon Buttons");
        makeSum.setActionCommand("makeSum");

        JButton makeEmerg = new JButton("Emergency Panel");
        makeEmerg.setActionCommand("makeEmerg");

        makeSim.addActionListener(this);
        makePanel.addActionListener(this);
        makeFloor.addActionListener(this);
        makeSum.addActionListener(this);
        makeEmerg.addActionListener(this);

        add(makeSim);
        add(makeFloor);
        add(makePanel);
        add(makeSum);
        add(makeEmerg);
    }

    // Creates an instance of each window other than emergency, and loops the
    // elevator music
    void createGUI()
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                // Set up main window (using Swing's Jframe)
                createMenuView();
                createFloorNumberView();
                createPanelView();
                createSummonView();
                createElevatorView();

                try
                {
                    AudioInputStream inputStream
                        = AudioSystem.getAudioInputStream(getClass().
                        getClassLoader().getResource(
                        "Elevator/util/ElevatorMusic.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                catch (FileNotFoundException e){}
                catch (IOException error){}
                catch (UnsupportedAudioFileException ex){}
                catch (LineUnavailableException ex){}
            }
        });
    }

    // Creates the Main menu JPanel
    void createMenuView()
    {
        mainMenu = new JFrame("Main Menu");
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setContentPane(new MenuView());
        mainMenu.pack();
        mainMenu.setVisible(true);
    }

    // Creates the simulation JPanel
    void createElevatorView()
    {
        elevatorSim = new JFrame("Elevator Simulation");
        elevatorSim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        elevatorSim.setContentPane(new ElevatorView());
        elevatorSim.pack();
        Point point = new Point(middle.x + (elevatorPanelWidth / 2), 0);
        elevatorSim.setLocation(point);
        elevatorSim.setVisible(true);
    }

    // Creates the floor number JPanel
    void createFloorNumberView()
    {
        floorInd = new JFrame("Floor Indicator");
        floorInd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        floorInd.setContentPane(new FloorNumberView());
        floorInd.pack();
        floorIndWidth = floorInd.getWidth();
        Point point = new Point(middle.x - (floorIndWidth / 2), 0);
        floorInd.setLocation(point);
        floorInd.setVisible(true);
    }

    // Creates the floor buttons JPanel
    void createPanelView()
    {
        elevatorPanel = new JFrame("Elevator Panel");
        elevatorPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        elevatorPanel.setContentPane(new PanelView());
        elevatorPanel.pack();
        elevatorPanelWidth = elevatorPanel.getWidth();
        floorIndHeight = floorInd.getHeight();
        Point point = new Point(middle.x - (elevatorPanelWidth / 2),
            floorIndHeight);
        elevatorPanel.setLocation(point);
        elevatorPanel.setVisible(true);
    }

    // Creates the summon buttons JPanel
    void createSummonView()
    {
        summonButt = new JFrame("Summon Buttons");
        summonButt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        summonButt.setContentPane(new SummonView());
        summonButt.pack();
        summonButtWidth = summonButt.getWidth();
        Point point = new Point(middle.x - (elevatorPanelWidth / 2)
            - (summonButtWidth), 0);
        summonButt.setLocation(point);
        summonButt.setVisible(true);
    }

    // Creates the emergency JPanel
    void createEmergencyView()
    {
        emergView = new JFrame("Emergency View");
        emergView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        emergView.setContentPane(new EmergencyView());
        emergView.pack();
        Point point = new Point(0, middle.y);
        emergView.setLocation(point);
        emergView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (null != e.getActionCommand())
        {
            switch (e.getActionCommand())
            {
                case "makeSim":
                    try
                    {
                        createElevatorView();
                    }
                    catch (NullPointerException npe){};
                    break;
                case "makePanel":
                    try
                    {
                        createPanelView();
                    }
                    catch (NullPointerException npe){};
                    break;
                case "makeFloor":
                    try
                    {
                        createFloorNumberView();
                    }
                    catch (NullPointerException npe){};
                    break;
                case "makeSum":
                    try
                    {
                        createSummonView();
                    }
                    catch (NullPointerException npe){};
                    break;
                case "makeEmerg":
                    try
                    {
                        createEmergencyView();
                    }
                    catch (NullPointerException npe){};
                    break;
            }
        }
    }
}