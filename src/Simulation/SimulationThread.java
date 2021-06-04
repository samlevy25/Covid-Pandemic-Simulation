package Simulation;

import Country.Settlement;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SimulationThread implements Runnable{
    private final Settlement settlement;
    private final CyclicBarrier barrier;
    public SimulationThread(Settlement settlement, CyclicBarrier barrier){
        this.settlement = settlement;
        this.barrier = barrier;
        }

    @Override
    public void run() {
        settlement.runSimulation();
        try{
            barrier.await();
        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}
