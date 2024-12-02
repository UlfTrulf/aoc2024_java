import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java main <day> <part>");
            System.exit(1);
        }

        int day = Integer.parseInt(args[0]);
        int part = Integer.parseInt(args[1]);

        if (day < 1 || day > 25 || part < 1 || part > 2) {
            System.out.println("Invalid day or part.");
            System.exit(1);
        }

        String inputPath = "input/day" + String.format("%02d", day) + ".txt";

        try {
            // Read input
            StringBuilder input = new StringBuilder();
            Scanner scanner = new Scanner(new File(inputPath));
            while (scanner.hasNextLine()) {
                input.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            // Import and run day/part
            String packageName = "day" + String.format("%02d", day);
            Class<?> daypart = Class.forName(packageName + ".part" + part);
            Object target = daypart.newInstance();
            Method runMethod = daypart.getMethod("run", String.class);
            runMethod.invoke(target, input.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}