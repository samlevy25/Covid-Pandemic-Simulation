package Simulation;

public class Clock {
    private static long now;

    public static long now() {
        nextTick();
        return now;
    }
    public static void nextTick() {
        now++;
    }

    public Clock(long n) {
        now = n;
    }

    @Override
    public String toString() {
        return "Clock = " + now();
    }
}
