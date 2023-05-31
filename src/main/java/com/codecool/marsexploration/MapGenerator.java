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
    private String[][] map = new String[width][width];

    private void fillingMapWithSpaces(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                map[i][j] = " ";
            }
        }
    }

    public void creatingMap() {
        fillingMapWithSpaces();
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

            System.out.println(randomX + " " + randomY);

            String symbol = Arrays.stream(Symbol.values()).filter(x -> x.name().equals(terrain.toUpperCase())).toList().get(0).getSymbol();

            System.out.println(symbol);

            if (randomX + area <= width && randomY + area <= width) {
                for (int i = 0; i < area; i++) {
                    int x = random.nextInt(area);
                    int y = random.nextInt(area);

                    int xCoordinate = randomX + x;
                    int yCoordinate = randomY + y;
                    map[xCoordinate][yCoordinate] = symbol;
                }
            }
        }
    }

    public void writingMap() throws IOException {
        fileWriter = new FileWriter("src\\main\\resources\\exploration-4.map");
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