package com.codecool.marsexploration;

import com.codecool.marsexploration.data.Symbol;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MapGenerator {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private Random random = new Random();

    private int width = random.nextInt(20, 25);
    private String[][] line = new String[width][width];

    public void creatingMap() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a terrain element name: ");
            String terrain = scanner.nextLine();
            if (terrain.equals("q")) {
                break;
            }
            System.out.println("How many " + terrain + " do you want?");
            int area = scanner.nextInt();

            int randomX = random.nextInt(width);
            int randomY = random.nextInt(width);
            String symbol = Arrays.stream(Symbol.values()).filter(x -> x.name().equals(terrain.toUpperCase())).toList().get(0).getSymbol();

            if (randomX + area <= width && randomY + area <= width) {
                for (int i = 0; i < area; i++) {
                    int x = random.nextInt(area);
                    int y = random.nextInt(area);

                    int xCoordinate = randomX + x;
                    int yCoordinate = randomY + y;
                    line[xCoordinate][yCoordinate] = symbol;
                }
            }
        }
    }

    public void writingMap() throws IOException {
        fileWriter = new FileWriter("src\\main\\resources\\exploration-4.map");
        bufferedWriter = new BufferedWriter(fileWriter);
        for (String[] strings : line) {
            String newLine = Arrays.toString(strings).replaceAll("[^~*#^]", " ");
            System.out.println(newLine);
            bufferedWriter.write(newLine);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }
}

