package Virus;

import Population.Person;

public class ChineseVariant extends Variant {

    private static final double killUnder18 = 0.001; // Probability of dying from the virus for someone under 18
    private static final double killUnder55 = 0.05; // Probability of dying from the virus for someone under 55
    private static final double killAbove55 = 0.1; //  Probability of dying from the virus for someone above 55
    private static final double contagionUnder18 = 0.2; // Probability of being sick with the virus for a person under the age of 18
    private static final double contagionUnder55 = 0.5; // Probability of being sick with the virus for a person under the age of 55
    private static final double contagionAbove55 = 0.7; // Probability of being sick with the virus for a person above the age of 55

    /**
     * Give the probability of the person by his age
     * @param p : Person
     * @return Probability of the person "p"
     */
    public double killingProbability(Person p) {
        if (p.getAge() <= 18)
            return killUnder18;
        else if (p.getAge() <= 55)
            return killUnder55;
        else
            return killAbove55;
    }
    /**
     * Give the contagion probability  of the person by his age
     * @param p : Person
     * @return Probability of the person "p"
     */
    @Override
    public double contagionProbability(Person p) {
        if (p.getAge() <= 18)
            return contagionUnder18*p.contagionProbability();
        if (p.getAge() <= 55)
            return contagionUnder55*p.contagionProbability();
        else
            return contagionAbove55*p.contagionProbability();
    }

    @Override
    public String toString() {
        return "Chinese Variant";
    }
}
