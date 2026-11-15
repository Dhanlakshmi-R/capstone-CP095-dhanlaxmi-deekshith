import java.util.Scanner;

class Input {
    static final Scanner scanner = new Scanner(System.in);

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            scanner.nextLine();
            System.out.println("Please enter a whole number.");
        }
    }

    static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    static String readRequired(String prompt) {
        while (true) {
            String value = readLine(prompt);
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    static int chooseOption(String title, String[] options) {
        System.out.println(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + ". " + options[i]);
        }
        while (true) {
            int choice = readInt("Enter 1-" + options.length + ": ");
            if (choice >= 1 && choice <= options.length) return choice - 1;
            System.out.println("Invalid choice.");
        }
    }

    static boolean confirm(String message) {
        String answer = readLine(message + " (y/n): ");
        return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes");
    }
}
