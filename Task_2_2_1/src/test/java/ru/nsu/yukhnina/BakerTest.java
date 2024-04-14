package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BakerTest {
    @Test
    void cook() throws InterruptedException {
        TaskQueue tasks = new TaskQueue();
        TaskQueue tasksC = new TaskQueue(100L);
        for (int i = 0; i < 200; i++) {
            tasks.addTask(new Task("Pizza", "Address", 1, i));
        }
        Baker baker = new Baker(1, tasks, tasksC, "Barbie");
        Thread.sleep(400);
        baker.interrupt();
        assert(baker.howPizzas() > 0);
    }
}