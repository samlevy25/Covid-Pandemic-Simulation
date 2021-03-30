package Simulation;

import Country.Settlement;
import IO.SimulationFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, please enter name of file: ");
        String nameOfFile = input.nextLine();
        SimulationFile myFile = new SimulationFile(nameOfFile);
        Settlement[] s = myFile.readFromFile().toArray(new Settlement[0]);
        for (Settlement settlement : s) System.out.println(settlement);
    }
}
