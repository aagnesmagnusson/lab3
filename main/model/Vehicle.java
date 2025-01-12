package main.model;

import main.Drawable;

import java.awt.*;

public abstract class Vehicle implements Movable, Drawable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double xPos; // x-position of the car
    private double yPos; // y-position of the car
    private double direction; // direction of the car in radians

    protected boolean engineOn;

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.xPos = 0;
        this.yPos = 0;
        this.direction = Math.PI/2;
        stopEngine();
    }


    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){engineOn = true;}


    public void stopEngine(){
        currentSpeed = 0;
        engineOn = false;
    }

    public boolean getEngineOn(){return engineOn;}

    protected abstract double speedFactor();

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    public double getDirection() {
        return direction;
    }

    public void setXPos(double x) {
        xPos = x;
    }

    public void setYPos(double y) {
        yPos = y;
    }

    public void setDirection(double dir) {
        direction = (dir % (2*Math.PI) + 2*Math.PI)%(2*Math.PI);
    }

    public void move() {
        xPos += currentSpeed * Math.cos(direction);
        yPos += currentSpeed * Math.sin(direction);
    }

    public void turnLeft() {
        direction = (direction + Math.PI /2) % (2*Math.PI);
    }

    public void turnRight() {
        direction = (direction - Math.PI/2 + 2*Math.PI) % (2*Math.PI);
    }

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    };

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    };

    public void gas(double amount) {
        if (engineOn) {
        if (amount >= 0 && amount <=1){
            incrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("Argument must be between 0 and 1");
        }}
        else { throw new IllegalArgumentException("Turn engine on before you push gas");}
    }

    public void brake(double amount){
        if (amount >= 0 && amount <=1){
            decrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("Argument must be between 0 and 1");
        }
    }


}
