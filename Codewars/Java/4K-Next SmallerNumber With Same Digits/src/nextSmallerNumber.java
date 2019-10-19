
/** This class solves the Codewars Kata Next smaller number with the same digits.
 *  https://www.codewars.com/kata/5659c6d896bc135c4c00021e
 *
 *  Author: Sebastian Lindfors
 */
public class nextSmallerNumber {

    public static long nextSmaller(long n) {
        String number = String.valueOf(n);

        for (int i = number.length() - 1; i > 0; i--) {
            int j = i - 1;
            if (number.charAt(j) > number.charAt(i)) {
                System.out.println(number.charAt(j) + " " + number.charAt(i));
                int swapIndex = i;
                for (int k = i; k < number.length(); k++) {
                    if (number.charAt(k) < number.charAt(j) && number.charAt(k) > number.charAt(swapIndex)) swapIndex = k;
                }
                char[] digitArray = number.toCharArray();
                char temp = digitArray[j];
                digitArray[j] = digitArray[swapIndex];
                digitArray[swapIndex] = temp;

                if (digitArray[0] == '0') return -1;

                String newNumber = new String(digitArray);
                String suffix = findBiggestNumber(newNumber.substring(j+1));
                return Long.parseLong(newNumber.substring(0,j+1) + suffix);

            }
        }
        return -1;
    }

    private static String findBiggestNumber(String number) {

        if (number.length() == 1) return number;

        char[] ch = number.toCharArray();

        for (int i = 1; i < ch.length; i++) {
            char key = ch[i];
            int j = i - 1;

            while (j >= 0 && ch[j] < key) {
                ch[j + 1] = ch[j];
                j = j - 1;
            }
            ch[j + 1] = key;
        }

    return new String(ch);
    }


}


