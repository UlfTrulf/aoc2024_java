package day04;


import java.util.ArrayList;


public class part1 {

    public static void run(String input) {
        Integer solution = 0;
        String [] lines = input.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            char [] chars = lines[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == 'X') {
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
        char [] word1 = new char[4];
        for(int n = 0; n < 4; n++) {
            try {
                word1[n] = lines[y + n].toCharArray()[x];
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        words.add(word1);
        char [] word2 = new char[4];
        for(int n = 0; n < 4; n++) {
            try {
                word2[n] = lines[y - n].toCharArray()[x];
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        words.add(word2);
        char [] word3 = new char[4];
        for(int n = 0; n < 4; n++) {
            try {
                word3[n] = lines[y].toCharArray()[x + n];
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        words.add(word3);
        char [] word4 = new char[4];
        for(int n = 0; n < 4; n++) {
            try {
                word4[n] = lines[y].toCharArray()[x - n];
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        words.add(word4);
        char [] word5 = new char[4];
        for(int n = 0; n < 4; n++) {
            try {
                word5[n] = lines[y + n].toCharArray()[x + n];
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        words.add(word5);
        char [] word6 = new char[4];
        for(int n = 0; n < 4; n++) {
            try {
                word6[n] = lines[y - n].toCharArray()[x - n];
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        words.add(word6);
        char [] word7 = new char[4];
        for(int n = 0; n < 4; n++) {
            try {
                word7[n] = lines[y - n].toCharArray()[x + n];
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        words.add(word7);
        char [] word8 = new char[4];
        for(int n = 0; n < 4; n++) {
            try {
                word8[n] = lines[y + n].toCharArray()[x - n];
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        words.add(word8);
        return words;
    }
}
