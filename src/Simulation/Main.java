package Simulation;

import IO.SimulationFile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, please enter name of file: ");
        String nameOfFile = input.nextLine();
        SimulationFile myFile = new SimulationFile(nameOfFile);
        myFile.readFromFile();
    }
}
