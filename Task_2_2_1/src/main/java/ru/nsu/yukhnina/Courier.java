package ru.nsu.yukhnina;

public class Courier extends Thread {
    long capacity;
    TaskQueue tasks;
    String name;
    private int deliveredPizzas;

    public Courier(long capacity, TaskQueue tasks, String name) {
        this.capacity = capacity;
        this.tasks = tasks;
        this.name = name;
        start();
    }

    @Override
    public void run() {
        int weight = 0;
        long time = 0;
        System.out.println("Начал работать");
        while (!isInterrupted()) {
            Task currentTask;
            try {
                currentTask = tasks.getTask();
            } catch (InterruptedException e) {
                deliveredPizzas += weight;
                System.out.println("Заказ доставлен");
                System.out.println(name+" завершил работу");
                return;
            }
            if (currentTask != null) {
                System.out.println(name + " взял" + currentTask.getPizza());
                weight += 1;
                time += currentTask.getTimeToDelivery();
            }
            if (weight == capacity) {
                try {
                    deliveredPizzas += weight;
                    System.out.println("Заказ доставлен");
                    Thread.sleep(time);
                    time = 0;
                    weight = 0;
                } catch (InterruptedException e) {
                    System.out.println("упси вупси работаем без сна");
                }
            }
        }
        deliveredPizzas += weight;
        System.out.println("Заказ доставлен");
        System.out.println(name+" завершил работу");
    }

    public int howPizzas() {
        return deliveredPizzas;
    }
}
