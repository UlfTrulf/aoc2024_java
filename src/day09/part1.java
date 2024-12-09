package day09;


public class part1 {
    public void run(String input) {
        String [] map = input.split("");
        int [] numbers = new int [map.length];
        for(int i = 0;i < map.length - 1;i++) {
            numbers[i] = Integer.parseInt(map[i]);
        }
        long solution = 0;
        int position = 0;
        int front = 0;
        int low = 0;
        int back = map.length - 2;
        int high = (map.length-1) / 2;
        while (true) {
            if (front % 2 == 0) {
                if (numbers[front] > 0) {
                    numbers[front]--;
                    solution += position * low;
                    position++;
                } else {
                    front++;
                    low++;
                }
            } else {
                if (front >= back) {
                    break;
                }
                if (numbers[front] > 0 && numbers[back] > 0) {
                    numbers[front]--;
                    numbers[back]--;
                    solution += position * high;
                    position++;
                } else {
                    if (numbers[front] == 0){
                        front++;
                    }
                    if (numbers[back] == 0) {
                        high--;
                        back -= 2;
                    }
                }
            }
        }
        System.out.println(solution);
    }

}
