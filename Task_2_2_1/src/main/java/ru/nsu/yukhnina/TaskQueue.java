package ru.nsu.yukhnina;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskQueue {
    private final List<Task> taskQueue;
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    private final Long warehouseLimit;

    public TaskQueue(Long warehouseLimit) {
        LOGGER.setLevel(Level.INFO);
        taskQueue = new LinkedList<>();
        this.warehouseLimit = warehouseLimit;
    }

    public TaskQueue() {
        LOGGER.setLevel(Level.INFO);
        taskQueue = new LinkedList<>();
        this.warehouseLimit = Long.MAX_VALUE;
    }

    public synchronized Task getTask() throws InterruptedException {
        while (taskQueue.isEmpty()) {
            wait();
        }
        if (taskQueue.size() == this.warehouseLimit) {
            notifyAll();
        }
        LOGGER.info("Somebody get task");
        return taskQueue.remove(0);
    }

    public synchronized void addTask(Task task) throws InterruptedException {
        while (taskQueue.size() == warehouseLimit) {
            wait();
        }
        if (taskQueue.isEmpty()) {
            notifyAll();
        }
        taskQueue.add(task);
        LOGGER.info("Add task  " + task.getPizza());
    }
}
