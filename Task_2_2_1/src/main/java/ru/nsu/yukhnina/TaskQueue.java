package ru.nsu.yukhnina;

import java.util.*;

public class TaskQueue {
    private List<Task> taskQueue;
    int countTask;

    private final int warehouseLimit = 20;
    public TaskQueue() {
        taskQueue = new LinkedList();
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

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}
