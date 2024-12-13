package day13;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part2 {
    public void run(String input) {
        String [] machines = input.split("\\n\\n");
        long cost = 0;
        for (String machine: machines) {
            String [] info = machine.split("\\n");
            int [][] numbers = new int[3][2];
            for (int i = 0; i < info.length; i ++) {
                Pattern p = Pattern.compile("(\\d+)");
                Matcher m = p.matcher(info[i]);
                int k = 0;
                while (m.find()) {
                    numbers[i][k] = Integer.parseInt(m.group(1));
                    k++;
                }
            }
            /** Cramer:
             * a1x + b1y = c1z
             * a2x + b2y = c2z
             * D = a1 * b2 - a2 * b1
             * Dx = c1 * b2 - c2 * b1;
             * Dy = a1 * c2 - a2 * c1;
             * x = Dx/D
             * y = Dy/D
             */
            double d = numbers[0][0] * numbers[1][1] - numbers[0][1] * numbers[1][0];
            double dx = (numbers[2][0] + 10000000000000L) * numbers[1][1]  - (numbers[2][1] + 10000000000000L) * numbers[1][0];
            double dy = numbers[0][0] * (numbers[2][1] + 10000000000000L)  - numbers[0][1] * (numbers[2][0] + 10000000000000L);
            double x = dx / d;
            double y = dy / d;
            if( x % 1 == 0 && y % 1 == 0) {
                cost += x * 3 + y;
            }
        }
        System.out.println(cost);
    }
}
