public class RailFenceCipher {

    static String encode(String s, int n) {

        char[][] rails = new char[n][s.length()];

        int counter = 1;
        int number = 0;
        for (char ch : s.toCharArray()) {

            int rail = counter <= n ? ((counter % (2*n)) - 1) : ((2*n) - (counter % (2*n)) - 1);
            rails[rail][number] = ch;

            if (counter == 2*n - 1) {
                counter = 1;
            }
            counter++;
            number++;
        }

        String encodedString = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (rails[i][j] != '\u0000') {
                encodedString += rails[i][j];
                }
            }
        }

        return encodedString;
    }

    static String decode(String s, int n) {
        // Your code here
        return null;
    }
}