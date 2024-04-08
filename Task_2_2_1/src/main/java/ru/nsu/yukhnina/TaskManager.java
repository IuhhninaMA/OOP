package ru.nsu.yukhnina;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

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
    public TaskManager(String input, int workTime) {
        courierTasks = null;
        bakersTasks = null;
        bakers = new ArrayList<>();
        couriers = new ArrayList<>();
        inputFile = input;
        this.workTime = workTime;
        countPizzas = 0;
        countCookedPizzas = 0;
        warehouseLimit = 0;
    }

    public void openPizzeria() {
        JSONParser parser = new JSONParser();
        //добавить чтение из ресурса
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(inputFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            System.out.println("Ой ой ой а файлика то нету");
        }
        warehouseLimit = (long) jsonObject.get("warehouse");
        courierTasks = new TaskQueue(warehouseLimit);
        bakersTasks = new TaskQueue(warehouseLimit);
        for (Object pizzaObj : (JSONArray) jsonObject.get("pizzas")) {
            countPizzas++;
            JSONObject pizza = (JSONObject) pizzaObj;
            bakersTasks.addTaskToBaker(new Task((String) pizza.get("name"), (String) pizza.get("address"), (Long) pizza.get("time")));
        }
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
        //добавить сохранение оставшихся заказов в json
        System.out.println("Всего заказов: " + countPizzas
                + "\nПриготовлено: " + countCookedPizzas
                + "\nДоставлено: " + deliveredPizzas);
    }
}