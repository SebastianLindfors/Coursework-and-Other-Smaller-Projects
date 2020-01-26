import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    @Test
    public void FinderTest1() {

        String a = ".W.\n" +
                ".W.\n" +
                "...";

        assertTrue(Finder.pathFinder(a));
    }

    @Test
    public void FinderTest2() {

        String b = ".W.\n" +
                ".W.\n" +
                "W..";
        assertFalse(Finder.pathFinder(b));
    }

    @Test
    public void FinderTest3() {
                String c = "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        "......";
        assertTrue(Finder.pathFinder(c));
    }
    @Test
    public void FinderTest4() {

        String d = "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".....W\n" +
                "....W.";

        assertFalse(Finder.pathFinder(d));
    }








}