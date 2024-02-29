package main;

import main.controller.CarController;
import main.model.*;
import main.view.DrawPanel;


import java.awt.*;

public class CreateGame {

    public static void main(String[] args) {
        // Create model
        VehicleModel model = createModel();

        // Create drawPanel
        DrawPanel drawPanel = new DrawPanel(model);   // definiera fönstrets storlek i modellen

        // Create ui
        UserInterface ui = new UserInterface("CarSim 1.0", drawPanel, model.getWidth(), model.getHeight());

        // Create controller
        CarController cc = new CarController(model, ui);


        model.addListener(drawPanel);

        // Add listeners
        ui.addGasButtonListener(cc.createGasActionListener());
        ui.addBrakeButtonListener(cc.createBrakeActionListener());
        ui.addStartButtonListener(cc.createStartActionListener());
        ui.addStopButtonListener(cc.createStopActionListener());
        ui.addTurboOnButtonListener(cc.createTurboOnActionListener());
        ui.addTurboOffButtonListener(cc.createTurboOffActionListener());
        ui.addLiftBedButtonListener(cc.createLiftBedActionListener());
        ui.addLowerBedButtonListener(cc.createLowerBedActionListener());
        ui.addAddCarButtonListener(cc.createAddCarActionListener());
        ui.addGasSpinnerListener(cc.createGasSpinnerChangeListener());
        ui.addRemoveCarButtonListener(cc.createremoveCarActionListener());
        ui.addTurnLeftButtonListener(cc.createTurnLeftActionListener());
        ui.addTurnRightButtonListener(cc.createTurnRightActionListener());



        // Get serviceshop and set position
        ServiceShop<Volvo240> volvoServiceShop = model.getVolvoServiceShop();
        volvoServiceShop.setXPos(0);
        volvoServiceShop.setYPos(300);

        // Start the timer
        model.timer.start();


    }

    public static VehicleModel createModel(){
        VehicleModel model = new VehicleModel();

        Vehicle volvo = VehicleFactory.createVolvo240(4, 100, Color.BLACK, "ILoveVolvo");
        model.addCar(volvo);

        Vehicle saab = VehicleFactory.createSaab(4,50,Color.RED,"ILoveSaab",true);
        saab.setXPos(0);
        saab.setYPos(100);
        model.addCar(saab);

        Vehicle scania = VehicleFactory.createScania(2, 100, Color.BLUE, "ILoveScania");
        scania.setXPos(0);
        scania.setYPos(200);
        model.addCar(scania);

        return model;
    }

}
