package Country;

import Population.Person;
import Population.Sick;
import Simulation.Main;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import java.util.Arrays;
import java.util.Random;

public class Map {
    private final Settlement[] settlements;
    private final IVirus[] virus;
    private final int size;
    private boolean state = false;

    public Map(Settlement[] s) {
        size = s.length;
        settlements = new Settlement[size];
        for (int i = 0; i < size; i++) {
            settlements[i] = s[i];
        }
        virus = new IVirus[]{new BritishVariant(), new ChineseVariant(), new SouthAfricanVariant()};
    }

    public Settlement[] getSettlements() {
        return settlements;
    }

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

    public IVirus[] getVirus() {
        return virus;
    }

    public boolean runningSimulation(){
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void runSimulation() {
        //step one
        for (Settlement settlement : settlements) {
            int percent = (int) (settlement.getHealthyPerson().size() * 0.2);
            for (int j = 0; j < percent; j++) {
                settlement.isSick(settlement.getHealthyPerson().get(j), Main.randomVirus(this));
            }
        }
        //step two
        IVirus virus;
        for (Settlement settlement : settlements) {
            for (int j = 0; j < settlement.getSickPerson().size(); j++) {
                int k = 0;
                while (k < 3 && settlement.getHealthyPerson().size() > 0) { // Pick 3 random healthy person and try to contagion them
                    int rand = new Random().nextInt(settlement.getHealthyPerson().size());
                    k++;
                    virus = settlement.getSickPerson().get(j).getVirus();
                    if (virus.tryToContagion(settlement.getSickPerson().get(j), settlement.getHealthyPerson().get(rand))) {
                        settlement.isSick(settlement.getHealthyPerson().get(rand), virus.getRandomVariant(this));
                    }
                }
            }
        }
        for (Settlement settlement : settlements) {
            settlement.checkConvalescents();
        }
        //step three
        Person p = null;
        for (int i = 0; i < settlements.length; i++) {
            for (int j = 0; j < settlements[i].getPeople().size()*0.03; j++)
                p = settlements[i].getPeople().get(j);
            settlements[i].transferPerson(p, settlements[Main.exclusiveRandom(size, i)]);
        }
        //step four
        for (Settlement settlement : settlements) {
            settlement.vaccinePopulation();
        }
    }
}
