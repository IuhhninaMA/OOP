package ru.nsu.yukhnina;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Baker extends Thread {
    private long timeToCook;
    private TaskQueue tasks;
    private volatile TaskQueue warehouse;
    private String name;
    private int cookedPizzas;
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
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
                LOGGER.info("Baker " + name + " cooked " + currentTask.getPizza());
                cookedPizzas++;
                LOGGER.info("Baker " + name + " put to warehouse " + currentTask.getPizza());
            } catch (InterruptedException e) {
                LOGGER.info("Baker " + name + " end work");
                return;
            }
        }
        LOGGER.info("Baker " + name + " end work");
    }

    public int howPizzas() {
        return cookedPizzas;
    }
}
