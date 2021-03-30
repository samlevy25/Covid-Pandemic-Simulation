package Country;

import java.util.Arrays;

public class Map {
    private Settlement[] settlements;
    public Map(Settlement[] s, int n) {
        settlements = new Settlement[n];
        for (int i = 0; i < n; i++) {
            settlements[i] = s[i];
        }
    }

    @Override
    public String toString() {
        return "Map{" +
                "settlements=" + Arrays.toString(settlements) +
                '}';
    }
}
