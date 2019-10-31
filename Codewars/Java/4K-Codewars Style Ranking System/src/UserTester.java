import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class UserTester {

    @Test
    public void userClassTest() {

        User u1 = new User();

        //Test that constructor works as intended.
        assertEquals(-8, u1.rank);
        assertEquals(0, u1.progress);


        u1.incProgress(-7); // Should add 10 progress

        //Test that 10 progress was added, rank should be unaffected.
        assertEquals(-8, u1.rank);
        assertEquals(10, u1.progress);

        u1.incProgress(-5);

        //Test that 90 progress was added, rank should be upgraded and progress should be 0.
        assertEquals(-7, u1.rank);
        assertEquals(0, u1.progress);

    }


}
