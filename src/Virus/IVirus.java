package Virus;

import Population.Person;
import Population.Sick;

public interface IVirus {
    double contagionProbability(Person p);
    boolean tryToContagion(Person p, Person other);
    boolean tryToKill(Sick p);
}
