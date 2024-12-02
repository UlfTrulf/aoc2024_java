package day01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part1 {

    public static void run(String input) {
        Integer solution = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(input);
        boolean left_side = true;
        while(m.find())
        {
            if (left_side) {
                left.add(Integer.parseInt(m.group(1)));
            } else {
                right.add(Integer.parseInt(m.group(1)));
            }
            left_side = !left_side;
        }
        Collections.sort(left);
        Collections.sort(right);
        while (left.size() > 0) {
            solution += Math.abs(left.remove(0) - right.remove(0));
        }

        System.out.println(solution);
    }
}
