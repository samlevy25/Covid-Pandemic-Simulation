package Virus;

import Population.Person;
import Population.Sick;
import Simulation.Clock;
import java.util.Random;

public class SouthAfricanVariant implements IVirus {

    private static final double killUnder18 = 0.05;
    private static final double killAbove18 = 0.08;
    private static final double contagionUnder18 = 0.6;
    private static final double contagionAbove18 = 0.5;
    private boolean[] mutations = {true, true, true};

    public double killingProbability(Person p) {
        if (p.getAge() <= 18)
            return killUnder18;
        else
            return killAbove18;
    }

    @Override
    public double contagionProbability(Person p) {
        if (p.getAge() <= 18)
            return contagionUnder18*p.contagionProbability();
        else
            return contagionAbove18*p.contagionProbability();
    }

    @Override
    public boolean tryToContagion(Sick p, Person other) {
        if(!(other instanceof Sick)) {
            if (Clock.numOfDays(p.getContagiousTime()) >= 5) {
                double distance = Math.sqrt(Math.pow(p.getLocation().getM_x() - other.getLocation().getM_x(), 2) + Math.pow(p.getLocation().getM_y() - other.getLocation().getM_y(), 2));
                double probability = contagionProbability(other) * Math.min(1, 0.14 + Math.exp(2 - 0.25 * distance));
                int i = new Random().nextInt(100);
                return i < probability * 100;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public boolean tryToKill(Sick p) {

        long t = Clock.now() - p.getContagiousTime();
        double probability = Math.max(0, killingProbability(p) - 0.01*killingProbability(p)*Math.pow(t-15, 2));
        int i = new Random().nextInt(100);
        return i < probability * 100;
    }

    @Override
    public String toString() {
        return "South-Africa Variant";
    }

    @Override
    public boolean[] getMutations() {
        return mutations;
    }

    @Override
    public void setMutations(int index, boolean bool) {
        this.mutations[index] = bool;
    }
}
