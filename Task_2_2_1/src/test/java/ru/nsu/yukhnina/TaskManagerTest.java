package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

class TaskManagerTest {

    @Test
    void openPizzeria() {
        TaskManager p = new TaskManager("src/main/resources/package.json", 3000);
        p.openPizzeria();
        //проверка, что очередь работает нормально и количество готовых пицц не больше всех заказов
        //и колличество доставленных не больше приготовленных
        assert(p.countPizzas == 15);
        assert(p.countPizzas >= p.deliveredPizzas);
        assert(p.countCookedPizzas >= p.deliveredPizzas);
    }

    @Test
    void countPizzas() {
        TaskManager p = new TaskManager("src/main/resources/package2.json", 7000);
        p.openPizzeria();
        assert(p.countPizzas == 15);
        assert(p.countCookedPizzas == 15);
        assert(p.deliveredPizzas <= 15);
    }
    @Test
    void noneCookedPizzas() {
        //в этом файле нет пекарей
        TaskManager p = new TaskManager("src/main/resources/package3.json", 200);
        p.openPizzeria();
        assert(p.countPizzas == 15);
        assert(p.countCookedPizzas == 0);
    }

    @Test
    void addPizzas() throws InterruptedException {
        TaskManager p = new TaskManager("src/main/resources/package4.json", 10000);
        Thread thread = new Thread(() -> {
            p.openPizzeria();
        });
        thread.start();
        Thread.sleep(20);
        for (int i = 0; i < 10; i++) {
            p.addTaskToBaker(new Task("Pizza", "Address", 12));
        }
        Thread.sleep(10000);
        assert(p.countPizzas > 1);
        assert(p.countCookedPizzas > 1);
    }
}