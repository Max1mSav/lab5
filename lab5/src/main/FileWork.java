package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileWork {
    private String fileName;

    public FileWork(String fileName) {
        this.fileName = fileName;
    }

    public void readCollectionFromFile() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 200);


    }

}
