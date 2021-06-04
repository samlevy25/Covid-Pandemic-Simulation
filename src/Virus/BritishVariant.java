package Virus;

import Population.Person;

public class BritishVariant extends Variant {

    private static final double killUnder18 = 0.01; // Probability of dying from the virus for someone under 18
    private static final double killAbove18 = 0.1; // Probability of dying from the virus for someone above 18
    private static final double contagion = 0.7; // Probability of being sick with the virus

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

    @Override
    public String toString() {
        return "British Variant";
    }
}
