package ru.nsu.yukhnina;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class TaskManager {
    volatile TaskQueue courierTasks;
    volatile TaskQueue bakersTasks;
    volatile boolean bakerStop = false;
    volatile boolean courierStop = false;
    ArrayList<Baker> bakers;
    ArrayList<Courier> couriers;
    String inputFile;
    public TaskManager(String input) {
        courierTasks = new TaskQueue();
        bakersTasks = new TaskQueue();
        bakers = new ArrayList<>();
        couriers = new ArrayList<>();
        inputFile = input;
    }

    public void openPizzeria() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(inputFile));
        for (Object pizzaObj : (org.json.simple.JSONArray) jsonObject.get("pizzas")) {
            JSONObject pizza = (JSONObject) pizzaObj;
            bakersTasks.addTaskToBaker(new Task((String) pizza.get("name"), (String) pizza.get("address"), (Long) pizza.get("time")));
        }
        for (Object bakerObj : (org.json.simple.JSONArray) jsonObject.get("bakers")) {
            JSONObject baker = (JSONObject) bakerObj;
            bakers.add(new Baker((Long) baker.get("time"), bakersTasks, courierTasks, (String) baker.get("name"), bakerStop));
        }
        for (Object courierObj : (org.json.simple.JSONArray) jsonObject.get("couriers")) {
            JSONObject courier = (JSONObject) courierObj;
            couriers.add(new Courier((Long) courier.get("capacity"), courierTasks, (String) courier.get("name")));
        }
        for (Baker baker : bakers) {
            try {
                baker.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (Courier courier : couriers) {
            try {
                courier.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}