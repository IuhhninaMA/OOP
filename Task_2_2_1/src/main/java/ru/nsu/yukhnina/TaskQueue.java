package ru.nsu.yukhnina;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class TaskQueue {
    ArrayDeque<Task> taskQueue;
    int countTask;
    public TaskQueue() {
        taskQueue = new ArrayDeque<Task>();
    }

    public Task getTask() {
        countTask--;
        return taskQueue.poll();
    }

    public void addTaskToBaker(Task task) {
        taskQueue.add(task);
        countTask++;
    }

    public void addTaskToCourier(Task task) {
        //пусть пока размер склада - 10
        if (taskQueue.size() < 100) {
            taskQueue.add(task);
            countTask++;
        }
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}
