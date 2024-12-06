package day06;


import java.util.ArrayList;


public class part1 {

    public static void run(String input) {
        String [] lines = input.split("\\n");
        ArrayList<int []> visited = new ArrayList<>();
        int [] pos = new int[2];
        char look = ' ';
        for (int y = 0; y < lines.length; y++){
            if (lines[y].contains("<")) {
                int index = lines[y].indexOf("<");
                pos = new int[]{index, y};
                visited.add(new int[]{index, y});
                look = lines[y].charAt(index);
                 break;
            } else if (lines[y].contains(">")) {
                int index = lines[y].indexOf(">");
                pos = new int[]{index, y};
                visited.add(new int[]{index, y});
                look = lines[y].charAt(index);
                break;
            } else if (lines[y].contains("v")) {
                int index = lines[y].indexOf("v");
                pos = new int[]{index, y};
                visited.add(new int[]{index, y});
                look = lines[y].charAt(index);
                break;
            } else if (lines[y].contains("^")) {
                int index = lines[y].indexOf("^");
                pos = new int[]{index, y};
                visited.add(new int[]{index, y});
                look = lines[y].charAt(index);
                break;
            }
        }
        if (look == ' ') {
            System.out.println("nope");
            System.exit(1);
        }
        while (pos[0] > 0 && pos[1] > 0 && pos[1] < lines.length && pos[0] < lines[pos[1]].length()) {
            int [] dir = move(look);
            try {
                if (lines[pos[1] + dir[1]].charAt(pos[0] + dir[0]) == '#') {
                    look = turn(look);
                } else {
                    pos[1] += dir[1];
                    pos[0] += dir[0];
                    if (!stupid_contain(visited, pos)) {
                        visited.add(pos.clone());
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        System.out.println(visited.size());
    }

    public static boolean stupid_contain(ArrayList<int[]> tested, int[] test) {
        for(int [] item: tested){
            if (item[0] == test [0] && item[1] == test[1]){
                return true;
            }
        }
        return false;
    }
    public static int [] move(char cur) {
        return switch (cur) {
            case '<' -> new int[]{-1, 0};
            case '^' -> new int[]{0, -1};
            case '>' -> new int[]{1, 0};
            default -> new int[]{0, 1};
        };
    }
    public static char turn(char cur) {
        return switch (cur) {
            case '<' -> '^';
            case '^' -> '>';
            case '>' -> 'v';
            case 'v' -> '<';
            default -> cur;
        };
    }
}
