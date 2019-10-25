
import java.util.regex.Pattern;

/** This class solves the Codewars Kata: Regular Expression for Binary Numbers Divisible by 5
 *  https://www.codewars.com/kata/5647c3858d4acbbe550000ad
 *
 *  Author: Sebastian Lindfors
 */
public class DivisibleByFive {

    public static Pattern pattern() {

        return Pattern.compile("^(0|1(10)*(0|11)(01*01|01*00(10)*(0|11))*1)+$");
    }


}
