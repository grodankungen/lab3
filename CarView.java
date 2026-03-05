import UI_Elements.Button;
import UI_Elements.ControlPanel;
import UI_Elements.Spinner;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 */

public class CarView extends JFrame implements Observer<DrawableObject, CarEventType> {
    private final int X;
    private final int Y;
    private final ArrayList<DrawableObject> drawableObjects;

    // The controller member
    ICarController controller;
    DrawPanel drawPanel;

    Button gasButton;
    Button brakeButton;
    Button turboOnButton;
    Button turboOffButton;
    Button liftBedButton;
    Button lowerBedButton;

    Button startButton;
    Button stopButton;

    Button addCarButton;
    Button removeCarButton;

    Spinner gasSpinner;
    Spinner bedSpinner;

    // Constructor
    public CarView(String framename, int window_x, int window_y, int canvas_x, int canvas_y, ICarController controller, ArrayList<DrawableObject> drawableObjects) {
        X = window_x;
        Y = window_y;
        this.controller = controller;
        this.drawableObjects = drawableObjects;
        this.drawPanel = new DrawPanel(canvas_x, canvas_y, this.drawableObjects);

        createUI();
        initComponents(framename);
    }

    @Override
    public void update(DrawableObject data, CarEventType event) {

        switch (event) {
            case REPAINT -> this.repaint();
            case REMOVE -> this.removeDrawable(data);
            case ADD -> this.drawableObjects.add(data);
        }
    }

    private void removeDrawable(DrawableObject dr) {
        this.drawableObjects.remove(dr);
        this.repaint();
    }

    private void createUI() {
        this.gasSpinner = new Spinner(new SpinnerNumberModel(0, 0, 100, 1));
        this.gasButton = new Button("Gas", () -> controller.gas(gasSpinner.getAmount()));
        this.brakeButton = new Button("Brake", () -> controller.brake(gasSpinner.getAmount()));

        this.turboOnButton = new Button("Saab Turbo on", () -> controller.setTurboOn());
        this.turboOffButton = new Button("Saab Turbo off", () -> controller.setTurboOff());

        this.bedSpinner = new Spinner(new SpinnerNumberModel(0, 0, 70, 1));
        this.liftBedButton = new Button("Scania Lift Bed", () -> controller.liftBed(bedSpinner.getAmount()));
        this.lowerBedButton = new Button("Lower Lift Bed", () -> controller.lowerBed(bedSpinner.getAmount()));

        this.startButton = new Button("Start all cars", () -> controller.startCar());
        this.startButton.setBackground(Color.GREEN);

        this.stopButton = new Button("Stop all cars", () -> controller.stopCar());
        this.stopButton.setBackground(Color.RED);

        this.addCarButton = new Button("Add car", () -> controller.addCar());
        this.removeCarButton = new Button("Remove car", () -> controller.removeCar());
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
        ControlPanel controlPanel = new ControlPanel(0, 4, new Dimension((X / 2) + 10, 200), Color.CYAN, gasButton, turboOnButton, liftBedButton, lowerBedButton, brakeButton, turboOffButton, addCarButton, removeCarButton);
        this.add(controlPanel);

        ControlPanel startStopPanel = new ControlPanel(2, 1, new Dimension((X / 5) - 15, 200), Color.BLACK, startButton, stopButton);
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