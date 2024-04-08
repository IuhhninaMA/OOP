package ru.nsu.yukhnina;

import java.util.Timer;
import java.util.TimerTask;

public class Baker extends Thread {
    private long timeToCook;
    private TaskQueue tasks;
    private volatile TaskQueue warehouse;
    private String name;
    int cookedPizzas;
    public Baker(long timeToCook,
                 TaskQueue bakersTasks,
                 TaskQueue courierTasks,
                 String name) {
        this.timeToCook = timeToCook;
        this.tasks = bakersTasks;
        this.name = name;
        this.warehouse = courierTasks;
        this.cookedPizzas = 0;
        start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Task currentTask;
            try {
                currentTask = tasks.getTask();
            } catch (InterruptedException e) {
                System.out.println(name+" завершил работу");
                return;
            }
            try {
                Thread.sleep(timeToCook);
                System.out.println(name + " приготовил " + currentTask.getPizza());
                cookedPizzas++;
                warehouse.addTaskToCourier(currentTask);
                System.out.println(name + " отправил на склад " + currentTask.getPizza());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name+" завершил работу");
    }

    public int howPizzas() {
        return cookedPizzas;
    }
}
