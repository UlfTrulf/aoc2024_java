package day05;


import java.util.ArrayList;


public class part1 {

    public static void run(String input) {
        Integer solution = 0;
        String [] parts = input.split("\\n\\n");
        ArrayList <int []> rules = new ArrayList<>();
        for (String rule: parts[0].split("\\n")){
            String [] nums = rule.split("\\|");
            int [] r = {Integer.parseInt(nums[0]), Integer.parseInt(nums[1])};
            rules.add(r);
        }

        for (String set: parts[1].split("\\n")) {
            ArrayList<Integer> page = new ArrayList<>();
            boolean valid = true;
            for(String s: set.split(",")) {
                int number = Integer.parseInt(s);
                ArrayList<Integer> forbidden = findSecondValues(rules, number);
                for (int evil: forbidden) {
                    if (page.contains(evil)) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) {
                    break;
                }
                page.add(number);
            }
            if (valid) {
                solution += page.get(page.size()/2);
            }
        }
        System.out.println(solution);
    }
    public static ArrayList<Integer> findSecondValues(ArrayList<int[]> pairs, int target) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] pair : pairs) {
            if (pair[0] == target) {
                result.add(pair[1]);
            }
        }
        return result;
    }
}
