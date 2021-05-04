/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */

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
    /**
     * @param map : The map
     * @return One of the three viruses
     */
    public static IVirus randomVirus(Map map)
    {
        return map.getVirus()[new Random().nextInt(3)];
    }

    /**
     * Returns a random number that is not the same as "i"
     * @param bound :  the bound
     * @param i : Number
     * @return random number
     */
    public static int exclusiveRandom(int bound, int i) {
        int rand;
        do {
            rand = new Random().nextInt(bound);
        } while (rand == i);
        return rand;
    }


    public static void main(String[] args) throws InterruptedException
    {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

        do {
            Thread.sleep(1000);

        }while(!mainWindow.hasFileLoaded());

        Map map = mainWindow.getMap();

        while(!mainWindow.isClosed())
        {
            Clock.nextTick();
            if(map.runningSimulation()) {

                map.runSimulation();
                mainWindow.pack();
            }
        }
    }
}
