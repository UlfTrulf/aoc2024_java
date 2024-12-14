package day14;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part1 {
    public void run(String input) {
        Pattern p = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");
        Matcher m = p.matcher(input);
        int dim_x = 101;
        int dim_y = 103;
        int [] quads = {0,0,0,0};
        int seconds = 100;
        while (m.find()) {
            long start_x = Long.parseLong(m.group(1));
            long start_y = Long.parseLong(m.group(2));
            long vel_x = Long.parseLong(m.group(3));
            long vel_y = Long.parseLong(m.group(4));
            long end_x = (start_x + seconds * vel_x) % dim_x;
            long end_y = (start_y + seconds * vel_y) % dim_y;
            if (end_x < 0) {
                end_x += dim_x;
            }
            if (end_y < 0) {
                end_y += dim_y;
            }
            if (end_y < (dim_y - 1) / 2) {
                if (end_x < (dim_x - 1) / 2) {
                    quads[0]++;
                } else if (end_x > (dim_x - 1) / 2) {
                    quads[1]++;
                }
            } else if (end_y > (dim_y - 1) / 2) {
                if (end_x < (dim_x - 1) / 2) {
                    quads[2]++;
                } else if (end_x > (dim_x - 1) / 2) {
                    quads[3]++;
                }
            }
        }
        long result = 1;
        for (int q: quads) {
            result *= q;
        }
        System.out.println(result);
    }
}
