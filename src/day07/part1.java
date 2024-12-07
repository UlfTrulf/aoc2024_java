package day07;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class part1 {
    public void run(String input) {
        long solution = 0;
        String [] lines = input.split("\\n");
        for (String line: lines) {
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(line);
            ArrayList<Long> numbers = new ArrayList<>();
            while (m.find()) {
                numbers.add(Long.parseLong(m.group(1)));
            }
            long target = numbers.remove(0);
            for (int i = 0; i < numbers.size(); i++) {
                if (calculate(numbers, target, i)) {
                    solution += target;
                    break;
                }
            }
        }
        System.out.println(solution);
    }

    public boolean calculate(ArrayList<Long> values, long target, int mults) {
        if (values.size() == 1) {
            return values.get(0) == target;
        }
        ArrayList<Long> values_old= new ArrayList<>(values);
        long a = values_old.remove(0);
        long b = values_old.remove(0);
        boolean answer = false;
        if (!(mults == values.size() - 1)) {
            ArrayList<Long> values_added = new ArrayList<>(values_old);
            values_added.add(0, a + b);
            answer = calculate(values_added, target, mults);
        } if (!(mults == 0)) {
            values_old.add(0, a * b);
            answer = answer || calculate(values_old, target, mults - 1);
        }

        return answer;
    }
}
