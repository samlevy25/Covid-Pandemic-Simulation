/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Country;

import Simulation.SimulationThread;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Map {

    private final Settlement[] settlements;
    private final IVirus[] virus; // Array of all virus variants.
    private final int size;
    private boolean state = false;
    private ExecutorService executor;
    private List<SimulationThread> threads;
    private CyclicBarrier barrier;

    /**
     * Constructor
     *
     * @param s : Array of Settlements.
     */
    public Map(Settlement[] s) {
        size = s.length;
        settlements = new Settlement[size];
        for (int i = 0; i < size; i++) {
            settlements[i] = s[i];
        }
        virus = new IVirus[]{new BritishVariant(), new ChineseVariant(), new SouthAfricanVariant()};
        executor = Executors.newFixedThreadPool(size);
        barrier = new CyclicBarrier(size);
        threads = new ArrayList<SimulationThread>();
        for (int i = 0; i < size; i++) {
            threads.add(new SimulationThread(settlements[i], this.barrier));
        }
    }

    /**
     * @return : settlements (this)
     */
    public Settlement[] getSettlements() {
        return settlements;
    }

    /**
     * check all settlements and verify the index of "s".
     *
     * @param s : (String) Name of Settlement.
     * @return : settlement's index in the Array of Settlements.
     */
    public int getSettlement(String s) {
        for (int i = 0; i < size; i++) {
            if (settlements[i].getName().equals(s))
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(settlements);
    }

    /**
     * @return Array of all virus variants.
     */
    public IVirus[] getVirus() {
        return virus;
    }

    /**
     * @return True if the simulation can run otherwise else
     */
    public boolean runningSimulation() {
        return state;
    }

    /**
     * Change simulation's state.
     *
     * @param state : Simulation's state.
     */
    public void setState(boolean state){
        this.state = state;
        if(state)
            synchronized (this){
            this.notifyAll();
            }
    }

    /**
     * Simulation performed on settlements
     */
    public void runSimulation() {
        for (SimulationThread simulationThread : threads) {
            executor.submit(simulationThread);
        }
    }
}
