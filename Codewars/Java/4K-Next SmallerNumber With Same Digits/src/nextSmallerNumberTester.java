import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class nextSmallerNumberTester {

    @Test
    public void basicTests() {
        assertEquals(12, nextSmallerNumber.nextSmaller(21));
        assertEquals(790, nextSmallerNumber.nextSmaller(907));
        assertEquals(513, nextSmallerNumber.nextSmaller(531));
        assertEquals(-1, nextSmallerNumber.nextSmaller(1027));
        assertEquals(414, nextSmallerNumber.nextSmaller(441));
        assertEquals(123456789, nextSmallerNumber.nextSmaller(123456798));
    }


}
