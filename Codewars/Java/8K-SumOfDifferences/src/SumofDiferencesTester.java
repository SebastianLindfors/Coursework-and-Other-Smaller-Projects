import org.junit.jupiter.api.Assertions;

import org.testng.annotations.Test;

public class SumofDiferencesTester {

    @Test
    public void basicTests() {
        Assertions.assertEquals(9, SumofDifferences.sumOfDifferences(new int[]{1, 2, 10}));
        Assertions.assertEquals(2, SumofDifferences.sumOfDifferences(new int[]{-3, -2, -1}));
        Assertions.assertEquals(0, SumofDifferences.sumOfDifferences(new int[]{1, 1, 1, 1, 1}));
        Assertions.assertEquals(34, SumofDifferences.sumOfDifferences(new int[]{-17, 17}));
        Assertions.assertEquals(0, SumofDifferences.sumOfDifferences(new int[0]));
        Assertions.assertEquals(0, SumofDifferences.sumOfDifferences(new int[]{0}));
        Assertions.assertEquals(0, SumofDifferences.sumOfDifferences(new int[]{-1}));
        Assertions.assertEquals(0, SumofDifferences.sumOfDifferences(new int[]{1}));
    }
}