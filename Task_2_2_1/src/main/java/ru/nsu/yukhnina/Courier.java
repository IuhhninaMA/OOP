package ru.nsu.yukhnina;

import static ru.nsu.yukhnina.Courier.CourierState.HAVENTWORK;

public class Courier extends Thread {
    public enum CourierState {
        HAVENTWORK,
        WORK
    };
    long capacity;
    CourierState state;
    TaskQueue tasks;
    String name;

    public Courier(long capacity, TaskQueue tasks, String name) {
        this.state = HAVENTWORK;
        this.capacity = capacity;
        this.tasks = tasks;
        this.name = name;
        start();
    }

    @Override
    public synchronized void run() {
        int weight = 0;
        long time = 0;
        boolean stop = true;
        System.out.println("Начал работать");
        while (stop) {
            Task currentTask;
            synchronized (tasks) {
                currentTask = tasks.getTask();
            }
            if (currentTask != null) {
                System.out.println(name + " взял" + currentTask.getPizza());
                weight += 1;
                time += currentTask.getTimeToDelivery();
            }
            if (weight == capacity) {
                try {
                    System.out.println("Заказ доставлен");
                    Thread.sleep(time);
                    time = 0;
                    weight = 0;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (tasks) {
                stop = tasks.isEmpty();
            }
        }
        if (weight != 0) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Заказ доставлен");
        }
        System.out.println("Курьер уехал домой");
    }
}
