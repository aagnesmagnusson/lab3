package main.model;

import main.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ServiceShop<T extends Car> implements Loadable<T>, Drawable {
    private final List<T> storage = new ArrayList<>();  // Composition
    private final int maxCapacity;  // Maximalt antal bilar som verkstaden kan ta emot

    private double xPos;
    private double yPos;

    public ServiceShop(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void load(T car) {
        if (storage.size() < maxCapacity) {
            storage.add(car);
        } else {
            throw new IllegalStateException("Verkstaden är full.");
        }
    }

    public T unload() {
        if (!storage.isEmpty()) {
            return storage.removeFirst();
        } else {
            throw new NoSuchElementException("Bilen finns inte i verkstaden.");
        }
    }

    public List<T> getStorage() {
        return storage;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }


    public void setXPos(double x) {
        xPos = x;
    }

    public void setYPos(double y) {
        yPos = y;
    }
}
