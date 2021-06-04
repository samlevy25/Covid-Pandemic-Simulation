package Virus;
import Country.Map;

import java.util.Random;

public class VirusStrategy {
    public static boolean [][] variantsTab = new boolean[3][3];

    public static IVirus getVariant(IVirus virus) {
        int count = 0;
        for (int i = 0; i < Map.virus.length; i++) {
            if (variantsTab[indexVariant(virus)][i])
                count++;
        }
        if (count > 0) {
            int index;
            do {
                index = new Random().nextInt(Map.virus.length);
            } while (!variantsTab[indexVariant(virus)][index]);
            return Map.virus[index];
        }
        else return null;
    }

    private static int indexVariant(IVirus virus){
        int index = 0;
        for(int i = 0; i < Map.virus.length; i++){
            if(Map.virus[i] == virus)
                index = i;
        }
        return index;
    }
}
