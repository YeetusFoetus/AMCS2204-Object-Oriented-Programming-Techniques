import java.util.Scanner;

public class Library {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    sayHello();
                    break;
                case 2:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    static void showMenu() {
        System.out.println("\n====================");
        System.out.println("   MAIN MENU");
        System.out.println("====================");
        System.out.println("1. Say Hello");
        System.out.println("2. Exit");
        System.out.print("Select option: ");
    }

    static void sayHello() {
        System.out.println("\nHello! Welcome to the system.");
    }

    static void exitProgram() {
        System.out.println("Exiting...");
        scanner.close();
    }
}

