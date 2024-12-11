package day11;

import java.util.HashMap;
import java.util.Map;

public class part2 {
    public void run(String input) {
        input = input.replace("\n", "");
        String [] initial = input.split(" ");
        Map<Long, Long> stones = new HashMap<>();
        for (String stone: initial) {
            stones.put(Long.parseLong(stone), stones.getOrDefault(Long.parseLong(stone), 0L) + 1);
        }
        int iterations = 75;
        for (int i = 0; i < iterations; i++) {
            Map<Long, Long> new_stones = new HashMap<>();
            for (Map.Entry<Long, Long> entry : stones.entrySet()) {
                long stone = entry.getKey();
                long amount = entry.getValue();
                if (stone  == 0) {
                    new_stones.put(1L, new_stones.getOrDefault(1L, 0L) + amount);
                } else if (String.valueOf(stone).length() % 2 == 0) {
                    String first = String.valueOf(stone).substring(0, String.valueOf(stone).length() / 2);
                    String second = String.valueOf(stone).substring(String.valueOf(stone).length() / 2);
                    new_stones.put(Long.parseLong(first), new_stones.getOrDefault(Long.parseLong(first), 0L) + amount);
                    new_stones.put(Long.parseLong(second), new_stones.getOrDefault(Long.parseLong(second), 0L) + amount);

                } else {
                    new_stones.put((stone * 2024), new_stones.getOrDefault((stone * 2024), 0L) + amount);
                }
            }
            stones = new_stones;
        }
        long total = 0;
        for (long c: stones.values()) {
            total += c;
        }
        System.out.println(total);
    }


}
