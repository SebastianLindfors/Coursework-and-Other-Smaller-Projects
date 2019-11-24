import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/** This class solves the Codewars Kata: Pick Peaks
 *  https://www.codewars.com/kata/5279f6fe5ab7f447890006a7/
 *
 *  Author: Sebastian Lindfors
 */
public class PickPeaks {

    public static Map<String,List<Integer>> getPeaks(int[] arr) {

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> peak = new ArrayList<>();

        int peaksFound = 0;


        int peakPos = 1;
        int forwardPos = peakPos + 1;
        while (peakPos < arr.length - 1) {

            if (arr[peakPos] > arr[peakPos - 1]) {
                if (arr[peakPos] > arr[forwardPos]) { //Peak found!
                    pos.add(peakPos);
                    peak.add(arr[peakPos]);

                    peakPos = forwardPos;
                    forwardPos = peakPos + 1;
                    continue;
                }
                else if (arr[peakPos] == arr[forwardPos]) { //Platau found.
                    forwardPos++;
                    if (forwardPos == arr.length) {
                        break;
                    }
                }
                else {
                    peakPos = forwardPos;
                    forwardPos = peakPos + 1;
                    continue;
                }


            }
            else {
                peakPos = forwardPos;
                forwardPos = peakPos + 1;
            }
        }

        Map<String,List<Integer>> output = new HashMap<>();
        output.put("pos", pos);
        output.put("peaks", peak);


        return output;
    }
}