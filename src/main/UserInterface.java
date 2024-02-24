package main;

import main.controller.CarController;
import main.view.DrawPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class UserInterface extends JFrame implements ActionListener{
    private static final int X = 800;
    private static final int Y = 800;

    // The controller member
    CarController carC;

    DrawPanel drawPanel;

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    public JSpinner gasSpinner = new JSpinner();

    JLabel gasLabel = new JLabel("Amount of gas");

    public JButton gasButton = new JButton("Gas");
    public JButton brakeButton = new JButton("Brake");
    public JButton turboOnButton = new JButton("Saab Turbo on");
    public JButton turboOffButton = new JButton("Saab Turbo off");
    public JButton liftBedButton = new JButton("main.model.Scania Lift Bed");
    public JButton lowerBedButton = new JButton("Lower Lift Bed");

    public JButton startButton = new JButton("Start all cars");
    public JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public UserInterface(String framename, CarController cc){
        this.carC = cc;
        this.drawPanel = new DrawPanel(carC, X, Y-240); // Kanske kan skapas i carcontroller
        initComponents(framename);

    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.gas(gasAmount);

            }
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.brake(gasAmount);

            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.startEngine();

            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.stopEngine();
            }
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.turboOn();

            }
        });
        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.turboOff();

            }
        });
        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.liftBed();

            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.lowerBed();

            }
        });

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void add

    public void actionPerformed(ActionEvent e){
        this.repaint();
    } //drawpanel
}