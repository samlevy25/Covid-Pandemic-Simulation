/*
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


    public static void main(String[] args) throws InterruptedException
    {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

        do {
            Thread.sleep(1000);

        }while(!mainWindow.hasFileLoaded());



        Map map = mainWindow.getMap();
        synchronized (map) {
            while (!mainWindow.isClosed()) {

                if (map.runningSimulation()) {
                    map = mainWindow.getMap();
                    Clock.nextTick();
                    map.runSimulation();
                    if (mainWindow.getStatWindow() != null)
                        mainWindow.getStatWindow().getStatsTable().repaint();
                    mainWindow.pack();
                }
                else
                    map.wait();
            }
        }
    }
}
