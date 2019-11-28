import java.util.Arrays;
import java.util.stream.IntStream;

public class SumOfParts {

    public static int[] sumParts(int[] ls) {

        int[] outputArray = new int[ls.length + 1];

        IntStream sumStream = Arrays.stream(ls);
        int totalSum = sumStream.sum();
        outputArray[0] = totalSum;
        for (int i = 1; i < outputArray.length; i++) {
           outputArray[i] = totalSum - ls[i-1];
           totalSum -= ls[i-1];
        }

        return outputArray;

    }
}
