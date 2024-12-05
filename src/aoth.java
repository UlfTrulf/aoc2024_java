import java.util.Scanner;

public class aoth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        day05.part1.run(input);
        day05.part2.run(input);
    }
}
