package com.codecool.marsexploration.ui;

import java.util.Scanner;

public class Input {

    private Scanner scanner = new Scanner(System.in);

    public String getFileName(){
        System.out.println("Give me the name of the file to save:");
        return scanner.nextLine();
    }

    public String getTerrainInput(){
        System.out.println("Enter a terrain element name: ");
        return scanner.nextLine();
    }

    public int getAreaInput(String terrain){
        System.out.println("How many " + terrain + " do you want?");
        return scanner.nextInt();
    }

}
