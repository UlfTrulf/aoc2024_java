package day03;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part1 {

    public static void run(String input) {
        Integer solution = 0;
        Pattern p = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher m = p.matcher(input);
        while(m.find())
        {
            solution += Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2));
        }
        System.out.println(solution);
    }
}