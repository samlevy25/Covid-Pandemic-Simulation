package Virus;

import Country.Settlement;
import Population.Person;
import java.util.Random;

public class SouthAfricanVariant extends Variant {

    private static final double killUnder18 = 0.05; // Probability of dying from the virus for someone under 18
    private static final double killAbove18 = 0.08; // Probability of dying from the virus for someone above 18
    private static final double contagionUnder18 = 0.6; // Probability of being sick with the virus for a person under the age of 18
    private static final double contagionAbove18 = 0.5; //Probability of being sick with the virus for a person above the age of 18
    private final boolean[] mutations = {true, true, true};

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

        return (p.getAge() <= 18) ? contagionUnder18*p.contagionProbability() : contagionAbove18*p.contagionProbability();
    }

    @Override
    public String toString() {
        return "South-Africa Variant";
    }

    /**
     * @return The virus's mutation
     */
    @Override
    public boolean[] getMutations() {
        return mutations;
    }
    /**
     * Change the mutation
     * @param index : index
     * @param bool : True or False
     */
    @Override
    public void setMutations(int index, boolean bool) {
        this.mutations[index] = bool;
    }

    /**
     * Return a variant of the virus
     * @param map : The map
     * @return : Variant virus
     */
    @Override
    public IVirus getRandomVariant(Settlement map) {
        int i;
        do {
            i = new Random().nextInt(3);
        } while (!mutations[i]);
        return map.getVirus()[i];
    }
}
