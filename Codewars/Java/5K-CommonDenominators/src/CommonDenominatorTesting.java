
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;


public class CommonDenominatorTesting {
    @Test
    public void test_fractions() throws Exception {
        long[][] lst;
        lst = new long[][] { {1, 2}, {1, 3}, {10, 40} };
        Assertions.assertEquals("(6,12)(4,12)(3,12)", CommonDenominator.convertFrac(lst));
    }
    @Test
    public void test_fractions2() throws Exception {
        long[][] lst;
        lst = new long[][] { {60, 240}, {45, 90}, {18, 72}, {34, 68}, {71, 142}, {71, 213}, {19, 38}, {55, 220} };
        Assertions.assertEquals("(3,12)(6,12)(3,12)(6,12)(6,12)(4,12)(6,12)(3,12)", CommonDenominator.convertFrac(lst));

    }
}
