
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;


public class CommonDenominatorTesting {
    @Test
    public void test_fractions() throws Exception {
        long[][] lst;
        lst = new long[][] { {1, 2}, {1, 3}, {10, 40} };
        Assertions.assertEquals("(6,12)(4,12)(3,12)", CommonDenominator.convertFrac(lst));
    }
}
