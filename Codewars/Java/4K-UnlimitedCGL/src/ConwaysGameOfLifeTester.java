import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Console;

public class ConwaysGameOfLifeTester{

    @Test
    public void testGlider() {
        int[][][] gliders = {
                {{1,0,0},
                        {0,1,1},
                        {1,1,0}},
                {{0,1,0},
                        {0,0,1},
                        {1,1,1}}
        };
        System.out.println("Glider");
        int[][] res = ConwaysGameOfLife.getGeneration(gliders[0], 1);
        Assertions.assertArrayEquals(gliders[1], res);
    }

}