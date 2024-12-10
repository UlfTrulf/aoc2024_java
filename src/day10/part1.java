package day10;

import java.util.ArrayList;


public class part1 {
    public void run(String input) {
        int solution = 0;
        String [] lines = input.split("\\n");
        int[][] map = new int[lines.length][lines[0].length()];
        ArrayList<Point> trailhead = new ArrayList<>();
        for(int i = 0; i < lines.length; i++){
            for (int j = 0; j< lines[i].length(); j++) {
                int height = Integer.parseInt(lines[i].charAt(j) + "");
                if (height == 0) {
                    trailhead.add(new Point(j, i, 0));
                }
                map[i][j] = height;
            }
        }
        for(Point start: trailhead) {
            int tops = 0;
            ArrayList<Point> path = new ArrayList<>();
            path.add(start);
            for (int i = 0; i < path.size(); i++) {
                Point trail = path.get(i);
                if (trail.z == 9) {
                    tops ++;
                } else {
                    ArrayList<Point> neighbors = get_neighbors(trail, map);
                    for (Point n : neighbors) {
                        if (!is_in(path, n)) {
                            path.add(n);
                        }
                    }
                }
            }
            solution += tops;
        }
        System.out.println(solution);
    }

    public ArrayList<Point> get_neighbors(Point old, int[][] map){
        ArrayList<Point> neighbors = new ArrayList<>();
        int max_y = map.length;
        int max_x = map[0].length;
        if (inbounds(old.x + 1, old.y, max_x, max_y)) {
            if (height_ok(old.z, map[old.y][old.x + 1])) {
                neighbors.add(new Point(old.x + 1, old.y, map[old.y][old.x + 1]));
            }
        }
        if (inbounds(old.x - 1, old.y, max_x, max_y)) {
            if (height_ok(old.z, map[old.y][old.x - 1])) {
                neighbors.add(new Point(old.x - 1, old.y, map[old.y][old.x - 1]));
            }
        }
        if (inbounds(old.x, old.y + 1, max_x, max_y)) {
            if (height_ok(old.z, map[old.y + 1][old.x])) {
                neighbors.add(new Point(old.x, old.y + 1, map[old.y + 1][old.x]));
            }
        }
        if (inbounds(old.x, old.y - 1, max_x, max_y)) {
            if (height_ok(old.z, map[old.y - 1][old.x])) {
            neighbors.add(new Point(old.x, old.y - 1, map[old.y - 1][old.x]));
            }
        }
        return neighbors;
    }

    public boolean height_ok(int cur, int next) {
        return (cur + 1 == next);
    }

    public boolean inbounds (int x, int y, int max_x, int max_y) {
        return(x >=0 && y >=0 && x < max_x && y < max_y);
    }

    public boolean is_in (ArrayList<Point> test, Point target) {
        for (Point item: test) {
            if (target.equals(item)) {
                return true;
            }
        }
        return false;
    }

    class Point {
        int x;
        int y;
        int z;

        public Point (int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public boolean equals(Point other){
            return (this.x == other.x && this.y == other.y);
        }
    }

}
