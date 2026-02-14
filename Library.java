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
                    return;
                case 3:
                    return;
                case 4:
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
        System.out.println("1. Member Login");
        System.out.println("2. Admin Login");
        System.out.println("3. Register Member");
        System.out.println("4. Exit");
    }

    static void sayHello() {
        System.out.println("\nHello! Welcome to the system.");
    }

    static void exitProgram() {
        System.out.println("Exiting...");
        scanner.close();
    }
}

