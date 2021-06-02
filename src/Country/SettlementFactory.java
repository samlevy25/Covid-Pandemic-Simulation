package Country;

import Location.Location;
import Population.Healthy;
import Population.Person;

import java.util.List;
import java.util.Random;

public class SettlementFactory {
    public Settlement create(String[] arrOfData, Location l, List<Person> personList){
        Settlement newSettlement = null;
        switch (arrOfData[0]) {
            case "City":
                newSettlement = new City(arrOfData[1], l, personList, Integer.parseInt(arrOfData[6]));
                break;
            case "Kibbutz":
                newSettlement = new Kibbutz(arrOfData[1], l, personList, Integer.parseInt(arrOfData[6]));
                break;
            case "Moshav":
                newSettlement = new Moshav(arrOfData[1], l, personList, Integer.parseInt(arrOfData[6]));
                break;
        }
        for (int i = 0; i < Integer.parseInt(arrOfData[6]); i++) {
            int y = new Random().nextInt(5);
            double val = new Random().nextGaussian() * 6 + 9;
            int x = (int) Math.round(val);
            int age = 5 * x + y;
            assert newSettlement != null;
            Healthy person = new Healthy(age, newSettlement.randomLocation(), newSettlement);
            newSettlement.addPerson(person);
        }
        return newSettlement;
    }
}
