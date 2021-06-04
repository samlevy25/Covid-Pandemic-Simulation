/*
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Simulation;

public class Clock {
    private static long now; // Represents the current time in the simulation
    private static long ticks_per_day = 1;
    private static long snooze = 1000; // the time of the thread to sleep

    /**
     * Constructor
     * @param n : Represents the current time in the simulation
     */
    public Clock(long n) {
        now = n;
    }

    /**
     * @return The current time in the simulation.
     */
    public static long now() {
        return now;
    }

    /**
     * Increases the current time in the simulation by one and give to the thread the order to sleep
     */
    public static void nextTick() throws InterruptedException {
        now++;
        Thread.sleep(snooze);
    }

    /**
     * @param n The current time in the simulation.
     * @return number of day since the start of the simulation.
     */
    public static long numOfDays(long n)
    {
        return (long)Math.ceil((double)(now - n)/ticks_per_day);
    }


    @Override
    public String toString() {
        return "Clock = " + now();
    }

    /**
     * @return the Ticks per day.
     */
    public static long getTicks_per_day() {
        return ticks_per_day;
    }

    /**
     * Change the Ticks per day.
     * @param newTicks_per_day The new Ticks per day.
     */
    public static void setTicks_per_day(long newTicks_per_day) {ticks_per_day = newTicks_per_day;}

    /**
     * Change the time of the thread to sleep
     * @param snooze The new time
     */
    public static void setSnooze(int snooze) {
        Clock.snooze = snooze;
    }
}
