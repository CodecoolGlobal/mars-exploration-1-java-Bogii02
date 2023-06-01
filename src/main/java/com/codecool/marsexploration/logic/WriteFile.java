package com.codecool.marsexploration.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    public void writingMap(String fileName, String[][] map) throws IOException {

        fileWriter = new FileWriter("C:\\Users\\mehty\\Documents\\gitcodecool\\Java\\mars-exploration-visuals\\src\\main\\resources\\" + fileName + ".map");
        bufferedWriter = new BufferedWriter(fileWriter);
        for (String[] strings : map) {
            System.out.println(String.join("", strings));
            bufferedWriter.write(String.join("", strings));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }
}
