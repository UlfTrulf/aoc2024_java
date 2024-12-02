package day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class part1 {
    public static void run(String input) {
        Integer solution = 0;
        String lines[] = input.split("\\n");
        for (String line: lines) {
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(line);
            Boolean inc = false;
            Boolean dec = false;
            m.find();
            Integer last = Integer.parseInt(m.group(1));
            while (m.find()) {
                Integer current = Integer.parseInt(m.group(1));
                Integer dif = last - current;
                if (dif > 0) {
                    dec = true;
                } else if (dif < 0) {
                    inc = true;
                }
                if (Math.abs(dif) < 1 || Math.abs(dif) > 3) {
                    dec = true;
                    inc = true;
                    break;
                }
                last = current;
            }
            if (inc ^ dec) {
                solution += 1;
            }

        }
        System.out.println(solution);
    }
}
