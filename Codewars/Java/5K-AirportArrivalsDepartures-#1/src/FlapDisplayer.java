public class FlapDisplayer {

    /** This class solves the Codewars Kata Airport Arrivals/Departures - #1
     *  https://www.codewars.com/kata/airport-arrivals-slash-departures-number-1/train/java
     *
     *  Author: Sebastian Lindfors
     */

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ?!@#&()|<>.:=-+*/0123456789";

    public static String[] flapDisplay(final String[] lines, final int[][] rotors) {

        int totalRotations = 0;
        String outString = "";
        String[] output = new String[lines.length];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                totalRotations += rotors[i][j];
                int startPos = ALPHABET.indexOf(lines[i].toCharArray()[j]);
                int numberOfSteps = totalRotations % ALPHABET.length();
                if (startPos + numberOfSteps >= ALPHABET.length()) startPos = startPos - ALPHABET.length();
                outString += ALPHABET.charAt(startPos + numberOfSteps);
            }
            output[i] = outString;
            outString = "";
            totalRotations = 0;
        }

        return output;
    }
}
