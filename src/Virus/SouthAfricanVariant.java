package Virus;

import Population.Person;

public class SouthAfricanVariant extends Variant {

    private static final double killUnder18 = 0.05; // Probability of dying from the virus for someone under 18
    private static final double killAbove18 = 0.08; // Probability of dying from the virus for someone above 18
    private static final double contagionUnder18 = 0.6; // Probability of being sick with the virus for a person under the age of 18
    private static final double contagionAbove18 = 0.5; //Probability of being sick with the virus for a person above the age of 18

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
}
