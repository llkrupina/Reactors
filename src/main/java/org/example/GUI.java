package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class GUI extends JFrame {
    private JButton fileChooserButton;
    private JTree reactorTree;
    private final Manager manager;

    public GUI() {
        setTitle("File Chooser");
        setSize(300, 400); // Увеличил высоту, чтобы вместить дерево
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        manager = new Manager();

        fileChooserButton = new JButton("Choose File");
        fileChooserButton.addActionListener(e -> chooseFile());

        JPanel panel = new JPanel();
        panel.add(fileChooserButton);
        add(panel, BorderLayout.NORTH);

        reactorTree = new JTree(new DefaultMutableTreeNode("Reactors"));
        JScrollPane scrollPane = new JScrollPane(reactorTree);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON, XML, YAML Files", "json", "xml", "yaml");
        fileChooser.setFileFilter(filter);

        String currentDirectory = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(currentDirectory));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            manager.readFile(selectedFile);
            updateTree();
        }
    }

    private void updateTree() {
        Map<String, Reactor> reactorMap = manager.getReactorMap();

        DefaultMutableTreeNode rootNode = null;
        if (!reactorMap.isEmpty()) {
            String source = reactorMap.values().iterator().next().getSource();
            rootNode = new DefaultMutableTreeNode("Reactors from " + source);
        } else {
            rootNode = new DefaultMutableTreeNode("No reactors found");
        }

        for (Reactor reactor : reactorMap.values()) {
            DefaultMutableTreeNode reactorNode = new DefaultMutableTreeNode(reactor.getType());
            reactorNode.add(new DefaultMutableTreeNode("Burnup: " + reactor.getBurnup()));
            reactorNode.add(new DefaultMutableTreeNode("KPD: " + reactor.getKpd()));
            reactorNode.add(new DefaultMutableTreeNode("Enrichment: " + reactor.getEnrichment()));
            reactorNode.add(new DefaultMutableTreeNode("Thermal Capacity: " + reactor.getThermal_capacity()));
            reactorNode.add(new DefaultMutableTreeNode("Electrical Capacity: " + reactor.getElectrical_capacity()));
            reactorNode.add(new DefaultMutableTreeNode("Life Time: " + reactor.getLife_time()));
            reactorNode.add(new DefaultMutableTreeNode("First Load: " + reactor.getFirst_load()));
            rootNode.add(reactorNode);
        }

        reactorTree.setModel(new javax.swing.tree.DefaultTreeModel(rootNode));
    }
}