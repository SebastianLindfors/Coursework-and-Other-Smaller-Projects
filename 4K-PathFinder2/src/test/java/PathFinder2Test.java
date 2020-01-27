import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathFinder2Test {


    @Test
    public void sampleTests1() {

        String a = ".W.\n"+
                ".W.\n"+
                "...";

        assertEquals(4,  PathFinder2.pathFinder(a));

    }

    @Test
    public void sampleTests2() {

        String b = ".W.\n"+
                ".W.\n"+
                "W..";

        assertEquals(-1,  PathFinder2.pathFinder(b));

    }

    @Test
    public void sampleTests3() {

        String c = "......\n"+
                "......\n"+
                "......\n"+
                "......\n"+
                "......\n"+
                "......";

        assertEquals(10,  PathFinder2.pathFinder(c));

    }

    @Test
    public void sampleTests4() {

        String d = "......\n"+
                "......\n"+
                "......\n"+
                "......\n"+
                ".....W\n"+
                "....W.";

        assertEquals(-1,  PathFinder2.pathFinder(d));

    }

}

