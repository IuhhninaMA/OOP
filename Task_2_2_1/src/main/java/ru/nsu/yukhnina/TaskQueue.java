package ru.nsu.yukhnina;

import java.util.*;

public class TaskQueue {
    private volatile List<Task> taskQueue;
    int countTask;

    private final Long warehouseLimit;
    public TaskQueue(Long warehouseLimit) {
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
        return taskQueue.remove(0);
    }

    public synchronized void addTaskToBaker(Task task) {
        taskQueue.add(task);
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
    }
}
