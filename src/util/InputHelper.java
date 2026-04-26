package util;

import java.util.Scanner;

/**
 * Utility class for reading and validating console input.
 * Demonstrates: Static methods, Method Overloading
 */
public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Overloaded — with a default fallback
    public static String readString(String prompt, String defaultValue) {
        System.out.print(prompt + " [default: " + defaultValue + "]: ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ✘ Invalid number. Please try again.");
            }
        }
    }

    // Overloaded — with min/max range validation
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            int val = readInt(prompt);
            if (val >= min && val <= max) return val;
            System.out.printf("  ✘ Please enter a number between %d and %d.%n", min, max);
        }
    }

    public static void pause() {
        System.out.print("\n  Press Enter to continue...");
        scanner.nextLine();
    }
}
