package Country;

public class Map {
    private Settlement[] settlements;
    Map(Settlement[] s, int n) {
        settlements = new Settlement[n];
        for (int i = 0; i < n; i++) {
            settlements[i] = s[i];
        }
    }
}
