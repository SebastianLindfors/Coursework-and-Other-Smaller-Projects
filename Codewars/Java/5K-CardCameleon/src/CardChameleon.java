
import java.util.*;

/** This class solves the Codewars Kata: Card-Chameleon, a Cipher with Playing Cards
 *  https://www.codewars.com/kata/59c2ff946bddd2a2fd00009e/
 *
 *  Author: Sebastian Lindfors
 */
public class CardChameleon {
    /**
     * Takes a String containing a message and an array of Strings representing a deck
     * of playing cards, and returns a String containing the text encrypted, or null if
     * the message, or the deck, is invalid.
     */
    public String encrypt(String message, String[] deck) {

        final Map<String, Character> BLACK_CIPHER = new HashMap<String, Character>() {{
           put("AC", 'A'); put("2C", 'B'); put("3C", 'C');
           put("4C", 'D'); put("5C", 'E'); put("6C", 'F'); put("7C", 'G'); put("8C", 'H'); put("9C", 'I');
           put("TC", 'J'); put("JC", 'K'); put("QC", 'L'); put("KC", 'M'); put("AS", 'N'); put("2S", 'O');
           put("3S", 'P'); put("4S", 'Q'); put("5S", 'R'); put("6S", 'S'); put("7S", 'T'); put("8S", 'U');
           put("9S", 'V'); put("TS", 'W'); put("JS", 'X'); put("QS", 'Y'); put("KS", 'Z'); put("XB", ' ');
        }};
        final Map<String, Character> RED_CIPHER = new HashMap<String, Character>() {{
            put("AD", 'A'); put("2D", 'B'); put("3D", 'C');
            put("4D", 'D'); put("5D", 'E'); put("6D", 'F'); put("7D", 'G'); put("8D", 'H'); put("9D", 'I');
            put("TD", 'J'); put("JD", 'K'); put("QD", 'L'); put("KD", 'M'); put("AH", 'N'); put("2H", 'O');
            put("3H", 'P'); put("4H", 'Q'); put("5H", 'R'); put("6H", 'S'); put("7H", 'T'); put("8H", 'U');
            put("9H", 'V'); put("TH", 'W'); put("JH", 'X'); put("QH", 'Y'); put("KH", 'Z'); put("XR", ' ');
        }};

        Map<Character, String> REVERSE_RED_CIPHER = new HashMap<Character, String>();
        for (String key : RED_CIPHER.keySet()) {
            REVERSE_RED_CIPHER.put(RED_CIPHER.get(key), key);
        }
        Map<Character, String> REVERSE_BLACK_CIPHER = new HashMap<Character, String>();
        for (String key : BLACK_CIPHER.keySet()) {
            REVERSE_BLACK_CIPHER.put(BLACK_CIPHER.get(key), key);
        }

        for (char ch : message.toCharArray()) {
            if (!REVERSE_BLACK_CIPHER.containsKey(ch)) {
                return null; //Test for invalid characters in the message.
            }
        }

       String[] cipherDeck = createPairedDeck(deck);
        if (cipherDeck == null) {
            return null;
        }

        StringBuilder cipherText = new StringBuilder();
        for (Character ch : message.toCharArray()) {

            String firstRedCard = "";
            String firstBlackCard = REVERSE_BLACK_CIPHER.get(ch);
            for (int i = 1; i < cipherDeck.length; i = i + 2) {
                if (cipherDeck[i].equals(firstBlackCard)) {
                    firstRedCard = cipherDeck[i-1];
                    break;
                }
            }
            String secondBlackCard = REVERSE_BLACK_CIPHER.get(RED_CIPHER.get(firstRedCard));
            String secondRedCard = "";
            int exchangeIndex = -1;
            boolean found = false;
            for (int i = 1; i < cipherDeck.length; i = i + 2) {
                if (cipherDeck[i].equals(secondBlackCard)) {
                    secondRedCard = cipherDeck[i-1];
                    exchangeIndex = i - 1;
                    found = true;
                    break;
                }
            }

            cipherText.append(RED_CIPHER.get(secondRedCard));

            putTopPairLast(cipherDeck, exchangeIndex);

        }
        return cipherText.toString();
    }

    /**
     * Takes a String containing an encrypted message and an array of Strings
     * representing a deck of playing cards, and returns a String containing the
     * message decrypted, or null if the text, or the deck, is invalid.
     */
    public String decrypt(String encrypted, String[] deck) {

        final Map<String, Character> BLACK_CIPHER = new HashMap<String, Character>() {{
            put("AC", 'A'); put("2C", 'B'); put("3C", 'C');
            put("4C", 'D'); put("5C", 'E'); put("6C", 'F'); put("7C", 'G'); put("8C", 'H'); put("9C", 'I');
            put("TC", 'J'); put("JC", 'K'); put("QC", 'L'); put("KC", 'M'); put("AS", 'N'); put("2S", 'O');
            put("3S", 'P'); put("4S", 'Q'); put("5S", 'R'); put("6S", 'S'); put("7S", 'T'); put("8S", 'U');
            put("9S", 'V'); put("TS", 'W'); put("JS", 'X'); put("QS", 'Y'); put("KS", 'Z'); put("XB", ' ');
        }};
        final Map<String, Character> RED_CIPHER = new HashMap<String, Character>() {{
            put("AD", 'A'); put("2D", 'B'); put("3D", 'C');
            put("4D", 'D'); put("5D", 'E'); put("6D", 'F'); put("7D", 'G'); put("8D", 'H'); put("9D", 'I');
            put("TD", 'J'); put("JD", 'K'); put("QD", 'L'); put("KD", 'M'); put("AH", 'N'); put("2H", 'O');
            put("3H", 'P'); put("4H", 'Q'); put("5H", 'R'); put("6H", 'S'); put("7H", 'T'); put("8H", 'U');
            put("9H", 'V'); put("TH", 'W'); put("JH", 'X'); put("QH", 'Y'); put("KH", 'Z'); put("XR", ' ');
        }};

        Map<Character, String> REVERSE_RED_CIPHER = new HashMap<Character, String>();
        for (String key : RED_CIPHER.keySet()) {
            REVERSE_RED_CIPHER.put(RED_CIPHER.get(key), key);
        }
        Map<Character, String> REVERSE_BLACK_CIPHER = new HashMap<Character, String>();
        for (String key : BLACK_CIPHER.keySet()) {
            REVERSE_BLACK_CIPHER.put(BLACK_CIPHER.get(key), key);
       }

        for (char ch : encrypted.toCharArray()) {
            if (!REVERSE_BLACK_CIPHER.containsKey(ch)) {
                return null; //Test for invalid characters in the message.
            }
        }

        String[] cipherDeck = createPairedDeck(deck);
        if (cipherDeck == null) {
            return null;
        }

        StringBuilder decryptedText = new StringBuilder();
        for (Character ch : encrypted.toCharArray()) {

            String firstRedCard = REVERSE_RED_CIPHER.get(ch);
            String firstBlackCard ="";
            int exchangeIndex = -1;
            for (int i = 0; i < cipherDeck.length; i = i + 2) {
                if (cipherDeck[i].equals(firstRedCard)) {
                    firstBlackCard = cipherDeck[i+1];
                    exchangeIndex = i;
                    break;
                }
            }
            String secondBlackCard = "";
            String secondRedCard = REVERSE_RED_CIPHER.get(BLACK_CIPHER.get(firstBlackCard));;
            for (int i = 0; i < cipherDeck.length; i = i + 2) {
                if (cipherDeck[i].equals(secondRedCard)) {
                    secondBlackCard = cipherDeck[i+1];
                    break;
                }
            }

            decryptedText.append(BLACK_CIPHER.get(secondBlackCard));

            putTopPairLast(cipherDeck, exchangeIndex);
        }

        return decryptedText.toString();
    }

    private String[] createPairedDeck(String[] deck) {
        String[] allCards = {"AC" ,"2C" ,"3C" ,"4C" ,"5C" ,"6C" ,"7C" ,"8C" ,"9C" ,"TC" ,"JC" ,"QC" ,"KC"
                ,"AD" ,"2D" ,"3D" ,"4D" ,"5D" ,"6D" ,"7D" ,"8D" ,"9D" ,"TD" ,"JD" ,"QD" ,"KD"
                ,"AH" ,"2H" ,"3H" ,"4H" ,"5H" ,"6H" ,"7H" ,"8H" ,"9H" ,"TH" ,"JH" ,"QH" ,"KH"
                ,"AS" ,"2S" ,"3S" ,"4S" ,"5S" ,"6S" ,"7S" ,"8S" ,"9S" ,"TS" ,"JS" ,"QS" ,"KS"
                ,"XB", "XR"};

        ArrayList<String> cardList = new ArrayList<String>();
        for (String card : allCards) {
            cardList.add(card);
        }

        ArrayList<String> blackCards = new ArrayList<>();
        ArrayList<String> redCards = new ArrayList<>();

        for (String card : deck) {
            if (cardList.contains(card)) {
                cardList.remove(card);
                char suit = card.charAt(1);
                if (suit == 'C' || suit == 'S' || suit == 'B') {
                    blackCards.add(card);
                }
                else if (suit == 'H' || suit == 'D' || suit == 'R') {
                    redCards.add(card);
                }
                else {
                    System.out.println("\nInvalid card found: " + card);
                    return null; //Invalid deck
                }
            }
            else {
                System.out.println("\nDuplicate card found: " + card);
                return null; //Invalid deck
            }
        }
        if (!cardList.isEmpty()) {return null;} //Invalid deck

        String[] cipherDeck = new String[54];
        for (int i = 0; i < 27; i++) {
            cipherDeck[i*2+1] = blackCards.get(0);
            blackCards.remove(0);
            cipherDeck[i*2] = redCards.get(0);
            redCards.remove(0);
        }

        return cipherDeck;

    }

    private void putTopPairLast(String[] cipherDeck, int exchangeIndex) {

            String temp0 = cipherDeck[exchangeIndex];
            String temp1 = cipherDeck[1];
            cipherDeck[exchangeIndex] = cipherDeck[0];

            for (int i = 0; i < cipherDeck.length - 3; i = i + 2) {
                cipherDeck[i] = cipherDeck[i+2];
                cipherDeck[i+1] = cipherDeck[i + 3];
            }
            cipherDeck[cipherDeck.length - 2] = temp0;
            cipherDeck[cipherDeck.length - 1] = temp1;



    }



}