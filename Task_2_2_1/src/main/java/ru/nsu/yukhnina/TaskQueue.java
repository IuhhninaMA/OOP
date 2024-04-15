package ru.nsu.yukhnina;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * My realisation blocked queue.
 */
public class TaskQueue {
    private final List<Task> taskQueue;
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    private final Long warehouseLimit;

    /**
     * I have 2 constructors because first to courier task list,
     * second baker task list, because baker hasnt limit.
     */
    public TaskQueue(Long warehouseLimit) {
        LOGGER.setLevel(Level.INFO);
        taskQueue = new LinkedList<>();
        this.warehouseLimit = warehouseLimit;
    }

    /**
     * I have 2 constructors because first to courier task list,
     * second baker task list, because baker hasnt limit.
     */
    public TaskQueue() {
        LOGGER.setLevel(Level.INFO);
        taskQueue = new LinkedList<>();
        this.warehouseLimit = Long.MAX_VALUE;
    }

    /**
     * If tasks queue isnt empty return task.
     */
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

    /**
     * If we can we add new task.
     */
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

    /**
     * I need it to end work pizzeria.
     */
    public List<Task> getAllTasks() {
        return taskQueue;
    }
}
