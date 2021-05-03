package Country;

import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import java.util.Arrays;

public class Map {
    private final Settlement[] settlements;
    private final IVirus[] virus;
    public Map(Settlement[] s, int n) {
        settlements = new Settlement[n];
        for (int i = 0; i < n; i++) {
            settlements[i] = s[i];
        }
        virus = new IVirus[]{new BritishVariant(), new ChineseVariant(), new SouthAfricanVariant()};
    }

    public Settlement[] getSettlements() {
        return settlements;
    }

    public int getSettlement(String s)
    {
        for (int i = 0 ; i < settlements.length; i++)
        {
            if (settlements[i].getName().equals(s))
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return  Arrays.toString(settlements) ;
    }

    public IVirus[] getVirus() {
        return virus;
    }
}
