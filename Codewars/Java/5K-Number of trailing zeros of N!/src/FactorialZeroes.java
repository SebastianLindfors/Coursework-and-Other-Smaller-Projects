public class FactorialZeroes {
    /** This class solves the Codewars Kata: Number of trailing zeros of N!
     *  https://www.codewars.com/kata/52f787eb172a8b4ae1000a34
     *
     *  Author: Sebastian Lindfors
     */

    public static int zeros(int n) {
        int count = 0;

        for (int i = 5; n / i >= 1; i *= 5)
            count += n / i;

        return count;
    }

}
