import UI_Elements.Button;
import UI_Elements.ControlPanel;
import UI_Elements.Spinner;
import src.Car;
import src.CarWorkshop;
import src.Volvo240;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 **/

public class CarView extends JFrame {
    private static final int X = 800;
    private static final int Y = 800;

    // The controller member
    CarController carC;
    DrawPanel drawPanel;

    UI_Elements.Button gasButton;
    UI_Elements.Button brakeButton;
    UI_Elements.Button turboOnButton;
    UI_Elements.Button turboOffButton;
    UI_Elements.Button liftBedButton;
    UI_Elements.Button lowerBedButton;

    UI_Elements.Button startButton;
    UI_Elements.Button stopButton;

    Spinner gasSpinner;
    Spinner bedSpinner;

    // Constructor
    public CarView(String framename, CarController cc, ArrayList<DrawableObject<Car>> carEntities, ArrayList<DrawableObject<CarWorkshop<Volvo240>>> workshopEntities) {
        this.carC = cc;
        this.drawPanel = new DrawPanel(X, Y - 240, carEntities, workshopEntities);

        createUI();
        initComponents(framename);
    }

    private void createUI() {
        this.gasSpinner = new Spinner(new SpinnerNumberModel(0, 0, 100, 1));
        this.gasButton = new UI_Elements.Button("Gas", () -> carC.gas(gasSpinner.getAmount()));
        this.brakeButton = new UI_Elements.Button("Brake", () -> carC.brake(gasSpinner.getAmount()));

        this.turboOnButton = new UI_Elements.Button("Saab Turbo on", () -> carC.setTurboOn());
        this.turboOffButton = new UI_Elements.Button("Saab Turbo off", () -> carC.setTurboOff());

        this.bedSpinner = new Spinner(new SpinnerNumberModel(0, 0, 70, 1));
        this.liftBedButton = new UI_Elements.Button("Scania Lift Bed", () -> carC.liftBed(bedSpinner.getAmount()));
        this.lowerBedButton = new UI_Elements.Button("Lower Lift Bed", () -> carC.lowerBed(bedSpinner.getAmount()));

        this.startButton = new UI_Elements.Button("Start all cars", () -> carC.startCar());
        this.startButton.setBackground(Color.GREEN);

        this.stopButton = new Button("Stop all cars", () -> carC.stopCar());
        this.stopButton.setBackground(Color.RED);
    }

    // Sets everything in place and fits everything
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        // Spinners
        setupSpinner("Amount of gas", gasSpinner);
        setupSpinner("Bed raise angle", bedSpinner);

        // Buttons
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

    private void setupSpinner(String labelName, Spinner spinner) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(labelName);

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.PAGE_START);
        panel.add(spinner, BorderLayout.PAGE_END);
        this.add(panel);
    }
}