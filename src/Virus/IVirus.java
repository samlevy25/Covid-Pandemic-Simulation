package Virus;

import Country.Settlement;
import Population.Person;
import Population.Sick;

public interface IVirus {
    /**
     * Give the contagion probability  of the person by his age
     * @param p : Person
     * @return Probability of the person "p"
     */
    double contagionProbability(Person p);

    /**
     * The calculation function has a probability of being contaminated by a virus depending on the type of person.
     * @param p The sick person
     * @param other Vaccinated person or Healthy  person or Convalescent person
     * @return True if the person is contaminated otherwise false
     */

    boolean tryToContagion(Sick p, Person other);

    /**
     * The function calculates the probability of a sick person dying from the virus.
     * @param p : Sick person
     * @return True if the person dies (RIP) otherwise false
     */
    boolean tryToKill(Sick p);

    /**
     * @return The virus's mutation
     */
    boolean[] getMutations();

    /**
     * Change the mutation
     * @param i : index
     * @param b : True or False
     */
    void setMutations(int i, boolean b);


    /**
     * Return a variant of the virus
     * @param settlement : The settlement
     * @return : Variant virus
     */
    IVirus getRandomVariant(Settlement settlement);
}
