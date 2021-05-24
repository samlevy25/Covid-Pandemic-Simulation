/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */

package Simulation;

import Country.Map;
import UI.MainWindow;
import Virus.*;

import java.util.Random;

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



        while(!mainWindow.isClosed()) {
            Map map = mainWindow.getMap();
            Clock.nextTick();
            map.runSimulation();
            if (mainWindow.getStatWindow() != null)
                mainWindow.getStatWindow().getStatsTable().repaint();
            mainWindow.pack();

        }
    }
}
