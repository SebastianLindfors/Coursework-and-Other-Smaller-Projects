import javax.sound.midi.Soundbank;

public class Bit_Counter {

    public static int countBits(int n) {

        String nBase2 = Integer.toBinaryString(n);

        int output = 0;
        for (char ch : nBase2.toCharArray()) {
            if (ch == '1') {
                output += 1;
            }
        }
        return output;
    }
}
