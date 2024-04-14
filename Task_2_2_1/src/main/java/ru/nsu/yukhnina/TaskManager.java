package ru.nsu.yukhnina;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.logging.Level;
import java.util.logging.Logger;


public class TaskManager {
    final int workTime;
    int countPizzas;
    int countCookedPizzas;
    int deliveredPizzas;
    volatile TaskQueue courierTasks;
    volatile TaskQueue bakersTasks;
    volatile boolean pizzeriaClose = false;
    ArrayList<Baker> bakers;
    ArrayList<Courier> couriers;
    String inputFile;
    long warehouseLimit;
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    public TaskManager(String input, int workTime) {
        courierTasks = null;
        bakersTasks = null;
        bakers = new ArrayList<>();
        couriers = new ArrayList<>();
        inputFile = input;
        this.workTime = workTime;
        countPizzas = 0;
        countCookedPizzas = 0;
        deliveredPizzas = 0;
        warehouseLimit = 0;
    }

    public void openPizzeria() {
        LOGGER.info("Pizzeria start work");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(inputFile));
            LOGGER.info("File " + inputFile + " open");
            warehouseLimit = (long) jsonObject.get("warehouse");
            courierTasks = new TaskQueue(warehouseLimit);
            bakersTasks = new TaskQueue();
            for (Object pizzaObj : (JSONArray) jsonObject.get("pizzas")) {
                JSONObject pizza = (JSONObject) pizzaObj;
                addTaskToBaker(new Task((String) pizza.get("name"), (String) pizza.get("address"), (Long) pizza.get("time"), countPizzas));
            }
            LOGGER.info("Get " + countPizzas + " tasks");
            for (Object bakerObj : (JSONArray) jsonObject.get("bakers")) {
                JSONObject baker = (JSONObject) bakerObj;
                bakers.add(new Baker((Long) baker.get("time"), bakersTasks, courierTasks, (String) baker.get("name")));
            }
            for (Object courierObj : (JSONArray) jsonObject.get("couriers")) {
                JSONObject courier = (JSONObject) courierObj;
                couriers.add(new Courier((Long) courier.get("capacity"), courierTasks, (String) courier.get("name")));
            }
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pizzeriaClose = true;

            for (Baker baker: bakers) {
                baker.interrupt();
                countCookedPizzas += baker.howPizzas();
            }
            for (Courier courier: couriers) {
                courier.interrupt();
                deliveredPizzas += courier.howPizzas();
            }
        } catch (IOException e) {
            System.out.println("Ой ой ой а файлика то нету");
        } catch (ParseException e) {
            System.out.println("Ой ой ой а файлик то кривой");
        }
        //добавить сохранение оставшихся заказов в json
        System.out.println("Всего заказов: " + countPizzas
                + "\nПриготовлено: " + countCookedPizzas
                + "\nДоставлено: " + deliveredPizzas);
    }

    public void addTaskToBaker(Task task) {
        countPizzas++;
        try {
            bakersTasks.addTask(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}