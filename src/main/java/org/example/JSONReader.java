package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class JSONReader extends org.example.FileReader {

    public void readFile(String filePath, ReactorStorage reactorStorage) {

        if (filePath.endsWith(".json")) {
            JSONParser parser = new JSONParser();

            try (FileReader reader = new FileReader(filePath)) {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);

                for (Object key : jsonObject.keySet()) {
                    String reactorName = (String) key;
                    Object reactorData = jsonObject.get(reactorName);

                    if (reactorData instanceof JSONObject) {
                        Reactor reactor = parseData((JSONObject) reactorData, reactorName);
                        reactorStorage.addReactor(reactorName, reactor);
                    } else {
                        System.out.println("Ошибка чтения данных: " + reactorName);
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                System.out.println("Ошибка преобразования типа данных в JSON объект: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Ошибка при обращении к элементу коллекции: " + e.getMessage());
            }
        } else if (next != null) {
            next.readFile(filePath, reactorStorage);
        } else {
            System.out.println("Формат файла не подходит");
        }
    }

    private Reactor parseData(JSONObject reactorData, String reactorName){
        String reactorClass = (String) reactorData.get("class");
        Double burnup = getValueAsDouble(reactorData, "burnup");
        Double kpd = getValueAsDouble(reactorData, "kpd");
        Double enrichment = getValueAsDouble(reactorData, "enrichment");
        Double thermalCapacity = getValueAsDouble(reactorData, "termal_capacity");
        Double electricalCapacity = getValueAsDouble(reactorData, "electrical_capacity");
        Integer lifeTime = getValueAsInteger(reactorData, "life_time");
        Double firstLoad = getValueAsDouble(reactorData, "first_load");


        return new Reactor(reactorName, reactorClass, burnup, electricalCapacity,
                enrichment, firstLoad, kpd, lifeTime,
                thermalCapacity, "JSON");
    }
    private Double getValueAsDouble(JSONObject reactorData, String key) {
        Number value = (Number) reactorData.get(key);
        return value != null ? value.doubleValue() : 0.0;
    }

    private Integer getValueAsInteger(JSONObject reactorData, String key) {
        Number value = (Number) reactorData.get(key);
        return value != null ? value.intValue() : 0;
    }

}