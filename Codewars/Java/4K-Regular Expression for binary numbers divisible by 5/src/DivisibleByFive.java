
import java.util.regex.Pattern;


public class DivisibleByFive {

    public static Pattern pattern() {

        System.out.println(Integer.toBinaryString(1000));
        System.out.println(Integer.toBinaryString(1025));
        System.out.println(Integer.toBinaryString(1500));
        System.out.println(Integer.toBinaryString(5000));
        System.out.println(Integer.toBinaryString(5025));
        System.out.println(Integer.toBinaryString(6430));
        System.out.println(Integer.toBinaryString(6435));
        System.out.println(Integer.toBinaryString(7800));
        System.out.println(Integer.toBinaryString(8005));
        System.out.println(Integer.toBinaryString(10000));
        // Partial solution:
        return Pattern.compile("^0|(101(0)*)$");
    }


}
