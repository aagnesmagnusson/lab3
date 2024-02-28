package main.model;

import main.Drawable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.Color.RED;


public class VehicleModel{
    private final VehicleList vehicleList;

    private final ServiceShop volvoServiceShop = new ServiceShop(10);
    private static final int width = 800;
    private static final int height = 800;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    public Timer timer = new Timer(delay, new TimerListener());


    public VehicleModel(){
        this.vehicleList = new VehicleList();
    }

    public void addVehicle(Vehicle v){
        vehicleList.addCar(v);
        notifyListeners();

    }

    public ArrayList<Drawable> getDrawables(){
        ArrayList<Drawable> drawables = new ArrayList<>();
        for (Vehicle v : vehicleList.getVehicles()) {
            drawables.add(v);
        }
        drawables.add(volvoServiceShop);
        return drawables; // lista med vehicles och serviceshop
    }

    public void updateVehicles(){
        for (Vehicle v : vehicleList.getVehicles()) {
            v.move();
            checkCollisionWithVolvoServiceShop(v);
            checkOutOfBounds(v);
            notifyListeners();
        }}

    private void checkCollisionWithVolvoServiceShop(Vehicle vehicle) {
        if (vehicle.getCurrentSpeed() != 0){
            if (Math.abs(vehicle.getXPos() - volvoServiceShop.getXPos()) < 5 && Math.abs(vehicle.getYPos() - volvoServiceShop.getYPos()) < 5){
                if (vehicle instanceof Volvo240) {
                    Volvo240 volvo = (Volvo240) vehicle;
                    volvoServiceShop.load(volvo);
                    volvo.stopEngine();
                }
            }
        }}

    private void checkOutOfBounds(Vehicle vehicle) {
        int x = (int) Math.round(vehicle.getXPos());
        int y = (int) Math.round(vehicle.getYPos());
        if (x < 0) {
            vehicle.setXPos(0);
            vehicle.setDirection(-vehicle.getDirection());
        }
        else if (x> width) {
            vehicle.setXPos(width);
            vehicle.setDirection(-vehicle.getDirection());
        }

        if (y < 0) {
            vehicle.setYPos(0);
            vehicle.setDirection(-vehicle.getDirection());
        }
        else if (y > height) {
            vehicle.setYPos(height);
            vehicle.setDirection(-vehicle.getDirection());
        }}

    public ArrayList<Vehicle> getVehicles(){return vehicleList.getVehicles();
    }

    public ServiceShop getVolvoServiceShop(){return volvoServiceShop;}

    // Listener methods
    private final ArrayList<ModelUpdateListener> listeners = new ArrayList<>();

    public void addListener(ModelUpdateListener listener) {
        listeners.add(listener);
    }

    protected void notifyListeners() {
        for (ModelUpdateListener l : listeners)
            l.actOnModelUpdate();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logik som ska utföras vid varje tick
            updateVehicles();
        }
    }


    }