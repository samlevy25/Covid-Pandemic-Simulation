package Virus;

import Population.Person;

public interface IVirus {
    public double contagionProbability(Person p);
    public boolean tryToContagion(Person p, Person other);
    public boolean tryToKill(Person p);
}
