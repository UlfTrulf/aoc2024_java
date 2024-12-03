package day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part2 {

    public static void run(String input) {
        Integer solution = 0;
        Pattern p = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");
        Matcher m = p.matcher(input);
        Boolean enabled = true;
        while (m.find()) {
            if (m.group(0).equals("do()")) {
                enabled = true;
            } else if (m.group(0).equals("don't()")) {
                enabled = false;
            } else if (enabled) {
                solution += Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2));
            }
        }
        System.out.println(solution);
    }
}
