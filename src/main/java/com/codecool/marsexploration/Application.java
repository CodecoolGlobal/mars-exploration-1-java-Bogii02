package com.codecool.marsexploration;

import com.codecool.marsexploration.data.Symbol;

import java.io.IOException;
import java.util.Random;

public class Application {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        System.out.println(random.nextInt(42));

        MapGenerator mapGenerator = new MapGenerator();
        mapGenerator.creatingMap();
        mapGenerator.writingMap();
    }
}
