package com.codecool.marsexploration;

import com.codecool.marsexploration.data.TerrainAndArea;
import com.codecool.marsexploration.logic.MapGenerator;
import com.codecool.marsexploration.logic.WriteFile;
import com.codecool.marsexploration.ui.Input;
import com.codecool.marsexploration.ui.TerrainAndAreas;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        Input input = new Input();
        String fileName = input.getFileName();
        TerrainAndAreas terrainAndAreas = new TerrainAndAreas();
        terrainAndAreas.fillingTerrainUndAreas();
        List<TerrainAndArea> terrainAndAreaList = terrainAndAreas.getTerrainUndAreas();
        int width = terrainAndAreas.getWidth();
        MapGenerator mapGenerator = new MapGenerator(terrainAndAreaList, width);
        mapGenerator.creatingMap();
        String[][] map = mapGenerator.getMap();
        WriteFile writeFile = new WriteFile();
        writeFile.writingMap(fileName, map);
    }
}
