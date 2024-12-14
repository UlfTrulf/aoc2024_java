package day14;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part2 {
    public void run(String input) {
        Pattern p = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");
        Matcher m = p.matcher(input);
        int dim_x = 101;
        int dim_y = 103;
        ArrayList<int []> bots = new ArrayList<>();
        int seconds = 10403;
        while (m.find()) {
            int [] bot = new int [4];
            bot[0] = Integer.parseInt(m.group(1));
            bot[1] = Integer.parseInt(m.group(2));
            bot[2] = Integer.parseInt(m.group(3));
            bot[3] = Integer.parseInt(m.group(4));
            bots.add(bot);

        }
        for (int i = 1; i < seconds; i++){
            ArrayList<int[]> bots_next = new ArrayList<>();
            for (int[] bot: bots) {
                bots_next.add(move(bot, dim_x, dim_y));
            }
            bots = bots_next;

            if (is_cluster(bots)) {
                System.out.println(i);
                break;
            }
        }
    }

    private int[] move (int[] bot, int dim_x, int dim_y) {
        int [] bot_next = new int[4];
        bot_next[0] = bot[0] + bot[2] % dim_x;
        if (bot_next[0] < 0) {
            bot_next[0] += dim_x;
        }
        bot_next[1] = bot[1] + bot[3] % dim_y;
        if (bot_next[1] < 0) {
            bot_next[1] += dim_y;
        }
        bot_next[2] = bot[2];
        bot_next[3] = bot[3];
        return bot_next;
    }
    private boolean is_in(ArrayList<int[]> l, int x, int y) {
        for (int[] t: l) {
            if (t[0] == x && t[1] == y) {
                return true;
            }
        }
        return false;
    }
    private boolean is_cluster(ArrayList<int[]> l) {
        int count = 0;
        for (int[] t: l) {
            if (is_in(l, t[0] - 3, t[1]) || is_in(l, t[0] + 3, t[1])) {
                count++;
            }
            if (is_in(l, t[0] - 2, t[1]) || is_in(l, t[0] + 2, t[1])) {
                count++;
            }
            if (is_in(l, t[0] - 1, t[1]) || is_in(l, t[0] + 1, t[1])) {
                count++;
            }
            if (is_in(l, t[0], t[1] - 1) || is_in(l, t[0], t[1] + 1)) {
                count++;
            }
            if (is_in(l, t[0], t[1] - 2) || is_in(l, t[0], t[1] + 2)) {
                count++;
            }
            if (is_in(l, t[0], t[1] - 1) || is_in(l, t[0], t[1] + 1)) {
                count++;
            }
        }
        //Wert wurde erfolgreich "erraten" bis noch eine Möglichkeit übrig
        return count > 150;
    }
}
