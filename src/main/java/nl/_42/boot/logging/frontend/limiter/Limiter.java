package nl._42.boot.logging.frontend.limiter;

/**
 * Limiters are used to limit the amount of times a function is invoked,
 * used to relief a server in a time of peak.
 */
public interface Limiter {

    /**
     * Determine if the function may proceed.
     * @return if the function may proceed
     */
    boolean obtain();

}
