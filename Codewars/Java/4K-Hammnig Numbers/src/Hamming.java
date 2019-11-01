import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
/** This class solves the Codewars Kata: Hamming Numbers
 *  https://www.codewars.com/kata/526d84b98f428f14a60008da
 *
 *  Author: Sebastian Lindfors
 */
public class Hamming {

    public static long hamming(int n) {

       ArrayList<Long> hammingList = new ArrayList<>();
       hammingList.add(1L);
       HammingNode root = new HammingNode(1L, 2);

       Long lastSmallestHammingValue = 1L;

       for (int i = 1; i < n; i++) {
           hammingList.remove(0);
           HammingNode currentNode = root;
           while (lastSmallestHammingValue % 2 == 0) {
               currentNode = currentNode.TwoNode;
               lastSmallestHammingValue = lastSmallestHammingValue / 2;
           }
           while (lastSmallestHammingValue % 3 == 0) {
               currentNode = currentNode.ThreeNode;
               lastSmallestHammingValue = lastSmallestHammingValue / 3;
           }
           while (lastSmallestHammingValue % 5 == 0) {
               currentNode = currentNode.FiveNode;
               lastSmallestHammingValue = lastSmallestHammingValue / 5;
           }

           hammingList.addAll(currentNode.addChildren());
           Collections.sort(hammingList);

           lastSmallestHammingValue = hammingList.get(0);

       }
        return lastSmallestHammingValue;
    }

    private static class HammingNode {

        int type;
        long value;

        HammingNode TwoNode;
        HammingNode ThreeNode;
        HammingNode FiveNode;

        public HammingNode(Long value, int type) {
            this.value = value;
            this.type = type;

            this.TwoNode = null;
            this.ThreeNode = null;
            this.FiveNode = null;

        }

        public Collection<Long> addChildren() {

            if (this.type == 2) {
                this.TwoNode = new HammingNode(this.value * 2, 2);
                this.ThreeNode = new HammingNode(this.value * 3, 3);
                this.FiveNode = new HammingNode(this.value * 5, 5);
                return Arrays.asList(this.value * 2, this.value * 3, this.value * 5);
            }
            else if (this.type == 3) {
                this.ThreeNode = new HammingNode(this.value * 3, 3);
                this.FiveNode = new HammingNode(this.value * 5, 5);
                return Arrays.asList(this.value * 3, this.value * 5);
            }
            else {
                this.FiveNode = new HammingNode(this.value * 5, 5);
                return Arrays.asList(this.value * 5);
            }

        }




    }
}
