package ru.nsu.yukhnina;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskQueue {
    private final List<Task> taskQueue;
    int countTask;
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    private final Long warehouseLimit;
    public TaskQueue(Long warehouseLimit) {
        LOGGER.setLevel(Level.INFO);
        taskQueue = new LinkedList<>();
        this.warehouseLimit = warehouseLimit;
    }

    public synchronized Task getTask() throws InterruptedException {
        while (taskQueue.isEmpty()) {
            wait();
        }
        if (taskQueue.size() == this.warehouseLimit) {
            notifyAll();
        }
        LOGGER.info("Sombody get task");
        return taskQueue.remove(0);
    }

    public synchronized void addTaskToBaker(Task task) {
        taskQueue.add(task);
        LOGGER.info("Add task to baker" + task.getPizza());
        countTask++;
    }

    public synchronized void addTaskToCourier(Task task) throws InterruptedException {
        while (taskQueue.size() == warehouseLimit) {
            wait();
        }
        if (taskQueue.isEmpty()) {
            notifyAll();
        }
        taskQueue.add(task);
        LOGGER.info("Add task to courier " + task.getPizza());
    }
}
