
/** This class solves the Codewars Kata: Range Extraction
 *  https://www.codewars.com/kata/51ba717bb08c1cd60f00002f
 *
 *  Author: Sebastian Lindfors
 */
public class RangeExtraction {


    public static String rangeExtraction(int[] arr) {

        String output  = "";
        int currentRangeMin = Integer.MAX_VALUE;
        int currentRangeMax = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1] + 1) {
                if (currentRangeMin == Integer.MAX_VALUE) {
                    currentRangeMin = arr[i-1];
                    currentRangeMax = arr[i];
                }
                else {
                    currentRangeMax = arr[i];
                }
            }
            else {
                if (currentRangeMin == Integer.MAX_VALUE) {
                    output += "," + arr[i-1];
                }
                else {
                    if (currentRangeMax - currentRangeMin > 1) {
                        output += "," + currentRangeMin + "-" + currentRangeMax;
                        currentRangeMin = Integer.MAX_VALUE;
                    }
                    else {
                        output += "," +currentRangeMin + "," + currentRangeMax;
                        currentRangeMin = Integer.MAX_VALUE;
                    }
                }
            }
        }
        if (arr[arr.length - 1] == arr[arr.length - 2] + 1) {
            if (currentRangeMax - currentRangeMin > 1) {
                output += "," + currentRangeMin + "-" + currentRangeMax;
            }
            else {
                output += "," +currentRangeMin + "," + currentRangeMax;
            }
        }
        else {
            output += "," + arr[arr.length - 1];
        }



        return output.substring(1);
    }

}
