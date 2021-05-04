package Virus;

import Country.Map;
import Population.Person;
import Population.Sick;
import Simulation.Clock;
import java.util.Random;

public class BritishVariant implements IVirus {

    private static final double killUnder18 = 0.01; // Probability of dying from the virus for someone under 18
    private static final double killAbove18 = 0.1; // Probability of dying from the virus for someone above 18
    private static final double contagion = 0.7; // Probability of being sick with the virus
    private boolean[] mutations = {true, true, true};

    /**
     * Give the probability of the person by his age
     * @param p : Person
     * @return Probability of the person "p"
     */
    public double killingProbability(Person p) {

        return (p.getAge() <= 18) ? killUnder18 : killAbove18;
    }

    /**
     * Give the contagion probability  of the person by his age
     * @param p : Person
     * @return Probability of the person "p"
     */
    @Override
    public double contagionProbability(Person p) {
        return contagion*p.contagionProbability();
    }

    /**
     * The calculation function has a probability of being contaminated by a virus depending on the type of person.
     * @param p The sick person
     * @param other Vaccinated person or Healthy  person or Convalescent person
     * @return True if the person is contaminated otherwise false
     */
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

    /**
     * The function calculates the probability of a sick person dying from the virus.
     * @param p : Sick person
     * @return True if the person dies (RIP) otherwise false
     */
    @Override
    public boolean tryToKill(Sick p) {
        long t = Clock.now() - p.getContagiousTime();
        double probability = Math.max(0, killingProbability(p) - 0.01*killingProbability(p)*Math.pow(t-15, 2));
        int i = new Random().nextInt(100);
        return i < probability * 100;
    }

    @Override
    public String toString() {
        return "British Variant";
    }

    /**
     * @return The mutation
     */
    @Override
    public boolean[] getMutations() {
        return mutations;
    }

    /**
     * Change the mutation
     * @param i : index
     * @param b : True or False
     */
    @Override
    public void setMutations(int i, boolean b) {
        if(i != 0)
            this.mutations[i] = b;
    }

    /**
     * Return a variant of the virus
     * @param map : The map
     * @return : Variant virus
     */
    @Override
    public IVirus getRandomVariant(Map map) {
        int i;
        do {
            i = new Random().nextInt(3);
        } while (!mutations[i]);
        return map.getVirus()[i];
    }
}
