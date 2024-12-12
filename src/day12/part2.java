package day12;

import java.util.ArrayList;



public class part2 {
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
                ArrayList<Coord> plot = new ArrayList<>();
                plot.add(current);
                for (int i = 0; i < plot.size(); i++) {
                    Coord check = plot.get(i);
                    seen.add(check);
                    ArrayList<Coord> neighbours  = get_neigbours(check, lines);
                    for (Coord n: neighbours) {
                        if (!coord_contain(plot, n)) {
                            plot.add(n);
                        }
                    }
                }
                int sides = 0;
                for (int i = 0; i < plot.size(); i++) {
                    sides += get_side(plot.get(i), plot);
                }

                solution += sides * plot.size();
            }
        }
        System.out.println(solution);
    }

    public boolean inbounds(int x, int y, Coord dim) {
        return (x >= 0 && y >= 0 && x < dim.x && y < dim.y);
    }

    public ArrayList<Coord> get_neigbours(Coord check, String [] grid) {
        ArrayList<Coord> directions = new ArrayList<>();
        directions.add(new Coord(-1, 0));
        directions.add(new Coord(1, 0));
        directions.add(new Coord(0, 1));
        directions.add(new Coord(0, -1));
        ArrayList<Coord> neighbours = new ArrayList<>();
        Coord dim = new Coord(grid[0].length(), grid.length);
        for (Coord dir: directions) {
            if (inbounds(check.x + dir.x, check.y + dir.y, dim)) {
                if (grid[check.y].charAt(check.x) == grid[check.y + dir.y].charAt(check.x + dir.x)) {
                    neighbours.add(new Coord(check.x + dir.x, check.y + dir.y));
                }
            }
        }
        return neighbours;
    }

    public int get_side(Coord check, ArrayList<Coord> region) {
        ArrayList<Coord> directions = new ArrayList<>();
        directions.add(new Coord(-1, 0));
        directions.add(new Coord(0, -1));
        directions.add(new Coord(1, 0));
        directions.add(new Coord(0, 1));
        int side = 0;
        for (Coord direction : directions) {
            Coord hop = new Coord(direction.x + check.x, direction.y + check.y);
            Coord ctl1;
            Coord ctl2;

            if (direction.y == 0) {
                ctl1 = new Coord(check.x, check.y - 1);
                ctl2 = new Coord(hop.x, ctl1.y);
            } else {
                ctl1 = new Coord(check.x - 1, check.y);
                ctl2 = new Coord(ctl1.x, hop.y);
            }

            if (!coord_contain(region, hop) && !(coord_contain(region, ctl1) && !coord_contain(region, ctl2))) {
                side += 1;
            }
        }
        return side;
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
