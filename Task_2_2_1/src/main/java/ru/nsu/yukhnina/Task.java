package ru.nsu.yukhnina;

public class Task {
    String pizza;
    String address;
    long timeToDelivery;
    public Task(String pizza, String address, long time) {
        this.pizza = pizza;
        this.address = address;
        timeToDelivery = time;
    }

    public long getTimeToDelivery() {
        return timeToDelivery;
    }

    public String getPizza() {
        return pizza;
    }
}
