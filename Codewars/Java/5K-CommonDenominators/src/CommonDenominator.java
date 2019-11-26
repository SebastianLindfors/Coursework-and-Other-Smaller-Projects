import java.util.HashMap;
import java.util.Map;


public class CommonDenominator {
    // your code
    public static String convertFrac(long[][] list) {
        Map<Long, Integer> primes;
        for (int i = 0; i < list.length; i++) {
            System.out.print(i + ": ");
            primes = primeFactors(list[i][1]);
            for (long j : primes.keySet()) {
                System.out.print(j + " " + primes.get(j) + " ");
            }
            System.out.println();
        }

        return null;
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