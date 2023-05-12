package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.ReadFile("Colors.csv");
        System.out.println("Hello world!");

    }

    public void ReadFile(String fileName){
        File fileColor = new File(getClass().getClassLoader().getResource("Colors.csv").getPath());
        System.out.println(fileColor.toString());
    }
}