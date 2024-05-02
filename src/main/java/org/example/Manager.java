package org.example;

import java.io.File;
import java.util.Map;

    public class Manager {
        private final JSONReader jsonReader;
        private final XMLReader xmlReader;
        private final YAMLReader yamlReader;
        private final ReactorStorage reactorStorage;

        public Manager() {
            jsonReader = new JSONReader();
            xmlReader = new XMLReader();
            yamlReader = new YAMLReader();

            jsonReader.setNextReader(xmlReader);
            xmlReader.setNextReader(yamlReader);

            reactorStorage = new ReactorStorage();
        }

        public void readFile(File file) {
            jsonReader.CheckAndReadFile(file.getAbsolutePath(), reactorStorage);
        }

        public Map<String, Reactor> getReactorMap() {
            return reactorStorage.getReactorMap();
        }
    }
