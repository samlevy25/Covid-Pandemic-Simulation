package Virus;

import Population.Person;

public class BritishVariant implements IVirus {
    @Override
    public double contagionProbability(Person p) {
        if (p.getAge() < 18)
            return p.contagionProbability()*0.001;
        else if (p.getAge() <= 55)
            return p.contagionProbability()*0.05;
        else
            return p.contagionProbability()*0.1;
    }
    @Override
    public boolean tryToContagion(Person p, Person other) {

        return false;
    }

    @Override
    public boolean tryToKill(Person p) {
        return false;
    }
}
