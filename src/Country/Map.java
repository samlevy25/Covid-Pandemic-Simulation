package Country;

import java.util.Arrays;

public class Map {
    private final Settlement[] settlements;
    public Map(Settlement[] s, int n) {
        settlements = new Settlement[n];
        for (int i = 0; i < n; i++) {
            settlements[i] = s[i];
        }
    }

    public Settlement[] getSettlements() {
        return settlements;
    }

    @Override
    public String toString() {
        return "Map{" + Arrays.toString(settlements) +
                '}';
    }
}
