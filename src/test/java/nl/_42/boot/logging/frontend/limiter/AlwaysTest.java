package nl._42.boot.logging.frontend.limiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlwaysTest {

    @Test
    public void obtain_shouldSucceed() {
        assertEquals(true, Always.INSTANCE.obtain());
    }

}
