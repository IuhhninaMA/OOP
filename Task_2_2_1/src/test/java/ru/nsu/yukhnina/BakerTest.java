package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

class BakerTest {
    @Test
    void cook() throws InterruptedException {
        TaskQueue tasks = new TaskQueue();
        TaskQueue tasksC = new TaskQueue(100L);
        for (int i = 0; i < 50; i++) {
            tasks.addTask(new Task("Pizza", "Address", 1, i));
        }
        Baker baker = new Baker(1, tasks, tasksC, "Barbie");
        Thread.sleep(400);
        baker.interrupt();
        assert (baker.howPizzas() > 25);
    }
}