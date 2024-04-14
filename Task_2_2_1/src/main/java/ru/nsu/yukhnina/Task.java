package ru.nsu.yukhnina;

public class Task {
    private String pizza;
    private String address;
    private long timeToDelivery;
    private int taskId;
    public Task(String pizza, String address, long time, int taskId) {
        this.pizza = pizza;
        this.address = address;
        timeToDelivery = time;
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public long getTimeToDelivery() {
        return timeToDelivery;
    }

    public String getPizza() {
        return pizza;
    }
}
