import java.util.ArrayList;

/** This class solves the Codewars Kata: Jaden Casing Strings
 *  https://www.codewars.com/kata/5390bac347d09b7da40006f6
 *
 *  Author: Sebastian Lindfors
 */
public class JadenCase {

    public String toJadenCase(String phrase) {

        if (phrase == null || phrase.stripTrailing().equals("")) {
            return null;
        }
        String[] words = phrase.split(" ");

        String jadenphrase = "";

        for (String word : words) {
            jadenphrase += word.substring(0,1).toUpperCase() + word.substring(1) + " ";
        }

        System.out.println(jadenphrase);

        return jadenphrase.stripTrailing();
    }

}