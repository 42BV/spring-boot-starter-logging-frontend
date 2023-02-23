package nl._42.boot.logging.frontend.limiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimedTest {

    @Test
    public void obtain_shouldSucceed() throws InterruptedException {
        Timed timed = new Timed(10);

        assertEquals(true, timed.obtain());

        Thread.sleep(100);

        assertEquals(true, timed.obtain());
    }

    @Test
    public void obtain_spike_shouldFail() {
        Timed timed = new Timed(10000);

        assertEquals(true, timed.obtain());
        assertEquals(false, timed.obtain());
    }

}
