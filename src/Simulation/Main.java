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

    public static IVirus randomVirus(Map map)
    {
        return map.getVirus()[new Random().nextInt(3)];
    }

    public static int exclusiveRandom(int bound, int i) {
        int rand;
        do {
            rand = new Random().nextInt(bound);
        } while (rand == i);
        return rand;
    }


    public static void main(String[] args) throws InterruptedException {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        do{
            Thread.sleep(1000);
        }while(!mainWindow.hasFileLoaded());

        Map map = mainWindow.getMap();

        while(!mainWindow.isClosed()){
            Clock.nextTick();
            if(map.runningSimulation()) {
                map.runSimulation();
                mainWindow.pack();
            }
        }
    }
}
