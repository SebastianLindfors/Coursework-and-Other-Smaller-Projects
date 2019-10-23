import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class CardChameleonTest {
    CardChameleon cardChameleon = new CardChameleon();

    @Test
    public void testEncryptSecretMessage() {
        String message = "ATTACK TONIGHT ON CODEWARS";
        String[] deck = new String[] {
                "2C", "6H", "5S", "7S", "JS", "8C", "7C", "2D", "3D", "8D", "3C", "KS", "QS",
                "2S", "7D", "TD", "QC", "TS", "AH", "5C", "XB", "TH", "AC", "9H", "6D", "4C",
                "7H", "3S", "5H", "KC", "3H", "6C", "4D", "8H", "KH", "8S", "JC", "5D", "TC",
                "9D", "2H", "9C", "4S", "4H", "QD", "AS", "JH", "6S", "QH", "9S", "XR", "JD",
                "AD", "KD"
        };
        String encrypted = "QNBSCTZQOLOBZNKOHUHGLQWLOK";

        assertEquals(encrypted, cardChameleon.encrypt(message, deck));
    }

    @Test
    public void testDecryptSecretMessage() {
        String encrypted = "QNBSCTZQOLOBZNKOHUHGLQWLOK";
        String[] deck = new String[] {
                "2C", "6H", "5S", "7S", "JS", "8C", "7C", "2D", "3D", "8D", "3C", "KS", "QS",
                "2S", "7D", "TD", "QC", "TS", "AH", "5C", "XB", "TH", "AC", "9H", "6D", "4C",
                "7H", "3S", "5H", "KC", "3H", "6C", "4D", "8H", "KH", "8S", "JC", "5D", "TC",
                "9D", "2H", "9C", "4S", "4H", "QD", "AS", "JH", "6S", "QH", "9S", "XR", "JD",
                "AD", "KD"
        };
        String message = "ATTACK TONIGHT ON CODEWARS";

        assertEquals(message, cardChameleon.decrypt(encrypted, deck));
    }
}