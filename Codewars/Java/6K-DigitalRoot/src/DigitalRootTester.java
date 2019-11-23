import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class DigitalRootTester {

    @Test
    public void Tests() {
        Assertions.assertEquals(7, DigitalRoot.digital_root(16));
        Assertions.assertEquals(7, DigitalRoot.digital_root(25));
        Assertions.assertEquals(7, DigitalRoot.digital_root(34));
        Assertions.assertEquals(6, DigitalRoot.digital_root(123));
        Assertions.assertEquals(1, DigitalRoot.digital_root(1234));
    }
}