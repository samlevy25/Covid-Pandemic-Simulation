package Simulation;

import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
import Population.Sick;
import Virus.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Map stepOne() {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, please enter your simulation file's name: ");
        String nameOfFile = input.nextLine();
        SimulationFile myFile = new SimulationFile(nameOfFile);
        Settlement[] s = myFile.readFromFile().toArray(new Settlement[0]);
        return new Map(s, s.length);
    }

    private static IVirus choosingVirus() {
        System.out.println("Choose a variant virus to start simulation: \n1.Chinese Variant  2.British Variant  3.South-Africa Variant");
        int variant;
        IVirus virus;
        Scanner input = new Scanner(System.in);
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
        return virus;
    }

    private static void stepTwo(Map myMap, IVirus virus) {

        for(int i = 0; i < myMap.getSettlements().length; i++){
            int onePercent = (int) (myMap.getSettlements()[i].getPeople().size() * 0.01);
            for (int j = 0; j < onePercent; j++) {
                Sick newSick = (Sick) myMap.getSettlements()[i].getPeople().remove(j).contagion(virus);
                myMap.getSettlements()[i].getPeople().add(newSick);
            }
        }
    }

    private static void stepThree(Map myMap, IVirus virus) {
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


    public static void main(String[] args) {
        // Step 1: Map loading
        Map myMap = stepOne();
        // Step 2: 1% Sick
        IVirus virus = choosingVirus();
        stepTwo(myMap, virus);
        // Step 3: Simulation
        stepThree(myMap, virus);
    }
}
