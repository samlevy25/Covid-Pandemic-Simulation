package Virus;

import Country.Map;
import Population.Person;
import Population.Sick;

public interface IVirus {
    double contagionProbability(Person p);
    boolean tryToContagion(Sick p, Person other);
    boolean tryToKill(Sick p);
    boolean[] getMutations();
    void setMutations(int i, boolean b);
    IVirus getRandomVariant(Map map);
}
