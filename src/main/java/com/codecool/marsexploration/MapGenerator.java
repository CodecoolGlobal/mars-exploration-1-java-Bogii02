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

    private int width = random.nextInt(20, 40);
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
                int x = random.nextInt(area);
                int y = random.nextInt(area);

                //System.out.println(x);
                //System.out.println(y);

                for (int i = 0; i < area; i++) {

                    int xCoordinate = randomX + x;
                    int yCoordinate = randomY + y;
                    map[xCoordinate][yCoordinate] = symbol;
                    System.out.println(xCoordinate + " " + yCoordinate);
                    //System.out.println("Created");

                    int pairItemsChance = random.nextInt(10);
                    System.out.println(pairItemsChance);
                    if(pairItemsChance == 1){
                        int j = -1;
                        while(j < 2){
                            for(int k = -1; k < 2; k++){
                                if(xCoordinate + j <= width && xCoordinate + j >= 0 &&
                                        yCoordinate + k <= width && yCoordinate + k >= 0 &&
                                        map[xCoordinate + j][yCoordinate + k].equals(" ") ){
                                    map[xCoordinate + j][yCoordinate + k] = symbol.equals("^") ? "*" : "~";
                                    j = 2;
                                    break;
                                }
                            j++;
                            }
                        }
                    }

                    while(true) {
                        int nextRandomX = x > 0 && x < area ? random.nextInt(-1, 2) : x == area ? random.nextInt(-1, 1) :
                                random.nextInt(0, 2);

                        //System.out.println(xCoordinate);
                        int randomNumberInCaseOfXEqualsPreviousX = random.nextInt(2);

                        int nextRandomY = nextRandomX == 0 && randomNumberInCaseOfXEqualsPreviousX == 0 &&
                                y > 0 && y < area ? -1 :
                                nextRandomX == 0 && randomNumberInCaseOfXEqualsPreviousX == 1 &&
                                        y > 0 && y < area ? 1 :
                                        nextRandomX == 0 && y == 0 ? 1 :
                                                nextRandomX == 0 && y == area ? -1 :
                                                        y > 0 && y < area ? random.nextInt(-1, 2) :
                                                                y == area ? random.nextInt(-1, 1) :
                                                                        random.nextInt(0, 2);

                        System.out.println(map[xCoordinate + nextRandomX][yCoordinate + nextRandomY]);
                        if(map[xCoordinate + nextRandomX][yCoordinate + nextRandomY].equals(" ")){
                            x += nextRandomX;
                            y += nextRandomY;
                            break;
                        }
                    }
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
