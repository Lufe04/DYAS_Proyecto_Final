package com.trainly.app.trainlyapp.services;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Subject {
    private List<Observer> observers;

    public NotificationService() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void sendNotification(String message) {
        System.out.println("Enviando notificación: " + message);
        notifyObservers(message);
    }
}
