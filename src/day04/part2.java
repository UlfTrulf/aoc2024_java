package day04;

import java.util.ArrayList;

public class part2 {

    public static void run(String input) {
        Integer solution = 0;
        String [] lines = input.split("\\n");
        for (int i = 1; i < lines.length - 1; i++) {
            char [] chars = lines[i].toCharArray();
            for (int j = 1; j < chars.length - 1; j++) {
                if (chars[j] == 'A') {
                    ArrayList<char[]> words = get_chars(lines, j, i);
                    int count = 0;
                    for (char[] word: words) {
                        if(String.valueOf(word).equals("MAS") || String.valueOf(word).equals("SAM")){
                            count ++;
                        }
                    }
                    if (count > 1) {
                        solution++;
                    }
                }
            }
        }
        System.out.println(solution);
    }

    private static ArrayList<char []> get_chars (String [] lines, int x, int y) {
        ArrayList <char []> words = new ArrayList<char[]>(2);

        char [] word1 = new char[3];
        word1[0] = lines[y + 1].toCharArray()[x + 1];
        word1[1] = lines[y].toCharArray()[x];
        word1[2] = lines[y - 1].toCharArray()[x - 1];
        words.add(word1);

        char [] word2 = new char[3];
        word2[0] = lines[y + 1].toCharArray()[x - 1];
        word2[1] = lines[y].toCharArray()[x];
        word2[2] = lines[y - 1].toCharArray()[x + 1];
        words.add(word2);

        return words;
    }
}
