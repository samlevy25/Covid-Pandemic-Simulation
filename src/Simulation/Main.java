package Simulation;

import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
import Population.Sick;
import Virus.*;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Map stepOne(String nameOfFile) throws IOException // data recovery from a file to associate it with a map
    {
        SimulationFile myFile = new SimulationFile(nameOfFile);
        Settlement[] s = myFile.readFromFile().toArray(new Settlement[0]);
        return new Map(s, s.length);
    }

    private static IVirus choosingVirus() // simulation of variant virus
    {
        int variant;
        IVirus virus;
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("Choose a variant virus to start simulation: \n1.Chinese Variant  2.British Variant  3.South-Africa Variant");
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

    private static void stepTwo(Map myMap, IVirus virus) //  definition of 1% all local residents as patients in one of the variants.
    {

        for(int i = 0; i < myMap.getSettlements().length; i++){
            int onePercent = (int) (myMap.getSettlements()[i].getPeople().size() * 0.01);
            for (int j = 0; j < onePercent; j++) {
                Sick newSick = (Sick) myMap.getSettlements()[i].getPeople().remove(j).contagion(virus);
                myMap.getSettlements()[i].getPeople().add(newSick);
            }
        }
    }

    private static void stepThree(Map myMap, IVirus virus) // random contagion simulation
    {
        for (int count = 0; count < 5; count++) { // Simulate 5 time
            for (int i = 0; i < myMap.getSettlements().length; i++) {
                for (int j = 0; j < myMap.getSettlements()[i].getPeople().size(); j++) {
                    if (myMap.getSettlements()[i].getPeople().get(j) instanceof Sick) {
                        for (int k = 0; k < 6; k++) { // Pick 6 random people and try to contagion them
                            int rand = new Random().nextInt(myMap.getSettlements()[i].getPeople().size());
                            if(virus.tryToContagion(myMap.getSettlements()[i].getPeople().get(j), myMap.getSettlements()[i].getPeople().get(rand))){
                                Sick newSick = (Sick) myMap.getSettlements()[i].getPeople().remove(rand).contagion(virus);
                                myMap.getSettlements()[i].getPeople().add(newSick);
                            }
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args)  {

    }
}
