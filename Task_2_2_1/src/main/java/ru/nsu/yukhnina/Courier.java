package ru.nsu.yukhnina;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Courier got task from werwhouse while he can and delivered order.
 */
public class Courier extends Thread {
    long capacity;
    TaskQueue tasks;
    String name;
    private int deliveredPizzas;
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());

    /**
     * Described courier and start work.
     */
    public Courier(long capacity, TaskQueue tasks, String name) {
        LOGGER.setLevel(Level.INFO);
        this.capacity = capacity;
        this.tasks = tasks;
        this.name = name;
        this.deliveredPizzas = 0;
        start();
    }

    /**
     * Courirer work while not interrupted.
     */
    @Override
    public void run() {
        LOGGER.info("Courier " + name + " start work");
        int weight = 0;
        long time = 0;
        while (!isInterrupted()) {
            Task currentTask;
            try {
                currentTask = tasks.getTask();
            } catch (InterruptedException e) {
                deliveredPizzas += weight;
                LOGGER.info("Courier " + name + " delivered");
                LOGGER.info("Courier " + name + " end work");
                return;
            }
            if (currentTask != null) {
                LOGGER.info("Courier " + name
                        + " get " + currentTask.getPizza()
                        + "ID: " + currentTask.getTaskId());
                weight += 1;
                time += currentTask.getTimeToDelivery();
            }
            if (weight == capacity) {
                try {
                    deliveredPizzas += weight;
                    LOGGER.info("Courier " + name + " delivered");
                    Thread.sleep(time);
                    time = 0;
                    weight = 0;
                } catch (InterruptedException e) {
                    System.out.println("упси вупси работаем без сна");
                }
            }
        }
        deliveredPizzas += weight;
        LOGGER.info("Courier " + name + " end work");
    }

    /**
     * Return how pizzas he deliveret to check how pizzeria work.
     */
    public int howPizzas() {
        return deliveredPizzas;
    }
}
