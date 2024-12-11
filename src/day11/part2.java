package day11;

import java.util.ArrayList;
import java.util.Arrays;

public class part2 {
    public void run(String input) {
        input = input.replace("\n", "");
        String[] initial = input.split(" ");
        ArrayList<String> stones = new ArrayList<>(Arrays.asList(initial));
        int iterations = 75;
        int total_size = 0;
        ArrayList<Integer> sizes = new ArrayList<>();
        stones.parallelStream().forEach((stone) -> {
            sizes.add(get_size(iterations, stone));
        });
        for (int s: sizes) {
            total_size += s;
        }

        System.out.println(total_size);
    }


    public int get_size(int iterations, String start) {
        if (iterations == 0) {
            return 1;
        }
        ArrayList<String> new_stones = new ArrayList<>();
        if (Long.parseLong(start) == 0) {
            new_stones.add("1");
        } else if (start.length() % 2 == 0) {
            new_stones.add(String.valueOf(Long.parseLong(start.substring(0, start.length() / 2))));
            new_stones.add(String.valueOf(Long.parseLong(start.substring(start.length() / 2))));
        } else {
            new_stones.add(String.valueOf(Long.parseLong(start) * 2024));
        }
        int size = 0;
        for (String stone: new_stones) {
            size += get_size(iterations - 1, stone);
        }
        return size;
    }


}
