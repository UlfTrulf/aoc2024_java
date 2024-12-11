package day11;


import java.util.ArrayList;
import java.util.Arrays;

public class part1 {
    public void run(String input) {
        input = input.replace("\n", "");
        String [] initial = input.split(" ");
        ArrayList<String> stones = new ArrayList<>(Arrays.asList(initial));
        int iterations = 25;
        for (int i = 0; i < iterations; i++) {
            ArrayList<String> new_stones = new ArrayList<>();
            for (String stone: stones) {
                if (Long.parseLong(stone) == 0) {
                    new_stones.add("1");
                } else if (stone.length() % 2 == 0) {
                    new_stones.add(String.valueOf(Long.parseLong(stone.substring(0, stone.length() / 2))));
                    new_stones.add(String.valueOf(Long.parseLong(stone.substring(stone.length() / 2))));
                } else {
                    new_stones.add(String.valueOf(Long.parseLong(stone) * 2024));
                }
            }
            stones = new_stones;
        }

        System.out.println(stones.size());
    }

}
