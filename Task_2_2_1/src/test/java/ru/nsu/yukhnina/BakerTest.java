package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BakerTest {
    @Test
    void cook() throws InterruptedException {
        TaskQueue tasks = new TaskQueue(100L);
        TaskQueue tasksC = new TaskQueue(100L);
        for (int i = 0; i < 200; i++) {
            tasks.addTaskToBaker(new Task("Pizza", "Address", 1));
        }
        Baker baker = new Baker(1, tasks, tasksC, "Barbie");
        Thread.sleep(400);
        baker.interrupt();
        assertEquals(101, baker.howPizzas());
    }
}