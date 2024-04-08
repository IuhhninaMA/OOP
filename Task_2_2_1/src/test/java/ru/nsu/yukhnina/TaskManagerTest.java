package ru.nsu.yukhnina;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TaskManagerTest {

    @Test
    void openPizzeria() throws IOException, ParseException {
        TaskManager p = new TaskManager("src/main/resources/package.json", 500);
        p.openPizzeria();
    }
}