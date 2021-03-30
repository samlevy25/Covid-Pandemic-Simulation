package Simulation;

import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
import Population.Person;
import Population.Sick;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        // Step 1: Map loading
        System.out.println("Hello, please enter your simulation file's name: ");
        String nameOfFile = input.nextLine();
        SimulationFile myFile = new SimulationFile(nameOfFile);
        Settlement[] s = myFile.readFromFile().toArray(new Settlement[0]);
        Map myMap = new Map(s, s.length);
        for (Settlement settlement : s) System.out.println(settlement);
        // Step 2: 1% Sick
        System.out.println("Choose a variant virus to start simulation: \n1.Chineese Variant  2.British Variant  3.South-Africa Variant");
        int variant;
        IVirus virus;
        do{
        variant = input.nextInt();
        switch (variant) {
            case 1:
                virus = new ChineseVariant();
                break;
            case 2:
                virus = new BritishVariant();
                break;
            case 3:
                virus = new SouthAfricanVariant();
                break;
            default:
                virus = null;
        }}while(virus == null);
        for (Settlement settlement : s) {
            int onePercent = (int) (settlement.getPeople().size() / 0.01);
            for (int j = 0; j < onePercent; j++) {
                Sick newSick = settlement.getPeople().remove(j).contagion(virus);
                settlement.getPeople().add(newSick);
            }
        }
    }
}
