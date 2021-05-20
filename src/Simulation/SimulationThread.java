package Simulation;

import Country.Map;
import Country.Settlement;

import java.util.concurrent.CyclicBarrier;

public class SimulationThread implements Runnable{
    private Settlement settlement;
    private CyclicBarrier barrier;
    public SimulationThread(Settlement settlement, CyclicBarrier barrier){
        this.settlement = settlement;
        this.barrier = barrier;
        }

    @Override
    public void run() {
        settlement.runSimulation();
    }
}
