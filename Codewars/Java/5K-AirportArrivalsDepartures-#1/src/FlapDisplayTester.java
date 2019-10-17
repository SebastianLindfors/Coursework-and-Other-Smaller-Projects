import org.junit.Test;
import static org.junit.Assert.*;

public class FlapDisplayTester {


        @Test
        public void example() {
            // CAT => DOG
            String[] before = new String[]{"CAT"};
            int[][] rotors = new int[][]{{1,13,27}};
            String[] after = FlapDisplayer.flapDisplay(before,rotors);
            String[] expected = new String[]{"DOG"};
            assertArrayEquals(expected, after);
        }

        @Test
        public void basic() {
            // HELLO => WORLD!
            String[] before = new String[]{"HELLO "};
            int[][] rotors = new int[][]{{15,49,50,48,43,13}};
            String[] after = FlapDisplayer.flapDisplay(before,rotors);
            String[] expected = new String[]{"WORLD!"};
            assertArrayEquals(expected, after);

            // CODE => WARS
            String[] before2 = new String[]{"CODE"};
            int[][] rotors2 = new int[][]{{20,20,28,0}};
            String[] after2 = FlapDisplayer.flapDisplay(before2,rotors2);
            String[] expected2 = new String[]{"WARS"};
            assertArrayEquals(expected2, after2);
        }

}


