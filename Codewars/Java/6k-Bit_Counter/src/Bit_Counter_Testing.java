import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class Bit_Counter_Testing {

    @Test
    public void testGame() {
        assertEquals(5, Bit_Counter.countBits(1234));
        assertEquals(1, Bit_Counter.countBits(4));
        assertEquals(3, Bit_Counter.countBits(7));
        assertEquals(2, Bit_Counter.countBits(9));
        assertEquals(2, Bit_Counter.countBits(10));
        assertEquals(3, Bit_Counter.countBits(11));
    }
}



