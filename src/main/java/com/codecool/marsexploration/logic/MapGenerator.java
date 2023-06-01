package com.codecool.marsexploration.logic;

import com.codecool.marsexploration.data.Symbol;
import com.codecool.marsexploration.data.TerrainAndArea;

import java.util.*;

public class MapGenerator {
    private Random random = new Random();
    int width;
    private String[][] map;
    private List<TerrainAndArea> terrainUndAreas;

    public MapGenerator(List<TerrainAndArea> terrainAndAreas, int width) {
        this.terrainUndAreas = terrainAndAreas;
        this.width = width;
        map = new String[width][width];
    }

    public void creatingMap() {
        fillingMapWithSpaces();

        for (TerrainAndArea terrainUndArea : terrainUndAreas) {

            String terrain = terrainUndArea.terrain();
            String symbol = Arrays.stream(Symbol.values()).
                    filter(x -> x.name().equals(terrain.toUpperCase())).toList().get(0).getSymbol();
            int area = terrainUndArea.area();

            int vertical = random.nextInt(width - area);
            int horizontal = random.nextInt(width - area);


            for (int i = 0; i < area; i++) {

                int verticalCoordinate = vertical;
                int horizontalCoordinate = horizontal;
                map[verticalCoordinate][horizontalCoordinate] = symbol;

                System.out.println(verticalCoordinate + " " + horizontalCoordinate);

                pairingItems(verticalCoordinate, horizontalCoordinate, symbol);

                if (isThereAnEmptyCellAround(verticalCoordinate, horizontalCoordinate)) {
                    while (true) {
                        int nextRandomVerticalCoordinate = verticalCoordinate > 0 ? random.nextInt(-1, 2) :
                                random.nextInt(0, 2);

                        int nextRandomHorizontalCoordinate = generatingNextRandomHorizontal(nextRandomVerticalCoordinate, horizontalCoordinate);

                        if (isEmptyCell(verticalCoordinate, horizontalCoordinate, nextRandomVerticalCoordinate, nextRandomHorizontalCoordinate)) {
                            vertical += nextRandomVerticalCoordinate;
                            horizontal += nextRandomHorizontalCoordinate;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void fillingMapWithSpaces() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = " ";
            }
        }
    }

    private boolean isEmptyCell(int verticalCoordinate, int horizontalCoordinate, int j, int k) {
        return verticalCoordinate + j <= width && verticalCoordinate + j >= 0 &&
                horizontalCoordinate + k <= width && horizontalCoordinate + k >= 0 &&
                map[verticalCoordinate + j][horizontalCoordinate + k].equals(" ");
    }

    private void pairingItems(int verticalCoordinate, int horizontalCoordinate, String symbol) {
        int pairItemsChance = random.nextInt(10);
        System.out.println(pairItemsChance);
        if (pairItemsChance == 1) {
            int j = -1;
            while (j < 2) {
                for (int k = -1; k < 2; k++) {
                    if (isEmptyCell(verticalCoordinate, horizontalCoordinate, j, k)) {
                        map[verticalCoordinate + j][horizontalCoordinate + k] = symbol.equals("^") ? "*" : "~";
                        j = 2;
                        break;
                    }
                    j++;
                }
            }
        }
    }

    private int generatingNextRandomHorizontal(int nextRandomVertical, int horizontalCoordinate) {
        int randomNumberInCaseOfXEqualsPreviousX = random.nextInt(2);
        if (nextRandomVertical == 0 && randomNumberInCaseOfXEqualsPreviousX == 0 &&
                horizontalCoordinate > 0) {
            return -1;
        } else if (nextRandomVertical == 0 && randomNumberInCaseOfXEqualsPreviousX == 1 &&
                horizontalCoordinate > 0) {
            return 1;
        } else if (nextRandomVertical == 0 && horizontalCoordinate == 0) {
            return 1;
        } else if (horizontalCoordinate > 0) {
            return random.nextInt(-1, 2);
        }
        return random.nextInt(0, 2);
    }

    private boolean isThereAnEmptyCellAround(int verticalCoordinate, int horizontalCoordinate){
        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (isEmptyCell(verticalCoordinate, horizontalCoordinate, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[][] getMap(){ return map;}

}
