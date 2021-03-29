package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;

public class Vaccinated extends Person {
    private final long vaccinationTime;
    public Vaccinated(int a, Point p, Settlement s, long t) {
        super(a, p, s);
        vaccinationTime = t;
    }

    public long getVaccinationTime() {
        return vaccinationTime;
    }

    @Override
    public String toString() {
        return "Vaccinated{" +
                "vaccinationTime=" + vaccinationTime +
                '}' + super.toString();
    }

    @Override
    public double contagionProbability() {
        long t = Clock.now() - vaccinationTime;
        if (t < 21)
            return Math.min(1, 0.56 + 0.15*Math.sqrt(21-t));
        else
            return Math.max(0.05, 1.05/(t- 14));
    }
}
