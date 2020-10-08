package nl._42.boot.logging.frontend.limiter;

/**
 * Limiter based on the system current nano time. Grants
 * when the a second amount of nanoseconds have passed
 * since the last obtain.
 */
public class Timed implements Limiter {

    private static final long MILLIS_TO_NANO = 1000000;

    private final long nanos;

    private Long last;

    public Timed(long millis) {
        this.nanos = millis * MILLIS_TO_NANO;
    }

    @Override
    public boolean obtain() {
        long current = System.nanoTime();
        if (last == null || current > (last + nanos)) {
            last = current;
            return true;
        } else {
            return false;
        }
    }

}