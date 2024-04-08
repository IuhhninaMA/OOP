package ru.nsu.yukhnina;

public class Task {
    private String pizza;
    private String address;
    private long timeToDelivery;
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
