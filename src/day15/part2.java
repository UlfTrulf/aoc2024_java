package day15;


import java.util.HashMap;
import java.util.Map;

public class part2 {
    public void run(String input) {
        long gps = 0;
        String [] in = input.split("\\n\\n");
        String [] smol = in[0].split("\\n");
        String [] warehouse = new String[smol.length];
        for (int i = 0; i < smol.length; i++) {
            warehouse[i] = smol[i].replaceAll("\\.", "..").replaceAll("#","##").replaceAll("O", "[]").replaceAll("@", "@.");
        }
        Grid grid = new Grid(warehouse);
        String orders = in[1].replace("\n","");
        for (int i = 0; i < orders.length(); i++) {
            grid.move(grid.robo, orders.charAt(i));
        }
        for (int y = 0; y < grid.grid.length; y++) {
            for (int x = 0; x < grid.grid[y].length(); x++) {
                if (grid.grid[y].charAt(x) == '[') {
                    gps += y * 100L + x;
                }
            }
        }
        System.out.println(gps);
    }


    private class Grid {
        public String [] grid;
        int[] robo;
        private Map<Character, int[]> directions;

        public Grid (String [] grid) {
            this.grid = grid;
            get_robo();
            this.directions = new HashMap<>();
            this.directions.put('>', new int[]{1, 0});
            this.directions.put('<', new int[]{-1, 0});
            this.directions.put('^', new int[]{0, -1});
            this.directions.put('v', new int[]{0, 1});
        }

        private void get_robo() {
            for (int y = 0; y < this.grid.length; y++) {
                for (int x = 0; x < this.grid[y].length(); x++) {
                    if (this.grid[y].charAt(x)=='@'){
                        this.robo = new int[]{x, y};
                        return;
                    }
                }
            }
        }

        public boolean move(int[] thing, char dir) {
            int[] target_mv = this.directions.get(dir);
            int[] target_pos = {thing[0] + target_mv[0], thing[1] + target_mv[1]};
            int[] alt_target_pos = new int[2];
            boolean box = false;
            char next = this.grid[target_pos[1]].charAt(target_pos[0]);
            if (dir == '^' || dir == 'v') {
                if (next == '[') {
                    alt_target_pos = new int[]{target_pos[0] + 1, target_pos[1]};
                    box = true;
                } else if (next == ']') {
                    alt_target_pos = new int[]{target_pos[0] - 1, target_pos[1]};
                    box = true;
                }
            }
            if (!box) {
                switch (next) {
                    case '#' -> {
                        return false;
                    }
                    case '.' -> {
                        char old = this.grid[thing[1]].charAt(thing[0]);
                        String row1 = this.grid[thing[1]].substring(0, thing[0]) +
                                '.' +
                                this.grid[thing[1]].substring(thing[0] + 1);
                        this.grid[thing[1]] = row1;
                        String row2 = this.grid[target_pos[1]].substring(0, target_pos[0]) +
                                old +
                                this.grid[target_pos[1]].substring(target_pos[0] + 1);
                        this.grid[target_pos[1]] = row2;
                        if (old == '@') {
                            this.robo = target_pos;
                        }
                        return true;
                    }
                    default -> {
                        if (move(target_pos, dir)) {
                            return move(thing, dir);
                        }
                        return false;
                    }
                }
            } else {
                String [] grid_backup = this.grid.clone();
                if (move(target_pos, dir) && move(alt_target_pos, dir)) {
                    return move(thing, dir);
                } else {
                    this.grid = grid_backup;
                    return false;
                }
            }
        }
    }
}
