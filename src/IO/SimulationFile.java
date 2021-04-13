package IO;

import Country.*;
import Location.*;
import Population.Healthy;
import Population.Person;

import java.io.*;
import java.util.*;

public class SimulationFile {
    String nameOfFile;
    public SimulationFile(String n) {
        nameOfFile = n;
    }
    public List<Settlement> readFromFile() throws IOException {
        List<Settlement> settlementsList = new ArrayList<>();
        FileReader myFile = new FileReader(nameOfFile);
        BufferedReader myReader = new BufferedReader(myFile);
        String data;
        while ((data = myReader.readLine()) != null) {
            try {
                String[] arrOfData = data.split(";", 0);
                Point p = new Point(Integer.parseInt(arrOfData[2]), Integer.parseInt(arrOfData[3]));
                Size s = new Size(Integer.parseInt(arrOfData[4]), Integer.parseInt(arrOfData[5]));
                Location l = new Location(p, s);
                List<Person> personList = new ArrayList<>();
                Random rand = new Random();
                switch (arrOfData[0]) {
                    case "City":
                        City myCity = new City(arrOfData[1], l, personList);
                        for (int i = 0; i < Integer.parseInt(arrOfData[6]); i++) {
                            int y = rand.nextInt(5);
                            double val = rand.nextGaussian() * 6 + 9;
                            int x = (int) Math.round(val);
                            int age = 5 * x + y;
                            Healthy person = new Healthy(age, myCity.randomLocation(), myCity);
                            myCity.addPerson(person);
                        }
                        settlementsList.add(myCity);
                        break;
                    case "Kibbutz":
                        Kibbutz myKibbutz = new Kibbutz(arrOfData[1], l, personList);
                        for (int i = 0; i < Integer.parseInt(arrOfData[6]); i++) {
                            int y = rand.nextInt(5);
                            double val = rand.nextGaussian() * 6 + 9;
                            int x = (int) Math.round(val);
                            int age = 5 * x + y;
                            Healthy person = new Healthy(age, myKibbutz.randomLocation(), myKibbutz);
                            myKibbutz.addPerson(person);
                        }
                        settlementsList.add(myKibbutz);
                        break;
                    case "Moshav":
                        Moshav myMoshav = new Moshav(arrOfData[1], l, personList);
                        for (int i = 0; i < Integer.parseInt(arrOfData[6]); i++) {
                            int y = rand.nextInt(5);
                            double val = rand.nextGaussian() * 6 + 9;
                            int x = (int) Math.round(val);
                            int age = 5 * x + y;
                            Healthy person = new Healthy(age, myMoshav.randomLocation(), myMoshav);
                            myMoshav.addPerson(person);
                        }
                        settlementsList.add(myMoshav);
                        break;
                }

            } catch (RuntimeException e) {
                System.out.println("File not found");
                System.exit(-1);
            }
        }
        myReader.close();
        myFile.close();
        return settlementsList;
    }

    @Override
    public String toString() {
        return "SimulationFile{" + nameOfFile + '}';
    }
}
