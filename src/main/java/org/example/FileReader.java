package org.example;

public abstract class FileReader {
    protected FileReader next;
    public void setNextReader(FileReader next){
        this.next = next;
    }

    public void CheckAndReadFile(String filePath, ReactorStorage reactorStorage) {
        if (isFileFormatSupported(filePath)) {
            readFile( filePath, reactorStorage);
        } else if (next != null) {
            next.CheckAndReadFile(filePath, reactorStorage);
        } else {
            System.out.println("Формат файла не подходит");
        }
    }

    public abstract boolean isFileFormatSupported(String filePath);

    public abstract void readFile(String filePath, ReactorStorage reactorStorage);
}

