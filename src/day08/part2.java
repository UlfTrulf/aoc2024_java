package day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class part2 {
    public void run(String input) {
    String [] lines = input.split("\\n");
    Coord dimension = new Coord(lines[0].length(), lines.length);
    Map<Character, ArrayList<Coord>> antennas = new HashMap<>();
    for (int y = 0; y < lines.length; y++) {
        char[] chars = lines[y].toCharArray();
        for (int x = 0; x < chars.length; x++) {
            char spot = chars[x];
            if (spot != '.') {
                if (antennas.containsKey(spot)) {
                    ArrayList<Coord> signal_of_spot = antennas.get(spot);
                    signal_of_spot.add(new Coord(x, y));
                    antennas.put(spot, signal_of_spot);
                } else {
                    ArrayList<Coord> signal_of_spot = new ArrayList<>();
                    signal_of_spot.add(new Coord(x, y));
                    antennas.put(spot, signal_of_spot);
                }
            }
        }
    }
    ArrayList<Coord> antinodes = new ArrayList<>();
    for (Map.Entry<Character, ArrayList<Coord>> entry : antennas.entrySet()) {
        ArrayList<Coord> value = entry.getValue();
        for (Coord a : value) {
            for (Coord b : value) {
                if (a.x == b.x && a.y == b.y) {
                    continue;
                }
                int diff_x = a.x - b.x;
                int diff_y = a.y - b.y;
                for (int i = 0; inbounds(a.x + i * diff_x, a.y + i * diff_y, dimension); i++) {
                    Coord new_a = new Coord(a.x + i * diff_x, a.y + i * diff_y);
                    if (!coord_contain(antinodes,new_a)) {
                        antinodes.add(new_a);
                    }
                }
                for (int j = 0; inbounds(b.x - j * diff_x, b.y - j * diff_y, dimension); j++) {
                    Coord new_b = new Coord(b.x - j * diff_x, b.y - j * diff_y);
                    if (!coord_contain(antinodes,new_b)) {
                        antinodes.add(new_b);
                    }
                }
            }
        }
    }
    System.out.println(antinodes.size());
}

    public boolean inbounds(int x, int y, Coord dim) {
        return (x >= 0 && y >= 0 && x < dim.x && y < dim.y);
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
