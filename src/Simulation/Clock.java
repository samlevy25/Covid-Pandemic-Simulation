package Simulation;

public class Clock {
    public static long now() {
        return now;
    }
    public static void nextTick() {
        now++;
    }
    private static long now;
    public Clock(long n) {
        now = n;
    }

    @Override
    public String toString() {
        return "Clock = " + now();
    }
}
