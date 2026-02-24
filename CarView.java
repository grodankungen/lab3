import src.Car;
import src.CarWorkshop;
import src.Volvo240;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame {
    private static final int X = 800;
    private static final int Y = 800;
    private int gasAmount = 0;
    private int bedAngle = 0;

    // The controller member
    CarController carC;
    DrawPanel drawPanel;

    JPanel gasPanel = new JPanel();
    JLabel gasLabel = new JLabel("Amount of gas");
    JSpinner gasSpinner = new JSpinner();

    JPanel bedAnglePanel = new JPanel();
    JLabel bedAngleLabel = new JLabel("Bed raise angle");
    JSpinner bedAngleSpinner = new JSpinner();

    Button gasButton;
    Button brakeButton;
    Button turboOnButton;
    Button turboOffButton;
    Button liftBedButton;
    Button lowerBedButton;

    Button startButton;
    Button stopButton;

    // Constructor
    public CarView(String framename, CarController cc, ArrayList<DrawableObject<Car>> carEntities, ArrayList<DrawableObject<CarWorkshop<Volvo240>>> workshopEntities) {
        this.carC = cc;
        this.drawPanel = new DrawPanel(X, Y - 240, carEntities, workshopEntities);

        gasButton = new Button("Gas", () -> carC.gas(gasAmount));
        brakeButton = new Button("Brake", () -> carC.brake(gasAmount));
        turboOnButton = new Button("Saab Turbo on", () -> carC.setTurboOn());
        turboOffButton = new Button("Saab Turbo off", () -> carC.setTurboOff());
        liftBedButton = new Button("Scania Lift Bed", () -> carC.liftBed(bedAngle));
        lowerBedButton = new Button("Lower Lift Bed", () -> carC.lowerBed(bedAngle));

        startButton = new Button("Start all cars", () -> carC.startCar());
        startButton.setBackground(Color.GREEN);

        stopButton = new Button("Stop all cars", () -> carC.stopCar());
        stopButton.setBackground(Color.RED);

        initComponents(framename);

    }

    // Sets everything in place and fits everything
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);


        SpinnerModel gasSpinnerModel = new SpinnerNumberModel(0, //initial value
                0, //min
                100, //max
                1);//step
        gasSpinner = new JSpinner(gasSpinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        SpinnerModel bedSpinnerModel = new SpinnerNumberModel(0, 0, 70, 1);
        bedAngleSpinner = new JSpinner(bedSpinnerModel);
        bedAngleSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                bedAngle = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        this.add(gasPanel);

        bedAnglePanel.setLayout(new BorderLayout());
        bedAnglePanel.add(bedAngleLabel, BorderLayout.PAGE_START);
        bedAnglePanel.add(bedAngleSpinner, BorderLayout.PAGE_END);
        this.add(bedAnglePanel);

        // Control Panel
        ControlPanel controlPanel = new ControlPanel(
                0, 3,
                new Dimension((X / 2) + 4, 200),
                Color.CYAN,
                gasButton,
                turboOnButton,
                liftBedButton,
                brakeButton,
                turboOffButton,
                lowerBedButton
        );
        this.add(controlPanel);

        ControlPanel startStopPanel = new ControlPanel(
                2, 1,
                new Dimension((X / 5) - 15, 200),
                Color.BLACK,
                startButton,
                stopButton
        );
        this.add(startStopPanel);

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}