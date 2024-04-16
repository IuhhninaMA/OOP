package ru.nsu.yukhnina;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class described baker and how he work.
 * Baker has task queue and get task from it;
 * next he is cooking pizza(sleep) and put pizza to warehouse.
 */
public class Baker extends Thread {
    private long timeToCook;
    private TaskQueue tasks;
    private volatile TaskQueue warehouse;
    private String name;
    private int cookedPizzas;
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());

    /**
     * timeToCook - time thaht baker cook pizza(sleep).
     * bakersTasks - taskQueue from baker got tasks.
     * courierTasks - warehouse or queue with tasks for courier.
     * name - baker name.
     * Baker has this fields and counter how pizzas he cooked to check it at the end.
     */
    public Baker(long timeToCook,
                 TaskQueue bakersTasks,
                 TaskQueue courierTasks,
                 String name) {
        LOGGER.setLevel(Level.INFO);
        this.timeToCook = timeToCook;
        this.tasks = bakersTasks;
        this.name = name;
        this.warehouse = courierTasks;
        this.cookedPizzas = 0;
        start();
    }

    /**
     * All bakers it is threads,
     * baker get task from queue if he can do it else he waits,
     * cook it and got another task.
     */
    @Override
    public void run() {
        LOGGER.info("Baker " + name + " start work");
        while (!isInterrupted()) {
            Task currentTask;
            try {
                currentTask = tasks.getTask();
            } catch (InterruptedException e) {
                LOGGER.info("Baker " + name + " end work");
                return;
            }
            try {
                Thread.sleep(timeToCook);
                LOGGER.info("Baker " + name
                        + " cooked " + currentTask.getPizza()
                        + "ID: " + currentTask.getTaskId());
                cookedPizzas++;
                LOGGER.info("Baker " + name
                        + " put to warehouse " + currentTask.getPizza()
                        + "ID: " + currentTask.getTaskId());
            } catch (InterruptedException e) {
                LOGGER.info("Baker " + name + " end work");
                return;
            }
        }
        LOGGER.info("Baker " + name + " end work");
    }

    /**
     * Return count cooked pizza to check how good my pizzeria work.
     */
    public int howPizzas() {
        return cookedPizzas;
    }
}
