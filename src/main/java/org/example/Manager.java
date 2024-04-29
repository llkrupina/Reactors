package org.example;

import java.io.File;
import java.util.Map;

    public class Manager {
        private final FileReader jsonReader;
        private final FileReader xmlReader;
        private final FileReader yamlReader;
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
            jsonReader.readFile(file.getAbsolutePath(), reactorStorage);
        }

        public Map<String, Reactor> getReactorMap() {
            return reactorStorage.getReactorMap();
        }
    }