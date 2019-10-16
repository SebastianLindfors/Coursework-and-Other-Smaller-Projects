import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;
import java.util.Arrays;

public class Cure_CancerTester {
    @Test
    public void testSomething1() {
        char[][] body =  {
                "thisIsAsmallerPerson".toCharArray(),
                "thisIsAsmallerPerson".toCharArray(),
                "thisIsAsmallerPerson".toCharArray(),
                "thisssAsmallerPerson".toCharArray()
        };
        int[] answer = Cure_Cancer.mutationLocation(body);
        assertEquals(Arrays.toString(new int[]{3,4}), Arrays.toString(answer));
    }
    @Test
    public void testSomething2() {
        char[][] body =  {
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuff1thinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray(),
                "someGreaTstuffIthinkThisIs".toCharArray()
        };
        int[] answer = Cure_Cancer.mutationLocation(body);
        assertEquals(Arrays.toString(new int[]{9,14}), Arrays.toString(answer));
    }
}
