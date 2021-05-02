package Simulation;

public class Clock {
    private static long now;
    private static int ticks_per_day = 1;

    public static long now() {
        return now;
    }
    public static void nextTick() {
        now++;
    }
    public static long numOfDays(long n) {
        return (long)Math.ceil((double)(now - n)/ticks_per_day);
    }

    public Clock(long n) {
        now = n;
    }

    @Override
    public String toString() {
        return "Clock = " + now();
    }

    public static int getTicks_per_day() {
        return ticks_per_day;
    }
}
