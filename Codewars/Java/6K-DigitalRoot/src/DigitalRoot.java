
/** This class solves the Codewars Kata: Digital Roots
 *  https://www.codewars.com/kata/541c8630095125aba6000c00
 *
 *  Author: Sebastian Lindfors
 */
import java.util.ArrayList;
public class DigitalRoot {
    public static int digital_root(int n) {
        int output = n;
        while (output > 9) {
            ArrayList<Integer> digits = new ArrayList<>();
            int whole = output;
            int remainder;
            while (whole > 9) {
                remainder = whole % 10;
                whole = whole / 10;
                digits.add(remainder);
            }
            digits.add(whole);
            output = digits.stream().mapToInt(x -> x).sum();
        }

        return output;

    }
}