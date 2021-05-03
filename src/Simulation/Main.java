// Jacob Elbaz 336068895
// Samuel Levy 345112148

package Simulation;

import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
import Population.Healthy;
import Population.Person;
import Population.Sick;
import Population.Vaccinated;
import UI.MainWindow;
import Virus.*;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static IVirus randomVirus()
    {
        IVirus[] arrVirus = {new BritishVariant(), new ChineseVariant(), new SouthAfricanVariant()};
        return arrVirus[new Random().nextInt(3)];
    }

    private static int exclusiveRandom(int bound, int i){
        int rand = i;
        while(rand == i)
            rand = new Random().nextInt(bound);
        return rand;
    }

    private static void stepTwo(Map myMap) //  definition of 20% all local residents as patients in one of the variants.
    {
        for(int i = 0; i < myMap.getSettlements().length; i++){
            int percent = (int) (myMap.getSettlements()[i].getPeople().size() * 0.2);
            for (int j = 0; j < percent; j++) {
                Sick newSick = (Sick) myMap.getSettlements()[i].getPeople().remove(j).contagion(randomVirus());
                myMap.getSettlements()[i].addPerson(newSick);
            }
        }
    }

    private static void stepThree(Map myMap, IVirus virus) // random contagion simulation
    {

        for (int i = 0; i < myMap.getSettlements().length; i++) {
            for (int j = 0; j < myMap.getSettlements()[i].getPeople().size(); j++) {
                if (myMap.getSettlements()[i].getPeople().get(j) instanceof Sick) {
                    int k = 0;
                    while (k < 3) { // Pick 3 random healthy person and try to contagion them
                        int rand = new Random().nextInt(myMap.getSettlements()[i].getPeople().size());
                        if (myMap.getSettlements()[i].getPeople().get(rand) instanceof Healthy) {
                            k++;
                            if (virus.tryToContagion((Sick) myMap.getSettlements()[i].getPeople().get(j), myMap.getSettlements()[i].getPeople().get(rand))) {
                                Sick newSick = (Sick) myMap.getSettlements()[i].getPeople().remove(rand).contagion(virus);
                                myMap.getSettlements()[i].getPeople().add(newSick);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < myMap.getSettlements().length; i++){
            myMap.getSettlements()[i].checkConvalescents();
        }
    }

    private static void stepFour(Map map){
        Person p = null;
        int size = map.getSettlements().length;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < map.getSettlements()[i].getPeople().size()*0.03; j++)
                p = map.getSettlements()[i].getPeople().get(j);
                map.getSettlements()[i].transferPerson(p, map.getSettlements()[exclusiveRandom(size, i)]);
        }
    }

    private static void stepFive(Map map){
        for (int i = 0; i < map.getSettlements().length; i++){
            map.getSettlements()[i].vaccinePopulation();
        }
    }

    public static void main(String[] args)  {


        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
