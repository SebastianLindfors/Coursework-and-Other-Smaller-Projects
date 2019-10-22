
/** This class solves the Codewars Kata: Pyramid Slide Down
 *  https://www.codewars.com/kata/551f23362ff852e2ab000037
 *
 *  Author: Sebastian Lindfors
 */
public class PyramidSlideDown {

    public static int longestSlideDown(int[][] pyramid) {

        for (int i = pyramid.length - 2; i >= 0; i--) {
            for (int j = 0; j < pyramid[i].length; j++) {
                pyramid[i][j] = pyramid[i][j] + Math.max(pyramid[i+1][j], pyramid[i+1][j+1]);
            }
        }

//       for (int i = 0; i < pyramid.length; i++) {
//            for (int j = 0; j < pyramid[i].length; j++) {
//                System.out.print(pyramid[i][j]+ " ");
//            }
//            System.out.println("");
//        }
        return pyramid[0][0];

    }



}
