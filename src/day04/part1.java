package day04;


import java.util.ArrayList;


public class part1 {

    public static void run(String input) {
        Integer solution = 0;
        String [] lines = input.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length() ; j++) {
                if (lines[i].charAt(j) == 'X') {
                    ArrayList<char[]> words = get_chars(lines, j, i);
                    for (char[] word: words) {
                        if(String.valueOf(word).equals("XMAS") || String.valueOf(word).equals("SAMX")){
                            solution += 1;
                        }
                    }
                }
            }
        }
        System.out.println(solution);
    }

    private static ArrayList<char []> get_chars (String [] lines, int x, int y) {
        ArrayList <char []> words = new ArrayList<char[]>(8);

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1},
                {1, 1},
                {-1, -1},
                {-1, 1},
                {1, -1}
        };

        for (int[] dir : directions) {
            char[] word = new char[4];
            for (int k = 0; k < 4; k++) {
                int m = y + k * dir[0];
                int n = x + k * dir[1];
                if (m >= 0 && m < lines.length && n >= 0 && n < lines[m].length()) {
                    word[k] = lines[m].charAt(n);
                } else {
                    break;
                }
            }
            words.add(word);
        }
        return words;
    }
}
