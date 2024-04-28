package org.example;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

public class XMLReader  extends org.example.FileReader{

    public void readFile(String filePath, ReactorStorage reactorStorage) {
        if (filePath.endsWith(".xml")) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(filePath));

                document.getDocumentElement().normalize();

                NodeList nodeList = document.getDocumentElement().getChildNodes();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String reactorName = element.getNodeName();
                        String reactorClass = element.getElementsByTagName("class").item(0).getTextContent();
                        Double burnup = Double.parseDouble(element.getElementsByTagName("burnup").item(0).getTextContent());
                        Double kpd = Double.parseDouble(element.getElementsByTagName("kpd").item(0).getTextContent());
                        Double enrichment = Double.parseDouble(element.getElementsByTagName("enrichment").item(0).getTextContent());
                        Double thermalCapacity = Double.parseDouble(element.getElementsByTagName("termal_capacity").item(0).getTextContent());
                        Double electricalCapacity = Double.parseDouble(element.getElementsByTagName("electrical_capacity").item(0).getTextContent());
                        Integer lifeTime = Integer.parseInt(element.getElementsByTagName("life_time").item(0).getTextContent());
                        Double firstLoad = Double.parseDouble(element.getElementsByTagName("first_load").item(0).getTextContent());

                        Reactor reactor = new Reactor(reactorName, reactorClass, burnup, electricalCapacity,
                                enrichment, firstLoad, kpd, lifeTime,
                                thermalCapacity, "XML");
                        reactorStorage.addReactor(reactorName, reactor);
                    }
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        } else if (next != null) {
            next.readFile(filePath, reactorStorage);
        } else {
            System.out.println("Формат файла не подходит");
        }
    }

}