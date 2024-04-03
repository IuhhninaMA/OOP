package ru.nsu.yukhnina;

import java.util.ArrayDeque;
import java.util.Deque;

import static ru.nsu.yukhnina.Baker.BakerState.FREE;

public class Baker extends Thread {
    public enum BakerState {
        FREE,
        COOKING,
        WAITQUEUE
    };
    long timeToCook;
    BakerState state;
    TaskQueue tasks;
    TaskQueue warehouse;
    String name;
    volatile boolean bakerStop;

    public Baker(long timeToCook, TaskQueue bakersTasks, TaskQueue courierTasks, String name, boolean bakerStop) {
        this.state = FREE;
        this.timeToCook = timeToCook;
        this.tasks = bakersTasks;
        this.name = name;
        this.warehouse = courierTasks;
        this.bakerStop = bakerStop;
        start();
    }

    @Override
    public void run() {
        boolean stop = true;
        while (stop) {
            Task currentTask;
            synchronized (tasks) {
                currentTask = tasks.getTask();
            }
            if (currentTask != null) {
                try {
                    Thread.sleep(timeToCook);
                    System.out.println(name + " приготовил " + currentTask.getPizza());
                    warehouse.addTaskToCourier(currentTask);
                    System.out.println(name + " отправил на склад " + currentTask.getPizza());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (tasks) {
                stop = tasks.isEmpty();
            }
        }
        System.out.println(name + " закончила работу");
    }
}
