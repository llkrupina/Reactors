package org.example;

import org.yaml.snakeyaml.Yaml;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class YAMLReader extends org.example.FileReader {

    public void readFile(String filePath, ReactorStorage reactorStorage) {
        if (filePath.endsWith(".yaml")) {
            Yaml yaml = new Yaml();
            try (FileReader reader = new FileReader(filePath)) {
                Map<String, Map<String, Object>> yamlData = yaml.load(reader);

                if (yamlData != null) {
                    for (String reactorName : yamlData.keySet()) {
                        Map<String, Object> reactorData = yamlData.get(reactorName);

                        if (reactorData != null) {
                            Reactor reactor = parseData(reactorData, reactorName);
                            reactorStorage.addReactor(reactorName, reactor);
                        } else {
                            System.out.println("Ошибка чтения данных: " + reactorName);
                        }
                    }
                } else {
                    System.out.println("Ошибка чтения YAML файла: данные отсутствуют");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (next != null) {
            next.readFile(filePath, reactorStorage);
        } else {
            System.out.println("Формат файла не подходит");
        }
    }

    private Reactor parseData(Map<String, Object> reactorData, String reactorName) {
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
                thermalCapacity, "YAML");
    }

    private Double getValueAsDouble(Map<String, Object> data, String key) {
        Number value = (Number) data.get(key);
        return value != null ? value.doubleValue() : 0.0;
    }

    private Integer getValueAsInteger(Map<String, Object> data, String key) {
        Number value = (Number) data.get(key);
        return value != null ? value.intValue() : 0;
    }

    public void printReactorData(Map<String, Reactor> reactorMap) {
        for (Reactor reactor : reactorMap.values()) {
            System.out.println(reactor.toString());
        }
    }
}