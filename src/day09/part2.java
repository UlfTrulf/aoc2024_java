package day09;

import java.util.ArrayList;

public class part2 {
    public void run(String input) {
        String [] map = input.split("");
        int [] numbers = new int [map.length - 1];
        for(int i = 0;i < map.length - 1;i++) {
            numbers[i] = Integer.parseInt(map[i]);
        }
        ArrayList<Integer> moved = new ArrayList<>();
        long solution = 0;
        int position = 0;
        int front = 0;
        while (front < numbers.length) {
            if (front % 2 == 0) {
                if (numbers[front] > 0) {
                    numbers[front]--;
                    if (!moved.contains(front)) {
                        solution += position * (front / 2);
                    }
                    position++;
                } else {
                    front++;
                }
            } else {
                if (numbers[front] == 0) {
                    front++;
                } else {
                    int back = numbers.length - 1;
                    while (numbers[front] > 0) {
                        if (back <= front) {
                            numbers[front]--;
                            position++;
                        }
                        if (numbers[front] >= numbers[back] && !moved.contains(back)) {
                            int temp = numbers[back];
                            while (temp > 0) {
                                numbers[front]--;
                                solution += position * (back / 2);
                                position++;
                                temp--;
                            }
                            moved.add(back);
                        } else {
                            back -= 2;
                        }
                    }
                }
            }
        }
        System.out.println(solution);
    }

}
