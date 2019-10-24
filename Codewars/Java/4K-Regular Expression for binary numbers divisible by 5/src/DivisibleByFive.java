
import java.util.regex.Pattern;


public class DivisibleByFive {

    public static Pattern pattern() {

        return Pattern.compile("^(0|1(10)*(0|11)(01*01|01*00(10)*(0|11))*1)+$");
    }


}
