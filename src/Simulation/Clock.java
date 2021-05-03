package Simulation;

public class Clock {
    private static long now;
    private static long ticks_per_day = 1;
    private static long snooze = 1000;

    public static long now() {
        return now;
    }
    public static void nextTick() throws InterruptedException {
        now++;
        Thread.sleep(snooze);
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

    public static long getTicks_per_day() {
        return ticks_per_day;
    }

    public static void setTicks_per_day(long newTicks_per_day) {ticks_per_day = newTicks_per_day;}

    public static void setSnooze(int snooze) {
        Clock.snooze = snooze;
    }
}
