
/** This class solves the Codewars Kata: 4K-Magnet Particules In Boxes (sic)
 *  https://www.codewars.com/kata/56c04261c3fcf33f2d000534
 *
 *  Author: Sebastian Lindfors
 */
class Magnets {

    public static double doubles(int maxk, int maxn) {
        return s(maxk, maxn);
    }

    private static double v(int k,int n) {

        double output = Math.pow((k * Math.pow(n+1,2*k)),-1);
        return output;
    }

    private static double u(int k, int n) {
        double output = 0;
        for (int j = 1; j < n + 1; j++) {
            output += v(k,j);
        }
        return output;
    }

    private static double s(int k, int n) {
        double output = 0;
        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                output += v(i,j);

            }
        }
        return output;
    }

}