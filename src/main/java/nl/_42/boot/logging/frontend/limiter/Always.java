package nl._42.boot.logging.frontend.limiter;

/**
 * Limiter that always returns true. Use this
 * when limiting traffic is not desired.
 */
public class Always implements Limiter {

    public static final Always INSTANCE = new Always();

    private Always() {
    }

    @Override
    public boolean obtain() {
        return true;
    }

}
