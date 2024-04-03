package ru.nsu.yukhnina;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void openPizzeria() throws IOException, ParseException {
        TaskManager p = new TaskManager("src/main/resources/package.json");
        p.openPizzeria();
    }
}