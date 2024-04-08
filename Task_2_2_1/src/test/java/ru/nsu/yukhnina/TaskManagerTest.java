package ru.nsu.yukhnina;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TaskManagerTest {

    @Test
    void openPizzeria() {
        TaskManager p = new TaskManager("src/main/resources/package.json", 3000);
        p.openPizzeria();
        //проверка, что очередь работает нормально и количество готовых пицц не больше всех заказов
        //и колличество доставленных не больше приготовленных
        assert(p.countPizzas >= p.deliveredPizzas);
        assert(p.countCookedPizzas >= p.deliveredPizzas);
    }

    @Test
    void countPizzas() {
        TaskManager p = new TaskManager("src/main/resources/package2.json", 500);
        p.openPizzeria();

    }
}