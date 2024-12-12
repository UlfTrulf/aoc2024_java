package day12;

import java.util.ArrayList;


public class part1 {
    public void run(String input) {
        String [] lines = input.split("\\n");
        long solution = 0;
        ArrayList<Coord> seen = new ArrayList<>();
        for (int y = 0; y < lines.length; y ++) {
            for (int x = 0; x < lines[y].length(); x++) {
                Coord current = new Coord(x, y);
                if (coord_contain(seen, current)) {
                    continue;
                }
                long border = 0;
                ArrayList<Coord> plot = new ArrayList<>();
                plot.add(current);
                for (int i = 0; i < plot.size(); i++) {
                    Coord check = plot.get(i);
                    seen.add(check);
                    ArrayList<Coord> neighbours = get_neigbours(check, lines);
                    border += 4 - neighbours.size();
                    for (Coord n: neighbours) {
                        if (!coord_contain(plot, n)) {
                            plot.add(n);
                        }
                    }
                }
                solution += border * plot.size();
            }
        }
        System.out.println(solution);
    }

    public boolean inbounds(int x, int y, Coord dim) {
        return (x >= 0 && y >= 0 && x < dim.x && y < dim.y);
    }

    public ArrayList<Coord> get_neigbours(Coord check, String [] grid) {
        ArrayList<Coord> n = new ArrayList<>();
        Coord dim = new Coord(grid[0].length(), grid.length);
        if (inbounds(check.x - 1, check.y, dim)) {
            if (grid[check.y].charAt(check.x) ==  grid[check.y].charAt(check.x - 1)) {
                n.add(new Coord(check.x - 1, check.y));
            }
        }
        if (inbounds(check.x + 1, check.y, dim)) {
            if (grid[check.y].charAt(check.x) ==  grid[check.y].charAt(check.x + 1)) {
                n.add(new Coord(check.x + 1, check.y));
            }
        }
        if (inbounds(check.x, check.y - 1, dim)) {
            if (grid[check.y].charAt(check.x) ==  grid[check.y - 1].charAt(check.x)) {
                n.add(new Coord(check.x, check.y - 1));
            }
        }
        if (inbounds(check.x, check.y + 1, dim)) {
            if (grid[check.y].charAt(check.x) ==  grid[check.y + 1].charAt(check.x)) {
                n.add(new Coord(check.x, check.y + 1));
            }
        }
         return n;
    }

    public boolean coord_contain (ArrayList<Coord> list, Coord target) {
        for (Coord item: list) {
            if (item.x == target.x && item.y == target.y) {
                return true;
            }
        }
        return false;
    }

    class Coord {
        int x;
        int y;

        public Coord (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
