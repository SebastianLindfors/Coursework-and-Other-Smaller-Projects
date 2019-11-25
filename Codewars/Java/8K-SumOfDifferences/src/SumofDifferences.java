import java.util.Arrays;
/** This class solves the Codewars Kata: Sum of Differences.
 *  https://www.codewars.com/kata/5b73fe9fb3d9776fbf00009e
 *
 *  Author: Sebastian Lindfors
 */
public class SumofDifferences {
    public static int sumOfDifferences(int[] arr) {

        int output = 0;
        Arrays.sort(arr);

        for (int i = arr.length - 2; i >= 0; i--) {
            System.out.println(arr[i + 1] + " - " + arr[i]);
            output += (arr[i + 1] - arr[i]);
        }
        return output;
    }
}

