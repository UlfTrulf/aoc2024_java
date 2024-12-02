package day02;

public class part2 {

    public static void run(String input) {
        Integer solution = 0;
        String lines[] = input.split("\\n");
        for (String line: lines) {
            String numbers [] = line.split(" ");
            Boolean safe = isSafe(numbers);
            for (int i = 0; i < numbers.length && !safe; i++){
                safe = isSafe(removeElement(numbers, i));
            }
            if (safe) {
                solution += 1;
            }
        }
        System.out.println(solution);
    }

    private static boolean isSafe (String [] numbers) {
        Boolean desc  = false;
        Boolean asc = false;
        for (int i = 0; i < numbers.length - 1; i++) {
            Integer diff = Integer.parseInt(numbers [i + 1]) - Integer.parseInt(numbers [i]);
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            } else if (diff > 0) {
                asc = true;
            } else if (diff < 0) {
                desc = true;
            }
        }
        if (asc && desc) {
            return false;
        }
        return true;
    }

    private static String[] removeElement(String[] s, int index) {
        String [] n = new String [s.length - 1];
        for (int i = 0, j = 0; i < s.length; i++) {
            if (i != index) {
                n[j++] = s[i];
                System.out.print(s[i]);
            }
        }
        System.out.println("");
        return n;
    }
}
