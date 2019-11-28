import java.util.HashMap;
import java.util.Map;

/** This class solves the Codewars Kata: Pyramid Slide Down
 *  https://www.codewars.com/kata/551f23362ff852e2ab000037
 *
 *  Warning: This is a REALLY messy solution.
 *
 *  Author: Sebastian Lindfors
 */
public class CommonDenominator {
    // your code
    public static String convertFrac(long[][] list) {

        long[][] output = list;
        for (int i = 0; i < output.length; i++) {
            for (int j = 2; j <= Math.min((int) output[i][0] ,(int) output[i][1]); j++) {
                if (output[i][0] % j == 0 && output[i][1] % j == 0) {
                    output[i][0] /= j;
                    output[i][1] /= j;
                    j--;
                }
            }
        }

        Map<Long, Integer> primes;
        Map<Long, Integer> commonPrimes = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            System.out.print(i + ": ");
            primes = primeFactors(list[i][1]);
            for (long j : primes.keySet()) {
                if (commonPrimes.containsKey(j)) {
                    if (primes.get(j) > commonPrimes.get(j)) {
                        commonPrimes.put(j, primes.get(j));

                    }
                }
                else {
                    commonPrimes.put(j, primes.get(j));
                }
                System.out.print(j + " " + primes.get(j) + " | ");
            }
            System.out.println();
        }

        int product = 1;
        for (long key : commonPrimes.keySet()) {
            product *= Math.pow(key,commonPrimes.get(key));
        }
        System.out.println("Factor: " + product);

        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < output.length; i++) {
             long factor = product/output[i][1];

             output[i][0] *= factor;
             output[i][1] *= factor;
            String fractionString = "(" + output[i][0] + "," + output[i][1] + ")";
            System.out.println(fractionString);
            outputString.append(fractionString);
        }


        return outputString.toString();
    }

    private static Map<Long, Integer> primeFactors(long n) {

        Map<Long, Integer> output = new HashMap<>();
        for(long i = 2; i < n; i++) {
            while(n % i == 0) {
                if (output.containsKey(i)) {
                    output.put(i,output.get(i) + 1);
                }
                else {
                    output.put(i,1);
                }
                n = n/i;
            }
        }
        if(n >= 2) {
            if (output.containsKey(n)) {
                output.put(n,output.get(n) + 1);
            }
            else {
                output.put(n,1);
            }
        }
        return output;
    }

}