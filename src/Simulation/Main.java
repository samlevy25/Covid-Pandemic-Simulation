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
import java.util.Random;
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
            }
        }while(virus == null);
        for(int i = 0; i < myMap.getSettlements().length; i++){
            int onePercent = (int) (myMap.getSettlements()[i].getPeople().size() / 0.01);
            for (int j = 0; j < onePercent; j++) {
                Sick newSick = (Sick) myMap.getSettlements()[i].getPeople().remove(j).contagion(virus);
                myMap.getSettlements()[i].getPeople().add(newSick);
            }
        }
        // Step 3: Simulation
        for (int count = 0; count < 5; count++) { // Simulate 5 time
            for (int i = 0; i < myMap.getSettlements().length; i++) {
                for (int j = 0; j < myMap.getSettlements()[i].getPeople().size(); j++) {
                    if (myMap.getSettlements()[i].getPeople().get(j) instanceof Sick) {
                        for (int k = 0; k < 6; k++) { // Pick 6 random people and try to contagion them
                            int rand = new Random().nextInt(myMap.getSettlements()[i].getPeople().size());
                            virus.tryToContagion(myMap.getSettlements()[i].getPeople().get(j), myMap.getSettlements()[i].getPeople().get(rand));
                        }
                    }
                }
            }
        }
    }
}
