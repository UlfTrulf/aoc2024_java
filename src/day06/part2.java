package day06;

import java.util.*;

public class part2 {

    public void run(String input) {
        String [] lines = input.split("\\n");
        int sol = 0;
        int [] pos = new int[2];
        char look = ' ';
        for (int y = 0; y < lines.length; y++){
            if (lines[y].contains("<")) {
                int index = lines[y].indexOf("<");
                pos = new int[]{index, y};
                look = lines[y].charAt(index);
                break;
            } else if (lines[y].contains(">")) {
                int index = lines[y].indexOf(">");
                pos = new int[]{index, y};
                look = lines[y].charAt(index);
                break;
            } else if (lines[y].contains("v")) {
                int index = lines[y].indexOf("v");
                pos = new int[]{index, y};
                look = lines[y].charAt(index);
                break;
            } else if (lines[y].contains("^")) {
                int index = lines[y].indexOf("^");
                pos = new int[]{index, y};
                look = lines[y].charAt(index);
                break;
            }
        }
        if (look == ' ') {
            System.out.println("nope");
            System.exit(1);
        }
        int[] start = pos.clone();
        char look_start = look;

        ArrayList<Point> corners = new ArrayList<>(); //fehlerhafte annahme, dadurch verlust an edge cases bei mehreren ecken
        ArrayList<Point> visited = new ArrayList<>();

        while (pos[0] > 0 && pos[1] > 0 && pos[1] < lines.length && pos[0] < lines[pos[1]].length()) {
            int [] dir = move(look);
            try {
                if (lines[pos[1] + dir[1]].charAt(pos[0] + dir[0]) == '#') {
                    corners.add(new Point(pos[0], pos[1], look));
                    look = turn(look);
                } else {
                    pos[1] += dir[1];
                    pos[0] += dir[0];
                }
                if (!smart_contain(visited,new Point(pos[0], pos[1], look) )) {
                    visited.add(new Point(pos[0], pos[1], look));
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        /**
        ArrayList<Point> additional_Points = expand(corners);
        ArrayList<Point> possible_blocks = new ArrayList<>();
        for(Point corner: additional_Points) {
            Point clean = new Point(corner.x + move(corner.from)[0], corner.y + move(corner.from)[1], ' ');
            if (!stupid_contain(possible_blocks, clean) && stupid_contain(visited, corner)) {
                possible_blocks.add(clean);
            }
        }//**/
        //System.out.println(visited.size());
        ArrayList<Point> solution = new ArrayList<>();
        for (Point blocker: visited) { //possible_blocks) {
            Point clean = new Point(blocker.x + move(blocker.from)[0], blocker.y + move(blocker.from)[1], ' ');
            String[] grid = replaceCharAt(lines, clean.x, clean.y);
            if (grid == null || stupid_contain(solution, clean)) {
                continue;
            }
            boolean test = check_loop(grid, start.clone(), look_start);
            if (test) {
                //System.out.println((blocker.x + move(blocker.from)[0]) + " " + (blocker.y + move(blocker.from)[1]));
                solution.add(clean);
                sol++;
            }
        }//**/
        /**
        Point corner = new Point(6, 6, 'v');
        String[] grid = replaceCharAt(lines, corner.x + move(corner.from)[0], corner.y + move(corner.from)[1]);
        for (String l : grid) {
            System.out.println(l);
        }
        System.out.println(check_loop(grid, start, look_start));
         //**/
        System.out.println(sol);
    }

    public  ArrayList<Point> expand ( ArrayList<Point> Points) {
        ArrayList<Point> additional = new ArrayList<>();
        for (int i = 0; i < Points.size(); i++) {
            for (int j = 0; j < Points.size(); j++) {
                Point a = Points.get(i);
                Point b = Points.get(j);
                char dir = ' ';
                if (a.x > b.x && a.y > b.y) {
                    dir = '>';
                } else if (b.x > a.x && a.y > b.y) {
                    dir = '^';
                } else if (a.x > b.x && a.y < b.y) {
                    dir = 'v';
                } else if (b.x > a.x && a.y < b.y) {
                    dir = '<';
                } else {
                    continue;
                }
                Point possible_Point = new Point(a.x, b.y, dir);
                if (!smart_contain(Points, possible_Point) && !smart_contain(additional, possible_Point)){
                    additional.add(possible_Point);
                }
            }
        }
        return additional;
    }

    public boolean check_loop(String[] grid, int[] pos, char look) {
        ArrayList<Point> visited = new ArrayList<>();
        while (pos[0] > 0 && pos[1] > 0 && pos[1] < grid.length && pos[0] < grid[pos[1]].length()) {
            int [] dir = move(look);
            try {
                if (grid[pos[1] + dir[1]].charAt(pos[0] + dir[0]) == '#') {
                    look = turn(look);
                    continue;
                } else {
                    pos[1] += dir[1];
                    pos[0] += dir[0];
                }
                Point next = new Point(pos[0], pos[1], look);
                if (smart_contain(visited, next)) {
                    return true;
                }
                visited.add(next);
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return false;
    }
    public static String[] replaceCharAt(String[] input, int x, int y) {
        if (y < 0 || input.length <= y || x < 0 || x >= input[y].length()) {
            return null;
        }
        String[] output = input.clone();
        StringBuilder sb = new StringBuilder(output[y]);
        sb.setCharAt(x, '#');
        output[y] = sb.toString();
        return output;
    }

    public static boolean stupid_contain(ArrayList<Point> target, Point test) {
        for (Point item: target){
            if (item.x == test.x && item.y == test.y) {
                return true;
            }
        }
        return false;
    }

    public static boolean smart_contain(ArrayList<Point> target, Point test) {
        for (Point item: target){
            if (item.x == test.x && item.y == test.y && item.from == test.from){
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

    public class Point {
        public int x;
        public int y;
        public char from;

        public Point(int x,int y,char f){
            this.x = x;
            this.y = y;
            this.from = f;
        }
    }
}
