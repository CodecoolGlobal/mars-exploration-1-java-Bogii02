package com.codecool.marsexploration.ui;

import com.codecool.marsexploration.data.TerrainAndArea;

import java.util.ArrayList;
import java.util.List;

public class TerrainAndAreas {

    private List<TerrainAndArea> terrainUndAreas = new ArrayList<>();

    private int width;

    public void fillingTerrainUndAreas(){
        while (true) {
            Input input = new Input();
            String terrainName = input.getTerrainInput();
            if (terrainName.equals("q")) {
                break;
            }
            int area = input.getAreaInput(terrainName);
            terrainUndAreas.add(new TerrainAndArea(terrainName, area));
            width += area;
        }
    }

    public int getWidth(){ return width;}
    public List<TerrainAndArea> getTerrainUndAreas(){ return terrainUndAreas;}

}
