package org.example;

public abstract class FileReader {
    protected FileReader next;
    public void setNextReader(FileReader next){
        this.next = next;
    }
    public abstract void readFile(String filePath, ReactorStorage reactorStorage);
}

