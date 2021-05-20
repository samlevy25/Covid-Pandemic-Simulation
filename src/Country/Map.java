/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Country;

import Population.Person;
import Simulation.Main;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import java.util.Arrays;
import java.util.Random;

public class Map {

    private final Settlement[] settlements;
    private final IVirus[] virus; // Array of all virus variants.
    private final int size;
    private boolean state = false;

    /**
     * Constructor
     * @param s : Array of Settlements.
     */
    public Map(Settlement[] s) {
        size = s.length;
        settlements = new Settlement[size];
        for (int i = 0; i < size; i++) {
            settlements[i] = s[i];
        }
        virus = new IVirus[]{new BritishVariant(), new ChineseVariant(), new SouthAfricanVariant()};
    }

    /**
     * @return : settlements (this)
     */
    public Settlement[] getSettlements() {
        return settlements;
    }

    /**
     *  check all settlements and verify the index of "s".
     * @param s : (String) Name of Settlement.
     * @return : settlement's index in the Array of Settlements.
     */
    public int getSettlement(String s) {
        for (int i = 0; i < size; i++) {
            if (settlements[i].getName().equals(s))
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(settlements);
    }

    /**
     * @return Array of all virus variants.
     */
    public IVirus[] getVirus() {
        return virus;
    }

    /**
     * @return True if the simulation can run otherwise else
     */
    public boolean runningSimulation(){
        return state;
    }

    /**
     * Change simulation's state.
     * @param state : Simulation's state.
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * Simulation performed on settlements
     */
    public void runSimulation() {
        //step one
        for (Settlement settlement : settlements) {
            int percent = (int) Math.floor(settlement.getSickPerson().size() * 0.2);
            IVirus virus;

            for (int j = 0; j < percent; j++) {
                int k = 0;
                while (k < 3 && settlement.getHealthyPerson().size() > 0) { // Pick 3 random healthy person and try to contagion them
                    int rand = new Random().nextInt(settlement.getHealthyPerson().size());
                    k++;
                    virus = settlement.getSickPerson().get(j).getVirus();
                    if (virus.tryToContagion(settlement.getSickPerson().get(j), settlement.getHealthyPerson().get(rand))) {
                        settlement.isSick(settlement.getHealthyPerson().get(rand), virus.getRandomVariant(settlement));
                    }
                }
            }
        }
        for (Settlement settlement : settlements) {
            settlement.checkConvalescents();
        }
        //step two
        Person p = null;
        for (int i = 0; i < settlements.length; i++) {
            for (int j = 0; j < settlements[i].getPeople().size()*0.03; j++)
                p = settlements[i].getPeople().get(j);
            settlements[i].transferPerson(p, settlements[Main.exclusiveRandom(size, i)]);
        }
        //step three
        for (Settlement settlement : settlements) {
            settlement.vaccinePopulation();
        }
    }
}
