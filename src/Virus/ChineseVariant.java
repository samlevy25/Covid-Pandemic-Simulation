package Virus;

import Population.Person;
import Population.Sick;
import Simulation.Clock;

import java.util.Random;

public class ChineseVariant implements IVirus {
    public double killingProbability(Person p) {
        if (p.getAge() < 18)
            return 0.001;
        else if (p.getAge() <= 55)
            return 0.05;
        else
            return 0.1;
    }

    @Override
    public double contagionProbability(Person p) {
        if (p.getAge() < 18)
            return 0.2;
        if (p.getAge() <= 55)
            return 0.5;
        else
            return 0.7;
    }

    @Override
    public boolean tryToContagion(Person p, Person other) {
        if(p instanceof Sick)
        {
            double distance = Math.sqrt(Math.pow(p.getLocation().getM_x() - other.getLocation().getM_x(), 2) + Math.pow(p.getLocation().getM_y()-other.getLocation().getM_y(), 2));
            double probability = contagionProbability(other)*Math.min(1, 0.14+Math.exp(2-0.25*distance));
            int i = new Random().nextInt(100);
            return i < probability * 100;
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
}
